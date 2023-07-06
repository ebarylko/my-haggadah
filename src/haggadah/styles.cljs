(ns haggadah.styles
  (:require-macros
    [garden.def :refer [defcssfn]])
  (:require
    [spade.core   :refer [defglobal defclass]]
    [garden.units :refer [deg px]]
    [garden.color :refer [lighten as-hsl rgb]]))

(def atomic-tangerine (rgb 255 153 102))

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

(defclass login-page
  []
  {:background :papayawhip}
  #_[:form {:background (lighten (as-hsl atomic-tangerine) 15)}])

(defclass about-page
  []
  {:background :none})
