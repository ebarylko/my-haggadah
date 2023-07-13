(ns acceptance.view-haggadah-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [environ.core :refer [env]]
             [acceptance.core :as c :refer [driver]]
             [etaoin.keys :as k]))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)

(defn click-on-haggadah
  [d text]
  (doto d
    (e/screenshot "screenshots/create-haggadah-test-admin-exists-before-clicking-haggadah.png")
    (e/click-visible {:data-testid :haggadah-link} {:timeout 15})
    (e/screenshot "screenshots/edit-haggadah-test-viewing-edited-haggadah.png")
    (e/wait-has-text-everywhere text {:timeout 15})
    (e/screenshot "screenshots/create-haggadah-test-admin-exists-haggadah-text.png")))

(def haggadah-title
  "haggadah2023")

(def actual-haggadah-text
  "Amir's Haggadah")


(defn haggadah-content
  [d]
  (e/get-element-text d {:data-testid :haggadah-text}))

(t/deftest view-haggadah-from-dashboard
  (t/testing "When the current user has a haggadah and is at the dashboard, they should be able to view the the selected haggadah"
    (let [id (c/create-haggadah
              {"content" "## Amir's Haggadah"
               "title" "haggadah2023"}
              "user1")]
    (doto driver
      (c/home->dashboard)
      (click-on-haggadah id actual-haggadah-text)
      (e/screenshot "screenshots/show-text-test-admin-exists-haggadah-exists-after-clicking-haggadah"))
    (let [haggadah-text (haggadah-content driver)]
      (t/is (= actual-haggadah-text haggadah-text))))))
