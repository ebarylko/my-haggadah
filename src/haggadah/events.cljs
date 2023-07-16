(ns haggadah.events
  (:require
   [re-frame.core :as re-frame]
   [haggadah.fb.firestore :as firestore]
   ["firebase/firestore" :as fire]
   [haggadah.db :as db]
   [haggadah.dsl :as dsl]
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
 ::query!
 (fn [{:keys [path on-success on-error order-by]}]
 (-> (firestore/instance)
     (fire/collection  (clojure.string/join "/" path))
     (fire/query order-by)
     (fire/getDocs)
     (.then on-success)
     (.catch on-error))))

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
   (if (:uid db)
     {::query! {:path ["users" (:uid db) "haggadot"]
                :order-by (fire/orderBy "createdAt" "desc")
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
     (-> (auth/signout)
         (.then on-success)
         (.catch on-error))))


(def route-events
  {:dashboard [::fetch-haggadot {:on-success ::set-haggadot}]
   :haggadah-view [::fetch-haggadah {:on-success ::set-haggadah }]
   :haggadah-edit [::fetch-haggadah {:on-success ::set-haggadah }]})

(re-frame/reg-event-db
 ::do-nothing
 (fn [db [_]]
   db))

(re-frame/reg-event-fx
 ::store-user-info
 (fn [{:keys [db]}[_ user]]
   (if user 
     (let [route (get-in db [:current-route :data :name])
           fx (get route-events route [])
           user-db (:db db)]
       {:db (-> db
                (assoc :name (.-email user)  :uid (.-uid user) :user :registered))
        :fx [[:dispatch fx]]})
     {:db (assoc db :name nil :uid nil :user :unregistered)})))



(re-frame/reg-event-fx
 ::add-haggadah
 (fn [{:keys [db]} [_ title]]
   {::add-haggadah! {:path ["users" (:uid db) "haggadot"]
                     :haggadah (assoc dsl/haggadah :title title :createdAt (js/Date.))
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
   {::email-login! {:email "han@skywalker.com" :password "123456789" :on-success #(re-frame/dispatch [::push-state :dashboard]) :on-error #(re-frame/dispatch [::error %])}}))


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
              (:content)
              dsl/parse-haggadah))))

(re-frame/reg-event-db
 ::edit-haggadah
 (fn [db [_ new-content]]
     (assoc db :haggadah-text new-content)))


(re-frame/reg-event-fx
 ::modify-haggadah
 (fn [{:keys [db]} [_ {:keys [new-haggadah on-success on-error] :or {on-error :error}}]]
   (println "The haggadah " new-haggadah)
   (let [id (get-in db [:current-route :path-params :id])]
     {::update-doc {:path ["users" (db :uid) "haggadot" id]
                    :content #js{:content new-haggadah}
                    :on-success (keyword->func on-success)
                    :on-error (keyword->func on-error)}})))

(re-frame/reg-event-db
 ::set-preview
 (fn [db [_ preview?]]
   (assoc db :preview preview?)))

(re-frame/reg-fx
 ::update-doc
 (fn [{:keys [path content on-success on-error] :or {on-error ::error }}]
   (println "Here's the update path" path "Here's the content " content)
   (-> (firestore/instance)
       (fire/doc (clojure.string/join "/" path))
       (fire/updateDoc content)
       (.then on-success)
       (.catch on-error))))

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
  Post: triggers an event which stores the user info "
  [user]
  (re-frame/dispatch [::store-user-info user]))

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


(re-frame/reg-event-db
 ::set-dropdown
 (fn [db [_]]
   (let [active? (not (:dropdown db))]
     (assoc db :dropdown active?))))

(re-frame/reg-event-db
 ::active-menu
 (fn [db [_]]
   (let [active? (not (:active-menu? db))]
     (assoc db :active-menu? active?))))

(def example-haggadah
  "## Hello Why, who are you
  ### This is the example haggadah
  ### Look at all we `can show you`")



(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
