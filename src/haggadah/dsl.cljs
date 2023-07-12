(ns haggadah.dsl)


(defn create-haggadah
  "Pre: takes a bracha B
  Post: returns a Haggadah with bracha B"
  [bracha]
  {:content 
   {:bracha bracha}})

(def bracha "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן")

(defonce haggadah
  (create-haggadah bracha))

(defn parse-haggadah
  "Pre: takes a Haggadah
  Post: returns the same Haggadah represented in hiccup"
  [{:keys [bracha]}]
  [:div bracha])
