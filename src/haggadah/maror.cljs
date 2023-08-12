(ns haggadah.maror
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]))

(def instr-heb "כל אחד מהמסבִים לוקח כזית מרור, ּמטבִלו בַחרוסת, ּמנער החרוסת, מברך ואוכל בלי הסבה.")
(def instr-eng "All present should take a kazayit of marror, dip into the haroset, shake off the haroset, make the blessing and eat without reclining.")

(def bracha-heb "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, אֲשֶׁר קִדְּשָנוּ בְּמִצְוֹתָיו וְצִוָּנוּ עַל אֲכִילַת מָרוֹר.")

(def bracha-eng "Blessed are You, Lord our God, King of the Universe, who has sanctified us with His commandments and has commanded us on the eating of marror.")

(def maror
  (section "מָרוֹר"
           "Maror"
           (instruction instr-heb instr-eng)
           (bracha bracha-heb bracha-eng)))



