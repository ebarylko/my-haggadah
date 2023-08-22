(ns haggadah.add-default-haggadah
  (:require ["firebase-admin" :as admin]
            ["firebase-admin/firestore" :as admin-fire]
            [haggadah.full-haggadah :refer [full-haggadah]]))


(goog-define PROJECT_ID false)
(defonce app (.initializeApp admin ))
(def db (.getFirestore admin-fire))

(def default-haggadah (assoc full-haggadah :path "haggadah/full-haggadah"))

(defn add-haggadah
  []
  (println "The id " PROJECT_ID )
  (-> db
      (.collection "haggadah")
      (.doc "full-haggadah")
      (.set (clj->js default-haggadah))
      (.then (fn [doc] (js/console.log doc)
               (js/process.exit)))
      (.catch (fn [e] (println "The error " e )))))

;; (set! *main-cli-fn* add-haggadah)
