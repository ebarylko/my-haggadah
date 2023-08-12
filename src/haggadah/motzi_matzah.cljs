(ns haggadah.motzi-matzah
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]))

(def instr-heb " יקח המצות בסדר שהניחן, הפרוסה בין שתי השלמות, יאחז שלשתן בידו ויברך ״המוציא״ בכוונה עַל העליונה, ו״על אכילת מַצָּה״ בכוונה על הפרוסה. אחר כך יבצע כזית מן העליונה השלמה וכזית שני מן הפרוסה, ויטבלם במלח, ויאכל בהסבה שני הזיתים: ")
(def instr-eng " He takes out the matsa in the order that he placed them, the broken one between the two whole ones; he holds the three of them in his hand and blesses \"ha-motsi\" with the intention to take from the top one and \"on eating matsa\" with the intention of eating from the broken one. Afterwards, he breaks off a kazayit from the top whole one and a second kazayit from the broken one and he dips them into salt and eats both while reclining.")

(def lechem-bracha
  (bracha "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם הַמּוֹצִיא לֶחֶם מִן הָאָרֶץ."
          "Blessed are You, Lord our God, King of the Universe, who brings forth bread from the ground."))

(def matzah-bracha
  (bracha "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, אֲשֶׁר קִדְּשָׁנוּ בְּמִצְוֹתָיו וְצִוָּנוּ עַל אֲכִילַת מַצָּה."
          "Blessed are You, Lord our God, King of the Universe, who has sanctified us with His commandments and has commanded us on the eating of matsa."))

(def motzi-matzah
  (section
   "מוֹצִיא מַצָּה"
   "Motzi Matzah"
   (instruction instr-heb instr-eng)
   lechem-bracha
   matzah-bracha))

