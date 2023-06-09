(ns haggadah.fb.firestore
  (:require
   ["firebase/firestore" :as fs]))

(defonce firestore (atom nil))

(goog-define FIREBASE_FIRESTORE_HOST false)

(defn instance
  []
  @firestore)

(defn init [firebase-instance]
  (reset! firestore (fs/getFirestore firebase-instance))
  (when FIREBASE_FIRESTORE_HOST
    (println  "Connecting to firestore host " FIREBASE_FIRESTORE_HOST)
    (let [[host port] (clojure.string/split FIREBASE_FIRESTORE_HOST #":")]
      (fs/connectFirestoreEmulator @firestore host (js/parseInt port)))))

