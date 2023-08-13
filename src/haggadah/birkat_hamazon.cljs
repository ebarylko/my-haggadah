(ns haggadah.birkat-hamazon
  (:require [haggadah.dsl :as dsl :refer [instruction section general-content general-content-with-instruction]]))

(def ascents-heb "שִׁיר הַמַּעֲלוֹת, בְּשוּב ה' אֶת שִׁיבַת צִיּוֹן הָיִינוּ כְּחֹלְמִים. אָז יִמָּלֵא שְׂחוֹק פִּינוּ וּלְשׁוֹנֵנוּ רִנָּה. אָז יֹאמְרוּ בַגּוֹיִם: הִגְדִּיל ה' לַעֲשׂוֹת עִם אֵלֶּה. הִגְדִּיל ה' לַעֲשׂוֹת עִמָּנוּ, הָיִינוּ שְׂמֵחִים. שׁוּבָה ה' אֶת שְׁבִיתֵנוּ כַּאֲפִיקִים בַּנֶּגֶב. הַזֹּרְעִים בְּדִמְעָה, בְּרִנָּה יִקְצֹרוּ. הָלוֹךְ יֵלֵךְ וּבָכֹה נֹשֵׂא מֶשֶךְ הַזָּרַע, בֹּא יָבֹא בְרִנָּה נֹשֵׂא אֲלֻמֹּתָיו.")

(def ascents-eng "A Song of Ascents; When the Lord will bring back the captivity of Zion, we will be like dreamers. Then our mouth will be full of mirth and our tongue joyful melody; then they will say among the nations; \"The Lord has done greatly with these.\" The Lord has done great things with us; we are happy. Lord, return our captivity like streams in the desert. Those that sow with tears will reap with joyful song. He who surely goes and cries, he carries the measure of seed, he will surely come in joyful song and carry his sheaves.(Psalms 126)")

(def instr-heb-1 "מוזגים כוס שלישִי ומבָרכים בִרכַת המזון.")

(def instr-eng-1 "We pour the third cup and recite the Grace over the Food")

(def instr-heb-2 "שלשה שֶאכלו כאחד חיבים לזמן והמזַמן פותח:")
               
(def instr-eng-2 "Three that ate together are obligated to introduce the blessing and the leader of the introduction opens as follows:")

(def cont-heb-1 "רַבּוֹתַי נְבָרֵךְ:")

(def cont-eng-1 "My masters, let us bless:")


(def birkat-hamazon
  (general-content-with-instruction
   "Birkat Hamazon"
   ascents-heb ascents-eng
   (instruction instr-heb-1 instr-eng-1)
   (instruction instr-heb-2 instr-eng-2)
   (general-content cont-heb-1 cont-eng-1)
   ;; (general-content cont-heb-2 cont-eng-2)
   ;; (general-content cont-heb-3 cont-eng-3)
   ;; (instruction instr-heb-3 instr-eng-3)
   ;; (general-content cont-heb-4 cont-eng-4)
   ;; (instruction instr-heb-4 instr-eng-4)
   ;; (general-content cont-heb-5 cont-eng-5)
   ;; (instruction instr-heb-5 instr-eng-5)
   ;; (general-content cont-heb-6 cont-eng-6)
   ;; (instruction instr-heb-6 instr-eng-6)
   ;; (general-content cont-heb-7 cont-eng-7)
   ;; (instruction instr-heb-7 instr-eng-7)
   ;; (bracha bracha-heb-1 bracha-eng-1)
   ;; (general-content cont-heb-8 cont-eng-8)
   ;; (bracha bracha-heb-2 bracha-eng-2)
   ;; (general-content cont-heb-9 cont-eng-9)
   ;; (instruction instr-heb-8 instr-eng-8)
   ;; (general-content cont-heb-10 cont-eng-10)

   )
  )
