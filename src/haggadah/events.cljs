(ns haggadah.events
  (:require
   [re-frame.core :as re-frame]
   [cljsjs.marked]
   [haggadah.fb.firestore :as firestore]
   ["firebase/firestore" :as fire]
   [haggadah.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [haggadah.fb.auth :as auth]
   [haggadah.fb.functions :as func]))

(def interceptors [re-frame/trim-v])


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-event-fx
 ::call-func
 interceptors
 (fn [_ [fn-url text on-success on-error]]
   (println "This is for the url and text" fn-url "---" text)
   {::call-func! {:fn-url fn-url
                  :text text
                  :on-success on-success
                  :on-error on-error}}))

(re-frame/reg-fx
 ::call-func!
 (fn [{:keys [fn-url text on-success on-error]}]
   (println "This is for the url and text" fn-url "---" text)
   (let [f (func/callable-from-url fn-url)]
     (println "This is the function" f)
     (-> (f text)
         (.then on-success)
         (.catch on-error)))))

(re-frame/reg-fx
 ::email-login!
 (fn [{:keys [email password on-success on-error]}]
   (-> (auth/email-login email password)
       (.then (fn [user]  (on-success (.-user user))))
       (.catch (fn [error]  (on-error error) )))))
 

(re-frame/reg-fx
 ::fetch-collection!
 (fn [{:keys [path on-success on-error]}]
   (-> (firestore/instance)
       (fire/collection  (clojure.string/join "/" path))
       (fire/getDocs)
       (.then on-success)
       (.catch on-error))))

(defn keyword->func
  [key]
  (cond
    (fn? key) key
    (vector? key) #(re-frame/dispatch (conj key %))
    :else #(re-frame/dispatch [key %])))

(re-frame/reg-event-fx
 ::fetch-haggadot
 (fn [{:keys [db]} [_ {:keys [on-success on-error] :or {on-error :error}}]]
   (println "Uid: " (:uid db))
   (if (:uid db)
     {::fetch-collection! {:path ["users" (:uid db) "haggadot"]
                           :on-success (keyword->func on-success)
                           :on-error (keyword->func on-error)}}
     {})))


(re-frame/reg-event-fx
 ::fetch-haggadah
 (fn [{:keys [db]} [_ {:keys [on-success on-error] :or {on-error :error}}]]
   (let [id (get-in db [:current-route :path-params :id])]
     (println "The id is " id)
     (when (:uid db)
       {::fetch-doc {:path ["users" (:uid db) "haggadot" id]
                     :on-success (keyword->func on-success)
                     :on-error (keyword->func on-error)}}))))


(re-frame/reg-event-fx
 ::signout
 (fn [_ [_]]
   {::signout! {:on-success #(re-frame/dispatch [::push-state :home %])
                :on-error (keyword->func ::error)}}))

(re-frame/reg-fx
 ::signout!
 (fn [{:keys [on-success on-error]}]
   (println "We are processing the signout of the user")
     (-> (auth/signout)
         (.then on-success)
         (.catch on-error))))

(re-frame/reg-event-fx
 ::logout-user
 (fn [_ [_]]
   (println "The user is logging out")
   {:db (assoc db/default-db :user :unregisterd)
    :fx [[:dispatch [::push-state :home]]]}))

(def route-events
  {:dashboard [::fetch-haggadot {:on-success ::set-haggadot}]
   :haggadah-view [::fetch-haggadah {:on-success ::set-haggadah }]})

(re-frame/reg-event-fx
 ::store-user-info
 (fn [{:keys [db] } [_ user]]
   (let [route (get-in db [:current-route :data :name])
         fx (get route-events route [])]
     (println "This is the effect " fx "The route " route)
   {:db (-> db
            (assoc :name (.-email user)  :uid (.-uid user) :user :registered))
    :fx [[:dispatch fx]]})))


(re-frame/reg-event-fx
 ::add-haggadah
 (fn [{:keys [db]} [_ title content]]
   (println "Content " content "Title " title)
   {::add-haggadah! {:path ["users" (:uid db) "haggadot"]
                     :haggadah {:title title
                                :content content}
                    :on-success (re-frame/dispatch [::push-state :haggadah-success])
                     :on-error (keyword->func ::error)}}))


(re-frame/reg-fx
 ::add-haggadah!
 (fn [{:keys [path haggadah on-success on-error]}]
   (-> (firestore/instance)
       (fire/collection (clojure.string/join "/" path))
       (fire/addDoc (clj->js haggadah))
       (.then on-success)
       (.catch on-error))))


(re-frame/reg-event-fx
 ::login
 interceptors
 (fn [_ [_]]
   {::email-login! {:email "han@skywalker.com" :password "123456789" :on-success #(re-frame/dispatch [::set-user %]) :on-error #(re-frame/dispatch [::error %])}}))


(re-frame/reg-fx
 ::fetch-doc
 (fn [{:keys [path on-success on-error] :or {on-error ::error }}]
   (println "Here's the path" path)
   (-> (firestore/instance)
       (fire/doc (clojure.string/join "/" path))
       (fire/getDoc)
       (.then (keyword->func on-success))
       (.catch (keyword->func on-error)))))

(re-frame/reg-event-db
 ::error
 (fn [db [_ error]]
   (assoc db :error {:error error
                     :code (. error -code)
                     :message (. error -message)})))

(re-frame/reg-event-db
 ::set-haggadah
 (fn [db [_ snap]]
   (assoc db :haggadah-text
          (-> snap
              (. data)
              (js->clj :keywordize-keys true)
              (:content)))))


(re-frame/reg-event-fx
 ::push-state
 (fn [_ [_ & route]]
   {:push-state route}))

(re-frame/reg-event-fx
 ::set-user
 (fn-traced [{:keys [db]} [_ user]]
            {:db 
             (-> db
                 (#(assoc % :name (.-email user)))
                 (#(assoc % :uid (.-uid user))))
             :fx [[:dispatch [::push-state :dashboard]]]}))

(defn auth-user-success
  "Pre: takes a user
  Post: navigates to dashboard and stores user info if the user is logged in, otherwise navigates them to the home page"
  [user]
  (if user
    (re-frame/dispatch [::store-user-info user])
    (re-frame/dispatch [::logout-user])))

(re-frame/reg-event-db
 ::set-haggadot
 (fn [db [_ snap]]
   (let [docs (->> snap (.-docs) js->clj)
         ids (map #(.-id %) docs)]
     (assoc db :haggadot
            (->> docs
                 (map #(.data %))
                 (map #(js->clj % :keywordize-keys true))
                 (map #(assoc %2 :id %1) ids))))))

(def example-haggadah
  "## Hello Why, who are you
  ### This is the example haggadah
  ### Look at all we `can show you`"
  )

(re-frame/reg-event-db
 ::do-nothing
 (fn [db [_]]
   db))


(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
