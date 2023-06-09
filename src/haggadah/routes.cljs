(ns ^:dev/always haggadah.routes
  (:require
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   [reitit.coercion.spec :as rss]
   [reitit.frontend :as rf]
   [reagent.core :as r]
   [re-frame.core :as re-frame]
   [haggadah.views :as views]
   [haggadah.events :as events]))




(def routes
  [
   ["/" {:name      :home
        :view      views/home-panel
        :link-text "Home"}]

   ["/login" {:name      :login
             :view      views/login-panel
             :link-text "Log in"}]
   ["/about" {:name :about
             :view views/about-panel
             :link-text "about" }]
   ["/dashboard"
    ["" {:name :dashboard
         :view views/dashboard-panel
         :link-text  "Submit"
        :controllers [{:start (fn [_] (re-frame/dispatch (:dashboard events/route-events)))}]}]
    ["/:id" {:name :haggadah-view
             :view views/haggadah-view-panel
             :link-text "haggadah"
             :controllers [{:start (fn []
                                    (println "Before the haggadah is fetched")
                                     (re-frame/dispatch (:haggadah-view events/route-events)))}]}]
    ["/:id/edit" {:name :haggadah-edit
             :view views/haggadah-edit-panel
             :link-text "haggadah"
             :controllers [{:start (fn []
                                     (println "Before the haggadah is fetched")
                                     (re-frame/dispatch (:haggadah-edit events/route-events)))}]}]
    ]
   ["/haggadah-creation"
    ["" {:name :haggadah-creation
         :view views/haggadah-creation-panel}]
    ["/success" {:name :haggadah-success
                 :view views/haggadah-success-panel}]
    ["/edit-success" {:name :edit-success
                      :view views/haggadah-edit-success}]]
   ])


(defn on-navigate [new-match]
  (when new-match
    :current-route
    (re-frame/dispatch [:navigated new-match])))

;; events


(re-frame/reg-event-db
 :navigated
 (fn [db [_ new-match]]
   (let [old-match   (:current-route db)
         controllers (rfc/apply-controllers (:controllers old-match) new-match)]
     (assoc db :current-route (assoc new-match :controllers controllers)))))



(re-frame/reg-fx
 :push-state
 (fn [route]
   (apply rfe/push-state route)))

;; subscriptions
(re-frame/reg-sub
 :current-route
 (fn [db]
   (:current-route db)))

(def router
  (rf/router
   routes
   {:data {:coercion rss/coercion}}))

(defonce history (atom nil))

(defn init-routes! []
  (js/console.log "initializing routes")
  (rfe/start!
   router
   on-navigate
   {:use-fragment true}))

(defn router-component [{:keys [router]}]
  (let [current-route @(re-frame/subscribe [:current-route])
        view (case (-> current-route :data :name)
               :home  views/home-panel
               :login views/login-panel
               :about views/about-panel
               :dashboard views/dashboard-panel
               :haggadah-view views/haggadah-view-panel
               :haggadah-edit views/haggadah-edit-panel
               :haggadah-creation views/haggadah-creation-panel
               :haggadah-success views/haggadah-success-panel
               :edit-success views/haggadah-edit-success
               views/home-panel)]
    [:div.main-container.is-flex.is-flex-direction-column
     [views/top-menu {:router router :current-route current-route}]
     (when current-route
       [:div.is-flex-grow-1.is-flex
        [view]])]))
