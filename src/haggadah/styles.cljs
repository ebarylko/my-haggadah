(ns haggadah.styles
  (:require-macros
    [garden.def :refer [defcssfn]])
  (:require
    [spade.core   :refer [defglobal defclass]]
    [garden.units :refer [deg px]]
    [garden.color :refer [lighten as-hsl rgb]]
    [garden.core :refer [css]]
    [garden.stylesheet :refer [at-media]]))

(def atomic-tangerine (rgb 255 153 102))
(def dark-pastel-green (rgb 17, 191, 32))
(def cinnibar (rgb 233, 79, 55))
(def periwinkle (rgb 216, 220, 255))
(def tea-green (rgb 197, 239, 203 ))
(def success (rgb 130, 140, 81 ))
(def danger :#881600)
(def uranian-blue :#b9e4ff)

(def page-background uranian-blue)
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
  [:.english-text {:text-align :left
              :font-size :1.25rem}]
  [:.page {:background page-background
           :width :100%}]
  [:.title {:font-size :2rem
            :font-weight :normal
            :text-align :center}
   [:.title {:font-size :1.7rem}]]
  [:.text {:text-align :right
           :font-size :1.25rem}]
  [:.table-content
   {:border-width :1px
    :display :flex
    :justify-content :center}])

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
   :background "linear-gradient(90deg, var(--atomic-tangerine ) 0%, var(--mountbatten-pink ) 100%)"}
  [:img {:width :50%
         :margin :auto}]
  (at-media {:max-width :768px}
            [:img {:width :70%}]))


(defclass dashboard-table
  []
  [:td {:height :50px
         :vertical-align :middle}])

(defclass menu
  []
  {:background atomic-tangerine}
  [:a.navbar-item {:background-color atomic-tangerine
                  :color "var(--ivory)"}]
  [:.navbar-menu {:padding 0}])

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
   :width :100%}
  [:form {:background :whitesmoke
          #_#_:width :37%}]
  [:.columns {:padding-right :0rem}])

(defclass about-page
  []
  {:background page-background
   :width :100%
   #_#_:overflow :auto})

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
   :width :100%
   :padding "20px 0px"})

(defclass seder-view
  []
  {:background page-background
   :width :100%
   :padding "20px 0px"})
