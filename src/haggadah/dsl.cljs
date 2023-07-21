(ns haggadah.dsl)

(defn bracha
  [title text]
  {:type :bracha :title title :text text})

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
  "Pre: takes a title and conotent for a Haggadah
  Post: returns a model of Haggadah with the same content and title"
  [title & content]
  {:type :haggadah :content content :title title})

(def default-haggadah
  (haggadah "Default-haggadah" (bracha "Wine" "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")))

(defn render-bracha
  "Pre: takes a title and content for a bracha
  Post: returns a hiccup representation of the bracha"
  [title content]
  [:div.bracha
   [:div.title.has-text-centered.has-text-weight-bold.is-size-3.pb-2 {:data-testid :bracha-title} title]
   [:div.content.has-text-right.is-size-5 {:data-testid :bracha-content} content]])

(defn render-song
  "Pre: takes a title and content for a song
  Post: returns a hiccup representation of the song"
  [title content]
  [:div.pt-3
   [:div.has-text-centered.has-text-weight-bold.is-size-4.pb-2 title]
   [:div.has-text-right.is-size-5 content]])

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

(defn render-table
  "Pre: takes a title and the content for a table
  Post: returns the hiccup representation of the table"
  [title table]
  [:div.pt-3
   [:div.has-text-centered.pb-4.is-size-5 title]
   [:table.is-bordered.is-flex.is-justify-content-center.table
    (into [:tbody] (map ->row table))]])


(def table-content
  [["Sangre" "דָּם"]
   ["Ranas" "צְפַרְדֵּעַ"]
   ["Piojos"  "כִּנִּים"]
   ["Bestias"  "עָרוֹב"]
   ["Peste" "דֶּבֶר"]])

(declare haggadah->hiccup)

(defn render-subsec
  "Pre: takes a subsection title and content
  Post: returns the hiccup representation of the subsection"
  [title content]
  (into [:div 
         [:div.has-text-centered.has-text-weight-bold.is-size-3.pb-2 title]]
        (map haggadah->hiccup content)))

(defn render-section
  "Pre: takes the title of a section and its subsections
  Post: returns the hiccup representation of the section"
  [title subsections]
  (into [:div
         [:div.has-text-centered.has-text-weight-bold.is-size-4.pb-2 title]]
        (map haggadah->hiccup subsections)))

(defn haggadaah
  [title & content]
  {:type :haggadah :title title :content content})

(defn section
  [title & content]
  {:type :section :title title :content content})





;; (dsl/hagaddah
;;  "The best Hagadda"
;;  (dsl/section
;;   "Introduction"
;;   (dsl/song ...)
;;   (dsl/bracha ...)
;;   (dsl/table "Ten Commandments"
;;              (dsl/row "dam" "blood" "dam")))
;;  (dsl/section
;;   "Birkat"
;;   (dsl/brachaa ...)
;;   (dsl/text ....)))


(defn render-content
  "Pre: takes a Haggadah
  Post: returns the hiccup representation of the Haggadah"
  [{:keys [content]}]
  (->> content
       (map haggadah->hiccup)
       first))

(defmulti render-haggadah (comp keyword :type ))

(defmethod render-haggadah :default [args]
  [:div
   [:div "What did you pass me? " (keyword? (:type args) )]])

(defmethod render-haggadah :haggadah [{:keys [title content]}]
  [:div.haggadah
   [:div.title title]
   [:div.content
    (map render-content content)]])

(defmethod render-haggadah :bracha [{:keys [title text]}]
  [:div.bracha
   [:div.title title]
   [:div.text text]])

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

(defn haggadah->hiccup
  [{:keys [type]}
   [k {:keys [title content subsections]}]]
  (case k
    :bracha (render-bracha title content)
    :song (render-song title content)
    :table (render-table title content)
    :subsection (render-subsec title content)
    :section (render-section title subsections)
    :else [:div]))

