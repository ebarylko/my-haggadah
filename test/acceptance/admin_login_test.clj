(ns acceptance.admin-login-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [etaoin.keys :as k]
             [acceptance.core :as c :refer [driver]])
  (:import com.google.firebase.cloud.FirestoreClient))

(def default-message
  "Hello (Unknown). We're glad to see you.")

(def admin-login-message
  "Hello han@skywalker.com. Welcome. To make a new Haggadah, click the button to your right. To share and edit your existing Haggadah, look at your Haggadot below")

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)

(t/deftest message-test
  (t/testing "When the admin user exists"
    (doto driver
      (c/home->dashboard)
      (e/wait-has-text-everywhere admin-login-message))
    (let [actual (e/get-element-text driver {:data-testid :user})]
      (e/screenshot driver "screenshots/message-test-when-the-admin-exists.png")
      (t/is (= admin-login-message actual)))))


