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
       (.catch on-error))))


#_(re-frame/reg-event-fx
 ::login
 interceptors
 (fn [_ [_]]
   {::email-login! {:email "han@skywalker.com" :password "123456789" :on-success #(re-frame/dispatch [::set-user %] ) :on-error #(js/console.log % :error)}}))


(re-frame/reg-event-fx
 ::load-dashboard
 (fn [_ [_ user]]
   {:fx [[:dispatch [::set-user user]]
         [:dispatch [::fetch-haggadot user
                     #(re-frame/dispatch [::set-haggadot %])
                     #(js/console.log "The haggadot could not be found" % :error)]]]}))


(re-frame/reg-fx
 ::fetch-collection!
 (fn [{:keys [path on-success on-error]}]
   (println "This is the collection fetch" path)
   (-> (firestore/instance)
       (fire/collection  (clojure.string/join "/" path))
       (fire/getDocs)
       (.then on-success)
       (.catch on-error))))

(re-frame/reg-event-fx
 ::fetch-haggadot
 (fn [_ [_ uid on-success on-error]]
   {::fetch-collection! {:path ["users" uid "haggadot"] :on-success on-success :on-error on-error}}))

(re-frame/reg-event-fx
 ::fetch-haggadah
 (fn [{:keys [db]} [_ id on-success on-error]]
   {::fetch-doc {:path ["users" (:uid db) "haggadot" id]
                 :on-success on-success
                 :on-error on-error}}))

(re-frame/reg-event-fx
 ::login
 interceptors
 (fn [_ [_]]
   {::email-login! {:email "han@skywalker.com" :password "123456789" :on-success #(re-frame/dispatch [::set-user %]) :on-error #(js/console.log % :error)}}))

(defn keyword->func
  [key]
  (cond
    (fn? key) key
    (vector? key) #(re-frame/dispatch (conj key %))
    :else #(re-frame/dispatch [key %])))

(re-frame/reg-fx
 ::fetch-doc
 (fn [_ {:keys [path on-success on-error] :or {on-error ::error }}]
   (-> (firestore/instance)
       (fire/doc (clojure.string/join "/" path))
       (fire/getDoc)
       (.then (keyword->func on-success))
       (.catch (keyword->func on-error)))))

(re-frame/reg-event-db
 ::error
 (fn [db [_ error]]
   (js/console.log "There was an error" error)
   (assoc db :error error)))

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







(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
