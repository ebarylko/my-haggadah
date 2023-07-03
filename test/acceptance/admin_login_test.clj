(ns acceptance.admin-login-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [environ.core :refer [env]]
             [etaoin.keys :as k])
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
      (e/click-visible {:tag :a :data-testid "login"})
      (e/click-visible {:tag :a :data-testid "submit"})
      (e/screenshot "screenshots/message-test-dashboard.png")
      (e/wait-has-text-everywhere admin-login-message))
    (let [actual (e/get-element-text driver {:data-testid "user"})]
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
              (e/screenshot "screenshots/show-text-test-arriving-to-site.png")
              (e/click-visible {:tag :a :data-testid "login"})
              (e/click-visible {:tag :a :data-testid "submit"})
              (e/screenshot  "screenshots/show-text-test-admin-exists-haggaddah-exists-before-clicking-haggadah.png")
              (e/click-visible  {:tag :a :fn/text haggadah-title})
              (e/wait-visible  {:tag :div :data-testid "haggadah-text"})
              (e/screenshot "screenshots/show-text-test-admin-exists-haggadah-exists-after-clicking-haggadah"))
          haggadah-text (e/get-element-text driver {:tag :div :data-testid "haggadah-text"})]

      (t/is (= actual-haggadah-text haggadah-text)))))

(def new-haggadah-title "The best haggadah of the year")
(def new-haggadah-text "## We begin in Egypt")
(def parsed-haggadah-text  "We begin in Egypt")

(defn home->dashboard
  [d]
  (doto d
   (e/go "http://localhost:5000/")
   (e/click-visible {:tag :a :data-testid "login"})
   (e/click-visible {:tag :a :data-testid "submit"})
   (e/wait-visible {:tag :a :data-testid "create-haggadah"})
   (e/screenshot  "screenshots/create-haggadah-test-admin-exists-before-clicking-create.png")))

(defn create-haggadah
  [d title text]
  (doto d
   (e/click-visible {:tag :a :data-testid "create-haggadah"})
   (e/wait-visible {:tag :input :data-testid "haggadah-title"})
   (e/screenshot  "screenshots/create-haggadah-test-admin-exists-after-clicking-create.png")
   (e/fill  {:tag :input :data-testid "haggadah-title"} k/home (k/with-shift k/end) k/delete)
   (e/fill {:tag :input :data-testid "haggadah-title"} title)
   (e/fill {:tag :textarea :data-testid "haggadah-text"} k/home (k/with-shift k/end) k/delete)
   (e/fill {:tag :textarea :data-testid "haggadah-text"} text)
   (e/screenshot "screenshots/create-haggadah-test-admin-exists-before-creating-haggadah.png")
   (e/click-visible {:tag :a :data-testid "add-haggadah"})
   (e/click-visible {:tag :a :data-testid "return-dashboard"})))

(defn click-on-haggadah
  [d title text]
  (doto d
   (e/screenshot "screenshots/create-haggadah-test-admin-exists-before-clicking-haggadah.png")
   (e/click-visible {:tag :a :fn/text title})
   #(println "it exists " (e/exists? % {:tag :h4 :fn/text text}))
   (e/wait-has-text-everywhere text)
   (e/screenshot "screenshots/create-haggadah-test-admin-exists-haggadah-text.png")))

(t/deftest create-haggadah-test
  (t/testing "When the current user creates a new haggadah"
    (let [_ (home->dashboard driver)
          _ (create-haggadah driver new-haggadah-title new-haggadah-text)
          _ (click-on-haggadah driver new-haggadah-title parsed-haggadah-text)
          haggadah-text (e/get-element-text driver {:tag :div :data-testid "haggadah-text"})]
      (t/is (= parsed-haggadah-text haggadah-text)))))

(t/deftest refresh-page-test
  (t/testing "When the current user refreshes the haggadah"
    (let [_ (home->dashboard driver)
          _ (click-on-haggadah driver new-haggadah-title parsed-haggadah-text)
          _ (e/refresh driver)
          _ (e/wait-has-text-everywhere driver parsed-haggadah-text)
          haggadah-text (e/get-element-text driver {:data-testid "haggadah-text"})]
      (t/is (= parsed-haggadah-text haggadah-text)))))

(def unparsed-bracha "### סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")
(def haggadah-with-bracha-title "Haggadah with a bracha")
(def parsed-bracha "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")

(t/deftest bracha-rendered-test
  (t/testing "When the current user creates a haggadah with a bracha"
    (let [_ (home->dashboard driver)
          _ (create-haggadah driver haggadah-with-bracha-title unparsed-bracha)
          _ (click-on-haggadah driver haggadah-with-bracha-title parsed-bracha)
          _ (e/wait-has-text-everywhere driver parsed-bracha)
          _ (e/screenshot driver "screenshots/bracha-rendered-test-bracha-is-visible")
           bracha (e/get-element-text driver {:tag :h3 :fn/text parsed-bracha})]
      (t/is (= parsed-bracha bracha)))))
;; "http://localhost:8080/emulator/v1/projects/firestore-emulator-example/databases/(default)/documents"

(defn dashboard->edit-page
  [d title text]
  (doto d
    (e/click-visible {:tag :a :fn/text (str "Edit " title)})
    (e/wait-has-text-everywhere text)))

(defn edit-haggadah
  [d text]
  (doto d
    (e/screenshot "screenshots/edit-haggadah-test-edit-page.png")
    (e/wait-visible {:data-testid :preview})
    (e/fill  {:data-testid :preview} k/home (k/with-shift k/end) k/delete)
    (e/fill  {:data-testid :preview} text)
    (e/screenshot "screenshots/edit-test-editing-haggadah.png")
    (e/click-visible {:data-testid :submit})
    (e/click-visible {:data-testid :eturn-dashboard})))


(t/deftest edit-haggadah-test
  (t/testing "When the current user edits an existing haggadah"
    (let [haggadah-title "Edited haggadah"
          unedited-text "## This is unedited"
          haggadah-text "#### This is the edited text"
          parsed-haggadah-text "This is the edited text"
          _ (home->dashboard driver)
          _ (create-haggadah driver haggadah-title unedited-text)
          _ (dashboard->edit-page driver haggadah-title unedited-text)
          _ (edit-haggadah driver haggadah-text)
          _ (click-on-haggadah driver haggadah-title parsed-haggadah-text)
          _ (e/wait-has-text-everywhere driver parsed-haggadah-text)
          _ (e/screenshot driver "screenshots/edit-haggadah-test-haggadah-has-been-edited.png")
          edited-haggadah (e/get-element-text driver {:tag :h4 :fn/text parsed-haggadah-text})]
      (t/is (= parsed-haggadah-text edited-haggadah)))))
