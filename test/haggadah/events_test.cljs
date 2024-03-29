(ns haggadah.events-test
  (:require [haggadah.subs :as subs]
            [cljs.test :as t :include-macros true]
            [re-frame.core :as rf]
            [day8.re-frame.test :as rf-test]
            [haggadah.events :as events]
            [haggadah.core :as core]
            [haggadah.routes :as routes]))



(t/deftest admin-login
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

(def incorrect-password "auth/wrong-password")

(t/deftest unregistered-user-login
  (rf-test/run-test-async
   (core/firebase-init!)
   (rf/dispatch-sync [::events/initialize-db])
   (rf/dispatch-sync [::events/login "jan@weir.com" "123456789"])
   (rf-test/wait-for [::events/error]
                     (let [{:keys [code]} @(rf/subscribe [::subs/error])]
                       (t/is (= user-not-found code))))))

(t/deftest incorrect-pwd-login
  (rf-test/run-test-async
   (core/firebase-init!)
   (rf/dispatch-sync [::events/initialize-db])
   (rf/dispatch-sync [::events/login "han@skywalker.com" "12345678"])
   (rf-test/wait-for [::events/error]
                     (let [{:keys [code]} @(rf/subscribe [::subs/error])]
                       (t/is (= incorrect-password code))))))


(t/deftest registered-user-login
  (rf-test/run-test-async
   (routes/init-routes!)
   (core/firebase-init!)
   (rf/dispatch-sync [::events/initialize-db])
   (rf/dispatch [::events/login "han@skywalker.com" "123456789"])
   (rf-test/wait-for [::events/fetch-haggadot]
    (let [user (rf/subscribe [::subs/user])
          name (rf/subscribe [::subs/name])]
      (t/are [x y] (= x y)
        :registered @user
        "han@skywalker.com" @name)))))


#_(t/deftest routes-exist
  (rf-test/run-test-sync
   (routes/init-routes!)
   (core/firebase-init!)
   (rf/dispatch [::events/initialize-db])
   (rf/dispatch [::events/push-state :login])
   (let [user (rf/subscribe [::subs/user])
         uid (rf/subscribe [::subs/uid])
         name (rf/subscribe [::subs/name])
         route (rf/subscribe [:current-route])]
     (t/are [x y] (= x y)
       :registered @user
       "1234" @uid
       "hana@skywalker.com" @name
       :home @route))))
