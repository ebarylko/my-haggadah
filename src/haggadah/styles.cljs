(ns haggadah.styles
  (:require-macros
    [garden.def :refer [defcssfn]])
  (:require
    [spade.core   :refer [defglobal defclass]]
    [garden.units :refer [deg px]]
    [garden.color :refer [lighten as-hsl rgb]]))

(def atomic-tangerine (rgb 255 153 102))
(def dark-pastel-green (rgb 17, 191, 32))
(def cinnibar (rgb 233, 79, 55))
(def periwinkle (rgb 216, 220, 255))
(def tea-green (rgb 197, 239, 203 ))

(defcssfn linear-gradient
 ([c1 p1 c2 p2]
  [[c1 p1] [c2 p2]])
 ([dir c1 p1 c2 p2]
  [dir [c1 p1] [c2 p2]]))

(defglobal defaults
  [:body
   {:color               :black
    :background-color    :white}]
  [:nav.navbar {:background-color :transparent}]
  )

(defclass header
  []
  {:color :orange})

(defclass button
  []
  {:background-color :#3AC572
   :border :none})

(defclass home-page
  []
  {:background "linear-gradient(90deg, var(--atomic-tangerine ) 0%, var(--mountbatten-pink ) 100%)"})

(defclass menu
  []
  {:background "linear-gradient(90deg, var(--atomic-tangerine ) 0%, var(--mountbatten-pink ) 100%)"
   }
  [:.navbar-item {:color "var(--ivory)"}])

(defclass submit-button
  []
  {:background dark-pastel-green})

(defclass cancel-button
  []
  {:background cinnibar})


(defclass login-page
  []
  {:background :papayawhip}
  #_[:form {:background (lighten (as-hsl atomic-tangerine) 15)}])

(defclass about-page
  []
  {:background :papayawhip})

(defclass haggadah-creation-page
  []
  {:background periwinkle}
  [:form {:background tea-green}])
