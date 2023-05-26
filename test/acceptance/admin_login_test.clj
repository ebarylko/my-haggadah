(ns acceptance.admin-login-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [environ.core :refer [env]])
  (:import com.google.firebase.FirebaseApp
           com.google.firebase.internal.EmulatorCredentials
            #_com.google.firebase.FirebaseOptions
            com.google.firebase.FirebaseOptions$Builder
            #_[com.google.auth.oauth2 GoogleCredentials ServiceAccountCredentials]
            #_com.google.auth.oauth2.ServiceAccountCredentials
            com.google.firebase.auth.FirebaseAuth
            #_com.google.firebase.auth.UserRecord
            com.google.firebase.auth.UserRecord$CreateRequest
            #_com.google.firebase.auth.UserRecord$UpdateRequest))

(def project-id (env :gcloud-project))

(def driver (e/chrome-headless ))
(def default-message
  "Hello from (Unknown). This is the Home Page.We're glad to see you.")

(def admin-login-message
  "Hello from han@skywalker.com. This is the Home Page.We're glad to see you.")

(defn- build-firebase-options []
  (-> (new FirebaseOptions$Builder)
      (.setCredentials (EmulatorCredentials.))
      (.setProjectId project-id)
      (.build)))

(defn init []
  (let [options (build-firebase-options)]
    (. FirebaseApp initializeApp options))
  (. FirebaseAuth getInstance))

(defn- convert-user-record-to-map [user-record]
  {:email (. user-record getEmail)
   :email-verified (. user-record isEmailVerified)
   :uid (. user-record getUid)
   :provider-id (. user-record getProviderId)
   :photo-url (. user-record getPhotoUrl)
   :phone-number (. user-record getPhoneNumber)
   :display-name (. user-record getDisplayName)
   :disabled (. user-record isDisabled)})

(defn create-user
  [{:keys [email pwd]}]
  (let [firebase-auth (. FirebaseAuth getInstance)
        create-request (doto (new UserRecord$CreateRequest)
                         (.setEmail email)
                         (.setPassword pwd))]
    (convert-user-record-to-map (. firebase-auth createUser create-request))))


(t/deftest message-test
  #_(t/testing "default user"
      (let [_  (e/go driver "http://localhost:5000/")
            actual (e/get-element-text driver {:class :haggadah-styles-level1})]
        (t/is (= default-message actual))))
  (t/testing "admin user"
    (println "The GCLOUD project is" project-id)
    (init)
    (println "The user" (create-user {:email "han@skywalker.com"  :pwd "123456789"}))
    (let [_ (e/go driver "http://localhost:5000/")
          _ (e/screenshot driver "screenshots/homepage.png")
          _ (println (e/exists? driver {:id :test-id  :tag :button}))
          #_#__ (e/click-single driver {:id :test-id })
          _ (e/click-visible driver {:tag :button}#_{:tag :button})
          _ (e/screenshot driver "screenshots/button-clicked.png")
          actual (e/get-element-text driver {:class :haggadah-styles-level1})]
      (t/is (= admin-login-message actual)))))
