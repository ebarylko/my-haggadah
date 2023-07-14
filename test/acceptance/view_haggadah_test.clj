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
    (e/screenshot "screenshots/create-haggadah-test-admin-exists-before-clicking-haggadah.png")
    (e/click-visible {:data-testid :haggadah-link} {:timeout 15})
    (e/screenshot "screenshots/edit-haggadah-test-viewing-edited-haggadah.png")
    (e/wait-has-text-everywhere text {:timeout 15})
    (e/screenshot "screenshots/create-haggadah-test-admin-exists-haggadah-text.png")))

(def haggadah-title
  "haggadah2023")

(def actual-haggadah-text
  "Amir's Haggadah")

(defn haggadah-content
  [d]
  (e/get-element-text d {:data-testid :haggadah-text}))

(t/deftest view-haggadah-from-dashboard
  (t/testing "When the current user has a haggadah and is at the dashboard, they should be able to view the the selected haggadah"
    (let [id (c/create-haggadah
              {:content {:bracha  {:content "Amir's Haggadah" } }
               :title "haggadah2023"}
              "user1")]
    (doto driver
      (c/home->dashboard)
      (click-on-haggadah actual-haggadah-text)
      (e/screenshot "screenshots/show-text-test-admin-exists-haggadah-exists-after-clicking-haggadah"))
    (let [haggadah-text (haggadah-content driver)]
      (t/is (= actual-haggadah-text haggadah-text))))))

(def title "Wine")

(def bracha "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")

(defn bracha-title
  []
  (e/get-element-text driver {:data-testid :bracha-title :fn/has-classes [:has-text-centered :has-text-weight-bold :is-size-3 :pb-2]}))

(defn bracha-content
  []
  (e/get-element-text driver {:data-testid :bracha-content :fn/has-classes [:has-text-right :is-size-5]}))

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


(def parsed-haggadah-text  "We begin in Egypt")

(def new-haggadah-title "The best haggadah of the year")

(t/deftest refresh-page-test
  (t/testing "When the current user refreshes the haggadah"

      (c/create-haggadah {:title new-haggadah-title
                          :content {:bracha {:content parsed-haggadah-text}}} "user1")
      (doto driver
        (c/home->dashboard)
        (click-on-haggadah parsed-haggadah-text)
        (e/refresh)
        (e/wait-has-text-everywhere parsed-haggadah-text))
      (let [haggadah-text (haggadah-content driver)]
        (t/is (= parsed-haggadah-text haggadah-text)))))
