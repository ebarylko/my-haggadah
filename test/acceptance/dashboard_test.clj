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
  (let [title (e/get-element-text driver haggadah)
       #_#_ link (e/get-element-attribute driver haggadah :href)]
    {:title title #_#_:link link}))

(defn all-haggadot
  []
  (let [haggadot (e/query-all {:css "ul.haggadot  a"})]
    (map link-and-title haggadot)))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)


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
      (create-haggadah new-haggadah-title))
    (let [title  (->> all-haggadot
                           first
                            vals)]
      (t/is (= new-haggadah-title title)))))

(def new-haggadah-text "## We begin in Egypt")
(def parsed-haggadah-text  "We begin in Egypt")

(t/deftest refresh-page-test
  (t/testing "When the current user refreshes the haggadah"

    (let [id (c/create-haggadah {:title new-haggadah-title
                                 :content new-haggadah-text} "user1")]
     (doto driver
       (c/home->dashboard)
       (h/click-on-haggadah id parsed-haggadah-text)
       (e/refresh)
       (e/wait-has-text-everywhere parsed-haggadah-text))
     (let [haggadah-text (h/haggadah-content driver)]
       (t/is (= parsed-haggadah-text haggadah-text))))))




