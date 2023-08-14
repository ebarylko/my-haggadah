(ns haggadah.dsl)

(defn bracha
  ([hebrew-text english-text] (bracha "" hebrew-text english-text nil))
  ([title hebrew-text english-text]
   (bracha title hebrew-text english-text nil))
  ([title hebrew-text english-text & more-content] {:type :bracha :title title :hebrew hebrew-text :english english-text :children more-content}))

(defn bracha-with-more-content
  ([hebrew-text english-text & more-content] (apply bracha nil hebrew-text english-text more-content)))

(defn bracha-with-instruction
  ([title hebrew-text english-text instruction & more-content] (-> (apply bracha title hebrew-text english-text more-content)
                                                                   (assoc :instruction instruction))))


(defn song
  "A song has a title, an optional instruction, hebrew text and english translation"
  ([hebrew-text english-text] (song nil hebrew-text english-text nil))
  ([title hebrew-text english-text]
   (song title hebrew-text english-text nil))
  ([title hebrew-text english-text & more-content]
   {:type :song :title title :hebrew hebrew-text :english english-text :children more-content}))

(defn song-with-instruction
  [title hebrew-text english-text instruction & content]
  (-> (apply song title hebrew-text english-text content)
      (assoc :instruction instruction)))

(defn instruction
  [hebrew-text english-text]
  {:type :instruction :english english-text :hebrew hebrew-text})

(defn cell
  [content]
  [:td content])

(defn row
  [row]
  (apply merge [:tr] (map cell row)))

(defn table
  [title & rows]
  {:type :table :title title :rows rows})

(defn general-content
  "General content has a title, hebrew text and english translation, and optional instructions and additional content"
  ([hebrew-text english-text] (general-content nil hebrew-text english-text nil))
  ([title hebrew-text english-text] (general-content title hebrew-text english-text nil)
   #_{:type :general :title title :hebrew hebrew-text :english english-text})
  ([title hebrew-text english-text & more-content]
   {:type :general :title title :hebrew hebrew-text :english english-text :children more-content}))


(defn general-content-with-instruction
  [title hebrew english instruction & more-content]
  (-> (apply general-content title hebrew english more-content)
      (assoc :instruction instruction)))

(defn haggadah
  "Pre: takes a title and content for a Haggadah
  Post: returns a model of Haggadah with the same content and title"
  [title & content]
  {:type :haggadah :content content :title title})

(defonce default-haggadah
  (haggadah "Default-haggadah" (bracha "Wine" "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן"
                                       "Blessed are You, Lord our God, King of the universe, who creates the fruit of the vine.")))


(defn haggadaah
  [title & content]
  {:type :haggadah :title title :content content})

(defn section
  [hebrew-title english-title & content]
  {:type :section :english english-title :hebrew hebrew-title :content content})


(defn ->cell
  "Pre: takes a cell from a table
  Post: returns the hiccup representation of the cell"
  [cell]
  [:td cell])

(defn ->row
  "Pre: takes a row from a table
  Post: returns the hiccup representation of the row"
  [row]
  (into [:tr] (map ->cell row)))

(defmulti render-haggadah (comp keyword :type ))

(defmethod render-haggadah :default [args]
  (println "This is what you passed " args)
  #_nil
  #_[:div
   [:div "What did you pass me? " (:type args)
    "Original args " args]])

(defmethod render-haggadah :haggadah [{:keys [title content]}]
  [:div.haggadah
   [:div.title title]
   [:div.title
    (apply conj [:div.content] (map render-haggadah content))]])

(def mult-conj (partial apply conj))

(def has-content? (comp not nil? first))

(defmethod render-haggadah :bracha [{:keys [title english hebrew children instruction]}]
  (let [content [[:div.text.hebrew.pb-3 hebrew] [:div.english.text english]]]
    (cond-> [:div.bracha]
      (seq title) (conj [:div.title title])
      (seq instruction) (conj (render-haggadah instruction))
      :always (mult-conj content)
      (has-content? children) (mult-conj (map render-haggadah children)))))

(defmethod render-haggadah :instruction [{:keys [hebrew english]}]
  [:div.instruction
   [:div.instr.hebrew.pb-3 hebrew]
   [:div.instr.english english]])


(defmethod render-haggadah :general [{:keys [title english hebrew instruction children]}]
  (cond-> [:div.general]
    (seq title) (conj [:div.title title])
    (seq instruction) (conj (render-haggadah instruction))
    :always (conj [:div.text.hebrew.pb-3 hebrew] [:div.text.english english])
    (has-content? children) (mult-conj (map render-haggadah children))))


(defmethod render-haggadah :song [{:keys [title hebrew english instruction children]}]
  (let [content [[:div.text.hebrew.pb-3 hebrew]
                 [:div.english.text english]]]
    (cond-> [:div.song]
      (seq title) (conj [:div.title title])
      (seq instruction) (conj (render-haggadah instruction))
      :always (mult-conj content)
      (has-content? children) (mult-conj (map render-haggadah children)))))

(defmethod render-haggadah :table [{:keys [title rows]}]
  [:div.table.is-bordered
   [:div.title title]
   [:table.table.table-content
    (apply conj [:tbody] rows)]])


(defmethod render-haggadah :section [{:keys [hebrew english content]}]
  (apply conj
   [:div.section
    [:div.title english]
    [:div.title.hebrew hebrew]]
    (map render-haggadah content)))


