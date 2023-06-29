(ns haggadah.setup
  (:require  [cljs.test :as t :include-macros true]
             [clojure.string :as str]
             ["firebase-admin" :as admin]
             ["firebase-admin/auth" :as admin-auth]
             [cljs.nodejs :as nodejs]
             [haggadah.fb.config :as cfg]))

(nodejs/enable-util-print!)


(defonce app (.initializeApp admin #_(clj->js cfg/firebase)))
(goog-define PROJECT_ID false)

(defn main [& args]
  (println "Creating the user")
  (println "this is the project id " PROJECT_ID)
  (-> (.createUser (admin-auth/getAuth) #js{:email "han@skywalker.com" :password "123456789"})
      (.then (fn [user] (js/console.log user)
               (js/process.exit)))
      (.catch (fn [e] (println "The error " e )))))

(set! *main-cli-fn* main)
