(ns haggadah.core-test
  (:require [haggadah.core :as sut]
            [cljs.test :as t :include-macros true]))


(t/deftest write-test
  (t/testing "When the user is not authenticated, throws an exception"
    (println "Here are the options " (.-GOOGLE_APPLICATION_CREDENTIALS (.-env js/process) )
             "The hosting " (.-FIREBASE_FIRESTORE_EMULATOR_ADDRESS (.-env js/process) ))
    (let [e (try (sut/write "hello there" #js{:auth nil})
                 nil
                 (catch js/Error e
                   e))]
      (t/is (not (nil? e))))))

