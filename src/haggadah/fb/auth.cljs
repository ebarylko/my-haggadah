(ns haggadah.fb.auth
  (:require
   ["firebase/auth" :as fb-auth]))


(defonce auth (atom nil))

(goog-define FIREBASE_AUTH_HOST false)
(goog-define FIREBASE_HOSTING_HOST false)


(defn email-login
  [email password ]
  (fb-auth/signInWithEmailAndPassword @auth email password))

(defn create-user
  [email password]
  (fb-auth/createUserWithEmailAndPassword @auth email password))


(defn init [firebase-instance on-auth-change]
  (reset! auth (fb-auth/getAuth firebase-instance))
  (when FIREBASE_AUTH_HOST
    (println "Connecting to auth host "FIREBASE_AUTH_HOST)
    (fb-auth/connectAuthEmulator @auth FIREBASE_AUTH_HOST #js{:disableWarnings true}))
  (.onAuthStateChanged ^js @auth on-auth-change))


(defn signout
  []
  (fb-auth/signOut @auth))
