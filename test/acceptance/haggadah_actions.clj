(ns acceptance.haggadah-actions
  (:require [etaoin.api :as e]
            [acceptance.core :as c :refer [driver]]))


(defn haggadah-title
  "Pre: takes nothing
  Post: returns the title of the Haggadah on the page"
  []
  (e/get-element-text driver {:css "div.haggadah>div.title"}))


(defn bracha-title
  []
  (e/get-element-text driver {:css "div.bracha>div.title" }))

(defn bracha-hebrew-content
  []
  (e/get-element-text driver {:css "div.bracha>div.hebrew"}))


(defn bracha-english-content
  []
  (e/get-element-text driver {:css "div.bracha>div.english"}))


(defn wait-for-seder
  []
  (e/wait-visible driver {:fn/has-class :haggadah}))
