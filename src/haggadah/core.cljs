(ns haggadah.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as re-frame]
   [haggadah.events :as events]
   [haggadah.routes :as routes]
   [haggadah.views :as views]
   [haggadah.config :as config]
   ["firebase/app" :as fba]
   [haggadah.fb.functions :as fb-fn]
   [haggadah.fb.config :as cfg]
   [haggadah.fb.firestore :as fb-fs]
   [haggadah.fb.auth :as fb-auth]
   ))

(defonce firebase-instance (atom nil))


(defn dev-setup []
  (when config/debug?
    (println "dev mode")))

(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/main-panel] root-el)))

(defn fb-init [config]
  (when-not @firebase-instance
    (let [cfg (clj->js config)]
      (reset! firebase-instance (fba/initializeApp cfg))
      (fb-auth/init @firebase-instance)
      (fb-fs/init @firebase-instance))))

(defn firebase-init!
  []
  (fb-init cfg/firebase))

(defn init []
  (routes/start!)
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root)
  (firebase-init!))

