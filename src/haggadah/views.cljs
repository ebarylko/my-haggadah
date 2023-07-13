(ns  haggadah.views
  (:require
   [re-frame.core :as re-frame]
   [haggadah.styles :as styles]
   [haggadah.subs :as subs]
   [reitit.frontend.easy :as rfe]
   [haggadah.events :as events]
   [goog.string.format]))

(goog-define WRITE false)

(defn href
  "Return relative url for given route. Url can be used in HTML links."
  ([k]
   (href k nil nil))
  ([k params]
   (href k params nil))
  ([k params query]
   (rfe/href k params query)))


(re-frame/reg-event-fx
 ::push-state
 (fn [_ [_ & route]]
   {:push-state route}))

(defn wave
  []
[:div {:class "relative -mt-12 lg:-mt-24"}
       [:svg {:viewBox "0 0 1428 174", :version "1.1", :xmlns "http://www.w3.org/2000/svg", :xmlnsXlink "http://www.w3.org/1999/xlink"}
        [:g {:stroke "none", :stroke-width "1", :fill "none", :fill-rule "evenodd"}
         [:g {:transform "translate(-2.000000, 44.000000)", :fill "#FFFFFF", :fill-rule "nonzero"}
          [:path {:d "M0,0 C90.7283404,0.927527913 147.912752,27.187927 291.910178,59.9119003 C387.908462,81.7278826 543.605069,89.334785 759,82.7326078 C469.336065,156.254352 216.336065,153.6679 0,74.9732496", :opacity "0.100000001"}]
          [:path {:d "M100,104.708498 C277.413333,72.2345949 426.147877,52.5246657 546.203633,45.5787101 C666.259389,38.6327546 810.524845,41.7979068 979,55.0741668 C931.069965,56.122511 810.303266,74.8455141 616.699903,111.243176 C423.096539,147.640838 250.863238,145.462612 100,104.708498 Z", :opacity "0.100000001"}]
          [:path {:d "M1046,51.6521276 C1130.83045,29.328812 1279.08318,17.607883 1439,40.1656806 L1439,120 C1271.17211,77.9435312 1140.17211,55.1609071 1046,51.6521276 Z", :id "Path-4", :opacity "0.200000003"}]]
         [:g {:transform "translate(-4.000000, 76.000000)", :fill "#FFFFFF", :fill-rule "nonzero"}
          [:path {:d "M0.457,34.035 C57.086,53.198 98.208,65.809 123.822,71.865 C181.454,85.495 234.295,90.29 272.033,93.459 C311.355,96.759 396.635,95.801 461.025,91.663 C486.76,90.01 518.727,86.372 556.926,80.752 C595.747,74.596 622.372,70.008 636.799,66.991 C663.913,61.324 712.501,49.503 727.605,46.128 C780.47,34.317 818.839,22.532 856.324,15.904 C922.689,4.169 955.676,2.522 1011.185,0.432 C1060.705,1.477 1097.39,3.129 1121.236,5.387 C1161.703,9.219 1208.621,17.821 1235.4,22.304 C1285.855,30.748 1354.351,47.432 1440.886,72.354 L1441.191,104.352 L1.121,104.031 L0.457,34.035 Z"}]]]]])

(defn top-menu [{:keys [router current-route]}]
  [:div {:class (styles/menu)}
   [:nav {:class "navbar", :role "navigation", :aria-label "main navigation"}
    [:div {:class "navbar-brand"}
     [:a.navbar-item "ourhaggadah"]
     [:a {:on-click #(re-frame/dispatch [::events/active-menu]) :role "button", :class "navbar-burger", :aria-label "menu", :aria-expanded "false", :data-target "menu"}
      [:span {:aria-hidden "true"}]
      [:span {:aria-hidden "true"}]
      [:span {:aria-hidden "true"}]]]
    (let [active-menu? @(re-frame/subscribe [::subs/active-menu?])
          active-menu (when active-menu? "is-active")]
     [:div#menu.navbar-menu {:class active-menu}
      [:a.navbar-item {:class active-menu :on-click  #(re-frame/dispatch [::push-state :home])} "Home"]
      [:a.navbar-item {:class active-menu :on-click  #(re-frame/dispatch [::push-state :about])} "About"]
      [:a.navbar-item {:class active-menu :on-click #(re-frame/dispatch [::events/signout])} "Sign out"]]
     )
    ]])

(defn wave-top []
  [:svg {:class "wave-top", :viewBox "0 0 1439 147", :version "1.1", :xmlns "http://www.w3.org/2000/svg", :xmlnsXlink "http://www.w3.org/1999/xlink"}
    [:g {:stroke "none", :stroke-width "1", :fill "none", :fill-rule "evenodd"}
     [:g {:transform "translate(-1.000000, -14.000000)", :fill-rule "nonzero"}
      [:g {:class "wave", :fill "#f8fafc"}
       [:path {:d "M1440,84 C1383.555,64.3 1342.555,51.3 1317,45 C1259.5,30.824 1206.707,25.526 1169,22 C1129.711,18.326 1044.426,18.475 980,22 C954.25,23.409 922.25,26.742 884,32 C845.122,37.787 818.455,42.121 804,45 C776.833,50.41 728.136,61.77 713,65 C660.023,76.309 621.544,87.729 584,94 C517.525,105.104 484.525,106.438 429,108 C379.49,106.484 342.823,104.484 319,102 C278.571,97.783 231.737,88.736 205,84 C154.629,75.076 86.296,57.743 0,32 L0,0 L1440,0 L1440,84 Z"}]]
      [:g {:transform "translate(1.000000, 15.000000)", :fill "#FFFFFF"}
       [:g {:transform "translate(719.500000, 68.500000) rotate(-180.000000) translate(-719.500000, -68.500000) "}
        [:path {:d "M0,0 C90.7283404,0.927527913 147.912752,27.187927 291.910178,59.9119003 C387.908462,81.7278826 543.605069,89.334785 759,82.7326078 C469.336065,156.254352 216.336065,153.6679 0,74.9732496", :opacity "0.100000001"}]
        [:path {:d "M100,104.708498 C277.413333,72.2345949 426.147877,52.5246657 546.203633,45.5787101 C666.259389,38.6327546 810.524845,41.7979068 979,55.0741668 C931.069965,56.122511 810.303266,74.8455141 616.699903,111.243176 C423.096539,147.640838 250.863238,145.462612 100,104.708498 Z", :opacity "0.100000001"}]
        [:path {:d "M1046,51.6521276 C1130.83045,29.328812 1279.08318,17.607883 1439,40.1656806 L1439,120 C1271.17211,77.9435312 1140.17211,55.1609071 1046,51.6521276 Z", :opacity "0.200000003"}]]]]]])


(defn home-panel []
  [:div {:class (styles/home-page)}
   [:section.hero.container
    [:div {:class "hero-body"}
     [:figure.image
      [:img {:src "/images/hero.png"}] ]
     [:p.title.is-4 "Share your Haggadot with familiy and friends"  ]
     [:p.subtitle "Make a Haggadah effortlessly"  ]
     [:div.buttons
      [:a.button.is-focused {:href (href :login) :data-testid :login} "Log in"]
      [:a.button  "Register"]]]]])




(defn login-panel []
  [:div.is-dark {:class (styles/login-page)}
   [:section.pt-4
    [:div.is-centered.columns
     [:div.is-5-tablet.is-4-desktop.is-3-widescreen.column
      [:form.box
       [:div.text-center.pb-2
        "Please enter your email and password."]
       [:div {:class "field"}
        [:label "Email"]
        [:div {:class "control has-icons-left has-icons-right"}
         [:input {:class "input", :type "email", :placeholder "Email input", :defaultValue "han@skywalker.com"}]
         [:span {:class "icon is-small is-left"}
          [:i {:class "fas fa-envelope"}]]]]
       [:div.field
        [:label  "Password"]
        [:div {:class "control has-icons-left has-icons-right"}
         [:input {:class "input", :type "text", :placeholder "Text input", :defaultValue "123456789"}]
         [:span {:class "icon is-small is-left"}
          [:i {:class "fas fa-key"}]]]]
       [:div.field.is-grouped.is-grouped-right 
        [:div {:class "control"}
         [:button.is-small.button {:class (styles/cancel-button)}  "Cancel"]]
        [:div {:class "control"}
         [:a.button.is-small {:class (styles/submit-button) :on-click  #(re-frame/dispatch [::events/login]) :data-testid :submit} "Submit"]]]
       ]]]]])

(defn wave-bottom
  []
[:div {:class "relative -mt-12 lg:-mt-24"}
       [:svg {:viewBox "0 0 1428 174", :version "1.1", :xmlns "http://www.w3.org/2000/svg", :xmlnsXlink "http://www.w3.org/1999/xlink"}
        [:g {:stroke "none", :stroke-width "1", :fill "none", :fill-rule "evenodd"}
         [:g {:transform "translate(-2.000000, 44.000000)", :fill "#FFFFFF", :fill-rule "nonzero"}
          [:path {:d "M0,0 C90.7283404,0.927527913 147.912752,27.187927 291.910178,59.9119003 C387.908462,81.7278826 543.605069,89.334785 759,82.7326078 C469.336065,156.254352 216.336065,153.6679 0,74.9732496", :opacity "0.100000001"}]
          [:path {:d "M100,104.708498 C277.413333,72.2345949 426.147877,52.5246657 546.203633,45.5787101 C666.259389,38.6327546 810.524845,41.7979068 979,55.0741668 C931.069965,56.122511 810.303266,74.8455141 616.699903,111.243176 C423.096539,147.640838 250.863238,145.462612 100,104.708498 Z", :opacity "0.100000001"}]
          [:path {:d "M1046,51.6521276 C1130.83045,29.328812 1279.08318,17.607883 1439,40.1656806 L1439,120 C1271.17211,77.9435312 1140.17211,55.1609071 1046,51.6521276 Z", :id "Path-4", :opacity "0.200000003"}]]
         [:g {:transform "translate(-4.000000, 76.000000)", :fill "#FFFFFF", :fill-rule "nonzero"}
          [:path {:d "M0.457,34.035 C57.086,53.198 98.208,65.809 123.822,71.865 C181.454,85.495 234.295,90.29 272.033,93.459 C311.355,96.759 396.635,95.801 461.025,91.663 C486.76,90.01 518.727,86.372 556.926,80.752 C595.747,74.596 622.372,70.008 636.799,66.991 C663.913,61.324 712.501,49.503 727.605,46.128 C780.47,34.317 818.839,22.532 856.324,15.904 C922.689,4.169 955.676,2.522 1011.185,0.432 C1060.705,1.477 1097.39,3.129 1121.236,5.387 C1161.703,9.219 1208.621,17.821 1235.4,22.304 C1285.855,30.748 1354.351,47.432 1440.886,72.354 L1441.191,104.352 L1.121,104.031 L0.457,34.035 Z"}]]]]])

(defn dashboard-panel
  []
  [:div.page 
   [:div.container.is-large.hero.is-flex
    [:div.hero-body.pt-6
     [:div.pt-24.column
      (let [name (re-frame/subscribe [::subs/name])]
        [:div
         [:h1.text-center.is-size-4 {:data-testid :user}
          (str "Hello " @name ". Welcome. To make a new Haggadah, click the button to your right. To share and edit your existing Haggadah, look at your Haggadot below ")]])
      [:div.pl-6.buttons.is-right
       [:a.button.is-smalll.is-pulled-right.mt-2 {:data-testid :create-haggadah
                                                        :on-click #(re-frame/dispatch [::push-state :haggadah-creation])}   "Create Haggadah"]]
      [:div
       [:h1.is-size-3
        "Haggadot created"]
       (let [haggadot @(re-frame/subscribe [::subs/haggadot])]
         (when haggadot
           [:ul
            (for [{:keys [title id]} haggadot :when id] 
              ^{:key id}[:li.mb-2
                         [:a {:data-testid (str "view-" id)
                              :href (href :haggadah-view {:id id})} title]
                         [:a.is-small.ml-2 {:data-testid (str "edit-" id )
                                            :href (href :haggadah-edit {:id id})}
                          [:i.fas.fa-edit]]])]))] ]]]
   [wave-bottom]])

(def edit-explanation
  "Source contains the Haggadah with markdown, while preview shows you how the Haggadah will appear after applying the markdown.
To see changes in preview edit source and then click on preview.
 When you are satisfied with your changes please click the submit button below ")

(defn haggadah-edit-panel
  []
  (let [text @(re-frame/subscribe [::subs/haggadah-text])
        preview? @(re-frame/subscribe [::subs/src-preview])]
    [:div.container
     [:div.has-text-centered.box
      edit-explanation]
     [:div.tabs
      [:ul
       [:li {:class (if-not preview? "is-active" "") }
        [:a {:on-click #(re-frame/dispatch [::events/set-preview false])
             }"Source"]]
       [:li {:class (if preview? "is-active" "")}
        [:a {:on-click #(re-frame/dispatch [::events/set-preview true])
             } "Preview"]]]]
     [:div 
      [:div {:class (when preview? "is-hidden")}
       [:form.box
        [:div.field
         [:div
          [:textarea.textarea {:placeholder "Text input" 
                               :data-testid :preview
                               :value text
                               :on-change #(re-frame/dispatch [::events/edit-haggadah (-> %
                                                                                          (.-target)
                                                                                          (.-value))])}]]]]]
       [:div {:class (when-not preview? "is-hidden")}
        [:div.content {:dangerouslySetInnerHTML #js{:__html (js/marked.parse text #js{:breaks true :mangle false :headerIds false})} :id "haggadah-text"}]]]
     [:div
      [:button.button {:on-click #(re-frame/dispatch [::events/modify-haggadah {:new-haggadah text :on-success [::events/push-state :edit-success] }])
                       :data-testid :submit} "Submit changes"]]]))

(defn form-content
  "Pre: takes an id for a form field
  Post: returns the text of the field if it exists, ni otherwise"
  [id]
  (-> (.getElementById js/document id)
      (.-value)))



(defn haggadah-success-panel
  [_]
  [:div.is-flex-is-flex-grow-1 {:class (styles/haggadah-success-page)}
   [:div.container.has-text-centered.pt-3
    [:div.pb-5
     "Your Haggadah is ready. Please click the button below to return to the dashboard and see it"]
    [:div [:a.button.is-focused.is-link {:data-testid :return :on-click #(re-frame/dispatch [::push-state :dashboard])} "Return to dashboard"]]]])


(defn haggadah-edit-success
  [_]
  [:div.container.has-text-centered
   [:div.notification.is-success
    "Your Haggadah has been successfully changed. Please click the button below to return to the dashboard and see it"]
   [:a.button.is-focused.is-link {:data-testid :return-dashboard
                                  :on-click #(re-frame/dispatch [::push-state :dashboard])}
    "Return to dashboard"]])

(defn haggadah-creation-panel
  []
  (let [haggadah (atom nil)]
    [:div.pt-4.page 
     [:div.hero.is-fullheight
      [:div.columns.is-centered
       [:div.column.is-5-tablet.is-4-desktop.is-3-widescreen
        [:form.box.mt-4 
         [:h1.pb-4 "Please make a title and select the haggadah template"]
         [:div.field
          [:div 
           [:input#haggadah-title.input {:data-testid :haggadah-title :placeholder "The title of your Haggadah" }]]]
         [:div.field
          (let [dropdown? @(re-frame/subscribe [::subs/dropdown])
                expand (when dropdown? "is-active")]
           [:div.dropdown {:class expand}
            [:div {:class "dropdown-trigger"}
             [:button.button {:readonly true
                              :aria-haspopup "true", :aria-controls "dropdown-menu"}
              [:span "Base Haggadah"]
              [:span {:class "icon is-small"}
               [:i {:class "fas fa-angle-down", :aria-hidden "true"}]]]]
            [:div {:class "dropdown-menu", :id "dropdown-menu", :role "menu"}
             [:div {:class "dropdown-content"}
              [:a.dropdown-item.is-active "Base Haggadah" ]]]])]
         [:div.field.is-grouped.is-grouped-right 
          [:a.button.mr-3 "Cancel"]
          [:a.button {:class (styles/submit-button):data-testid :add-haggadah :on-click #(re-frame/dispatch [::events/add-haggadah
                                                                                        (form-content "haggadah-title")
                                                                                         %])
                              :id "submit"} "Create"]]]]]]]))

(defn haggadah-view-panel
  []
  [:div.page.is-flex.is-flex-grow-1 {:class (styles/haggadah-view)}
    (let [text @(re-frame/subscribe [::subs/haggadah-text])]
      [:section.container.is-flex
       [:div.box.is-flex-grow-1 {:data-testid :haggadah-text} text ]])])

(defn about-panel
  []
  [:div.page {:class (styles/about-page)}
   [:section.container
    [:div.is-variable
     [:div
      [:h1.has-text-weight-bold.is-size-3 "Why ourhaggadah.com?"]
      [:h1.pt-4.text-2xl "Creating a Haggadah can feel daunting. There are so many questions to answer, such as what to include, who reads what portions, and how to accomodate speakers of different languages. I wanted to make the process easier so that people can focus on celebrating Pesach with their friends and family."]]
     [:div
      [:h1.has-text-weight-bold.is-size-3 "About me"]
      [:img.is-96x96.image  {:src "/images/about-page-photo.jpeg"}]
      [:h1.pt-4.text-2xl "My name is Eitan Barylko and I am a university student. I love programming, cooking, and reading. If you want to see what else I've worked on, you can checkout my github repository on the link below."]
      [:a.pt-4-text-2xl {:href "https://github.com/ebarylko"} "https://github.com/ebarylko"]]]]])
