(ns acceptance.view-haggadah-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [acceptance.dashboard-actions :as d]
             [acceptance.haggadah-actions :as h]
             [acceptance.core :as c :refer [driver]]))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot c/delete-fs-emulator-data)

(defn click-on-haggadah
  [d text]
  (doto d
    (e/click-visible {:data-testid :haggadah-link} {:timeout 15})
    (e/wait-has-text-everywhere text {:timeout 15})))

(def expected-haggadah-title
  "haggadah2023")

(def actual-haggadah-text
  "Amir's Haggadah")

(defn haggadah-content
  []
  (e/get-element-text driver {:fn/has-class :content}))

(t/deftest view-haggadah-from-dashboard
  (t/testing "When the current user has a haggadah and is at the dashboard, they should be able to view the the selected haggadah"
    (let [id (c/fs-store-haggadah
              {:title "haggadah2023"}
              "user1")]
      (c/fs-store-haggadah-content {:type "bracha" :title "Amir's Haggadah" :text "" :path "haggadah/Bracha" :order 1})
      (doto driver
        (c/home->dashboard)
        (click-on-haggadah actual-haggadah-text))
      (let [haggadah-title (h/haggadah-title)
            haggadah-content (haggadah-content)
            expected-title "haggadah2023"
            expected-content "Amir's Haggadah"]
        (t/are [x y] (= x y)
          expected-title haggadah-title
          expected-content haggadah-content)))))


(def title "Wine")

(def hebrew-bracha "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")
(def english-bracha "Blessed are You, Lord our God, King of the universe, who creates the fruit of the vine.")

(t/deftest view-haggadah-with-bracha
  (t/testing "When the current user has a haggadah with a bracha in it and is at their dashboard, they should be able to view the haggadah and see it in a certain way"
    (c/fs-store-haggadah-content {:type "bracha" :title title :hebrew hebrew-bracha :english english-bracha :path "haggadah/Bracha" :order 1})
    (doto driver
      (c/home->dashboard)
      (d/create-haggadah title)
      (click-on-haggadah hebrew-bracha))
    (let [actual-title (h/bracha-title)
          actual-hebrew-bracha  (h/bracha-hebrew-content)
          actual-english-bracha (h/bracha-english-content)]
      (t/are [x y] (= x y)
        title actual-title
        hebrew-bracha actual-hebrew-bracha
        english-bracha actual-english-bracha))))

(t/deftest refresh-page-test
  (t/testing "When the current user refreshes the haggadah"
    (c/fs-store-haggadah {:title "The best haggadah of the year"}
                         "user1")
    (c/fs-store-haggadah-content {:type "bracha" :title title :hebrew hebrew-bracha :english english-bracha :path "haggadah/Bracha" :order 1})
    (doto driver
      (c/home->dashboard)
      (click-on-haggadah hebrew-bracha)
      (e/refresh)
      (e/wait-has-text-everywhere hebrew-bracha))
    (let [haggadah-title (h/haggadah-title)
          bracha-title (h/bracha-title)
          actual-hebrew-bracha (h/bracha-hebrew-content)
          actual-english-bracha (h/bracha-english-content)
          expected-haggadah-title "The best haggadah of the year"]
      (t/are [x y] (= x y)
        expected-haggadah-title haggadah-title
        english-bracha actual-english-bracha
        hebrew-bracha actual-hebrew-bracha
        title bracha-title))))
