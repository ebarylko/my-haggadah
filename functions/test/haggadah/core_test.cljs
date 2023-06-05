(ns haggadah.core-test
  (:require [haggadah.core :as sut]
            [cljs.test :as t :include-macros true]))


(t/deftest write-test
  (t/testing "When the user is not authenticated, throws an exception"
(t/is (= 3 (sut/example 1)))))
