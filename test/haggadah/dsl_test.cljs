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


(t/deftest render-section-test
    (t/testing "When rendering a section the title and content are returned"
      (let [section (dsl/section "Magid" bracha)
            expected-section [:div.section
                              [:div.title "Magid"]
                              expected-bracha]]
       (t/is (= expected-section (dsl/render-haggadah section))))))
