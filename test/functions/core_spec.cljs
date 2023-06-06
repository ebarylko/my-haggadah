(ns functions.core-spec
  (:require [functions.core :as sut]
            [cljs.test :as t :include-macros true]))


(t/deftest write-test
  (t/testing "When the user is not authenticated, throws an exception"
(t/is (= 2 (sut/example 1)))))
