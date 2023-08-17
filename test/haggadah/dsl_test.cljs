(ns haggadah.dsl-test
  (:require [haggadah.dsl :as dsl]
            [cljs.test :as t :include-macros true]))

(def instruction (dsl/instruction "Hebrew" "English"))
(def expected-instruction [:div.instruction
                           [:div.instr.hebrew.pb-3 "Hebrew"]
                           [:div.instr.english "English"]])

(t/deftest render-instruction-test
  (t/testing "When rendering an instruction, returns the content in English and in Hebrew"
    (t/is (= expected-instruction (dsl/render-haggadah instruction)))))

(def song (dsl/song "Ki lo nae"
                    "כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה. אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו"
                    "Since for Him it is pleasant, for Him it is suited." ))
(def expected-song [:div.song
                    [:div.title "Ki lo nae" ]
                    [:div.text.hebrew.pb-3 "כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה. אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו"]
                    [:div.english.text "Since for Him it is pleasant, for Him it is suited."]])

(t/deftest render-song-test
  (t/testing "When rendering the song, returns the title and content"
    (t/is (= expected-song (dsl/render-haggadah song)))))

(def bracha (dsl/bracha "Wine" "סַבְרִי מָרָנָן" "Baruj hata"))
(def expected-bracha [:div.bracha
                      [:div.title  "Wine"]
                      [:div.text.hebrew.pb-3 "סַבְרִי מָרָנָן"]
                      [:div.english.text  "Baruj hata"]])

(t/deftest render-bracha-test
  (t/testing "When rendering a bracha, returns the title and the text"
    (t/is (= expected-bracha (dsl/render-haggadah bracha)))))

(t/deftest render-bracha-with-instruction-test
  (t/testing "When rendering a bracha with an instruction, returns the title, instruction, main content, and additional content"
    (let [bracha (dsl/bracha-with-instruction "Title" "Heb" "Eng" instruction song)
          expected [:div.bracha
                    [:div.title "Title"]
                    expected-instruction
                    [:div.text.hebrew.pb-3 "Heb"]
                    [:div.english.text "Eng"]
                    expected-song]]
      (t/is (= expected (dsl/render-haggadah bracha))))))

(t/deftest render-song-with-instruction-test
  (t/testing "When rendering a song with an instruction, returns the title, instruction, main content, and additional content"
    (let [song (dsl/song-with-instruction "Title" "Heb" "Eng" instruction bracha)
          expected [:div.song
                    [:div.title "Title"]
                    expected-instruction
                    [:div.text.hebrew.pb-3 "Heb"]
                    [:div.english.text "Eng"]
                    expected-bracha]]
      (t/is (= expected (dsl/render-haggadah song))))))

(t/deftest render-general-content-test
  (t/testing "When rendering general content, returns the title and content"
    (let [gen-cont (dsl/general-content "Title" "Hebrew" "English")
          expected [:div.general
                    [:div.title "Title"]
                    [:div.text.hebrew.pb-3 "Hebrew"]
                    [:div.text.english "English"]]]
         (t/is (= expected (dsl/render-haggadah gen-cont ))))))

(t/deftest render-general-content-with-instruction-test
  (t/testing "When rendering general content with an instruction, returns the title, instruction, main content, and additional content"
    (let [actual (dsl/general-content-with-instruction "Title"
                                                       "Hebrew"
                                                       "English"
                                                       instruction
                                                       bracha)
          expected [:div.general
                    [:div.title "Title"]
                    expected-instruction
                    [:div.text.hebrew.pb-3 "Hebrew"]
                    [:div.text.english "English"]
                    expected-bracha]]
      (t/is (= expected (dsl/render-haggadah actual))))))

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

(def section-1 (dsl/section "מַגִּיד" "Magid"  bracha))
(def expected-section-1 [:div.section
                         [:div.title "Magid"]
                         [:div.title.hebrew "מַגִּיד"]
                         expected-bracha])

(t/deftest render-section-test
    (t/testing "When rendering a section the title and content are returned"
      (t/is (= expected-section-1 (dsl/render-haggadah section-1)))))

;; (defn has-all-content?
;;   "Pre: takes an entire Haggadah
;;   Post: returns true if the Haggadah has all the content"
;;   [haggadah]
;;   (let [full-haggadah? (every-pred has-every-section? has-every-bracha? has-every-song?)]
;;     (full-haggadah? haggadah)))

;; {:title section :brachas coll :songs }

(def section-2 (dsl/section "יַחַץ" "Yachatz" song))
(def expected-section-2 [:div.section
                         [:div.title "Yachatz"]
                         [:div.title.hebrew "יַחַץ"]
                         expected-song])


(t/deftest render-haggadah-test
  (t/testing "When rendering an entire haggadah, every section and the content associated with that section is returned"
    (let [haggadah (dsl/haggadah "Haggadah" section-1 section-2)
          expected-haggadah [:div.haggadah
                             [:div.title "Haggadah"]
                             [:div.title
                              [:div.content
                               expected-section-1 
                               expected-section-2]]]]
      (t/is (= expected-haggadah (dsl/render-haggadah haggadah))))))

