(ns acceptance.create-haggadah-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [acceptance.core :as c :refer [driver]] 
             [acceptance.view-haggadah-test :as vh]
             [etaoin.keys :as k]))


(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)


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

(def new-haggadah-title "The best haggadah of the year")
(def new-haggadah-text "## We begin in Egypt")
(def parsed-haggadah-text  "We begin in Egypt")


(t/deftest create-haggadah-test
  (t/testing "When the current user creates a new haggadah"
    (let [id (c/create-haggadah {:title new-haggadah-title
                                 :content new-haggadah-text} "user1")]
    (doto driver
      (c/home->dashboard)
      (vh/click-on-haggadah id parsed-haggadah-text))
    (let [haggadah-text (vh/haggadah-content driver)]
      (t/is (= parsed-haggadah-text haggadah-text))))))

(t/deftest refresh-page-test
  (t/testing "When the current user refreshes the haggadah"

    (let [id (c/create-haggadah {:title new-haggadah-title
                                 :content new-haggadah-text} "user1")]
     (doto driver
       (c/home->dashboard)
       (vh/click-on-haggadah id parsed-haggadah-text)
       (e/refresh)
       (e/wait-has-text-everywhere parsed-haggadah-text))
     (let [haggadah-text (vh/haggadah-content driver)]
       (t/is (= parsed-haggadah-text haggadah-text))))))

(def unparsed-bracha "### סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")
(def haggadah-with-bracha-title "Haggadah with a bracha")
(def parsed-bracha "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")




