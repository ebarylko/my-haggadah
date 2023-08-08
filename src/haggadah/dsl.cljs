(ns haggadah.dsl)

(defn bracha
  [title hebrew-text english-text]
  {:type :bracha :title title :hebrew hebrew-text :english english-text})

(defn song
  [title text]
  {:type :song :title title :text text})

(defn cell
  [content]
  [:td content])

(defn row
  [row]
  (apply merge [:tr] (map cell row)))

(defn table
  [title & rows]
  {:type :table :title title :rows rows})

(defn haggadah
  "Pre: takes a title and content for a Haggadah
  Post: returns a model of Haggadah with the same content and title"
  [title & content]
  {:type :haggadah :content content :title title})

(defonce default-haggadah
  (haggadah "Default-haggadah" (bracha "Wine" "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן"
                                       "Blessed are You, Lord our God, King of the universe, who creates the fruit of the vine.")))

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

(defn haggadaah
  [title & content]
  {:type :haggadah :title title :content content})

(defn section
  [title & content]
  {:type :section :title title :content content})

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
   [:div.english-text english]])

(defmethod render-haggadah :song [{:keys [title text]}]
  [:div.song
   [:div.title title]
   [:div.text text]])

(defmethod render-haggadah :table [{:keys [title rows]}]
  [:div.table.is-bordered
   [:div.title title]
   [:table.table.table-content
    (apply conj [:tbody] rows)]])


(defmethod render-haggadah :section [{:keys [title content]}]
  (apply conj
   [:div.section
    [:div.title title]]
    (map render-haggadah content)))


