(ns acceptance.dashboard-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [acceptance.core :as c :refer [driver]] 
             [etaoin.keys :as k]
             [acceptance.view-haggadah-test :as h]))

(defn open-edit-haggadah
  [d id text]
  (doto d
    (e/click-visible {:data-testid (str "edit-" id)})
    (e/wait-has-text-everywhere text)))

(defn link-and-title
  [haggadah]
  (let [title (e/get-element-text-el driver haggadah)]
    {:title title}))

(defn all-haggadot
  []
  (let [haggadot (e/query-all driver {:tag :li #_#_:data-testid :haggadah-link})]
    (map link-and-title haggadot)))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)

(def dashboard-message
  "Hello han@skywalker.com. Welcome. To make a new Haggadah, click the button to your right. To share and edit your existing Haggadah, look at your Haggadot below ")

(defn create-haggadah
  [d title]
  (doto d
    (e/click-visible {:data-testid :create-haggadah})
    (e/wait-visible {:data-testid :haggadah-title})
    (e/fill  {:data-testid :haggadah-title} k/home (k/with-shift k/end) k/delete)
    (e/fill {:data-testid :haggadah-title} title)
    (e/click-visible {:data-testid :add-haggadah})
    (e/click-visible {:data-testid :return})))

(def new-haggadah-title "The best haggadah of the year")


(t/deftest create-haggadah-test
  (t/testing "When the current user creates a new haggadah and goes back to the dashboard, the haggadah is listed first among the Haggadot"
    (doto driver
      (c/home->dashboard)
      (create-haggadah new-haggadah-title)
      (e/screenshot "screenshots/create-haggadah-test-before-getting-haggadot.png"))
    (let [title  (->> (all-haggadot)
                      first
                      vals
                      first)]
      (t/is (= new-haggadah-title title)))))


(def new-haggadah-text "## We begin in Egypt")




