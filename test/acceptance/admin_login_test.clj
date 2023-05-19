(ns acceptance.admin-login-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e])
  (:import com.google.firebase.FirebaseApp
            com.google.firebase.FirebaseOptions
            com.google.firebase.FirebaseOptions$Builder
            com.google.firebase.auth.FirebaseAuth
            com.google.firebase.auth.FirebaseAuthException
            com.google.firebase.auth.UserRecord
            com.google.firebase.auth.UserRecord$CreateRequest
            com.google.firebase.auth.UserRecord$UpdateRequest))

(def driver (e/firefox-headless))

(def default-message
  "Hello from (Unknown). This is the Home Page.We're glad to see you.")

(def admin-login-message
"Hello from han@skywalker.com. This is the Home Page.We're glad to see you.")

;; (e/go driver "http://localhost:8280/")

(defn- convert-user-record-to-map
  [^UserRecord user-record]
  { :email (. user-record getEmail)
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
 (t/testing "default user" 
   (let [_  (e/go driver "http://localhost:5000/")
         actual (e/get-element-text driver {:class :haggadah-styles-level1})]
     (t/is (= default-message actual))))
  (t/testing "admin user"
    (create-user {:email "han@skywalker.com"  :pwd "123456789"})
    (let [_  (e/click driver {:tag :button})
          actual (e/get-element-text driver {:class :haggadah-styles-level1})]
      (t/is (= admin-login-message actual)))))
