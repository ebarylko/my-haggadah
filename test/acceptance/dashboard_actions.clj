(ns acceptance.dashboard-actions
  (:require [etaoin.api :as e]
            [acceptance.core :as c :refer [driver]]
            [etaoin.keys :as k]))


(def coll-type
  {:sedarim {:fn/has-class :sedarim}
   :haggadot {:fn/has-class :haggadot}})

(defn wait-for-collection
  "Pre: takes a collection type
  Post: waits until a collection of the same type is found on the page"
  [coll]
  (e/wait-visible driver (coll coll-type)))

(defn wait-for-sedarim
  []
  (wait-for-collection :sedarim))

(defn wait-for-haggadot
  []
  (wait-for-collection :haggadot))

(defn dashboard->first-seder
  "Pre: takes nothing
  Post: navigates to the first seder on the dashboard"
  []
  (doto driver
    (e/click {:data-testid :activate-seder})
    (e/wait-visible {:data-testid :gen-link})))


(defn create-haggadah
  [d title]
  (doto d
    (e/click-visible {:data-testid :create-haggadah})
    (e/wait-visible {:data-testid :haggadah-title})
    (e/fill  {:data-testid :haggadah-title} k/home (k/with-shift k/end) k/delete)
    (e/fill-human {:data-testid :haggadah-title} title {:mistake-prob 0})
    (e/click-visible {:data-testid :add-haggadah})
    (e/click-visible {:data-testid :return})))


(defn gen-seder-link
  "Pre: takes nothing
  Post: clicks on the sentence which will generate the link for the seder"
  []
  (doto driver
    (e/click  {:data-testid :gen-link})
    (e/wait-visible {:id  :share-seder})))
