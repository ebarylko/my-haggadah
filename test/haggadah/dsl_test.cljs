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

(t/deftest haggadah-with-song-test
  (t/testing "When the user creates a Haggadah with a song in it and parses it using the dsl, the correct hiccup representation of the Haggadah will be returned"
    (let [haggadah (dsl/create-haggadah-with-song title song)
          hiccup-rep (dsl/parse-haggadah (:content haggadah))
          actual-haggadah haggadah-as-hiccup]
      (t/is (= actual-haggadah hiccup-rep)))))
