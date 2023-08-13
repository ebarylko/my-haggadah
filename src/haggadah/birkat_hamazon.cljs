(ns haggadah.birkat-hamazon
  (:require [haggadah.dsl :as dsl :refer [bracha instruction section general-content general-content-with-instruction]]))

(def ascents-heb "שִׁיר הַמַּעֲלוֹת, בְּשוּב ה' אֶת שִׁיבַת צִיּוֹן הָיִינוּ כְּחֹלְמִים. אָז יִמָּלֵא שְׂחוֹק פִּינוּ וּלְשׁוֹנֵנוּ רִנָּה. אָז יֹאמְרוּ בַגּוֹיִם: הִגְדִּיל ה' לַעֲשׂוֹת עִם אֵלֶּה. הִגְדִּיל ה' לַעֲשׂוֹת עִמָּנוּ, הָיִינוּ שְׂמֵחִים. שׁוּבָה ה' אֶת שְׁבִיתֵנוּ כַּאֲפִיקִים בַּנֶּגֶב. הַזֹּרְעִים בְּדִמְעָה, בְּרִנָּה יִקְצֹרוּ. הָלוֹךְ יֵלֵךְ וּבָכֹה נֹשֵׂא מֶשֶךְ הַזָּרַע, בֹּא יָבֹא בְרִנָּה נֹשֵׂא אֲלֻמֹּתָיו.")

(def ascents-eng "A Song of Ascents; When the Lord will bring back the captivity of Zion, we will be like dreamers. Then our mouth will be full of mirth and our tongue joyful melody; then they will say among the nations; \"The Lord has done greatly with these.\" The Lord has done great things with us; we are happy. Lord, return our captivity like streams in the desert. Those that sow with tears will reap with joyful song. He who surely goes and cries, he carries the measure of seed, he will surely come in joyful song and carry his sheaves.(Psalms 126)")

(def instr-heb-1 "מוזגים כוס שלישִי ומבָרכים בִרכַת המזון.")

(def instr-eng-1 "We pour the third cup and recite the Grace over the Food")

(def instr-heb-2 "שלשה שֶאכלו כאחד חיבים לזמן והמזַמן פותח:")
               
(def instr-eng-2 "Three that ate together are obligated to introduce the blessing and the leader of the introduction opens as follows:")

(def cont-heb-1 "רַבּוֹתַי נְבָרֵךְ:")

(def cont-eng-1 "My masters, let us bless:")


(def cont-heb-2 "יְהִי שֵׁם ה' מְבֹרָךְ מֵעַתָּה וְעַד עוֹלָם.")
(def cont-eng-2 "May the Name of the Lord be blessed from now and forever. (Psalms 113:2)")

(def instr-heb-3 "הַמְזַמֵן אומֵר:")
(def instr-eng-3 "The leader says:")

(def cont-heb-3 "בִּרְשׁוּת מָרָנָן וְרַבָּנָן וְרַבּוֹתַי, נְבָרֵךְ [אֱלֹהֵינוּ] שֶׁאָכַלְנוּ מִשֶּׁלוֹ.")
(def cont-eng-3 "With the permission of our gentlemen and our teachers and my masters, let us bless [our God] from whom we have eaten.")

(def instr-heb-4 "המסבים עונים:")
(def instr-eng-4 "Those present answer:")

(def cont-heb-4 "בָּרוּךְ [אֱלֹהֵינוּ] שֶׁאָכַלְנוּ מִשֶּׁלוֹ וּבְטוּבוֹ חָיִינוּ")
(def cont-eng-4 "Blessed is [our God] from whom we have eaten and from whose goodness we live.")

(def instr-heb-5 "כלם אומרים:")
(def instr-eng-5 "They all say:")

(def cont-heb-5 "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, הַזָּן אֶת הָעוֹלָם כֻּלּוֹ בְּטוּבוֹ בְּחֵן בְּחֶסֶד וּבְרַחֲמִים, הוּא נוֹתֵן לֶחֶם לְכָל בָּשָׂר כִּי לְעוֹלָם חַסְדוֹ. וּבְטוּבוֹ הַגָּדוֹל תָּמִיד לֹא חָסַר לָנוּ, וְאַל יֶחְסַר לָנוּ מָזוֹן לְעוֹלָם וָעֶד. בַּעֲבוּר שְׁמוֹ הַגָּדוֹל, כִּי הוּא אֵל זָן וּמְפַרְנֵס לַכֹּל וּמֵטִיב לַכֹּל, וּמֵכִין מָזוֹן לְכָל בְּרִיּוֹתָיו אֲשֶׁר בָּרָא. בָּרוּךְ אַתָּה ה', הַזָּן אֶת הַכֹּל.")
(def cont-eng-5 "Blessed are You, Lord our God, King of the Universe, who nourishes the entire world in His goodness, in grace, in kindness and in mercy; He gives bread to all flesh since His kindness is forever. And in His great goodness, we always have not lacked, and may we not lack nourishment forever and always, because of His great name. Since He is a Power that feeds and provides for all and does good to all and prepares nourishment for all of his creatures that he created. Blessed are You, Lord, who sustains all.")


(def cont-heb-6 "נוֹדֶה לְךָ ה' אֱלֹהֵינוּ עַל שֶׁהִנְחַלְתָּ לַאֲבוֹתֵינוּ אֶרֶץ חֶמְדָה טוֹבָה וּרְחָבָה, וְעַל שֶׁהוֹצֵאתָנוּ ה' אֱלֹהֵינוּ מֵאֶרֶץ מִצְרַיִם, וּפְדִיתָנוּ מִבֵּית עֲבָדִים, וְעַל בְּרִיתְךָ שֶׁחָתַמְתָּ בְּבְשָׂרֵנוּ, וְעַל תּוֹרָתְךָ שֶׁלִּמַּדְתָּנוּ, וְעַל חֻקֶּיךָ שֶׁהוֹדַעְתָּנוּ, וְעַל חַיִּים חֵן וָחֶסֶד שֶׁחוֹנַנְתָּנוּ, וְעַל אֲכִילַת מָזוֹן שָׁאַתָּה זָן וּמְפַרְנֵס אוֹתָנוּ תָּמִיד, בְּכָל יוֹם וּבְכָל עֵת וּבְכָל שָׁעָה:")
(def cont-eng-6 "We thank you, Lord our God, that you have given as an inheritance to our ancestors a lovely, good and broad land, and that You took us out, Lord our God, from the land of Egypt and that You redeemed us from a house of slaves, and for Your covenant which You have sealed in our flesh, and for Your Torah that You have taught us, and for Your statutes which You have made known to us, and for life, grace and kindness that You have granted us and for the eating of nourishment that You feed and provide for us always, on all days, and at all times and in every hour.")


(def cont-heb-7 "וְעַל הַכּל ה' אֱלֹהֵינוּ, אֲנַחְנוּ מוֹדִים לָךְ וּמְבָרְכִים אוֹתָךְ, יִתְבָּרַךְ שִׁמְךָ בְּפִי כָּל חַי תָּמִיד לְעוֹלָם וָעֶד. כַּכָּתוּב: וְאָכַלְתָּ וְשָׂבַעְתָּ וּבֵרַכְתָּ אֶת ה' אֱלֹהֵיךָ עַל הָאָרֶץ הַטּוֹבָה אֲשֶּׁר נָתַן לָךְ. בָּרוּךְ אַתָּה ה', עַל הָאָרֶץ וְעַל הַמָּזוֹן:")
(def cont-eng-7 "And for everything, Lord our God, we thank You and bless You; may Your name be blessed by the mouth of all life, constantly forever and always, as it is written (Deuteronomy 8:10); \"And you shall eat and you shall be satiated and you shall bless the Lord your God for the good land that He has given you.\" Blessed are You, Lord, for the land and for the nourishment.")

(def birkat-hamazon
  (general-content-with-instruction
   "Birkat Hamazon"
   ascents-heb ascents-eng
   (instruction instr-heb-1 instr-eng-1)
   (instruction instr-heb-2 instr-eng-2)
   (general-content cont-heb-1 cont-eng-1)
   (general-content cont-heb-2 cont-eng-2)
   (instruction instr-heb-3 instr-eng-3)
   (general-content cont-heb-3 cont-eng-3)
   (instruction instr-heb-4 instr-eng-4)
   (general-content cont-heb-4 cont-eng-4)
   (instruction instr-heb-5 instr-eng-5)
   (bracha cont-heb-5 cont-eng-5)
   (general-content cont-heb-6 cont-eng-6)
   (bracha cont-heb-7 cont-eng-7)
   ;; (instruction instr-heb-6 instr-eng-6)
   ;; (instruction instr-heb-7 instr-eng-7)
   ;; (bracha bracha-heb-1 bracha-eng-1)
   ;; (general-content cont-heb-8 cont-eng-8)
   ;; (bracha bracha-heb-2 bracha-eng-2)
   ;; (general-content cont-heb-9 cont-eng-9)
   ;; (instruction instr-heb-8 instr-eng-8)
   ;; (general-content cont-heb-10 cont-eng-10)

   )
  )
