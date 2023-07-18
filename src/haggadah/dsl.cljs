(ns haggadah.dsl)


(defn create-haggadah
  "Pre: takes a bracha B
  Post: returns a Haggadah with bracha B"
  [title bracha]
  {:content 
   {:bracha {:title title :content bracha }}})

;; Bracha tiene contenido y un titulo por ahora

(def bracha "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")

(defonce haggadah
  (create-haggadah "Wine" bracha))


(defn create-haggadah-with-song
  "Pre: takes a song and its title
  Post: returns a Haggadah with song and title passed within"
  [title song]
  {:content 
   {:song {:title title :content song}}})
  
(defn create-haggadah-with-table
  "Pre: takes the title of a table and its content
  Post: returns a Haggadah with a table within"
  [title table]
  {:content
   {:table {:title title :content table }}})

(defn create-haggadah-with-subsection
  "Pre: takes a subsection title and content
  Post: returns a Haggadah with a subsection within"
  [title content]
  {:content
   {:sucsection {:title title :content content}}})

(defn render-bracha
  "Pre: takes a title and content for a bracha
  Post: returns a hiccup representation of the bracha"
  [title content]
  [:div
   [:div.has-text-centered.has-text-weight-bold.is-size-3.pb-2 {:data-testid :bracha-title} title]
   [:div.has-text-right.is-size-5 {:data-testid :bracha-content} content]])

(defn render-song
  "Pre: takes a title and content for a song
  Post: returns a hiccup representation of the song"
  [title content]
  [:div
   [:div.has-text-centered.has-text-weight-bold.is-size-3.pb-2 title]
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
  [:div
   [:div.has-text-centered.pb-4.is-size-5 title]
   [:table.is-bordered.is-flex.is-justify-content-center.table
    (into [:tbody] (map ->row table))]])

{:bracha {} :song {}}

(def table-content
  [["Sangre" "דָּם"]
   ["Ranas" "צְפַרְדֵּעַ"]
   ["Piojos"  "כִּנִּים"]
   ["Bestias"  "עָרוֹב"]
   ["Peste" "דֶּבֶר"]])

(def bracha-con (:content haggadah))
(def song
  (:content
   (create-haggadah-with-song "kljlkjl" "hiello ehte")))

(def coll
  [bracha-con song])


(concat {:hi 2} {:whi 3})
(conj {} bracha-con song)
(conj {} {:hi 2} {:whi 3})

(defn make-subsec-content
  "Pre: takes a collection of items to have for the subsection
Post: returns a subsection with the collection of items within"
  [coll]
(apply conj {} coll))

(make-subsec-content coll)

(defn render-subsection
  "Pre: takes a title and content for a subsection
  Post: returns the hiccup representation of the subsection"
  [title content]
  [:div title
   (map haggadah->hiccup content)])

(into {:song {}} (:content haggadah))

(render-subsection "hlkjhj"
(:content haggadah)
                   )

(defn haggadah->hiccup
  [[k {:keys [title content]}]]
  (case k
    :bracha (render-bracha title content)
    :song (render-song title content)
    :table (render-table title content)
    :subsection (render-subsection title content)
    :else [:div]))

(defn parse-haggadah
  "Pre: takes a Haggadah
  Post: returns the same Haggadah represented in hiccup"
  [content]
  (->> content
       (map haggadah->hiccup)
       first))


