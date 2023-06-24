(ns haggadah.fb.auth
  (:require
  ["firebase/auth" :as fb-auth]))


(defonce auth (atom nil))

(goog-define FIREBASE_AUTH_HOST false)
(goog-define FIREBASE_HOSTING_HOST false)

(defonce user-info (atom nil))

(defn auth-user-success
  "Pre: takes a user
  Post: navigates to dashboard and stores user info if the user is logged in, otherwise navigates them to the home page"
  [user]
  (if user
    (println "THe user is" user "Name " (.-email user) " Uid " (.-uid user))
    (println "The user is logging out")))

(defn auth-user-faliure
  [user]
  (reset! user-info {:name "nil"
                    :uid nil}))

(defn init [firebase-instance]
  (reset! auth (fb-auth/getAuth firebase-instance))
  (when FIREBASE_AUTH_HOST
    (println "Connecting to auth host "FIREBASE_AUTH_HOST)
    (fb-auth/connectAuthEmulator @auth FIREBASE_AUTH_HOST #js{:disableWarnings true}))
  (.onAuthStateChanged ^js @auth auth-user-success))

(defn email-login
  [email password ]
  (fb-auth/signInWithEmailAndPassword @auth email password))

