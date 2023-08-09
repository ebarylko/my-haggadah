(ns haggadah.karpas
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]))


(def instr-heb-7 "לוקח מן הכרפס פחות מכזית – כדי שלא יתחייב בברכה אחרונה – טובל במי מלח, מברך בורא פרי האדמה, ומכווין לפטור בברכה גם את המרור. אוכל בלא הסבה.")
(def instr-eng-7 "Take from the greens less than a kazayit - so that you will not need to say the blessing after eating it; dip it into the salt water; say the blessing 'who creates the fruit of the earth;' and have in mind that this blessing will also be for the bitter herbs.  Eat without reclining.")

(def urchatz-bracha-heb "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, בּוֹרֵא פְּרִי הָאֲדָמָה.")
(def urchatz-bracha-eng "Blessed are you, Lord our God, King of the universe, who creates the fruit of the earth.")

(def karpas
  (section "כַּרְפַּס"
           "Karpas"
           (instruction instr-heb-7 instr-eng-7)
           (bracha urchatz-bracha-heb urchatz-bracha-eng)))

