(ns acceptance.core
  (:require [clojure.test :as t]
            [etaoin.api :as e]
            [clj-http.client :as http]
            [environ.core :refer [env]]
            [clojure.walk :as w])
  (:import com.google.firebase.FirebaseApp
           com.google.firebase.internal.EmulatorCredentials
           com.google.firebase.cloud.FirestoreClient
           com.google.firebase.FirebaseOptions$Builder
           com.google.firebase.auth.FirebaseAuth
           com.google.firebase.auth.UserRecord$CreateRequest))


(def project-id (env :gcloud-project))

(def driver-config
  {:args ["no-sandbox"
          "--disable-dev-shm-usage"
          "--disable-gpu"
          "--disable-extensions"
          "--start-maximized"]})

(def driver (e/chrome-headless
             (case (System/getProperty "os.name")
               "linux" driver-config
               :else (conj driver-config :path-browser "/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"))))

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

(defonce test-names (atom []))

(defmethod clojure.test/report :begin-test-var [m]
  (swap! test-names conj (-> m :var meta :name)))

(defn with-screenshot
  "Pre: takes a test
  Post: generates a screenshot after running the test"
  [test]
  (try
    (test)
    (finally
      (e/screenshot driver
                    (format "screenshots/%s.png" (last @test-names))))))

(defn delete-fs-emulator-data
  "Takes a test and deletes what is in firestore after running the test"
  [test]
  (test)
  (http/delete  "http://localhost:8080/emulator/v1/projects/my-haggadah/databases/(default)/documents"))

(defn home->dashboard
  [d]
  (doto d
    (e/go "http://localhost:5000/")
    (e/click-visible {:data-testid :login})
    (e/click-visible {:data-testid :submit})
    (e/wait-visible {:data-testid :create-haggadah})))


(defn fs-store-haggadah
  "Pre: takes a haggadah and a user
  Post: returns the id of the haggadah created"
  [haggadah user]
  (-> (FirestoreClient/getFirestore)
      (.collection "users")
      (.document user)
      (.collection "haggadot")
      (.add  (w/stringify-keys (assoc haggadah :createdAt (java.time.Instant/now))))
      (.get)
      (.getId)))

(defn haggadah
  "Pre: takes a user and an id for a Haggadah
  Post: returns the Haggadah which contains this id in firestore"
  [user id]
  (-> (FirestoreClient/getFirestore)
      (.collection "users")
      (.document user)
      (.collection "haggadot")
      (.document id)
      (.get)
      (.get)
      (.getData)))
