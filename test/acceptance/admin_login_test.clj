(ns acceptance.admin-login-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [environ.core :refer [env]])
  (:import com.google.firebase.FirebaseApp
           com.google.firebase.internal.EmulatorCredentials
           com.google.firebase.cloud.FirestoreClient
            com.google.cloud.firestore.Firestore
            com.google.firebase.FirebaseOptions$Builder
            com.google.firebase.auth.FirebaseAuth
            com.google.firebase.auth.UserRecord$CreateRequest
            ))

(def project-id (env :gcloud-project))

(def driver (e/chrome-headless { :args ["no-sandbox" "--disable-dev-shm-usage" "--disable-gpu" "--disable-extensions"  "--start-maximized" ] }))
(def default-message
  "Hello (Unknown). We're glad to see you.")

(def admin-login-message
  "Hello han@skywalker.com. We're glad to see you.")


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

(defn init-firebase
  "Pre: takes all tests
  Post: initializes all features of firebase, such as firestore, authentication,"
  [tests]
  (init)
  (create-user {:email "han@skywalker.com"  :pwd "123456789" :id "user1"})
  (tests))

(t/use-fixtures :once init-firebase)


(t/deftest message-test
  (t/testing "When the admin user exists"
    (doto driver
      (e/go "http://localhost:5000/")
      (e/screenshot "Screenshots/message-test-home-page.png")
      (e/click-visible {:tag :button :data-test-id "login"})
      (e/click-visible {:tag :button :id "load-haggadah"})
      (e/wait-has-text-everywhere admin-login-message))
    (let [actual (e/get-element-text driver {:id "user"})]
      (e/screenshot driver "screenshots/message-test-when-the-admin-exists.png")
      (t/is (= admin-login-message actual)))))

(def default-haggadah-text
  "The default haggadah")

(def actual-haggadah-text
  "This is Amir's excellent Haggadah")

(t/deftest show-text-test
  (t/testing "When the current user has a haggadah"
    (let [db (FirestoreClient/getFirestore)
          haggadah {"haggadah-text" "## This is Amir's excellent Haggadah"}
          write (-> db
                     (.collection "users")
                     (.document "user1")
                     (.set haggadah)
                     (.get) #_(#(println (.getData %)))
                     )
          exists (-> db
                     (.collection "users")
                     (.document "user1")
                     (.get)
                     (.get)
                     (#(println "The document exists? "(.exists %))))
          _ (doto driver
              (e/go "http://localhost:5000/")
              (e/click-visible {:tag :button :data-test-id "login"})
              (e/click-visible {:tag :button :id "load-haggadah"}))
          _ (e/wait-has-text-everywhere driver actual-haggadah-text)
          _ (try (e/wait-has-text-everywhere driver actual-haggadah-text)
                 (catch Exception e (prn (str "Timeout error: " (.getMessage e) )))
                 (finally (e/screenshot driver "screenshots/show-text-test-when-the-admin-exists-when-haggaddah-exists.png")))
          haggadah-text (e/get-element-text driver {:tag :div :id "haggadah-text"})]

      (t/is (= actual-haggadah-text haggadah-text)))))

;; "http://localhost:8080/emulator/v1/projects/firestore-emulator-example/databases/(default)/documents"

