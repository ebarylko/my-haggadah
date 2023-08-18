(ns acceptance.page-navigation-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [acceptance.core :as c :refer [driver home]]))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot c/delete-fs-emulator-data)

(defn home->login
  "Pre: takes nothing
  Post: takes you from the home page to the login page"
  []
  (doto driver
    (e/go home)
    (e/click-visible {:data-testid :login})
    (e/wait-visible {:data-testid :submit})))

(defn click-on-cancel-button
  "Pre: takes nothing
  Post: clicks on the cancel button on the page"
  []
  (e/click-visible driver {:data-testid :cancel}))

(defn wait-for-home-page
  "Pre: takes nothing
  Post: waits for the home page to the rendered"
  []
  (e/wait-visible driver {:data-testid :login}))

(defn at-home-page?
  "Pre: takes nothing
  Post: returns true if the user is at the home page. False otherwise"
  []
  (e/exists? driver {:data-testid :login}))

(defn current-page
  "Pre: takes nothing
  Post: returns the current page the user is on"
  []
  (cond
    (at-home-page?) :home-page
    :else :invalid-page))

(t/deftest click-cancel-button-test
  (t/testing "When the user goes to the login page and clicks the cancel button, they should return to the home page"
    (home->login)
    (click-on-cancel-button)
    (wait-for-home-page)
    (let [current-page (current-page)]
      (t/is (= :home-page current-page)))))
