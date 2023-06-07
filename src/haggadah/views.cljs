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
  [:div.bg-gray-400.font-sans.leading-normal.tracking-normal
   [:nav {:class "bg-gray-800 p-2 mt-0 fixed w-full z-10 top-0"}
    [:div {:class "container mx-auto flex flex-wrap items-center"} "\t\t    " 
     [:div {:class "flex w-full md:w-1/2 justify-center md:justify-start text-white font-extrabold"} "\t\t\t\t" 
      [:a {:class "text-white no-underline hover:text-white hover:no-underline", :href "#"} "\t\t\t\t\t" 
       [:span {:class "text-2xl pl-2"}
        [:i {:class "em em-grinning"}]" Brand McBrandface"]"\t\t\t\t"]]"\t\t\t" 
     [:div {:class "flex w-full pt-2 content-center justify-between md:w-1/2 md:justify-end"} "\t\t\t\t" 
      [:ul {:class "list-reset flex justify-between flex-1 md:flex-none items-center"} "\t\t\t\t  " 
       [:li {:class "mr-3"} "\t\t\t\t\t" 
        [:a {:class "inline-block py-2 px-4 text-white no-underline", :href "#"} "Active"]"\t\t\t\t  "]"\t\t\t\t  " 
       [:li {:class "mr-3"} "\t\t\t\t\t" 
        [:a {:class "inline-block text-gray-600 no-underline hover:text-gray-200 hover:text-underline py-2 px-4", :href "#"} "link"]"\t\t\t\t  "]"\t\t\t\t  " 
       [:li {:class "mr-3"} "\t\t\t\t\t" 
        [:a {:class "inline-block text-gray-600 no-underline hover:text-gray-200 hover:text-underline py-2 px-4", :href "#"} "link"]"\t\t\t\t  "]"\t\t\t\t\t" 
       [:li {:class "mr-3"} "\t\t\t\t\t" 
        [:a {:class "inline-block text-gray-600 no-underline hover:text-gray-200 hover:text-underline py-2 px-4", :href "#"} "link"]"\t\t\t\t  "]"\t\t\t\t"]"\t\t\t"]]]"\t" "<!--Container-->" "\t" 
   [:div {:class "container shadow-lg mx-auto bg-white mt-24 md:mt-16 h-screen"} "\t\t\n\t\n\t\t\n\t"] 
   ]


  #_[:div 
   [:div [:button {:on-click #(re-frame/dispatch [::events/navigate :about])
                   :class (styles/button)} "About"]]
   [:div [:h2 {:class (styles/header)}
          "Welcome to my-haggadah, a site where you can create your personal haggadah with just a click.
Please login below to access your haggadot."]

    (let [{:keys [haggadah-text]} @(re-frame/subscribe [::subs/haggadah-text])]
      (when haggadah-text
        [:div  {:dangerouslySetInnerHTML #js{:__html (js/marked.parse haggadah-text)} :id "haggadah-text"}]))
    [:div 
     [:button {:on-click #(re-frame/dispatch [::events/navigate :login]) :data-test-id "login"
               :class (styles/button)} "Log In"]]]])

(defmethod routes/panels :home-panel [] [home-panel])
;; about

(defn about-panel []
  [:div
   [:div
    [:button {:on-click #(re-frame/dispatch [::events/navigate :home]) :class (styles/button)}
     "Return to the Home Page"]]
   [:h1 {:class (styles/header)} "This is the About Page."]])

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

(defn login-panel []
  [:div
   [:button {:on-click #(re-frame/dispatch [::events/navigate :home]) :class (styles/button)}
    "Return to Home Page"]
   (let [name (re-frame/subscribe [::subs/name])]
     [:div
      [:h1.user (str "Hello from " @name ". This is the Home Page." "We're glad to see you.")]
      (let [{:keys [haggadah-text]} @(re-frame/subscribe [::subs/haggadah-text])]
        (when haggadah-text
          [:div  {:dangerouslySetInnerHTML #js{:__html (js/marked.parse haggadah-text)} :id "haggadah-text"}]))])
   [:h1 {:class (styles/header)}"To see your dashboard please click on the button below"]
   [:div 
    [:button {:on-click #(re-frame/dispatch [::events/login :admin]) :id "load-user"
              :class (styles/button)} "Load user"]]])

(defmethod routes/panels :haggadah-panel [] [haggadah-panel])
(defmethod routes/panels :about-panel [] [about-panel])
(defmethod routes/panels :login-panel [] [login-panel])
;; main

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    (routes/panels @active-panel)))

(let [name (re-frame/subscribe [::subs/name])]
  [:div
   

   (let [{:keys [haggadah-text]} @(re-frame/subscribe [::subs/haggadah-text])]
     (when haggadah-text
       [:div  {:dangerouslySetInnerHTML #js{:__html (js/marked.parse haggadah-text)} :id "haggadah-text"}]))
   
  
   ])
