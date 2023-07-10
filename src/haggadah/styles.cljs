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
(def success (rgb 130, 140, 81 ))
(def danger :#881600)


(def page-background periwinkle)
(def form-background (lighten (as-hsl success) 25))

(defcssfn linear-gradient
 ([c1 p1 c2 p2]
  [[c1 p1] [c2 p2]])
 ([dir c1 p1 c2 p2]
  [dir [c1 p1] [c2 p2]]))

(defglobal defaults
  [:div#app {:height :100%}]
  [:.main-container {:height :100%}]
  [:html {:height :100%}]
  [:body {:height :100%
          :color               :black
          :background-color    :white}]
  [:nav.navbar {:background-color :transparent}]
  [:form {:background form-background}]
  [:.page {:background page-background
           :width :100%}]
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
  {:width :100%
   :background "linear-gradient(90deg, var(--atomic-tangerine ) 0%, var(--mountbatten-pink ) 100%)"})

(defclass dashboard
  []
  {:width :100%})

(defclass menu
  []
  {:background atomic-tangerine #_"linear-gradient(90deg, var(--atomic-tangerine ) 0%, var(--mountbatten-pink ) 100%)"
   }
  [:.navbar-item {:color "var(--ivory)"}])

(defclass submit-button
  []
  {:background dark-pastel-green})

(defclass cancel-button
  []
  {:background danger
   :color :white})


(defclass login-page
  []
  {:background (lighten (as-hsl atomic-tangerine) 25)
   :width :100%})

(defclass about-page
  []
  {:background page-background
   :width :100%})

(defclass haggadah-creation-page
  []
  {:background page-background
   :width :100%}
  [:form {:background tea-green}])

(defclass haggadah-success-page
  []
  {:background page-background
   :width :100%})

(defclass haggadah-view
  []
  {:background page-background
   :width :100%})
