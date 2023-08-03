(ns acceptance.view-seder-test
  (:require [etaoin.api :as e]
            [clojure.test :as t]
            [acceptance.core :as c :refer [driver]]
            [acceptance.dashboard-actions :as d]))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot c/delete-fs-emulator-data)

(defn gen-seder-link
  "Pre: takes nothing
  Post: clicks on the sentence which will generate the link for the seder"
  []
  (doto driver
    (e/click  {:data-testid :gen-link})
    (e/wait-visible {:id  :share-seder})))

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

(defn haggadah-title
  "Pre: takes nothing
  Post: returns the title of the Haggadah on the page"
  []
  (e/get-element-text driver {:css "div.haggadah>div.title"}))


(defn bracha-title
  []
  (e/get-element-text driver {:css "div.bracha>div.title" }))

(defn bracha-content
  []
  (e/get-element-text driver {:css "div.bracha>div.text"}))


(defn wait-for-seder
  []
  (e/wait-visible driver {:fn/has-class :haggadah}))

(t/deftest view-seder-from-link-test
  (t/testing "When the current user has a Seder and copies the link to view the Seder and pastes it in the window, they should then see a welcome message and the Haggadah below"
    (let [id (c/fs-store-haggadah {:title "Haggadah 1"
                                   :type "haggadah"
                                   :content [{:type "bracha" :title "hello" :text "bracha"}]}
                                  "user1")
          doc (c/fs-store-seder "Seder title" "user1" id)]
      (c/home->dashboard driver)
      (d/wait-for-sedarim)
      (d/dashboard->first-seder)
      (gen-seder-link)
      (seder-link->seder)
      (wait-for-seder)
      (let [seder-title (seder-title)
            haggadah-title (haggadah-title)
            bracha-title (bracha-title)
            bracha-text (bracha-content)]
        (t/are [x y] (= x y)
          "Haggadah 1" haggadah-title
          "Seder title" seder-title
          "hello" bracha-title
          "bracha" bracha-text)))))
