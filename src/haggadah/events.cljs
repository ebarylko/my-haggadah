(ns haggadah.events
  (:require
   [re-frame.core :as re-frame]
   [cljsjs.marked]
   [haggadah.fb.firestore :as firestore]
   ["firebase/firestore" :as fire]
   [haggadah.db :as db]
   [haggadah.subs :as subs]
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
         [:dispatch [::navigate :dashboard]]
         [:dispatch [::fetch-haggadot user
                     #(re-frame/dispatch [::set-haggadot %])
                     #(js/console.log "The haggadot could not be found" % :error)]]]}))

(re-frame/reg-event-db
 ::set-haggadot
 (fn [db [_ snap]]
   (assoc db :haggadot
          (->> snap
               (.-docs)
               js->clj
               (map #(.data %))
               (map #(js->clj % :keywordize-keys true))))))

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
 (fn [_ [_ user on-success on-error]]
   {::fetch-collection! {:path ["users" (.-uid user) "haggadot"] :on-success on-success :on-error on-error}}))


(re-frame/reg-event-fx
 ::login
 interceptors
 (fn [_ [_]]
   {::email-login! {:email "han@skywalker.com" :password "123456789" :on-success #(re-frame/dispatch [::load-dashboard %]) :on-error #(js/console.log % :error)}}))

(re-frame/reg-fx
 ::fetch-haggadah
 (fn [{:keys [uid on-success on-error]}]
   (println uid)
   (-> (firestore/instance)
       (fire/doc "users" uid )
       (fire/getDoc)
       (.then on-success)
       (.catch on-error))))

(re-frame/reg-event-db
 ::set-haggadah
 (fn [db [_ snap]]
   (assoc db :haggadah-text
          (-> snap
              (. data)
              (js->clj :keywordize-keys true)))))


(re-frame/reg-event-db
 ::set-user
 (fn-traced [db [_ user]]
            (-> db
                     (#(assoc % :name (.-email user)))
                     (#(assoc % :uid (.-uid user))))))

#_(re-frame/reg-event-fx
 ::set-user
 (fn-traced [{:keys [db]} [_ user]]
            {:db (assoc db :name (.-email user))
             ::fetch-haggadah {:uid (.-uid user)
                               :on-success #(re-frame/dispatch [::set-haggadah %])
                               :on-error #(js/console.log "Haggadah could not be fetched" % :error)}}))


#_(re-frame/reg-event-fx
 ::render-haggadah
 (fn [_ _]
   (let [{:keys [haggadah-text]} @(re-frame/subscribe [::subs/haggadah-text])]
     (when haggadah-text
       [:div  {:dangerouslySetInnerHTML #js{:__html (js/marked.parse haggadah-text)} :id "haggadah-text"}]))))

(def example-haggadah
  "## Hello Why, who are you
  ### This is the example haggadah
  ### Look at all we `can show you`"
  )




#_(re-frame/reg-event-db
 ::render-login-text
 (fn [db [_ _]]
   {::email-login!
    {:email "han@skywalker.com" :password "123456789" :on-success #(re-frame/dispatch [::render-haggadah %] ) :on-error #(js/console.log % :error)}

    }
   (assoc db :haggadah-text (js/marked.parse example-haggadah))))



(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
