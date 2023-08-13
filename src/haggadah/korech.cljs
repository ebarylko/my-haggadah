(ns haggadah.korech
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content general-content-with-instruction]]))

(def instr-heb "כל אחד מהמסבים לוקח כזית מן המצה השְלישית עם כזית מרור, כורכים יחד, אוכלים בהסבה ובלי ברכה. לפני אכלו אומר.")
(def instr-eng "All present should take a kazayit from the third whole matsa with a kazayit of marror, wrap them together and eat them while reclining and without saying a blessing. Before he eats it, he should say:")

(def korech-heb-1 "זֵכֶר לְמִקְדָּשׁ כְּהִלֵּל. כֵּן עָשָׂה הִלֵּל בִּזְמַן שֶׁבֵּית הַמִּקְדָּשׁ הָיָה קַיָּם:")
(def korech-eng-1 "In memory of the Temple according to Hillel. This is what Hillel would do when the Temple existed:")


(def korech-heb-2 "הָיָה כּוֹרֵךְ מַצָּה וּמָרוֹר וְאוֹכֵל בְּיַחַד, לְקַיֵּם מַה שֶּׁנֶּאֱמַר: עַל מַצּוֹת וּמְרוׂרִים יֹאכְלֻהוּ.")
(def korech-eng-2 "He would wrap the matsa and marror and eat them together, in order to fulfill what is stated, (Numbers 9:11): \"You should eat it upon matsot and marrorim.\"")

(def korech
  (section
   "כּוֹרֵךְ" "Korech"
   (instruction instr-heb instr-eng)
   (general-content korech-heb-1 korech-eng-1)
   (general-content korech-heb-2 korech-eng-2)))
