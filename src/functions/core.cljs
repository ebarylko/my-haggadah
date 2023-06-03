(ns functions.core
  (:require ["firebase-functions" :as functions]
            ["firebase-admin" :as admin]
            ["firebase-admin/firestore" :as firestore]
))

(defonce app (.initializeApp admin))

(def db (.getFirestore firestore app))

(defn log
  "Log cljs data arguments to functions logger"
  [& args]
  (apply (.-log functions/logger) (map clj->js args)))

(defn write
  "Echo the passed in query parameters merged with the current time"
  [req res]
  (let [query (js->clj (.-query req) :keywordize-keys true)]
    (log "hello" query)
    (-> db
        (.doc "/users/amir")
        (.set #js {"haggadah" (:haggadah query)}))
    (.json res (clj->js {:time (.toString (js/Date.))
                         :query query}))))

#_(defn fire
  "Echo the passed in query parameters merged with the current time"
  [data context]
  (let [query (js->clj (.-query data) :keywordize-keys true)]
    (log "hello" query)
    (.json context (clj->js {:time (.toString (js/Date.))
                         :query query}))))

(defn echo
  [req res]
  (let [query (js->clj (.-query req) :keywordize-keys true)]
    (log "hello" query)
    (.json res (clj->js {:time (.toString (js/Date.))
                         :query query}))))

(def exports
  #js {:echo (.onRequest functions/https echo)
       #_#_:fire (.onCall functions/https fire)
       :write (.onRequest functions/https write)})

