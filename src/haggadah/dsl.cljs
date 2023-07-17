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

(defn haggadah->hiccup
  [[k {:keys [title content]}]]
  (case k
    :bracha (render-bracha title content)
    :song (render-song title content)
    :else [:div]))

(defn parse-haggadah
  "Pre: takes a Haggadah
  Post: returns the same Haggadah represented in hiccup"
  [content]
  (->> content
       (map haggadah->hiccup)
       first))


