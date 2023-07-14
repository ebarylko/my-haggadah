(ns acceptance.dashboard-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]
             [acceptance.core :as c :refer [driver]] 
             [tupelo.misc :as tu]
             [etaoin.keys :as k]
             )
  (:import
   com.google.firebase.cloud.FirestoreClient
   com.google.cloud.firestore.Query$Direction))


(defn delete-fs-emulator-data
  "Takes a test and deletes what is in firestore after running the test"
  [test]
  (test)
  (tu/shell-cmd "curl -v -X DELETE 'http://localhost:8080/emulator/v1/projects/my-haggadah/databases/(default)/documents'"))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot)
(t/use-fixtures :each delete-fs-emulator-data)

(defn open-edit-haggadah
  [d id text]
  (doto d
    (e/click-visible {:data-testid (str "edit-" id)})
    (e/wait-has-text-everywhere text)))

(defn link-and-title
  [haggadah]
  (let [title (e/get-element-text-el driver haggadah)
        link (e/get-element-attr-el driver haggadah :href)
        id (second
            (clojure.string/split link #"dashboard/") )]
    {:title title :id id}))

(defn all-haggadot
  []
  (let [haggadot (e/query-all driver {:data-testid :haggadah-link})]
    (map link-and-title haggadot)))


(def dashboard-message
  "Hello han@skywalker.com. Welcome. To make a new Haggadah, click the button to your right. To share and edit your existing Haggadah, look at your Haggadot below ")

(defn create-haggadah
  [d title]
  (doto d
    (e/click-visible {:data-testid :create-haggadah})
    (e/wait-visible {:data-testid :haggadah-title})
    (e/fill  {:data-testid :haggadah-title} k/home (k/with-shift k/end) k/delete)
    (e/fill {:data-testid :haggadah-title} title)
    (e/click-visible {:data-testid :add-haggadah})
    (e/click-visible {:data-testid :return})))

(def new-haggadah-title "The best haggadah of the year")

(defn get-id
  [doc]
  (.getId doc))

(defn latest-haggadah?
  "Pre: takes a user and a Haggadah id
  Post: returns true if the id of the latest Haggadah in firestore matches the id of the user's first Haggadah"
  [user id]
  (-> (FirestoreClient/getFirestore)
      (.collection "users")
      (.document user)
      (.collection "haggadot")
      (.orderBy "createdAt" Query$Direction/DESCENDING)
      (.limit 1)
      (.get)
      (.get)
      (.getDocuments)
      (#(map get-id %))
      first
      (= id)))

(t/deftest create-haggadah-test
  (t/testing "When the current user creates a new Haggadah and goes back to the dashboard, the Haggadah is listed first among the Haggadot and a new Haggadah with the same details is added to firestore"
    (doto driver
      (c/home->dashboard)
      (create-haggadah new-haggadah-title)
      (e/screenshot "screenshots/create-haggadah-test-before-getting-haggadot.png"))
    (let [[title id]  (->> (all-haggadot)
                      first
                      vals)
          latest? (latest-haggadah? "user1" id )]
      (t/are [x y] (= x y)
        new-haggadah-title title
        true latest?))))

(def haggadot
  [{:content {:bracha  {:content "Amir's Haggadah" } }
    :title "Third"}
   {:content {:bracha  {:content "Amir's Haggadah" } }
    :title "Second"}
   {:content {:bracha  {:content "Amir's Haggadah" } }
    :title "First"}])

(def haggadah-1
  {:content {:bracha  {:content "Amir's Haggadah" } } :title "Third"})

(def haggadah-2
  {:content {:bracha  {:content "Amir's Haggadah" } }
   :title "Second"})

(def haggadah-3
  {:content {:bracha  {:content "Amir's Haggadah" } }
   :title "First"})

(defn haggadot-titles
  "Pre: takes a collection of Haggadot
  Post: returns the title of every Haggadot"
  [haggadot]
 (map (comp val first) haggadot))

(t/deftest view-haggadot-ordered-test
  (t/testing "When the current user has already made Haggadot and goes to their dashboard, the Haggadot should be displayed in order from most recent to least recent"
    (c/create-haggadah haggadah-1 "user1")
    (c/create-haggadah haggadah-2 "user1")
    (c/create-haggadah haggadah-3 "user1")
    (doto driver
      (c/home->dashboard))
    (let [titles (haggadot-titles (all-haggadot))]
      (t/is (= ["First" "Second" "Third"] titles)))))

(def new-haggadah-text "## We begin in Egypt")

