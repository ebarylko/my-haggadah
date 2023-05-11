(ns haggadah.events-test
  (:require [haggadah.subs :as subs]
            [cljs.test :as t :include-macros true]
            [re-frame.core :as rf]
            [day8.re-frame.test :as rf-test]
            [haggadah.events :as events]))

(defn test-fixtures
  []
  ;; change this coeffect to make tests start with nothing
  (rf/reg-cofx
   :local-store-todos
   (fn [cofx _]
     "Read in todos from localstore, and process into a map we can merge into app-db."
     (assoc cofx :name
            "(Unkown)"))))

(t/deftest admin-login
  (rf-test/run-test-sync               ;; <-- add this
   ;; with the above macro this becomes a dispatch-sync
   ;; and app-db is isolated between tests
   #_(test-fixtures)
   (rf/dispatch [::events/initialise-db])
   ;; Define subscriptions to the app state
   (let [name (rf/subscribe [::subs/name])]
     ;;Assert the initial state
     (t/is (=  "(Unknown)" @name)))))

(t/deftest equality
  (t/is (= 2 3)))
#_(t/run-tests)
