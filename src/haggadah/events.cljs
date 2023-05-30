(ns haggadah.events
  (:require
   [re-frame.core :as re-frame]
   [shadow.resource :as rc]
   [goog.object :as gobj]
   [cljsjs.marked]
   ["marked" :as mark]
   ["react" :as react]
   [haggadah.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [haggadah.fb.auth :as auth]
   [re-frame.core :as rf]))

(def interceptors [re-frame/trim-v])


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-fx
 ::email-login!
 (fn [{:keys [email password on-success on-error]}]
   (-> (auth/email-login email password)
       (.then (fn [user]  (on-success (.-user user))))
       (.catch on-error))))


(re-frame/reg-event-fx
 ::login
 interceptors
 (fn [_ [_]]
   {::email-login! {:email "han@skywalker.com" :password "123456789" :on-success #(re-frame/dispatch [::set-user %] ) :on-error #(js/console.log % :error)}}))

(re-frame/reg-event-db
 ::set-user
 (fn-traced [db [_ user]]
            (assoc db :name (.-email user))))



(def example-haggadah
  "## Hello Why, who are you
  ### This is the example haggadah
  ### Look at all we `can show you`"
  )


(re-frame/reg-event-db
 ::render-login-text
 (fn [db [_ file]]
   (assoc db :haggadah-text (js/marked.parse example-haggadah))))





(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
