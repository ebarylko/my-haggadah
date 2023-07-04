(ns acceptance.core
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [environ.core :refer [env]]
             [clojure.walk :as w]
             [etaoin.keys :as k])
  (:import com.google.firebase.FirebaseApp
           com.google.firebase.internal.EmulatorCredentials
           com.google.firebase.cloud.FirestoreClient
           com.google.cloud.firestore.Firestore
           com.google.firebase.FirebaseOptions$Builder
           com.google.firebase.auth.FirebaseAuth
           com.google.firebase.auth.UserRecord$CreateRequest))


(def project-id (env :gcloud-project))

(def driver (e/chrome-headless { :args ["no-sandbox" "--disable-dev-shm-usage" "--disable-gpu" "--disable-extensions"  "--start-maximized" ] }))

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
  [{:keys [email pwd id]}]
  (let [firebase-auth (. FirebaseAuth getInstance)
        create-request (doto (new UserRecord$CreateRequest)
                         (.setEmail email)
                         (.setUid id)
                         (.setPassword pwd))]
    (convert-user-record-to-map (. firebase-auth createUser create-request))))

(defonce firebase (atom nil))

(defn init-firebase
  "Pre: takes all tests
  Post: initializes all features of firebase, such as firestore, authentication,"
  [tests]
  (when-not @firebase
    (reset! firebase (init))
   (create-user {:email "han@skywalker.com"  :pwd "123456789" :id "user1"}))
  (tests))

(defn with-screenshot
  "Pre: takes a test
  Post: generates a screenshot after running the test"
  [test]
  (test)
  #_(try
    (test)
    (finally (e/screenshot driver (format "screenshots/%" (inst-ms (java.time.Instant/now)))) )))


(defn home->dashboard
  [d]
  (doto d
    (e/go "http://localhost:5000/")
    (e/click-visible {:data-testid :login})
    (e/click-visible {:data-testid :submit})
    (e/wait-visible {:data-testid :create-haggadah})
    (e/screenshot  "screenshots/create-haggadah-test-admin-exists-before-clicking-create.png")))


(defn create-haggadah
  "Pre: takes a haggadah and a user
  Post: returns the id of the haggadah created"
  [haggadah user]
  (-> (FirestoreClient/getFirestore)
      (.collection "users")
      (.document user)
      (.collection "haggadot")
      (.add  (w/stringify-keys haggadah))
      (.get)
      (.getId)))
