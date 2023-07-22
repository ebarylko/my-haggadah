(ns acceptance.view-haggadah-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [environ.core :refer [env]]
             [acceptance.core :as c :refer [driver]]
             [etaoin.keys :as k]))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)
(t/use-fixtures :each c/delete-fs-emulator-data)

(defn click-on-haggadah
  [d text]
  (doto d
    (e/click-visible {:data-testid :haggadah-link} {:timeout 15})
    (e/wait-has-text-everywhere text {:timeout 15})))

(def expected-haggadah-title
  "haggadah2023")

(def actual-haggadah-text
  "Amir's Haggadah")

(defn haggadah-title
  []
  (e/get-element-text driver {:fn/has-class :title}))

(defn haggadah-content
  []
  (e/get-element-text driver {:fn/has-class :content}))

(t/deftest view-haggadah-from-dashboard
  (t/testing "When the current user has a haggadah and is at the dashboard, they should be able to view the the selected haggadah"
    (let [id (c/create-haggadah
              {:title "haggadah2023"
               :type "haggadah"
               :content [{:type "bracha" :title "Amir's Haggadah" :text ""} ]}
              "user1")]
      (doto driver
        (c/home->dashboard)
        (click-on-haggadah actual-haggadah-text))
      (let [haggadah-title (haggadah-title)
            haggadah-content (haggadah-content)
            expected-title "haggadah2023"
            expected-content "Amir's Haggadah"]
        (t/are [x y] (= x y)
          expected-title haggadah-title
          expected-content haggadah-content)))))

(def title "Wine")

(def bracha "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")

(defn bracha-title
  []
  (e/get-element-text driver {:css "div.bracha>div.title" }))

(defn bracha-content
  []
  (e/get-element-text driver {:css "div.bracha>div.text"}))

(defn create-haggadah
  [d title]
  (doto d
    (e/click-visible {:data-testid :create-haggadah})
    (e/wait-visible {:data-testid :haggadah-title})
    (e/fill  {:data-testid :haggadah-title} k/home (k/with-shift k/end) k/delete)
    (e/fill {:data-testid :haggadah-title} title)
    (e/click-visible {:data-testid :add-haggadah})
    (e/click-visible {:data-testid :return})))

(t/deftest view-haggadah-with-bracha
  (t/testing "When the current user has a haggadah with a bracha in it and is at their dashboard, they should be able to view the haggadah and see it in a certain way"
    (doto driver
      (c/home->dashboard)
      (create-haggadah title)
      (click-on-haggadah bracha))
    (let [actual-title (bracha-title)
          actual-bracha  (bracha-content)]
      (t/are [x y] (= x y)
        title actual-title
        bracha actual-bracha))))



(def expected-haggadah-title "The best haggadah of the year")

(t/deftest refresh-page-test
  (t/testing "When the current user refreshes the haggadah"
    (c/create-haggadah {:title expected-haggadah-title
                        :type "haggadah"
                        :content [{:type "bracha" :text bracha :title title}]}
                       "user1")
    (doto driver
      (c/home->dashboard)
      (click-on-haggadah bracha)
      (e/refresh)
      (e/wait-has-text-everywhere bracha))
    (let [haggadah-title (haggadah-title)
          bracha-title (bracha-title)
          bracha-content (bracha-content)]
      (t/are [x y] (= x y)
        expected-haggadah-title haggadah-title
        bracha bracha-content
        title bracha-title))))
