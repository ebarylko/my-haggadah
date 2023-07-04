(ns acceptance.create-haggadah-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [acceptance.core :as c :refer [driver]] 
             [etaoin.keys :as k]))


#_(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)


(def new-haggadah-title "The best haggadah of the year")
(def new-haggadah-text "## We begin in Egypt")
(def parsed-haggadah-text  "We begin in Egypt")

(defn home->dashboard
  [d]
  (doto d
   (e/go "http://localhost:5000/")
   (e/click-visible {:data-testid :login})
   (e/click-visible {:data-testid :submit})
   (e/wait-visible {:data-testid :create-haggadah})
   (e/screenshot  "screenshots/create-haggadah-test-admin-exists-before-clicking-create.png")))

(defn create-haggadah
  [d title text]
  (doto d
   (e/click-visible {:data-testid :create-haggadah})
   (e/wait-visible {:data-testid :haggadah-title})
   (e/screenshot  "screenshots/create-haggadah-test-admin-exists-after-clicking-create.png")
   (e/fill  {:data-testid :haggadah-title} k/home (k/with-shift k/end) k/delete)
   (e/fill {:data-testid :haggadah-title} title)
   (e/fill {:data-testid :haggadah-text} k/home (k/with-shift k/end) k/delete)
   (e/fill {:data-testid :haggadah-text} text)
   (e/screenshot "screenshots/create-haggadah-test-admin-exists-before-creating-haggadah.png")
   (e/click-visible {:data-testid :add-haggadah})
   (e/click-visible {:data-testid :return})))

(defn click-on-haggadah
  [d title text]
  (doto d
   (e/screenshot "screenshots/create-haggadah-test-admin-exists-before-clicking-haggadah.png")
   (e/click-visible {:fn/text title})
   (e/wait-has-text-everywhere text)
   (e/screenshot "screenshots/create-haggadah-test-admin-exists-haggadah-text.png")))

(t/deftest create-haggadah-test
  (t/testing "When the current user creates a new haggadah"
    (let [_ (home->dashboard driver)
          _ (create-haggadah driver new-haggadah-title new-haggadah-text)
          _ (click-on-haggadah driver new-haggadah-title parsed-haggadah-text)
          haggadah-text (e/get-element-text driver {:data-testid :haggadah-text})]
      (t/is (= parsed-haggadah-text haggadah-text)))))

(t/deftest refresh-page-test
  (t/testing "When the current user refreshes the haggadah"
    (let [_ (home->dashboard driver)
          _ (click-on-haggadah driver new-haggadah-title parsed-haggadah-text)
          _ (e/refresh driver)
          _ (e/wait-has-text-everywhere driver parsed-haggadah-text)
          haggadah-text (e/get-element-text driver {:data-testid :haggadah-text})]
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
    (e/click-visible {:fn/text (str "Edit " title)})
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
    (e/click-visible {:data-testid :return-dashboard})))


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
