(ns haggadah.dsl)

(defn bracha
  ([hebrew-text english-text] (bracha "" hebrew-text english-text))
  ([title hebrew-text english-text]
   {:type :bracha :title title :hebrew hebrew-text :english english-text}))

(defn song
  "A song has a title, an optional instruction, hebrew text and english translation"
  ([title hebrew-text english-text] (song title hebrew-text english-text nil))
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
  ([hebrew-text english-text] (general-content "" hebrew-text english-text))
  ([title hebrew-text english-text & more-content]
   {:type :general :title title :hebrew hebrew-text :english english-text :children more-content}))

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
  [:div
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

(defmethod render-haggadah :general [{:keys [title english hebrew]}]
  [:div.general
   [:div.title title]
   [:div.text.hebrew.pb-3 hebrew]
   [:div.text.english english]])

(defmethod render-haggadah :song [{:keys [title hebrew english]}]
  [:div.song
   [:div.title title]
   [:div.text.hebrew.pb-3 hebrew]
   [:div.english.text english]])

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


