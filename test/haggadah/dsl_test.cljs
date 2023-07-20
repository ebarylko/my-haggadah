(ns haggadah.dsl-test
  (:require [haggadah.dsl :as dsl]
            [cljs.test :as t :include-macros true]))

(def title "Ki lo nae")

(def song
  " כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה.
  אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה. "
  )


(def expected-bracha
  [:div.pt-3
   [:div.has-text-centered.has-text-weight-bold.is-size-4.pb-2 title]
   [:div.has-text-right.is-size-5 song]])

(t/deftest render-bracha-test
  (t/testing "When rendering a bracha, returns the title and the text"
    (let [bracha (dsl/bracha "Wine" "Baruj hata")
          expected [:div.bracha
                    [:div.title  "Wine"]
                    [:div.text  "Baruj hata"]]]
      (t/is (= expected (dsl/render-haggadah bracha))))))


(t/deftest render-song-test
  (t/testing "When rendering the song, returns the title and content"
    (let [song (dsl/song "Ki lo nae" "כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה. אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו")
          expected [:div.song
                    [:div.title "Ki lo nae" ]
                    [:div.text "כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה. אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו"]]]
      (t/is (= expected (dsl/render-haggadah song))))))

;; (def haggadah-with-table
;;   [:div.pt-3
;;    [:div.has-text-centered.pb-4.is-size-5 "Las Diez Plagas"]
;;    [:table.is-bordered.is-flex.is-justify-content-center.table
;;     [:tbody
;;      [:tr
;;       [:td "Sangre"]
;;       [:td "דָּם"]]
;;      [:tr  
;;       [:td "Ranas"]
;;       [:td "צְפַרְדֵּעַ"]]
;;      [:tr  
;;       [:td "Piojos"]
;;       [:td "כִּנִּים"]]
;;      [:tr
;;       [:td "Bestias"]
;;       [:td "עָרוֹב"]]
;;      [:tr  
;;       [:td "Peste"]
;;       [:td "דֶּבֶר"]]]]])

;; (def table-content
;;   [["Sangre" "דָּם"]
;;    ["Ranas" "צְפַרְדֵּעַ"]
;;    ["Piojos"  "כִּנִּים"]
;;    ["Bestias"  "עָרוֹב"]
;;    ["Peste" "דֶּבֶר"]])

;; (def table-title "Las Diez Plagas")

(t/deftest render-table-test
  (t/testing "When rendering the table, each row and the content within is returned"
    (let [table (dsl/table "Las Diez Plagas" 
                           (dsl/row ["Sangre" "דָּם"])
                           (dsl/row ["Ranas" "צְפַרְדֵּעַ"]))
          expected [:div.table.is-bordered
                    [:div.title "Las Diez Plagas"]
                    [:table.table.table-content
                     [:tbody
                      [:tr
                       [:td "Sangre"]
                       [:td "דָּם"]]
                      [:tr  
                       [:td "Ranas"]
                       [:td "צְפַרְדֵּעַ"]]]]]]
      (t/is (= expected (dsl/render-haggadah table))))))

;; (def subsection-title "Subsection")

;; (def haggadah-with-subsection
;;   [:div
;;    [:div.has-text-centered.has-text-weight-bold.is-size-3.pb-2 subsection-title]
;;    haggadah-as-hiccup
;;    haggadah-with-table])

;; (def subsection-content
;;   [{:song {:title title :content song}}
;;    {:table {:title table-title :content table-content}}])


#_(t/deftest haggadah-with-subsection-test
  (t/testing "When the user creates a Haggadah with a subsection and the Haggadah is parsed, the correct hiccup representation of the Haggadah is returned"
    (let [haggadah (dsl/create-haggadah-with-subsection subsection-title subsection-content)
          hiccup-rep (dsl/parse-haggadah (:content haggadah))]
      (t/is (= haggadah-with-subsection hiccup-rep)))))


(def ma-nishtana-content
  [["Ma nishtaná halaila azé micol aleilot?" "?מַה נִּשְׁתַּנָּה הַלַּיְלָה הַזֶּה מִכָּל הַלֵּילוֹת"]
   ["Shevejol haleilot anu ojlim jametz umatzá, halaila azé culó matzá" "שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין חָמֵץ וּמַצָּה, הַלַּיְלָה הַזֶּה - כּוּלוֹ מַצָּה"]
   ["Shevejol haleilot anu shear ierakot, halaila azé maror" "שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין שְׁאָר יְרָקוֹת - הַלַּיְלָה הַזֶּה מָרוֹר"]])

(def ha-lachma-anya
  [:div
   [:div.has-text-centered.is-size-3.pb-2 ""]
   [:div.pt-3
    [:div.has-text-centered.is-size-5.pb-3 "Ha Lachma Anya"]
    [:div.has-text-right.is-size-5 "הָא לַחְמָא עַנְיָא דִּי אֲכָלוּ אַבְהָתָנָא בְאַרְעָא דְמִצְרָיִם. כָּל דִכְפִין יֵיתֵי וְיֵיכֹל, כָּל דִצְרִיךְ יֵיתֵי וְיִפְסַח. הָשַּׁתָּא הָכָא, לְשָׁנָה הַבָּאָה בְּאַרְעָא דְיִשְׂרָאֵל. הָשַּׁתָּא עַבְדֵי, לְשָׁנָה הַבָּאָה בְּנֵי חוֹרִין"]]])


[["Ma nishtaná halaila azé micol aleilot?" "?מַה נִּשְׁתַּנָּה הַלַּיְלָה הַזֶּה מִכָּל הַלֵּילוֹת"]
 ["Shevejol haleilot anu ojlim jametz umatzá, halaila azé culó matzá" "שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין חָמֵץ וּמַצָּה, הַלַּיְלָה הַזֶּה - כּוּלוֹ מַצָּה"]
 ["Shevejol haleilot anu shear ierakot, halaila azé maror" "שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין שְׁאָר יְרָקוֹת - הַלַּיְלָה הַזֶּה מָרוֹר"]]

(def ma-nishtana
  [:div
   [:div.has-text-centered.is-size-3.pb-2 ""]
   [:div.pt-3
    [:div.has-text-centered.pb-4.is-size-5 "The Four Questions"]
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
       [:td "דֶּבֶר"]]]]]
   ]
  )

(def haggadah-with-section
  [:div.haggadah
   [:div.title "Magid"]
   [:section.section
    [:div.title ""]
    [:div.content
     [:div.song
      [:div.has-text-centered.is-size-5.pb-3 "Ha Lachma Anya"]
      [:div.has-text-right.is-size-5 "הָא לַחְמָא עַנְיָא דִּי אֲכָלוּ אַבְהָתָנָא בְאַרְעָא דְמִצְרָיִם. כָּל דִכְפִין יֵיתֵי וְיֵיכֹל, כָּל דִצְרִיךְ יֵיתֵי וְיִפְסַח. הָשַּׁתָּא הָכָא, לְשָׁנָה הַבָּאָה בְּאַרְעָא דְיִשְׂרָאֵל. הָשַּׁתָּא עַבְדֵי, לְשָׁנָה הַבָּאָה בְּנֵי חוֹרִין"]]]]
   ma-nishtana])


#_(t/deftest render-haggadah-test
  (t/testing "When rendering an haggadaah, returns the title and the rendering of every element in the content"
    (let [haggadah (dsl/haggadah "Title" {:type :bracha :title "Wine" :text "Baruj hata..."})
          expected [:div.haggadah
                    [:div.title "Title"]
                    [:div.content
                     [:div.bracha
                      [:div.title "Wine"]
                      [:div.text "Baruj hata..."]]]]]
      (t/is (= expected (dsl/render-haggadah haggadah))))))


#_(t/deftest render-section-test
  (t/testing "When the user creates a Haggadah with a section, rendering the section returns the title and content"
    (let [section-title "Magid"
          first-song-title "Ha Lachma Anya" 
          first-song-content "הָא לַחְמָא עַנְיָא דִּי אֲכָלוּ אַבְהָתָנָא בְאַרְעָא דְמִצְרָיִם. כָּל דִכְפִין יֵיתֵי וְיֵיכֹל, כָּל דִצְרִיךְ יֵיתֵי וְיִפְסַח. הָשַּׁתָּא הָכָא, לְשָׁנָה הַבָּאָה בְּאַרְעָא דְיִשְׂרָאֵל. הָשַּׁתָּא עַבְדֵי, לְשָׁנָה הַבָּאָה בְּנֵי חוֹרִין"
          table-title "The Four Questions" 
          table-content ma-nishtana-content
          second-song-title "We Were Slaves in Egypt"
          second-song-content "עֲבָדִים הָיִינוּ לְפַרְעֹה בְּמִצְרָיִם, וַיּוֹצִיאֵנוּ ה' אֱלֹהֵינוּ מִשָּׁם בְּיָד חֲזָקָה וּבִזְרֹעַ נְטוּיָה."
          section-content [{:subsection {:title "" :content {:song {:title first-song-title :content first-song-content}}}}
                           {:subsection {:title "" :content {:table {:title table-title :content table-content}}}}
                           {:subsection {:title "" :content {:song {:title second-song-title :content second-song-content}}}}]
          haggadah (-> (dsl/make-section section-title section-content)
                       (dsl/make-haggadah haggada-title ))]
      (t/is (= haggadah-with-section (dsl/parse-haggadah (:content haggadah)))))

    ))
