(ns acceptance.admin-login-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]))

(def driver (e/firefox-headless))

(def default-message
  "Hello from (Unknown). This is the Home Page.We're glad to see you.")

(def admin-login-message
"Hello from han@skywalker.com. This is the Home Page.We're glad to see you.")

;; (e/go driver "http://localhost:8280/")


(t/deftest message-test
 (t/testing "default user" 
   (let [_  (e/go driver "http://localhost:8280")
         actual (e/get-element-text driver {:class :haggadah-styles-level1})]
(t/is (= default-message actual)))))
