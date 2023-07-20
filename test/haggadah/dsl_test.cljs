(ns haggadah.dsl-test
  (:require [haggadah.dsl :as dsl]
            [cljs.test :as t :include-macros true]))

(def title "Ki lo nae")

(def song
  " כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה.
  אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה. ")


(def haggadah-as-hiccup
  [:div.pt-3
   [:div.has-text-centered.has-text-weight-bold.is-size-4.pb-2 title]
   [:div.has-text-right.is-size-5 song]])

(dsl/create-haggadah-with-song title song)


(t/deftest haggadah-with-song-test
  (t/testing "When the user creates a Haggadah with a song in it and parses it using the dsl, the correct hiccup representation of the Haggadah will be returned"
    (let [haggadah (dsl/create-haggadah-with-song title song)
          hiccup-rep (dsl/parse-haggadah (:content haggadah))
          actual-haggadah haggadah-as-hiccup]
      (t/is (= actual-haggadah hiccup-rep)))))

(def haggadah-with-table
  [:div.pt-3
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
  [:div
   [:div.has-text-centered.has-text-weight-bold.is-size-3.pb-2 subsection-title]
   haggadah-as-hiccup
   haggadah-with-table])

(def subsection-content
  [{:song {:title title :content song}}
   {:table {:title table-title :content table-content}}])


(t/deftest haggadah-with-subsection-test
  (t/testing "When the user creates a Haggadah with a subsection and the Haggadah is parsed, the correct hiccup representation of the Haggadah is returned"
    (let [haggadah (dsl/create-haggadah-with-subsection subsection-title subsection-content)
          hiccup-rep (dsl/parse-haggadah (:content haggadah))]
      (t/is (= haggadah-with-subsection hiccup-rep)))))


(def haggadah-with-section
  )

(def ma-nishtana-content
  [["Ma nishtaná halaila azé micol aleilot?" "?מַה נִּשְׁתַּנָּה הַלַּיְלָה הַזֶּה מִכָּל הַלֵּילוֹת"]
   ["Shevejol haleilot anu ojlim jametz umatzá, halaila azé culó matzá" "שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין חָמֵץ וּמַצָּה, הַלַּיְלָה הַזֶּה - כּוּלוֹ מַצָּה"]
   ["Shevejol haleilot anu shear ierakot, halaila azé maror" "שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין שְׁאָר יְרָקוֹת - הַלַּיְלָה הַזֶּה מָרוֹר"]])

#_(t/deftest haggadah-with-section-test
  (t/testing "When the user creates a Haggadah with a section and the Haggadah is parsed, the correct hiccup representation of the Haggadah is returned"
    (let [section-title "Magid"
          first-song-title "Ha Lachma Anya" 
          first-song-content "הָא לַחְמָא עַנְיָא דִּי אֲכָלוּ אַבְהָתָנָא בְאַרְעָא דְמִצְרָיִם. כָּל דִכְפִין יֵיתֵי וְיֵיכֹל, כָּל דִצְרִיךְ יֵיתֵי וְיִפְסַח. הָשַּׁתָּא הָכָא, לְשָׁנָה הַבָּאָה בְּאַרְעָא דְיִשְׂרָאֵל. הָשַּׁתָּא עַבְדֵי, לְשָׁנָה הַבָּאָה בְּנֵי חוֹרִין"
          table-title "The Four Questions" 
          table-content ma-nishtana-content
          second-song-title "We Were Slaves in Egypt"
          second-song-content "עֲבָדִים הָיִינוּ לְפַרְעֹה בְּמִצְרָיִם, וַיּוֹצִיאֵנוּ ה' אֱלֹהֵינוּ מִשָּׁם בְּיָד חֲזָקָה וּבִזְרֹעַ נְטוּיָה."
          section-content [{:subsection {:title "" :content {:song {:title song-title :content song-content}}}}
                           {:subsection {:title "" :content {:table {:title table-title :content table-content}}}}
                           {:subsection {:title "" :content {:song {:title second-song-title :content second-song-content}}}}]
          haggadah (dsl/create-haggadah-with-section section-title section-content)
          hiccup-rep (dsl/parse-haggadah (:content haggadah))]
      (t/is (= haggadah-with-section hiccup-rep)))

    ))
