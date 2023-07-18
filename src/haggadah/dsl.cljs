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
  "Pre: takes a table
  Post: returns a Haggadah with a table within"
  [title table]
  {:content
   {:table {:title title :content table }}})

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

{:1 ["cont" "more" "cont"]}

[["1" "2" "3" "4"]
 ["1" "2" "3" "4"]]
(mapcat str [1 2 3])


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

(defn haggadah->hiccup
  [[k {:keys [title content]}]]
  (case k
    :bracha (render-bracha title content)
    :song (render-song title content)
    :table (render-table title content)
    :else [:div]))

(defn parse-haggadah
  "Pre: takes a Haggadah
  Post: returns the same Haggadah represented in hiccup"
  [content]
  (->> content
       (map haggadah->hiccup)
       first))


