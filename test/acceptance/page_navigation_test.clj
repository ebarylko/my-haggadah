(ns acceptance.page-navigation-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [acceptance.core :as c :refer [driver]]
             ))


(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot c/delete-fs-emulator-data)

(defn ->home
  "Pre: takes nothing
  Post: takes you to the home page"
  []
  (e/go driver "http://localhost:5000/"))

(defn home->login
  "Pre: takes nothing
  Post: takes you from the home page to the login page"
  []
  (doto driver
    (e/go "http://localhost:5000/")
    (e/click-visible {:data-testid :login})
    (e/wait-visible {:data-testid :submit})))

(defn click-on-cancel-button
  "Pre: takes nothing
  Post: clicks on the cancel button on the page"
  []
  (println "The button exists " (e/exists? driver {:data-testid :cancel}))
  (let [button (e/query driver {:data-testid :cancel})]
    (e/js-execute driver "arguments[0].click()" (e/el->ref button))
  #_(e/click-visible driver {:data-testid :cancel})
  (e/wait 6)))

(defn wait-for-home-page
  "Pre: takes nothing
  Post: waits for the home page to the rendered"
  []
  (e/wait-visible driver {:data-testid :login}))

(defn at-home-page?
  []
  (e/exists? driver {:data-testid :login}))

(defn current-page
  "Pre: takes nothing
  Post: returns the current page the user is on"
  []
  (cond
    (at-home-page?) :home-page)
  :else :invalid-page)

(t/deftest click-cancel-button-test
  (t/testing "When the user goes to the login page and clicks the cancel button, they should return to the home page"
    (home->login)
    (click-on-cancel-button)
    #_(wait-for-home-page)
    (let [current-page (current-page)]
      (t/is (= :home-page current-page)))))
