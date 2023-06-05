(ns haggadah.core-test
  (:require [haggadah.core :as sut]
            [cljs.test :as t :include-macros true]))


(t/deftest write-test
  (t/testing "When the user is not authenticated, throws an exception"
    (let [e (try (sut/write "hello there" #js{:auth nil})
                 nil
                 (catch js/Error e
                   e))]
      (t/is (not (nil? e))))))

