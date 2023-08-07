(ns acceptance.dashboard-test
  (:require  [clojure.test :as t]
             [clojure.walk :as w]
             [etaoin.api :as e]
             [acceptance.dashboard-actions :as d]
             [acceptance.core :as c :refer [driver]] 
             [etaoin.keys :as k])
  (:import
   com.google.firebase.cloud.FirestoreClient
   com.google.cloud.firestore.Query$Direction))

(t/use-fixtures :once c/init-firebase)
(t/use-fixtures :each c/with-screenshot c/delete-fs-emulator-data)

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
  "Pre: takes nothing
  Post: returns a collection of every Haggadah on the page with its title and id"
  []
  (let [haggadot (e/query-all driver {:data-testid :haggadah-link})]
    (map link-and-title haggadot)))

(def dashboard-message
  "Hello han@skywalker.com. Welcome. To make a new Haggadah, click the button to your right. To share and edit your existing Haggadah, look at your Haggadot below ")

(def new-haggadah-title "The best haggadah of the year")

(defn get-id
  "Pre: takes a document
  Post: returns the id of the document"
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
      (d/create-haggadah new-haggadah-title))
    (d/wait-for-haggadot)
    (let [[title id]  (->> (all-haggadot)
                           first
                           vals)
          latest? (latest-haggadah? "user1" id )]
      (t/are [x y] (= x y)
        new-haggadah-title title
        true latest?))))

(def haggadot
  [{:type "haggadah" :content [{:type "bracha" :title "Amir's Haggadah"}] :title "Third"}
   {:type "haggadah" :content [{:type "bracha" :title "Amir's Haggadah"}] :title "Second"}
   {:type "haggadah" :content [{:type "bracha" :title "Amir's Haggadah"}] :title "First"}])

(defn haggadot-titles
  "Pre: takes a collection of Haggadot
  Post: returns the title of every Haggadot"
  [haggadot]
 (map (comp val first) haggadot))

(defn  create-haggadot
  [haggadot user]
  (doseq [haggadah haggadot]
    (c/fs-store-haggadah haggadah user)))

(t/deftest view-haggadot-ordered-test
  (t/testing "When the current user has already made Haggadot and goes to their dashboard, the Haggadot should be displayed in order from most recent to least recent"
    (create-haggadot haggadot "user1")
    (c/home->dashboard driver)
    (d/wait-for-haggadot)
    (let [titles (haggadot-titles (all-haggadot))]
      (t/is (= ["First" "Second" "Third"] titles)))))

(defn haggadah-path
  "Pre: takes a user and a Seder id
  Post: returns the location of the Haggadah used for the Seder"
  [user id]
  (println "This is the id " id) 
  (-> (FirestoreClient/getFirestore)
      (.collection "users")
      (.document user)
      (.collection "seders")
      (.document id)
      (.get)
      (.get)
      (.getString "haggadah-path")))

(defn create-seder
  "Pre: takes a title for the Seder
  Post: returns the id of the Seder created"
  [title]
  (doto driver
    (e/click-visible {:data-testid :create-seder})
    (e/wait-visible {:id :seder-title})
    (e/fill  {:id :seder-title} k/home (k/with-shift k/end) k/delete)
    (e/fill-human {:id :seder-title} title {:mistake-prob 0})
    (e/click {:data-testid :submit})))

(defn id-and-title
  "Pre: takes a Seder
  Post: returns the id and title of the seder in a map"
  [seder]
  (println "Here is the seder " seder)
  (let [title (e/get-element-text-el driver seder)
        id (e/get-element-attr-el driver seder :data-testid)]
    (println "Here is the title " title  " id " id)
    {:title title :id id}))

(defn all-sedarim
  "Pre: takes nothing
  Post: returns the ids and titles of every Seder on the dashboard"
  []
  (let [sedarim (e/query-all driver {:fn/has-class :seder-link})]
    (println "The sedarim " sedarim)
    (map id-and-title sedarim)))

(t/deftest create-seder-test
  (t/testing "When the current user has a Haggadah and creates a Seder which uses that Haggadah, a new Seder with the same Haggadah will appear in the user's collection of Sedarim"
    (let [id (c/fs-store-haggadah {:title "Haggadah 1"
                                   :type "haggadah"
                                   :content [{:type "bracha" :title "hello" :text "bracha"}]}
                                  "user1" )]
      (c/home->dashboard driver)
      (create-seder "The first Seder")
      (d/wait-for-sedarim)
      (let [expected-haggadah-path (format "users/user1/haggadot/%s" id)
            [_ seder-id] (->> (all-sedarim)
                              first
                              vals)
            haggadah-path (haggadah-path "user1" seder-id)]
        (t/is (= expected-haggadah-path haggadah-path))))))

(defn seder-link
  "Pre: takes nothing
  Post: returns the link of the Seder on the page"
  []
  (e/get-element-text driver {:id :share-seder}))

(t/deftest gen-seder-link-test
  (t/testing "When the current user has a Seder and generates the link for sharing the Seder, the link will point to the aforementioned Seder"
    (let [id (c/fs-store-haggadah {:title "Haggadah 1"
                                   :type "haggadah"
                                   :content [{:type "bracha" :title "hello" :text "bracha"}]}
                                  "user1" )
          seder-id (c/fs-store-seder "Haggadah 1" "user1" id)]
      (c/home->dashboard driver)
      (d/wait-for-sedarim)
      (d/dashboard->first-seder)
      (d/gen-seder-link)
      (let [seder-link (seder-link)
            expected-link (format "http://localhost:5000/#/seder/%s" seder-id)]
        (t/is (= expected-link seder-link))))))
