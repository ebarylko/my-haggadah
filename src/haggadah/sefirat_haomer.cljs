(ns haggadah.sefirat-haomer
  (:require [haggadah.dsl :refer [bracha-with-instruction instruction]])
  )

(def sefirat-haomer
  (bracha-with-instruction "Sefirat HaOmer"
                           "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, אֲשֶׁר קִדְּשָׁנוּ בְּמִצְוֹֹּתָיו וְצִוָּנוּ עַל סְפִירַת הָעֹמֶר. הַיּוֹם יוֹם אֶחָד בָּעֹמֶר."
                           "Blessed are You, Lord our God, King of the Universe, who has sanctified us with His commandments and has commanded us on the counting of the omer. Today is the first day of the omer."
                           (instruction "ספירת העמר בחוץ לארץ, בליל שני של פסח:"
                                        "The counting of the omer outside of Israel on the second night of Pesach:")))
