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


(defn menu
  []
  [:div
  [:nav {:class "navbar", :role "navigation", :aria-label "main navigation"}
   [:div {:class "navbar-brand"}
    [:h1.navbar-item.is-size-5.has-text-weight-bold "ourhaggadah"]
    [:a.navbar-item.is-size-5.has-text-weight-bold {:on-click  #(re-frame/dispatch [::events/navigate :home])} "Home"]
    [:a.navbar-item.is-size-5.has-text-weight-bold {:on-click  #(re-frame/dispatch [::events/navigate :about])} "About"]

    [:a {:role "button", :class "navbar-burger", :aria-label "menu", :aria-expanded "false", :data-target "navbarBasicExample"}
     [:span {:aria-hidden "true"}]
     [:span {:aria-hidden "true"}]
     [:span {:aria-hidden "true"}]]]
    [:div {:id "navbarBasicExample", :class "navbar-menu"}
     [:div {:class "navbar-start"}
      
      [:a {:class "navbar-item"} "Documentation"]
      [:div {:class "navbar-item has-dropdown is-hoverable"}
       [:a {:class "navbar-link"} "More"]
       [:div {:class "navbar-dropdown"}
        [:a {:class "navbar-item"} "Item 1"]
        [:a {:class "navbar-item"} "Item 2"]
        [:a {:class "navbar-item"} "Item 3"]
        [:hr {:class "navbar-divider"}]
        [:a {:class "navbar-item"} "Report an issue"]]]]
     [:div {:class "navbar-end"}
      [:div {:class "navbar-item"}]]]]
   #_ [:div {:class "relative -mt-12 lg:-mt-24"}
     [:svg {:viewBox "0 0 1428 174", :version "1.1", :xmlns "http://www.w3.org/2000/svg", :xmlns:xlink "http://www.w3.org/1999/xlink"}
      [:g {:stroke "none", :stroke-width "1", :fill "none", :fill-rule "evenodd"}
       [:g {:transform "translate(-2.000000, 44.000000)", :fill "#FFFFFF", :fill-rule "nonzero"}
        [:path {:d "M0,0 C90.7283404,0.927527913 147.912752,27.187927 291.910178,59.9119003 C387.908462,81.7278826 543.605069,89.334785 759,82.7326078 C469.336065,156.254352 216.336065,153.6679 0,74.9732496", :opacity "0.100000001"}]
        [:path {:d "M100,104.708498 C277.413333,72.2345949 426.147877,52.5246657 546.203633,45.5787101 C666.259389,38.6327546 810.524845,41.7979068 979,55.0741668 C931.069965,56.122511 810.303266,74.8455141 616.699903,111.243176 C423.096539,147.640838 250.863238,145.462612 100,104.708498 Z", :opacity "0.100000001"}]
        [:path {:d "M1046,51.6521276 C1130.83045,29.328812 1279.08318,17.607883 1439,40.1656806 L1439,120 C1271.17211,77.9435312 1140.17211,55.1609071 1046,51.6521276 Z", :id "Path-4", :opacity "0.200000003"}]]
       [:g {:transform "translate(-4.000000, 76.000000)", :fill "#FFFFFF", :fill-rule "nonzero"}
        [:path {:d "M0.457,34.035 C57.086,53.198 98.208,65.809 123.822,71.865 C181.454,85.495 234.295,90.29 272.033,93.459 C311.355,96.759 396.635,95.801 461.025,91.663 C486.76,90.01 518.727,86.372 556.926,80.752 C595.747,74.596 622.372,70.008 636.799,66.991 C663.913,61.324 712.501,49.503 727.605,46.128 C780.47,34.317 818.839,22.532 856.324,15.904 C922.689,4.169 955.676,2.522 1011.185,0.432 C1060.705,1.477 1097.39,3.129 1121.236,5.387 C1161.703,9.219 1208.621,17.821 1235.4,22.304 C1285.855,30.748 1354.351,47.432 1440.886,72.354 L1441.191,104.352 L1.121,104.031 L0.457,34.035 Z"}]]]]]
   ])

(defn home-panel []
  [:div {:class (styles/home-page)}
   [menu] 
   
   [:section.hero.is-medium.container
    [:div.columns.container {:class "hero-body"}

     [:div.column
      
      [:p.title.has-text-weight-bold.is-size-1"Share your haggadot with familiy and friends"  ]
      [:p.subtitle.is-size-3  "Make a Haggadah effortlessly with just a click"  ]
      [:div.buttons.is-medium
       [:button.button.is-focused {:on-click #(re-frame/dispatch [::events/navigate :login]) :data-test-id "login"} "Log in"]
       [:button.button {:on-click #(re-frame/dispatch [::events/navigate :register])} "Register"]]
      ]
     [:div.column
      [:img {:class "w-full md:w-4/5 z-50", :src "/images/hero.png"}] ]]]
   
   
   
   [:svg {:class "wave-top", :viewBox "0 0 1439 147", :version "1.1", :xmlns "http://www.w3.org/2000/svg", :xmlnsXlink "http://www.w3.org/1999/xlink"}
    [:g {:stroke "none", :stroke-width "1", :fill "none", :fill-rule "evenodd"}
     [:g {:transform "translate(-1.000000, -14.000000)", :fill-rule "nonzero"}
      [:g {:class "wave", :fill "#f8fafc"}
       [:path {:d "M1440,84 C1383.555,64.3 1342.555,51.3 1317,45 C1259.5,30.824 1206.707,25.526 1169,22 C1129.711,18.326 1044.426,18.475 980,22 C954.25,23.409 922.25,26.742 884,32 C845.122,37.787 818.455,42.121 804,45 C776.833,50.41 728.136,61.77 713,65 C660.023,76.309 621.544,87.729 584,94 C517.525,105.104 484.525,106.438 429,108 C379.49,106.484 342.823,104.484 319,102 C278.571,97.783 231.737,88.736 205,84 C154.629,75.076 86.296,57.743 0,32 L0,0 L1440,0 L1440,84 Z"}]]
      [:g {:transform "translate(1.000000, 15.000000)", :fill "#FFFFFF"}
       [:g {:transform "translate(719.500000, 68.500000) rotate(-180.000000) translate(-719.500000, -68.500000) "}
        [:path {:d "M0,0 C90.7283404,0.927527913 147.912752,27.187927 291.910178,59.9119003 C387.908462,81.7278826 543.605069,89.334785 759,82.7326078 C469.336065,156.254352 216.336065,153.6679 0,74.9732496", :opacity "0.100000001"}]
        [:path {:d "M100,104.708498 C277.413333,72.2345949 426.147877,52.5246657 546.203633,45.5787101 C666.259389,38.6327546 810.524845,41.7979068 979,55.0741668 C931.069965,56.122511 810.303266,74.8455141 616.699903,111.243176 C423.096539,147.640838 250.863238,145.462612 100,104.708498 Z", :opacity "0.100000001"}]
        [:path {:d "M1046,51.6521276 C1130.83045,29.328812 1279.08318,17.607883 1439,40.1656806 L1439,120 C1271.17211,77.9435312 1140.17211,55.1609071 1046,51.6521276 Z", :opacity "0.200000003"}]]]]]]])

(defmethod routes/panels :home-panel [] [home-panel])
;; about


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


(str "Hello " "name" ". We're glad to see you")

(defn login-panel []
  [:div {:class (styles/home-page)}
   [menu]   
   [:section.hero.is-medium.container
    [:div.pt-24.hero-body
     (let [name (re-frame/subscribe [::subs/name])]
       [:div
        [:h1.text-center.is-size-4 {:id "user"}
         (str "Hello " @name ". We're glad to see you.")]])
     [:div
      [:h1.text-center.is-size-4 {:id "user"}
       "Click the button below so you can see your haggadot and share them"]]
     [:div.pt-4
      [:button.button.is-large.is-focuesd  { :on-click  #(re-frame/dispatch [::events/login :admin]) :id "load-haggadah"}  "Load haggadah"]]
     (let [{:keys [haggadah-text]} @(re-frame/subscribe [::subs/haggadah-text])]
       (when haggadah-text
         [:div.pt-6
          [:div.box.title  {:dangerouslySetInnerHTML #js{:__html (js/marked.parse haggadah-text #js{:mangle false :headerIds false }  )} :id "haggadah-text"}]]))]]])
  


(defn about-panel
  []

  [:div {:class (styles/home-page)}
   [menu]
   [:sections.hero.is-large
    [:div.columns.is-8.is-variable.container {:class "hero-body"}
     [:div.column
      [:h1.has-text-weight-bold.is-size-3 "Why ourhaggadah.com?"]
      [:h1.pt-4.text-2xl "Creating a haggadah is daunting. There are so many questions to answer, such as what to include, who reads what portions, and how to accomodate speakers of different languages. I wanted to make the process easier so that people can focus on celebrating pesach with their friends and family."]]
     [:div.column
      [:h1.has-text-weight-bold.is-size-3 "About me"]
      [:img.is-96x96.image  {:src "/images/about-page-photo.jpeg"}]
      [:h1.pt-4.text-2xl "My name is Eitan Barylko and I am an undergraduate student at Simon Fraser University. I love programming, cooking, and complaining that all the interesting things are in Vancouver. If you want to see what else I've worked on, you can checkout my github repo on the link below."]
      [:a.pt-4-text-2xl {:href "https://github.com/ebarylko"} "https://github.com/ebarylko"]]]]
   ])

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
