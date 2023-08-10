(ns haggadah.dsl)

(defn bracha
  ([hebrew-text english-text] (bracha "" hebrew-text english-text))
  ([title hebrew-text english-text]
   {:type :bracha :title title :hebrew hebrew-text :english english-text}))

(defn song
  "A song has a title, an optional instruction, hebrew text and english translation"
  ([hebrew-text english-text] (song nil hebrew-text english-text nil))
  ([title-or-instruction hebrew-text english-text]
   (cond
     (map? title-or-instruction) (song nil hebrew-text english-text title-or-instruction)
     (string? title-or-instruction) (song title-or-instruction hebrew-text english-text nil)))
  ([title hebrew-text english-text instruction]
   {:type :song :title title :hebrew hebrew-text :english english-text :instruction instruction}))

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
  "General content has a title, instruction, hebrew text and english translation, and additional content"
  ([hebrew-text english-text] (general-content "" hebrew-text english-text nil nil))
  ([title hebrew-text english-text] (general-content title hebrew-text english-text nil nil))
  ([title hebrew-text english-text instruction & more-content]
   {:type :general :title title :hebrew hebrew-text :english english-text :instruction instruction :children more-content}))

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
  #_nil
  #_[:div
   [:div "What did you pass me? " (:type args)
    "Original args " args]])

(defmethod render-haggadah :haggadah [{:keys [title content]}]
  [:div.haggadah
   [:div.title title]
   [:div.title
    (apply conj [:div.content] (map render-haggadah content))]])

(defmethod render-haggadah :bracha [{:keys [title english hebrew]}]
  [:div.bracha
   [:div.title title]
   [:div.text.hebrew.pb-3 hebrew]
   [:div.english.text english]])

(defmethod render-haggadah :instruction [{:keys [hebrew english]}]
  [:div.instruction
   [:div.instr.hebrew.pb-3 hebrew]
   [:div.instr.english english]])

(defmethod render-haggadah :general [{:keys [title english hebrew instruction children]}]
  (cond-> [:div.genral]
    (seq instruction) (conj (render-haggadah instruction))
    (seq title) (conj [:div.title title])
    :always (conj [:div.text.hebrew.pb-3 hebrew] [:div.text.english english])
    (seq children) ((partial apply conj) (map render-haggadah children)))
  #_(apply conj [:div.general
               [:div.title title]
               [:div.text.hebrew.pb-3 hebrew]
               [:div.text.english english]]
         (map render-haggadah children)))

(conj [:div.gen] [:div.en] [:div.heb])

(def mult-conj (partial apply conj))

(defmethod render-haggadah :song [{:keys [title hebrew english instruction]}]
  (let [content [[:div.text.hebrew.pb-3 hebrew]
                 [:div.english.text english]]]
    (cond-> [:div.song]
      (seq title) (conj [:div.title title])
      (seq instruction) (conj (render-haggadah instruction))
      :always (mult-conj content))))

(cond-> [:div.song]
  true (conj [:div.hi])
  true (conj [:div.whoa]))

(conj [:div.song []])

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


