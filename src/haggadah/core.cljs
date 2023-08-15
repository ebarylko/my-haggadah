(ns haggadah.core
  (:require
   ["firebase/app" :as fba]
   ["react-dom/client" :refer [createRoot]]
   [haggadah.config :as config]
   [haggadah.events :as events]
   [haggadah.fb.auth :as fb-auth]
   [haggadah.fb.config :as cfg]
   [haggadah.fb.firestore :as fb-fs]
   [haggadah.fb.functions :as fb-fn]
   [haggadah.routes :as routes]
   [re-frame.core :as re-frame]
   [reagent.core :as r]))

(defonce firebase-instance (atom nil))

(defn dev-setup []
  (when config/debug?
    (println "dev mode")))


(defn ^:dev/after-load mount-root []
  (re-frame/clear-subscription-cache!)
  (let [root (createRoot (.getElementById js/document "app"))]
    (.render root (r/as-element [routes/router-component {:router routes/router}]))))


(defn fb-init [config]
  (when-not @firebase-instance
    (let [cfg (clj->js config)]
      (reset! firebase-instance (fba/initializeApp cfg))
      (fb-auth/init @firebase-instance events/auth-user-success)
      (fb-fs/init @firebase-instance)
      (fb-fn/init @firebase-instance))))

(defn firebase-init!
  []
  (fb-init cfg/firebase))

(defn init []
  (re-frame/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root)
  (firebase-init!)
  (re-frame/dispatch-sync [::events/add-full-haggadah])
  (routes/init-routes!))

