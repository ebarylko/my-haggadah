(ns haggadah.dsl
  (:require [clojure.walk :as w]))


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

(defn haggadah->hiccup
  [[k {:keys [title content]}]]
  (case k
    :bracha [:div
             [:div.has-text-centered.has-text-weight-bold.is-size-3.pb-2 {:data-testid :bracha-title} title]
             [:div.has-text-right.is-size-5 {:data-testid :bracha-content }content]]
    :else [:div]))


#_(defn parse-haggadah
  "Pre: takes a Haggadah
  Post: returns the same Haggadah represented in hiccup"
  [content]
  (w/walk key->hiccup #(conj [] %) #_#(into [] %) content))

(defn parse-haggadah
  "Pre: takes a Haggadah
  Post: returns the same Haggadah represented in hiccup"
  [content]
  (->> content
       ((partial map haggadah->hiccup))
       first))


