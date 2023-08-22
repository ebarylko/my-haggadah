(ns haggadah.setup
  (:require ["firebase-admin" :as admin]
            ["firebase-admin/auth" :as admin-auth]
            [cljs.nodejs :as nodejs]))

(nodejs/enable-util-print!)

(defonce app (.initializeApp admin ))
(goog-define PROJECT_ID false)


(defn main []
  (-> (.createUser (admin-auth/getAuth) #js{:email "han@skywalker.com" :password "123456789"})
      (.then (fn [user] (js/console.log user)
               (js/process.exit)))
      (.catch (fn [e] (println "The error " e )))
      ))

(set! *main-cli-fn* main)
