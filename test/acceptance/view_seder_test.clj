(ns acceptance.view-seder-test
  (:require [etaoin.api :as e]
            [clojure.test :as t]
            [acceptance.haggadah-actions :as h]
            [acceptance.core :as c :refer [driver]]
            [acceptance.dashboard-actions :as d]))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot c/delete-fs-emulator-data)

(defn seder-link->seder
  "Pre: takes nothing
  Post: navigates to the seder associated with the link"
  []
  (e/click driver {:id :share-seder}))

(defn seder-title
  "Pre: takes nothing
  Post: returns the title of the Seder on the page"
  []
  (e/get-element-text driver {:data-testid :seder-title}))

(defn wait-for-seder
  []
  (e/wait-visible driver {:fn/has-class :haggadah}))


(t/deftest view-seder-from-link-test
  (t/testing "When the current user has a Seder and copies the link to view the Seder and pastes it in the window, they should then see the Haggadah below"
    (let [id (c/fs-store-haggadah {:title "Haggadah 1"}
                                  "user1")]
      (c/fs-store-seder "Seder title" "user1" id)
      (c/fs-store-haggadah-content {:type "haggadah" :content [{:type "bracha" :title "hello" :english "bracha" :hebrew "text"}] :path "haggadah/full-haggadah"})
      (c/home->dashboard driver)
      (d/wait-for-sedarim)
      (d/dashboard->first-seder)
      (d/gen-seder-link)
      (seder-link->seder)
      (wait-for-seder)
      (let [seder-title (seder-title)
            haggadah-title (h/haggadah-title)
            bracha-title (h/bracha-title)
            hebrew-bracha (h/bracha-hebrew-content)
            english-bracha (h/bracha-english-content)]
        (t/are [x y] (= x y)
          "Haggadah 1" haggadah-title
          "Seder title" seder-title
          "hello" bracha-title
          "bracha" english-bracha
          "text" hebrew-bracha)))))
