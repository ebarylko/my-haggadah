(ns haggadah.views
  (:require
   [re-frame.core :as re-frame]
   [haggadah.styles :as styles]
   [haggadah.events :as events]
   [haggadah.routes :as routes]
   [haggadah.subs :as subs]
   ))


;; home




(defn home-panel []
  (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 
      {:class (styles/level1)}
      (str "Hello from " @name ". This is the Home Page." "We're glad to see you.")]
     [:div 
      [:button {:on-click #(re-frame/dispatch [::events/login :admin]) :data-test-id "login"} "Log In as Admin"]
      [:button {:on-click #(re-frame/dispatch  [::events/navigate :haggadah]  )}"Render text"]]
     [:div.underline
      [:a {:on-click #(re-frame/dispatch [::events/navigate :about])}
       "go to About Page"]]
     ]))

(defmethod routes/panels :home-panel [] [home-panel])
;; about

(defn about-panel []
  [:div
   [:h1 "This is the About Page."]

   [:div
    [:a {:on-click #(re-frame/dispatch [::events/navigate :home])}
     "go to Home Page"]]])

(defn haggadah-panel []
  [:div
   [:h1 "This is the Haggadah Page."]
   [:div
    [:a {:on-click #(re-frame/dispatch [::events/navigate :home])}
     "go to Home Page"]
    [:div 
    [:a {:on-click #(re-frame/dispatch [::events/render-login-text :home])}
     "Click here to see the haggadah"]]
    ]])

(defmethod routes/panels :haggadah-panel [] [haggadah-panel])
(defmethod routes/panels :about-panel [] [about-panel])

;; main

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    (routes/panels @active-panel)))

(defn login-message
  [message]

  )
