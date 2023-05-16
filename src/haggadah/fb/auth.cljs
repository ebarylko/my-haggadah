(ns haggadah.fb.auth
  (:require
  ["firebase/auth" :as fb-auth]))


(defonce auth (atom nil))

(goog-define FIREBASE_AUTH_HOST false)

(defn init [firebase-instance]
  (reset! auth (fb-auth/getAuth firebase-instance))
  (println FIREBASE_AUTH_HOST "1234")
  #_(fb-auth/connectAuthEmulator auth "http://localhost:9098"))

(defn email-login
  [email password ]
  (fb-auth/signInWithEmailAndPassword @auth email password))
