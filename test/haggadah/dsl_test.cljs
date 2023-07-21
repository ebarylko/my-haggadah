(ns haggadah.dsl-test
  (:require [haggadah.dsl :as dsl]
            [cljs.test :as t :include-macros true]))

(def bracha (dsl/bracha "Wine" "Baruj hata"))
(def expected-bracha
  [:div.bracha
   [:div.title  "Wine"]
   [:div.text  "Baruj hata"]])

(t/deftest render-bracha-test
  (t/testing "When rendering a bracha, returns the title and the text"
    (t/is (= expected-bracha (dsl/render-haggadah bracha)))))


(t/deftest render-song-test
  (t/testing "When rendering the song, returns the title and content"
    (let [song (dsl/song "Ki lo nae" "כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה. אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו")
          expected [:div.song
                    [:div.title "Ki lo nae" ]
                    [:div.text "כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה. אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו"]]]
      (t/is (= expected (dsl/render-haggadah song))))))

(t/deftest render-table-test
  (t/testing "When rendering the table, each row and the content within is returned"
    (let [table (dsl/table "Las Diez Plagas" 
                           (dsl/row ["Sangre" "דָּם"])
                           (dsl/row ["Ranas" "צְפַרְדֵּעַ"]))
          expected [:div.table.is-bordered
                    [:div.title "Las Diez Plagas"]
                    [:table.table.table-content
                     [:tbody
                      [:tr [:td "Sangre"] [:td "דָּם"]]
                      [:tr [:td "Ranas"] [:td "צְפַרְדֵּעַ"]]]]]]
      (t/is (= expected (dsl/render-haggadah table))))))


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
       [:td "דֶּבֶר"]]]]]])

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



(def expected-section
  [:div.section
   [:div.title "Magid"]
   expected-bracha])


(def section (dsl/section "Magid" bracha))

(t/deftest render-section-test
    (t/testing "When rendering a section the title and subsections are returned"
      (t/is (= expected-section (dsl/render-haggadah section)))))
