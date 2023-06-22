(ns haggadah.events-test
  (:require [haggadah.subs :as subs]
            [cljs.test :as t :include-macros true]
            [re-frame.core :as rf]
            [day8.re-frame.test :as rf-test]
            [haggadah.events :as events]
            [haggadah.core :as core]))


(t/deftest admin-login
  (rf-test/run-test-sync               ;; <-- add this
   ;; with the above macro this becomes a dispatch-sync
   ;; and app-db is isolated between tests
   (rf/dispatch [::events/initialize-db])
   ;; Define subscriptions to the app state
   (let [name (rf/subscribe [::subs/name])]
     ;;Assert the initial state
     (t/is (=  "(Unknown)" @name)))))

(def user-not-found "auth/user-not-found")

(t/deftest unregistered-user-login
  (rf-test/run-test-async
   (core/firebase-init!)
   (rf/dispatch-sync [::events/initialize-db])
   (rf/dispatch-sync [::events/login])
   (rf-test/wait-for [::events/error]
   (let [{:keys [code]} @(rf/subscribe [::subs/error])]
     (t/is (= user-not-found code))))))


