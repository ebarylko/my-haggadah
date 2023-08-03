(ns acceptance.dashboard-actions
  (:require [etaoin.api :as e]
            [acceptance.core :as c :refer [driver]]))


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
