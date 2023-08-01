(ns acceptance.dashboard-test
  (:require  [clojure.test :as t]
             [clojure.walk :as w]
             [etaoin.api :as e]
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
    (e/fill-human {:data-testid :haggadah-title} title {:mistake-prob 0})
    (e/click-visible {:data-testid :add-haggadah})
    (e/click-visible {:data-testid :return})))

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

(def coll-type
  {:sedarim {:fn/has-class :sedarim}
   :haggadot {:fn/has-class :haggadot}})

(defn wait-for-collection
  "Pre: takes a collection type
  Post: waits until a collection of the same type is found on the page"
  [coll]
  (e/wait-visible driver (coll coll-type)))

(defn wait-for-haggadot
  []
  (wait-for-collection :haggadot))


(t/deftest create-haggadah-test
  (t/testing "When the current user creates a new Haggadah and goes back to the dashboard, the Haggadah is listed first among the Haggadot and a new Haggadah with the same details is added to firestore"
    (doto driver
      (c/home->dashboard)
      (create-haggadah new-haggadah-title))
    (wait-for-haggadot)
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
    (wait-for-haggadot)
    (let [titles (haggadot-titles (all-haggadot))]
      (t/is (= ["First" "Second" "Third"] titles)))))

(defn haggadah-path
  "Pre: takes a user and a Seder id
  Post: returns the location of the Haggadah used for the Seder"
  [user id]
  (-> (FirestoreClient/getFirestore)
      (.collection "users")
      (.document user)
      (.collection "seders")
      (.document id)
      (.get)
      (.get)
      (.getString "haggadah-path")))

(defn wait-for-sedarim
  []
  (wait-for-collection :sedarim))

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
  (let [title (e/get-element-text-el driver seder)
        id (e/get-element-attr-el driver seder :data-testid)]
    {:title title :id id}))

(defn all-sedarim
  "Pre: takes nothing
  Post: returns the ids and titles of every Seder on the dashboard"
  []
  (let [sedarim (e/query-all driver {:fn/has-class :seder-link})]
    (map id-and-title sedarim)))

(t/deftest create-seder-test
  (t/testing "When the current user has a Haggadah and creates a Seder which uses that Haggadah, a new Seder with the same Haggadah will appear in the user's collection of Sedarim"
    (let [id (c/fs-store-haggadah {:title "Haggadah 1"
                                   :type "haggadah"
                                   :content [{:type "bracha" :title "hello" :text "bracha"}]}
                                  "user1" )]
      (c/home->dashboard driver)
      (create-seder "The first Seder")
      (wait-for-sedarim)
      (let [expected-haggadah-path (format "users/user1/haggadot/%s" id)
            [_ seder-id] (->> (all-sedarim)
                              first
                              vals)
            haggadah-path (haggadah-path "user1" seder-id)]
        (t/is (= expected-haggadah-path haggadah-path))))))

(defn fs-store-seder
  "Pre: takes a seder title, a user, and the id of a Haggadah
  Post: stores a seder with the same title and path to a haggadah within the user's collection of sedarim in firestore"
  [title user id]
  (let [id (-> (FirestoreClient/getFirestore)
              (.collection "users")
              (.document user)
              (.collection "seders")
              (.add  (w/stringify-keys (assoc {:title title
                                               :haggadah-path (clojure.string/join ["users" user "haggadot" id])}
                                              :createdAt (java.time.Instant/now))))
              (.get)
              (.getId))]
    (println "This is the id " id)
    (-> (FirestoreClient/getFirestore)
                 (.collection "users")
                 (.document user)
                 (.collection "seders")
                 (.update {"id" id})
                 (.get))))


(defn dashboard->first-seder
  "Pre: takes nothing
  Post: navigates to the first seder on the dashboard"
  []
  (doto driver
   (e/click {:data-testid :activate-seder})
   (e/wait-visible {:data-testid :gen-link})))

(defn gen-seder-link
  "Pre: takes nothing
  Post: clicks on the sentence which will generate the link for the seder"
  []
  (doto driver
    (e/click  {:data-testid :gen-link})
    (e/wait-visible {:id  :share-seder})))

(defn seder-link->seder
  "Pre: takes nothing
  Post: navigates to the seder associated with the link"
  []
   (e/click driver {:id :share-seder}))

(defn wait-for-seder
  []
  (e/wait-visible driver {:fn/has-class :haggadah}))


(t/deftest view-seder-test
  (t/testing "When the current user has a Seder and copies the link to view the Seder and pastes it in the window, they should then see a welcome message and the Haggadah below"
    (let [id (c/fs-store-haggadah {:title "Haggadah 1"
                                   :type "haggadah"
                                   :content [{:type "bracha" :title "hello" :text "bracha"}]}
                                  "user1")
          changes (fs-store-seder "Seder title" "user1" id)]
      ;; (println "The changes " changes)
      ;; (c/home->dashboard driver)
      ;; (wait-for-sedarim)
      ;; (dashboard->first-seder)
      ;; (gen-seder-link)
      ;; (seder-link->seder)
      ;; (wait-for-seder)
      (let [#_#_#_#_#_#_seder-title (seder-title)
            haggadah-title (haggadah-title)
            haggadah-content (haggadah-content)]
        (t/are [x y] (= x y)
          1 2
          ;; "Seder title" seder-title
          ;; "Haggadah 1" haggadah-title
          ;; "hello bracha" haggadah-content
          ))
      )
    )
  )
