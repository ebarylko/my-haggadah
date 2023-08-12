(ns haggadah.rachtzah
  (:require [haggadah.dsl :as dsl :refer [bracha instruction section]]))

(def instr-heb "נוטלים את הידים ומברכים:")
(def instr-eng "We wash the hands and make the blessing.")


(def bracha-heb "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, אֲשֶׁר קִדְּשָׁנוּ בְּמִצְוֹתָיו וְצִוָּנוּ עַל נְטִילַת יָדַיִם.")
(def bracha-eng "Blessed are You, Lord our God, King of the Universe, who has sanctified us with His commandments and has commanded us on the washing of the hands.")

(def rachtzah
  (section 
   "רָחְצָה"
   "Rachtzah"
   (instruction instr-heb instr-eng)
   (bracha bracha-heb bracha-eng)))
