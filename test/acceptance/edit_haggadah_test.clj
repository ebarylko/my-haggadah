(ns acceptance.edit-haggadah-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [acceptance.core :as c :refer [driver]] 
             [etaoin.keys :as k]
             [acceptance.dashboard-test :as dash]
             [acceptance.view-haggadah-test :as h]))

(def preview-field {:data-testid :preview})


(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)

(def submit-changes {:data-testid :submit})
(def return-to-dashboard {:data-testid :return-dashboard})

(defn edit-haggadah
  [d text]
  (doto d
    (e/screenshot "screenshots/edit-haggadah-test-edit-page.png")
    (e/click-visible preview-field)
    (e/fill preview-field k/home (k/with-shift k/end) k/delete)
    (e/fill-human preview-field text {:mistake-prob 0})
    (e/screenshot "screenshots/edit-test-editing-haggadah.png")
    (e/click-visible submit-changes)
    (e/click-visible return-to-dashboard)))

(t/deftest edit-haggadah-test
  (t/testing "When the current user edits an existing haggadah"
    (let [haggadah-title "Unique Edited haggadah"
          unedited-text "## This is unedited"
          haggadah-text "#### This is the edited text"
          parsed-haggadah-text "This is the edited text"
          id (c/create-haggadah {:title haggadah-title :content unedited-text} "user1")]
      (doto driver
        (c/home->dashboard)
        (dash/open-edit-haggadah id unedited-text)
        (edit-haggadah haggadah-text)
       (h/click-on-haggadah id parsed-haggadah-text)
       (e/wait-has-text-everywhere parsed-haggadah-text))
      (let [edited-haggadah (e/get-element-text driver {:tag :h4 :fn/text parsed-haggadah-text}) ]
        (t/is (= parsed-haggadah-text edited-haggadah))))))
