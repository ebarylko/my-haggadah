(ns acceptance.admin-login-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [etaoin.keys :as k]
             [acceptance.core :as c :refer [driver]])
  (:import com.google.firebase.cloud.FirestoreClient
            ))

(def default-message
  "Hello (Unknown). We're glad to see you.")

(def admin-login-message
  "Hello han@skywalker.com. Welcome to your dashboard. To make a new haggadah, click the button to your right. To share and edit your existing haggadah, look at your haggadot below")

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

(def default-haggadah-text
  "The default haggadah")

(def haggadah-title
  "haggadah2023")

(def actual-haggadah-text
  "Amir's Haggadah")

(t/deftest show-text-test
  (t/testing "When the current user has a haggadah"
    (let [db (FirestoreClient/getFirestore)
          haggadah {"content" "## Amir's Haggadah"
                    "title" "haggadah2023"}
          write (-> db
                     (.collection "users")
                     (.document "user1")
                     (.collection "haggadot")
                     (.add haggadah)
                     (.get))
          _ (doto driver
              (c/home->dashboard)
              (c/click-on-haggadah haggadah-title actual-haggadah-text)
              (e/screenshot "screenshots/show-text-test-admin-exists-haggadah-exists-after-clicking-haggadah"))
          haggadah-text (e/get-element-text driver {:data-testid :haggadah-text})]

      (t/is (= actual-haggadah-text haggadah-text)))))

