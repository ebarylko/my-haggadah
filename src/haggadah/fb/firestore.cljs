(ns haggadah.fb.firestore
  (:require
   ["firebase/firestore" :as fs]))

(defonce firestore (atom nil))

(defn init [firebase-instance]
  (reset! firestore (fs/getFirestore firebase-instance)))
