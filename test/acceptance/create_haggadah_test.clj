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


(t/deftest create-haggadah-test
  (t/testing "When the current user creates a new haggadah"
    (doto driver
      (c/home->dashboard)
      (c/create-haggadah new-haggadah-title new-haggadah-text)
      (c/click-on-haggadah new-haggadah-title parsed-haggadah-text))
    (let [haggadah-text (e/get-element-text driver {:data-testid :haggadah-text})]
      (t/is (= parsed-haggadah-text haggadah-text)))))

(t/deftest refresh-page-test
  (t/testing "When the current user refreshes the haggadah"
    (doto driver
      (c/home->dashboard)
      (c/click-on-haggadah new-haggadah-title parsed-haggadah-text)
      (e/refresh)
      (e/wait-has-text-everywhere parsed-haggadah-text))
    (let [haggadah-text (e/get-element-text driver {:data-testid :haggadah-text})]
      (t/is (= parsed-haggadah-text haggadah-text)))))

(def unparsed-bracha "### סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")
(def haggadah-with-bracha-title "Haggadah with a bracha")
(def parsed-bracha "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")

(t/deftest bracha-rendered-test
  (t/testing "When the current user creates a haggadah with a bracha"
    (doto driver
     (c/home->dashboard)
     (c/create-haggadah haggadah-with-bracha-title unparsed-bracha)
     (c/click-on-haggadah haggadah-with-bracha-title parsed-bracha)
     (e/wait-has-text-everywhere parsed-bracha)
     (e/screenshot "screenshots/bracha-rendered-test-bracha-is-visible"))
    (let [bracha (e/get-element-text driver {:tag :h3 :fn/text parsed-bracha})]
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
          parsed-haggadah-text "This is the edited text"]
      (doto driver
        (c/home->dashboard)
        (c/create-haggadah haggadah-title unedited-text)
        (dashboard->edit-page haggadah-title unedited-text)
        (edit-haggadah haggadah-text)
        (c/click-on-haggadah haggadah-title parsed-haggadah-text)
        (e/wait-has-text-everywhere parsed-haggadah-text)
        (e/screenshot "screenshots/edit-haggadah-test-haggadah-has-been-edited.png"))
      (let [edited-haggadah (e/get-element-text driver {:tag :h4 :fn/text parsed-haggadah-text}) ]
        (t/is (= parsed-haggadah-text edited-haggadah))))))
