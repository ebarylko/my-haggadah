(ns haggadah.events
  (:require
   [re-frame.core :as re-frame]
   [haggadah.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [haggadah.fb.auth :as auth]
   ))

(def interceptors [re-frame/trim-v])


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-fx
 ::email-login!
 (fn [{:keys [email password on-success on-error]}]
   (-> (auth/email-login email password)
       (.then on-success)
       (.catch on-error))))


(re-frame/reg-event-fx
 ::login
 interceptors
 (fn [_ [_]]
   {::email-login! {:email "han@skywalker.com" :password "123456789" :on-success #(js/console.log % :success) :on-error #(js/console.log % :error)}}))


(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
