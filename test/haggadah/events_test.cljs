(ns haggadah.events-test
  (:require [haggadah.subs :as subs]
            [cljs.test :as t :include-macros true]
            [re-frame.core :as rf]
            [day8.re-frame.test :as rf-test]
            [haggadah.events :as events]
            [haggadah.core :as core]
            [haggadah.fb.auth :as auth]
            [haggadah.routes :as routes]
            [re-frame.core :as re-frame]
            ["firebase-admin" :as admin]))


#_(t/deftest admin-login
  (rf-test/run-test-sync               ;; <-- add this
   ;; with the above macro this becomes a dispatch-sync
   ;; and app-db is isolated between tests
   (rf/dispatch [::events/initialize-db])
   ;; Define subscriptions to the app state
   (let [name (rf/subscribe [::subs/name])
         uid (rf/subscribe [::subs/uid])
         user (rf/subscribe [::subs/user])]
     ;;Assert the initial state
     (t/are [x y] (= x y)
       "(Unknown)" @name
       nil @uid
       :unloaded @user))))

(def user-not-found "auth/user-not-found")

#_(t/deftest unregistered-user-login
  (rf-test/run-test-async
   (core/firebase-init!)
   (rf/dispatch-sync [::events/initialize-db])
   (rf/dispatch-sync [::events/login])
   (rf-test/wait-for [::events/error]
   (let [{:keys [code]} @(rf/subscribe [::subs/error])]
     (t/is (= user-not-found code))))))


(defn new-user
  "Pre: takes an email and a password
  Post: returns a user if a new user has been created. Otherwise, throws an error"
  [email pwd]
  (-> (admin/auth)
      ( #js{:email "han@skywalker.com" :password "123456789"})
      (.then (fn [user] (println "The user was created " user) (rf/dispatch-sync [::events/login])))
      (.catch (fn [error] (println "The error " error)))))

(t/deftest registered-user-login
  (rf-test/run-test-async
   (core/firebase-init!)
   (rf/dispatch-sync [::events/initialize-db])
   (println "Before making the user")
   (new-user "han@skywalker.com" "123456789")
   (println "After making the user")
#_   (rf/dispatch-sync [::events/login])
   (rf-test/wait-for [::routes/setup-route!])
   (let [user (rf/subscribe [::subs/user])
         uid (rf/subscribe [::subs/uid])]
     (t/are [x y] (= x y)
       :registered @user
       "1234" @uid))))
