(ns haggadah.core
  (:require ["firebase-functions" :as functions]
            ["firebase-admin" :as admin]
            ["firebase-admin/firestore" :as firestore]))

(defonce app (.initializeApp admin))

(def db (.getFirestore firestore app))

(defn log
  "Log cljs data arguments to functions logger"
  [& args]
  (apply (.-log functions/logger) (map clj->js args)))

(defn write
  "Echo the passed in query parameters merged with the current time"
  [data ^js req]
  (js/console.log "This is auth" (.-auth req))
  (-> db
      (.doc "/users/amir")
      (.set #js {"haggadah" data})
      (.then   #(clj->js {:time (.toString (js/Date.))
                          :query data}))))


(defn example
  [arg]
  (inc arg))

(defn echo
  [req res]
  (let [query (js->clj (.-query req) :keywordize-keys true)]
    (log "hello" query)
    (.json res (clj->js {:time (.toString (js/Date.))
                         :query query}))))

(def exports
  #js {:echo (.onRequest functions/https echo)
       :write (.onCall functions/https write)})

