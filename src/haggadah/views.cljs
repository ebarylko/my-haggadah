(ns haggadah.views
  (:require
   [re-frame.core :as re-frame]
   [haggadah.styles :as styles]
   [haggadah.events :as events]
   [haggadah.routes :as routes]
   [haggadah.subs :as subs]
   ))


;; home

(goog-define WRITE false)


(defn home-panel []
  [:div 
   [:div 
    [:button {:on-click #(re-frame/dispatch [::events/navigate :about])
              :class (styles/button)}
     "About"]]
   (let [name (re-frame/subscribe [::subs/name])]
    [:div
     [:h1 
      {:class (styles/level1)}
      "Welcome to my-haggadah, a site where you can create your personal haggadah with just a click.
Please login below to access your haggadot."]

     (let [{:keys [haggadah-text]} @(re-frame/subscribe [::subs/haggadah-text])]
       (when haggadah-text
         [:div  {:dangerouslySetInnerHTML #js{:__html (js/marked.parse haggadah-text)} :id "haggadah-text"}]))
     [:div 
      [:button {:on-click #(re-frame/dispatch [::events/login :admin]) :data-test-id "login"
                :class (styles/button)} "Log In"]]

     
     [:div
      [:button {:on-click  #(re-frame/dispatch [::events/call-func WRITE "My name is" println js/console.log])}"Cloud function"  ]]
     ])])

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
   [:h1 {:id "haggadah-text"} "This is the Haggadah Page"]
   [:div
    [:a {:on-click #(re-frame/dispatch [::events/navigate :home])}
     "go to Home Page"]
    [:div 
    [:button {:on-click #(re-frame/dispatch [::events/render-login-text "haggadah-example.md"])}
     "Click here to see the haggadah"]]
    (let [haggadah-text (re-frame/subscribe [::subs/haggadah-text])]
      [:div  {:dangerouslySetInnerHTML #js{:__html @haggadah-text} :id "haggadah-text"}]
      
)]])

(defmethod routes/panels :haggadah-panel [] [haggadah-panel])
(defmethod routes/panels :about-panel [] [about-panel])

;; main

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    (routes/panels @active-panel)))

(defn login-message
  [message]

  )
