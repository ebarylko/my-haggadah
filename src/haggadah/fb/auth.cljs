(ns haggadah.fb.auth
  (:require
  ["firebase/auth" :as fb-auth]))

(defonce auth (atom nil))

(defn init [firebase-instance]
  (reset! auth (fb-auth/getAuth firebase-instance)))

(defn email-login
  [email password ]
  (fb-auth/signInWithEmailAndPassword @auth email password))
