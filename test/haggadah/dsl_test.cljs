(ns haggadah.dsl-test
  (:require [haggadah.dsl :as dsl]
            [cljs.test :as t :include-macros true]))

(def title "Ki lo nae")

(def song
  " כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה.
  אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה. ")


(def haggadah-as-hiccup
  [:div
   [:div.has-text-centered.has-text-weight-bold.is-size-3.pb-2  title]
   [:div.has-text-right.is-size-5 song]])

(dsl/create-haggadah-with-song title song)


(t/deftest haggadah-with-song-test
  (t/testing "When the user creates a Haggadah with a song in it and parses it using the dsl, the correct hiccup representation of the Haggadah will be returned"
    (let [haggadah (dsl/create-haggadah-with-song title song)
          hiccup-rep (dsl/parse-haggadah (:content haggadah))
          actual-haggadah haggadah-as-hiccup]
      (t/is (= actual-haggadah hiccup-rep)))))



(def table
  [:div
   [:div.has-text-centered.pb-4.is-size-5 "TITLE"]
   [:table.is-bordered.is-flex.is-justify-content-center.table
    [:tdead]
    [:tbody
     [:tr
      [:td "Sangre"]
      [:td "דָּם"]]
     [:tr  
      [:td "Ranas"]
      [:td "צְפַרְדֵּעַ"]]]]])


(def haggadah-with-table
  [:div
   [:div.has-text-centered.pb-4.is-size-5 "Las Diez Plagas"]
   [:table.is-bordered.is-flex.is-justify-content-center.table
    [:tbody
     [:tr
      [:td "Sangre"]
      [:td "דָּם"]]
     [:tr  
      [:td "Ranas"]
      [:td "צְפַרְדֵּעַ"]]
     [:tr  
      [:td "Piojos"]
      [:td "כִּנִּים"]]
     [:tr  
      [:td "Bestias"]
      [:td "עָרוֹב"]]
     [:tr  
      [:td "Peste"]
      [:td "דֶּבֶר"]]]]])

(def table-content
  [["Sangre" "דָּם"]
   ["Ranas" "צְפַרְדֵּעַ"]
   ["Piojos"  "כִּנִּים"]
   ["Bestias"  "עָרוֹב"]
   ["Peste" "דֶּבֶר"]])

(def table-title "Las Diez Plagas")

(t/deftest haggadah-with-table-test
  (t/testing "When the user creates a Haggadah with a table in it and the Haggadah is parsed, the correct hiccup representation of the Haggadah is returned"
    (let [haggadah (dsl/create-haggadah-with-table table-title table-content)
          hiccup-rep (dsl/parse-haggadah (:content haggadah))]
      (t/is (= haggadah-with-table hiccup-rep)))))

(def subsection-title "Subsection")

(def haggadah-with-subsection
  [:div subsection-title
   haggadah-as-hiccup
   haggadah-with-table])

(def subsection-content
  [{:song {:title title :content song}}
   {:table {:title table-title :content table-content}}])


(t/deftest haggadah-with-subsection-test
  (t/testing "When the user creates a Haggadah with a subsection and the Haggadah is parsed, the correct hiccup representation of the Haggadah is returned"
    (let [haggadah (dsl/create-haggadah-with-subsection subsection-title subsection-content)
          hiccup-rep (dsl/parse-haggadah (:content haggadah))]
      (t/is (= haggadah-with-subsection hiccup-rep)))
    ))
