(ns acceptance.admin-login-test
  (:require  [clojure.test :as t]
             [environ.core :refer [env]]
             [etaoin.api :as e]
             [clojure.string :as str]
             [cheshire.core :as json]
             [googlecredentials.core :as gcred])
  (:import com.google.firebase.FirebaseApp
            com.google.firebase.FirebaseOptions
            com.google.firebase.FirebaseOptions$Builder
            [com.google.auth.oauth2 GoogleCredentials ServiceAccountCredentials]
            [java.io IOException]
            com.google.auth.oauth2.ServiceAccountCredentials
            com.google.firebase.auth.FirebaseAuth
            com.google.firebase.auth.FirebaseAuthException
            com.google.firebase.auth.UserRecord
            com.google.firebase.auth.UserRecord$CreateRequest
            com.google.firebase.auth.UserRecord$UpdateRequest))

(println "Hello how are you"(System/getenv "GCLOUD_PROJECT"))

(def driver (e/firefox-headless))

(def default-message
  "Hello from (Unknown). This is the Home Page.We're glad to see you.")

(def admin-login-message
"Hello from han@skywalker.com. This is the Home Page.We're glad to see you.")

;; (e/go driver "http://localhost:8280/")


(println "Hello how are you"(System/getenv "GCLOUD_PROJECT"))

(defn- clean-env-var [env-var]
  (-> env-var (name) (str) (str/lower-case) (str/replace "_" "-") (str/replace "." "-") (keyword)))

(defn- build-service-account-credentials [creds]
  (let [credentials (ServiceAccountCredentials/fromPkcs8
                     ^String (-> creds :client_id str)
                     ^String (-> creds :client_email str)
                     ^String (-> creds :private_key str)
                     ^String (-> creds :private_key_id str)
                     [])]
    (-> (^ServiceAccountCredentials .toBuilder credentials)
        (.setProjectId (-> creds :project_id str))
        (^ServiceAccountCredentials .build))))

(defn ^GoogleCredentials load-service-credentials 
  "Load google application credentials from environment variable defaults to  GOOGLE_APPLICATION_CREDENTIALS"
  ([]
   (load-service-credentials "GOOGLE_APPLICATION_CREDENTIALS"))  
  ([^String env-var]
   (let [clean-var (clean-env-var env-var)
         creds (json/parse-stream-strict (env clean-var) true)]
     (cond
       (empty? creds)
       (throw (IOException. ^String (str "Environment variable " env-var " is empty or does not exist")))
       (empty? (:type creds))
       (throw (IOException. ^String (str "Error reading credentials from stream, 'type' field not specified.")))
       (= ^String "service-account-type" (:type creds))
       (build-service-account-credentials creds) 
       :else (throw (IOException. ^String (str "Error reading credentials from stream, 'type' value '" (:type creds) "' not recognized."
                                               "Expecting '" "user-file-type" "' or '" "service-account-type" "'.")))))))9


(defn- build-firebase-options 
  ([]
   (try 
     (-> (new FirebaseOptions$Builder) ;use thread-first when the final part of the function will return value to be used
         (.setCredentials (load-service-credentials))
         (.build))
     (catch Exception e (println "\nError: FIREBASE_CONFIG/GOOGLE_APPLICATION_CREDENTIALS AND GOOGLE_CLOUD_PROJECT environment variables must both be set"))))
  ([database-name]
   (try 
     (-> (new FirebaseOptions$Builder) ;use thread-first when the final part of the function will return value to be used
         (.setCredentials ^ServiceAccountCredentials (gcred/load-service-credentials))
         (.setDatabaseUrl (str "https://" database-name ".firebaseio.com"))
         (.build))
     (catch Exception e (println "\nError: FIREBASE_CONFIG/GOOGLE_APPLICATION_CREDENTIALS AND GOOGLE_CLOUD_PROJECT environment variables must both be set")))))

(defn init []
  (let [database-name (or (env :firebase-database) (env :google-cloud-project))]
    (if (nil? database-name)
      (try
        (. FirebaseAuth getInstance) 
        (catch IllegalStateException ise
          (let [options (build-firebase-options)]
            (. FirebaseApp initializeApp options)))))
    (try
      (. FirebaseAuth getInstance) 
      (catch IllegalStateException ise
        (let [options (build-firebase-options database-name)]
          (. FirebaseApp initializeApp options))))))




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
    (println "Hello how are you"(System/getenv "GCLOUD_PROJECT"))
    (init)   
    (create-user {:email "han@skywalker.com"  :pwd "123456789"})
    (let [_  (e/click driver {:tag :button})
          actual (e/get-element-text driver {:class :haggadah-styles-level1})]
      (t/is (= admin-login-message actual)))))
