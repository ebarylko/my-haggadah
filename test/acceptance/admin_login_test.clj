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
  "Hello han@skywalker.com. Welcome to your dashboard. To make a new haggadah, click the button to your right. To share and edit your existing haggadah, look at your haggadot below")



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

(defn with-screenshot
  "Pre: takes a test
  Post: generates a screenshot after running the test"
  [test]
  (test)
  #_(try
    (test)
    (finally (e/screenshot driver the-name-of-the-test) ))
  )

(t/use-fixtures :once init-firebase)
(t/use-fixtures :each with-screenshot)

(t/deftest message-test
  (t/testing "When the admin user exists"
    (doto driver
      (e/go "http://localhost:5000/")
      (e/screenshot "Screenshots/message-test-home-page.png")
      (e/click-visible {:tag :a :data-test-id "login"})
      (e/click-visible {:tag :a :id "submit"})
      (e/screenshot "screenshots/message-test-dashboard.png")
      (e/wait-has-text-everywhere admin-login-message))
    (let [actual (e/get-element-text driver {:id "user"})]
      (e/screenshot driver "screenshots/message-test-when-the-admin-exists.png")
      (t/is (= admin-login-message actual)))))

(def default-haggadah-text
  "The default haggadah")

(def haggadah-title
  "haggadah2023")

(def actual-haggadah-text
  "Amir's Haggadah")

(t/deftest show-text-test
  (t/testing "When the current user has a haggadah"
    (let [db (FirestoreClient/getFirestore)
          haggadah {"content" "## Amir's Haggadah"
                    "title" "haggadah2023"}
          write (-> db
                     (.collection "users")
                     (.document "user1")
                     (.collection "haggadot")
                     (.add haggadah)
                     (.get))
          _ (doto driver
              (e/go "http://localhost:5000/")
              (e/click-visible {:tag :a :data-test-id "login"})
              (e/click-visible {:tag :a :id "submit"})
              (e/screenshot  "screenshots/show-text-test-admin-exists-haggaddah-exists-before-clicking-haggadah.png")
              (e/click-visible  {:tag :a :fn/text haggadah-title})
              (e/wait-visible  {:tag :div :id "haggadah-text"})
              (e/screenshot "screenshots/show-text-test-admin-exists-haggadah-exists-after-clicking-haggadah"))
          haggadah-text (e/get-element-text driver {:tag :div :id "haggadah-text"})]

      (t/is (= actual-haggadah-text haggadah-text)))))

;; "http://localhost:8080/emulator/v1/projects/firestore-emulator-example/databases/(default)/documents"

