(ns haggadah.events-test
  (:require [haggadah.events :as sut]
            [haggadah.subs :as subs]
            #_[haggadah.src.haggadah.subs :as sub]
            [cljs.test :as t :include-macros true]
            [re-frame.core :as rf]
            [day8.re-frame.test :as rf-test]))

(t/deftest admin-login
  (rf-test/run-test-sync               ;; <-- add this
   ;; with the above macro this becomes a dispatch-sync
   ;; and app-db is isolated between tests
   (rf/dispatch [:initialise-db])
   ;; Define subscriptions to the app state
   (let [name (rf/subscribe [::subs/name])]
     ;;Assert the initial state
     (t/is (=  "(Unknown)" @name)))))


 (admin-login)
