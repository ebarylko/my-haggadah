(ns haggadah.fb.functions
(:require ["firebase/functions" :as func]))

(defonce functions (atom nil))

(goog-define FIREBASE_FUNCTIONS_HOST false)


(defn init [firebase-instance]
  (reset! functions (func/getFunctions firebase-instance))
  (when FIREBASE_FUNCTIONS_HOST
    (println "functions host" FIREBASE_FUNCTIONS_HOST )
    (let [[host port] (clojure.string/split FIREBASE_FUNCTIONS_HOST #":")]
      (func/connectFunctionsEmulator @functions host (js/parseInt port)))))

(defn callable-from-url
  [url]
(func/httpsCallableFromURL @functions url))
