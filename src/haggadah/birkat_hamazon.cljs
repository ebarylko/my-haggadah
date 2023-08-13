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


(def cont-heb-8 "רַחֵם נָא ה' אֱלֹהֵינוּ עַל יִשְׂרָאַל עַמֶּךָ וְעַל יְרוּשָׁלַיִם עִירֶךָ וְעַל צִיּוֹן מִשְׁכַּן כְּבוֹדֶךָ וְעַל מַלְכוּת בֵּית דָּוִד מְשִׁיחֶךָ וְעַל הַבַּיִת הַגָּדוֹל וְהַקָּדוֹשׁ שֶׁנִּקְרָא שִׁמְךָ עָלָיו: אֱלֹהֵינוּ אָבִינוּ, רְעֵנוּ זוּנֵנוּ פַרְנְסֵנוּ וְכַלְכְּלֵנוּ וְהַרְוִיחֵנוּ, וְהַרְוַח לָנוּ ה' אֱלֹהֵינוּ מְהֵרָה מִכָּל צָרוֹתֵינוּ. וְנָא אַל תַּצְרִיכֵנוּ ה' אֱלֹהֵינוּ, לֹא לִידֵי מַתְּנַת בָּשָׂר וָדָם וְלֹא לִידֵי הַלְוָאתָם, כִּי אִם לְיָדְךָ הַמְּלֵאָה הַפְּתוּחָה הַקְּדוֹשָׁה וְהָרְחָבָה, שֶׁלֹא נֵבוֹשׁ וְלֹא נִכָּלֵם לְעוֹלָם וָעֶד.")

(def cont-eng-8 "Please have mercy, Lord our God, upon Israel, Your people; and upon Jerusalem, Your city; and upon Zion, the dwelling place of Your Glory; and upon the monarchy of the House of David, Your appointed one; and upon the great and holy house that Your name is called upon. Our God, our Father, tend us, sustain us, provide for us, relieve us and give us quick relief, Lord our God, from all of our troubles. And please do not make us needy, Lord our God, not for the gifts of flesh and blood, and not for their loans, but rather from Your full, open, holy and broad hand, so that we not be embarrassed and we not be ashamed forever and always.")

(def instr-heb-6 "בשבת מוסיפין:")
(def instr-eng-6 "On Shabbat, we add the following paragraph")


(def cont-heb-9 "רְצֵה וְהַחֲלִיצֵנוּ ה' אֱלֹהֵינוּ בְּמִצְוֹתֶיךָ וּבְמִצְוַת יוֹם הַשְּׁבִיעִי הַשַּׁבָּת הַגָּדול וְהַקָּדוֹשׂ הַזֶּה. כִּי יוֹם זֶה גָּדוֹל וְקָדוֹשׁ הוּא לְפָנֶיךָ לִשְׁבָּת בּוֹ וְלָנוּחַ בּוֹ בְּאַהֲבָה כְּמִצְוַת רְצוֹנֶךָ. וּבִרְצוֹנְךָ הָנִיחַ לָנוּ ה' אֱלֹהֵינוּ שֶׁלֹּא תְהֵא צָרָה וְיָגוֹן וַאֲנָחָה בְּיוֹם מְנוּחָתֵנוּ. וְהַרְאֵנוּ ה' אֱלֹהֵינוּ בְּנֶחָמַת צִיּוֹן עִירֶךָ וּבְבִנְיַן יְרוּשָׁלַיִם עִיר קָדְשֶׁךָ כִּי אַתָּה הוּא בַּעַל הַיְשׁוּעוֹת וּבַעַל הַנֶּחָמוֹת.")

(def cont-eng-9 "May You be pleased to embolden us, Lord our God, in your commandments and in the command of the seventh day, of this great and holy Shabbat, since this day is great and holy before You, to cease work upon it and to rest upon it, with love, according to the commandment of Your will. And with Your will, allow us, Lord our God, that we should not have trouble, and grief and sighing on the day of our rest. And may You show us, Lord our God, the consolation of Zion, Your city; and the building of Jerusalem, Your holy city; since You are the Master of salvations and the Master of consolations.")


(def cont-heb-10 "אֱלֹהֵינוּ וֵאלֹהֵי אֲבוֹתֵינוּ, יַעֲלֶה וְיָבֹא וְיַגִּיעַ וְיֵרָאֶה וְיֵרָצֶה וְיִשָּׁמַע וְיִפָּקֵד וְיִזָּכֵר זִכְרוֹנֵנוּ וּפִקְדּוֹנֵנוּ, וְזִכְרוֹן אֲבוֹתֵינוּ, וְזִכְרוֹן מָשִׁיחַ בֶּן דָּוִד עַבְדֶּךָ, וְזִכְרוֹן יְרוּשָׁלַיִם עִיר קָדְשֶׁךָ, וְזִכְרוֹן כָּל עַמְּךָ בֵּית יִשְׂרָאַל לְפָנֶיךָ, לִפְלֵיטָה לְטוֹבָה לְחֵן וּלְחֶסֶד וּלְרַחֲמִים, לְחַיִּים וּלְשָׁלוֹם בְּיוֹם חַג הַמַּצּוֹת הַזֶּה זָכְרֵנוּ ה' אֱלֹהֵינוּ בּוֹ לְטוֹבָה וּפָקְדֵנוּ בוֹ לִבְרָכָה וְהושִׁיעֵנוּ בוֹ לְחַיִּים. וּבִדְבַר יְשׁוּעָה וְרַחֲמִים חוּס וְחָנֵּנוּ וְרַחֵם עָלֵינוּ וְהוֹשִׁיעֵנוּ, כִּי אֵלֶיךָ עֵינֵינוּ, כִּי אֵל מֶלֶךְ חַנּוּן וְרַחוּם אָתָּה. וּבְנֵה יְרוּשָׁלַיִם עִיר הַקֹּדֶשׁ בִּמְהֵרָה בְיָמֵינוּ. בָּרוּךְ אַתָּה ה', בּוֹנֶה בְרַחֲמָיו יְרוּשָׁלַיִם. אָמֵן.")

(def cont-eng-10  "God and God of our ancestors, may there ascend and come and reach and be seen and be acceptable and be heard and be recalled and be remembered - our remembrance and our recollection; and the remembrance of our ancestors; and the remembrance of the messiah, the son of David, Your servant; and the remembrance of Jerusalem, Your holy city; and the remembrance of all Your people, the house of Israel - in front of You, for survival, for good, for grace, and for kindness, and for mercy, for life and for peace on this day of the Festival of Matsot. Remember us, Lord our God, on it for good and recall us on it for survival and save us on it for life, and by the word of salvation and mercy, pity and grace us and have mercy on us and save us, since our eyes are upon You, since You are a graceful and merciful Power. And may You build Jerusalem, the holy city, quickly and in our days. Blessed are You, Lord, who builds Jerusalem in His mercy. Amen.")

(def cont-heb-11 "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, הָאֵל אָבִינוּ מַלְכֵּנוּ אַדִירֵנוּ בּוֹרְאֵנוּ גּוֹאֲלֵנוּ יוֹצְרֵנוּ קְדוֹשֵׁנוּ קְדוֹשׁ יַעֲקֹב רוֹעֵנוּ רוֹעֵה יִשְׂרָאַל הַמֶּלֶךְ הַטּוֹב וְהַמֵּטִיב לַכּל שֶׁבְּכָל יוֹם וָיוֹם הוּא הֵטִיב, הוּא מֵטִיב, הוּא יֵיטִיב לָנוּ. הוּא גְמָלָנוּ הוּא גוֹמְלֵנוּ הוּא יִגְמְלֵנוּ לָעַד, לְחֵן וּלְחֶסֶד וּלְרַחֲמִים וּלְרֶוַח הַצָּלָה וְהַצְלָחָה, בְּרָכָה וִישׁוּעָה נֶחָמָה פַּרְנָסָה וְכַלְכָּלָה וְרַחֲמִים וְחַיִּים וְשָׁלוֹם וְכָל טוֹב, וּמִכָּל טוּב לְעוֹלָם עַל יְחַסְּרֵנוּ.")

(def cont-eng-11  "Blessed are You, Lord our God, King of the Universe, the Power, our Father, our King, our Mighty One, our Creator, our Redeemer, our Shaper, our Holy One, the Holy One of Ya'akov, our Shepherd, the Shepherd of Israel, the good King, who does good to all, since on every single day He has done good, He does good, He will do good, to us; He has granted us, He grants us, He will grant us forever - in grace and in kindness, and in mercy, and in relief - rescue and success, blessing and salvation, consolation, provision and relief and mercy and life and peace and all good; and may we not lack any good ever.")


(def cont-heb-12 "הָרַחֲמָן הוּא יִמְלוֹךְ עָלֵינוּ לְעוֹלָם וָעֶד. הָרַחֲמָן הוּא יִתְבָּרַךְ בַּשָּׁמַיִם וּבָאָרֶץ. הָרַחֲמָן הוּא יִשְׁתַּבַּח לְדוֹר דּוֹרִים, וְיִתְפָּאַר בָּנוּ לָעַד וּלְנֵצַח נְצָחִים, וְיִתְהַדַּר בָּנוּ לָעַד וּלְעוֹלְמֵי עוֹלָמִים. הָרַחֲמָן הוּא יְפַרְנְסֵנוּ בְּכָבוֹד. הָרַחֲמָן הוּא יִשְׁבּוֹר עֻלֵּנוּ מֵעַל צַּוָּארֵנוּ, וְהוּא יוֹלִיכֵנוּ קוֹמְמִיּוּת לְאַרְצֵנוּ. הָרַחֲמָן הוּא יִשְׁלַח לָנוּ בְּרָכָה מְרֻבָּה בַּבַּיִת הַזֶּה, וְעַל שֻׁלְחָן זֶה שֶׁאָכַלְנוּ עָלָיו. הָרַחֲמָן הוּא יִשְׁלַח לָנוּ אֶת אֵלִיָּהוּ הַנָּבִיא זָכוּר לַטּוֹב, וִיבַשֶּׂר לָנוּ בְּשׂוֹרוֹת טוֹבוֹת יְשׁוּעוֹת וְנֶחָמוֹת. הָרַחֲמָן הוּא יְבָרֵךְ אֶת בַּעֲלִי / אִשְתִּי. הָרַחֲמָן הוּא יְבָרֵךְ אֶת [אָבִי מוֹרִי] בַּעַל הַבַּיִת הַזֶּה. וְאֶת [אִמִּי מוֹרָתִי] בַּעֲלַת הַבַּיִת הַזֶּה, אוֹתָם וְאֶת בֵּיתָם וְאֶת זַרְעָם וְאֶת כָּל אֲשֶׁר לָהֶם. אוֹתָנוּ וְאֶת כָּל אֲשֶׁר לָנוּ, כְּמוֹ שֶׁנִּתְבָּרְכוּ אֲבוֹתֵינוּ אַבְרָהָם יִצְחָק וְיַעֲקֹב בַּכֹּל מִכֹּל כֹּל, כֵּן יְבָרֵךְ אוֹתָנוּ כֻּלָּנוּ יַחַד בִּבְרָכָה שְׁלֵמָה, וְנֹאמַר, אָמֵן. בַּמָּרוֹם יְלַמְּדוּ עֲלֵיהֶם וְעָלֵינוּ זְכוּת שֶׁתְּהֵא לְמִשְׁמֶרֶת שָׁלוֹם. וְנִשָּׂא בְרָכָה מֵאֵת ה', וּצְדָקָה מֵאלֹהֵי יִשְׁעֵנוּ, וְנִמְצָא חֵן וְשֵׂכֶל טוֹב בְּעֵינֵי אֱלֹהִים וְאָדָם. בשבת: הָרַחֲמָן הוּא יַנְחִילֵנוּ יוֹם שֶׁכֻּלּוֹ שַׁבָּת וּמְנוּחָה לְחַיֵּי הָעוֹלָמִים. הָרַחֲמָן הוּא יַנְחִילֵנוּ יוֹם שֶׁכֻּלוֹ טוֹב.[יוֹם שֶׁכֻּלוֹ אָרוּךְ. יוֹם שֶׁצַּדִּיקִים יוֹשְׁבִים וְעַטְרוֹתֵיהֶם בְּרָאשֵׁיהֶם וְנֶהֱנִים מִזִּיו הַשְּׁכִינָה וִיהִי חֶלְקֵינוּ עִמָּהֶם]. הָרַחֲמָן הוּא יְזַכֵּנוּ לִימוֹת הַמָּשִׁיחַ וּלְחַיֵּי הָעוֹלָם הַבָּא. מִגְדּוֹל יְשׁוּעוֹת מַלְכּוֹ וְעֹשֶׂה חֶסֶד לִמְשִׁיחוֹ לְדָוִד וּלְזַרְעוֹ עַד עוֹלָם. עשֶׂה שָׁלוֹם בִּמְרוֹמָיו, הוּא יַעֲשֶׂה שָׁלוֹם עָלֵינוּ וְעַל כָּל יִשְׂרָאַל וְאִמְרוּ, אָמֵן. יִרְאוּ אֶת ה' קְדֹשָׁיו, כִּי אֵין מַחְסוֹר לִירֵאָיו. כְּפִירִים רָשׁוּ וְרָעֵבוּ, וְדֹרְשֵׁי ה' לֹא יַחְסְרוּ כָל טוֹב. הוֹדוּ לַיי כִּי טוֹב כִּי לְעוֹלָם חַסְדּוֹ. פּוֹתֵחַ אֶת יָדֶךָ, וּמַשְׂבִּיעַ לְכָל חַי רָצוֹן. בָּרוּךְ הַגֶּבֶר אֲשֶׁר יִבְטַח בַּיי, וְהָיָה ה' מִבְטַחוֹ. נַעַר הָיִיתִי גַם זָקַנְתִּי, וְלֹא רָאִיתִי צַדִּיק נֶעֱזָב, וְזַרְעוֹ מְבַקֶּשׁ לָחֶם. יי עֹז לְעַמּוֹ יִתֵּן, ה' יְבָרֵךְ אֶת עַמּוֹ בַשָּׁלוֹם.")

(def cont-eng-12  "May the Merciful One reign over us forever and always. May the Merciful One be blessed in the heavens and in the earth. May the Merciful One be praised for all generations, and exalted among us forever and ever, and glorified among us always and infinitely for all infinities.  May the Merciful One sustain us honorably. May the Merciful One break our yoke from upon our necks and bring us upright to our land. May the Merciful One send us multiple blessing, to this home and upon this table upon which we have eaten. May the Merciful One send us Eliyahu the prophet - may he be remembered for good - and he shall announce to us tidings of good, of salvation and of consolation. May the Merciful One bless my husband/my wife. May the Merciful One bless [my father, my teacher,] the master of this home and [my mother, my teacher,] the mistress of this home, they and their home and their offspring and everything that is theirs. Us and all that is ours; as were blessed Avraham, Yitschak and Ya'akov, in everything, from everything, with everything, so too should He bless us, all of us together, with a complete blessing and we shall say, Amen. From above, may they advocate upon them and upon us merit, that should protect us in peace; and may we carry a blessing from the Lord and charity from the God of our salvation; and find grace and good understanding in the eyes of God and man. [On Shabbat, we say: May the Merciful One give us to inherit the day that will be completely Shabbat and rest in everlasting life.] May the Merciful One give us to inherit the day that will be all good. [The day that is all long, the day that the righteous will sit and their crowns will be on their heads and they will enjoy the radiance of the Divine presence and my our share be with them.] May the Merciful One give us merit for the times of the messiah and for life in the world to come. A tower of salvations is our King; may He do kindness with his messiah, with David and his offspring, forever (II Samuel 22:51). The One who makes peace above, may He make peace upon us and upon all of Israel; and say, Amen. Fear the Lord, His holy ones, since there is no lacking for those that fear Him. Young lions may go without and hunger, but those that seek the Lord will not lack any good thing (Psalms 34:10-11). Thank the Lord, since He is good, since His kindness is forever (Psalms 118:1). You open Your hand and satisfy the will of all living things (Psalms 146:16). Blessed is the man that trusts in the Lord and the Lord is his security (Jeremiah 17:7). I was a youth and I have also aged and I have not seen a righteous man forsaken and his offspring seeking bread (Psalms 37:25). The Lord will give courage to His people. The Lord will bless His people with peace (Psalms 29:11).")

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
   (general-content cont-heb-8 cont-eng-8)
   (instruction instr-heb-6 instr-eng-6)
   (general-content cont-heb-9 cont-eng-9)
   (general-content cont-heb-10 cont-eng-10)
   (bracha cont-heb-11 cont-eng-11)
   (general-content cont-heb-12 cont-eng-12)
   ;; (instruction instr-heb-7 instr-eng-7)
   ;; (bracha bracha-heb-1 bracha-eng-1)
   ;; (bracha bracha-heb-2 bracha-eng-2)
   ;; (instruction instr-heb-8 instr-eng-8)

   )
  )
