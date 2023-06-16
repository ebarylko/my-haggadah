(ns haggadah.routes
  (:require
   [pushy.core :as pushy]
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   [reitit.coercion.spec :as rss]
   [reitit.frontend :as rf]
   [re-frame.core :as re-frame]
   [haggadah.views :as views]
   [haggadah.events :as events]
   [haggadah.subs :as subs]))

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
         :controllers [{:start
                        (fn [_]
                          (let [uid @(re-frame/subscribe [::subs/uid])]
                            (re-frame/dispatch [::events/fetch-haggadot uid #(re-frame/dispatch [::set-haggadot %])
                                                #(js/console.log "The haggadah could not be fetched")])))}]}]
    ["/:id" {:name :haggadah-view
              :view views/home-panel
              :link-text "haggadah"}]]])


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

(defn init-routes! []
  (js/console.log "initializing routes")
  (rfe/start!
   router
   on-navigate
   {:use-fragment true}))

(defn router-component [{:keys [router]}]
  (let [current-route @(re-frame/subscribe [:current-route])]
    [:div.main-container
     [views/top-menu {:router router :current-route current-route}]
     (when current-route
       [(-> current-route :data :view)])]))
