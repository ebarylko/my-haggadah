(ns haggadah.hallel-praise
  (:require [haggadah.dsl :as dsl :refer [song]]))

(def heb-cont-1 "הוֹדוּ לַיי כִּי טוֹב כִּי לְעוֹלָם חַסְדּוֹ. הוֹדוּ לֵאלהֵי הָאֱלהִים כִּי לְעוֹלָם חַסְדּוֹ. הוֹדוּ לָאֲדֹנֵי הָאֲדֹנִים כִּי לְעוֹלָם חַסְדּוֹ. לְעֹשֵׂה נִפְלָאוֹת גְדֹלוֹת לְבַדּוֹ כִּי לְעוֹלָם חַסְדּוֹ. לְעֹשֵׂה הַשָּׁמַיִם בִּתְבוּנָה כִּי לְעוֹלָם חַסְדּוֹ. לְרוֹקַע הָאָרֶץ עַל הַמָּיִם כִּי לְעוֹלָם חַסְדּוֹ. לְעֹשֵׂה אוֹרִים גְּדֹלִים כִּי לְעוֹלָם חַסְדּוֹ. אֶת הַשֶּׁמֶשׁ לְמֶמְשֶׁלֶת בַּיּוֹם כִּי לְעוֹלָם חַסְדּוֹ. אֶת הַיָּרֵחַ וְכוֹכָבִים לְמֶמְשְׁלוֹת בַּלַּיְלָה כִּי לְעוֹלָם חַסְדּוֹ. לְמַכֵּה מִצְרַיִם בִּבְכוֹרֵיהֶם כִּי לְעוֹלָם חַסְדּוֹ. וַיוֹצֵא יִשְׂרָאֵל מִתּוֹכָם כִּי לְעוֹלָם חַסְדּוֹ. בְּיָד חֲזָקָה וּבִזְרוֹעַ נְטוּיָה כִּי לְעוֹלָם חַסְדּוֹ. לְגֹזֵר יַם סוּף לִגְזָרִים כִּי לְעוֹלָם חַסְדּוֹ. וְהֶֶעֱבִיר יִשְׂרָאֵל בְּתוֹכוֹ כִּי לְעוֹלָם חַסְדּוֹ. וְנִעֵר פַּרְעֹה וְחֵילוֹ בְיַם סוּף כִּי לְעוֹלָם חַסְדּוֹ. לְמוֹלִיךְ עַמּוֹ בַּמִּדְבָּר כִּי לְעוֹלָם חַסְדּוֹ. לְמַכֵּה מְלָכִים גְּדֹלִים כִּי לְעוֹלָם חַסְדּוֹ. וַיַּהֲרֹג מְלָכִים אַדִּירִים כִּי לְעוֹלָם חַסְדּוֹ. לְסִיחוֹן מֶלֶךְ הָאֱמֹרִי כִּי לְעוֹלָם חַסְדּוֹ. וּלְעוֹג מֶלֶךְ הַבָּשָׁן כִּי לְעוֹלָם חַסְדּוֹ. וָנָתַן אַרְצָם לְנַחֲלָה כִּי לְעוֹלָם חַסְדּוֹ. נַחֲלָה לְיִשְׂרָאֵל עַבְדוּ כִּי לְעוֹלָם חַסְדּוֹ. שֶׁבְּשִׁפְלֵנוּ זָכַר לָנוּ כִּי לְעוֹלָם חַסְדּוֹ. וַיִפְרְקֵנוּ מִצָּרֵינוּ כִּי לְעוֹלָם חַסְדּוֹ. נֹתֵן לֶחֶם לְכָל בָּשָׂר כִּי לְעוֹלָם חַסְדּוֹ. הוֹדוּ לְאֵל הַשָּׁמַיִם כִּי לְעוֹלָם חַסְדּוֹ.")
(def eng-cont-1  "Thank the Lord, since He is good, since His kindness is forever. Thank the Power of powers since His kindness is forever. To the Master of masters, since His kindness is forever. To the One who alone does wondrously great deeds, since His kindness is forever. To the one who made the Heavens with discernment, since His kindness is forever. To the One who spread the earth over the waters, since His kindness is forever. To the One who made great lights, since His kindness is forever. The sun to rule in the day, since His kindness is forever. The moon and the stars to rule in the night, since His kindness is forever. To the One that smote Egypt through their firstborn, since His kindness is forever. And He took Israel out from among them, since His kindness is forever. With a strong hand and an outstretched forearm, since His kindness is forever. To the One who cut up the Reed Sea into strips, since His kindness is forever. And He made Israel to pass through it, since His kindness is forever. And He jolted Pharaoh and his troop in the Reed Sea, since His kindness is forever. To the One who led his people in the wilderness, since His kindness is forever. To the One who smote great kings, since His kindness is forever. And he killed mighty kings, since His kindness is forever. Sichon, king of the Amorite, since His kindness is forever. And Og, king of the Bashan, since His kindness is forever. And he gave their land as an inheritance, since His kindness is forever. An inheritance for Israel, His servant, since His kindness is forever. That in our lowliness, He remembered us, since His kindness is forever. And he delivered us from our adversaries, since His kindness is forever. He gives bread to all flesh, since His kindness is forever. Thank the Power of the heavens, since His kindness is forever. (Psalms 136)")


(def heb-cont-2 "נִשְׁמַת כָּל חַי תְּבַרֵךְ אֶת שִׁמְךָ, ה' אֱלֹהֵינוּ, וְרוּחַ כָּל בָּשָׂר תְּפָאֵר וּתְרוֹמֵם זִכְרְךָ, מַלְכֵּנוּ, תָמִיד. מִן הָעוֹלָם וְעַד הָעוֹלָם אַתָּה אֵל, וּמִבַּלְעָדֶיךָ אֵין לָנוּ מֶלֶךְ גּוֹאֵל וּמוֹשִיעַ, פּוֹדֶה וּמַצִּיל וּמְפַרְנֵס וּמְרַחֵם בְּכָל עֵת צָרָה וְצוּקָה. אֵין לָנוּ מֶלֶךְ אֶלָּא אַתָּה. אֱלהֵי הָרִאשׁוֹנִים וְהָאַחֲרוֹנִים, אֱלוֹהַּ כָּל בְּרִיּוֹת, אֲדוׁן כָּל תּוֹלָדוֹת, הַמְּהֻלָּל בְּרֹב הַתִּשְׁבָּחוֹת, הַמְנַהֵג עוֹלָמוֹ בְּחֶסֶד וּבְרִיּוֹתָיו בְּרַחֲמִים. וַיי לֹא יָנוּם וְלא יִישָׁן – הַמְּעוֹרֵר יְשֵׁנִים וְהַמֵּקִיץ נִרְדָּמִים, וְהַמֵּשִׂיחַ אִלְּמִים וְהַמַּתִּיר אֲסוּרִים וְהַסּוֹמֵךְ נוֹפְלִים וְהַזּוֹקֵף כְּפוּפִים. לְךָ לְבַדְּךָ אֲנַחְנוּ מוֹדִים.")

(def eng-cont-2 "The soul of every living being shall bless Your Name, Lord our God; the spirit of all flesh shall glorify and exalt Your remembrance always, our King. From the world and until the world, You are the Power, and other than You we have no king, redeemer, or savior, restorer, rescuer, provider, and merciful one in every time of distress and anguish; we have no king, besides You! God of the first ones and the last ones, God of all creatures, Master of all Generations, Who is praised through a multitude of praises, Who guides His world with kindness and His creatures with mercy. The Lord neither slumbers nor sleeps. He who rouses the sleepers and awakens the dozers; He who makes the mute speak, and frees the captives, and supports the falling, and straightens the bent. We thank You alone.")

(def heb-cont-3 "אִלּוּ פִינוּ מָלֵא שִׁירָה כַיָּם, וּלְשׁוֹנֵנוּ רִנָּה כֲּהַמוֹן גַּלָּיו, וְשִׂפְתוֹתֵינוּ שֶׁבַח כְּמֶרְחֲבֵי רָקִיעַ, וְעֵינֵינוּ מְאִירוֹת כַּשֶּׁמֶשׁ וְכַיָּרֵחַ, וְיָדֵינוּ פְרוּשׂות כְּנִשְׂרֵי שָׁמַיִם, וְרַגְלֵינוּ קַלּוֹת כָּאַיָּלוֹת – אֵין אֲנַחְנוּ מַסְפִּיקִים לְהוֹדוֹת לְךָ, ה' אֱלהֵינוּ וֵאלֹהֵי אֲבוֹתֵינוּ, וּלְבָרֵךְ אֶת שִׁמְךָ עַל אַחַת מֵאֶלֶף, אַלְפֵי אֲלָפִים וְרִבֵּי רְבָבוֹת פְּעָמִים הַטּוֹבוֹת שֶׁעָשִׂיתָ עִם אֲבוֹתֵינוּ וְעִמָּנוּ. מִמִּצְרַים גְּאַלְתָּנוּ, ה' אֱלהֵינוּ, וּמִבֵּית עֲבָדִים פְּדִיתָנוּ, בְּרָעָב זַנְתָּנוּ וּבְשָׂבָע כִּלְכַּלְתָּנוּ, מֵחֶרֶב הִצַּלְתָּנוּ וּמִדֶּבֶר מִלַּטְתָּנוּ, וּמֵחָלָיִם רָעִים וְנֶאֱמָנִים דִּלִּיתָנוּ.")

(def eng-cont-3 "Were our mouth as full of song as the sea, and our tongue as full of joyous song as its multitude of waves, and our lips as full of praise as the breadth of the heavens, and our eyes as sparkling as the sun and the moon, and our hands as outspread as the eagles of the sky and our feet as swift as deers - we still could not thank You sufficiently, Lord our God and God of our ancestors, and to bless Your Name for one thousandth of the thousand of thousands of thousands, and myriad myriads, of goodnesses that You performed for our ancestors and for us. From Egypt, Lord our God, did you redeem us and from the house of slaves you restored us. In famine You nourished us, and in plenty you sustained us. From the sword you saved us, and from plague you spared us; and from severe and enduring diseases you delivered us.")

(def heb-cont-4 "עַד הֵנָּה עֲזָרוּנוּ רַחֲמֶיךָ וְלֹא עֲזָבוּנוּ חֲסָדֶיךָ, וְאַל תִּטְּשֵׁנוּ, ה' אֱלהֵינוּ, לָנֶצַח. עַל כֵּן אֵבָרִים שֶׁפִּלַּגְתָּ בָּנוּ וְרוּחַ וּנְשָׁמָה שֶׁנָּפַחְתָּ בְּאַפֵּינוּ וְלָשׁוֹן אֲשֶׁר שַׂמְתָּ בְּפִינוּ – הֵן הֵם יוֹדוּ וִיבָרְכוּ וִישַׁבְּחוּ וִיפָאֲרוּ וִירוֹמְמוּ וְיַעֲרִיצוּ וְיַקְדִּישׁוּ וְיַמְלִיכוּ אֶת שִׁמְךָ מַלְכֵּנוּ. כִּי כָל פֶּה לְךָ יוֹדֶה, וְכָל לָשׁוֹן לְךָ תִּשָּׁבַע, וְכָל בֶּרֶךְ לְךָ תִכְרַע, וְכָל קוֹמָה לְפָנֶיךָ תִשְׁתַּחֲוֶה, וְכָל לְבָבוֹת יִירָאוּךָ, וְכָל קֶרֶב וּכְלָיּוֹת יְזַמֵּרוּ לִשְמֶךָ. כַּדָּבָר שֶׁכָּתוּב, כָּל עַצְמֹתַי תֹּאמַרְנָה, ה' מִי כָמּוֹךָ מַצִּיל עָנִי מֵחָזָק מִמֶּנוּ וְעָנִי וְאֶבְיוֹן מִגּזְלוֹ. מִי יִדְמֶה לָּךְ וּמִי יִשְׁוֶה לָּךְ וּמִי יַעֲרֹךְ לָךְ הָאֵל הַגָּדוֹל, הַגִּבּוֹר וְהַנּוֹרָא, אֵל עֶלְיוֹן, קנֵה שָׁמַיִם וָאָרֶץ. נְהַלֶּלְךָ וּנְשַׁבֵּחֲךָ וּנְפָאֶרְךָ וּנְבָרֵךְ אֶת שֵׁם קָדְשֶׁךָ, כָּאָמוּר: לְדָוִד, בָּרְכִי נַפְשִׁי אֶת ה' וְכָל קְרָבַי אֶת שֵׁם קָדְשׁוֹ. הָאֵל בְּתַעֲצֻמוֹת עֻזֶּךָ, הַגָּדוֹל בִּכְבוֹד שְׁמֶךָ, הַגִּבּוֹר לָנֶצַח וְהַנּוֹרָא בְּנוֹרְאוֹתֶיךָ, הַמֶּלֶךְ הַיּוׁשֵׁב עַל כִּסֵּא רָם וְנִשִֹּא. שׁוֹכֵן עַד מָּרוֹם וְקָּדוֹשׁ שְׁמּוֹ. וְכָתוּב: רַנְּנוּ צַדִּיקִים בַּיי, לַיְשָׁרִים נָאוָה תְהִלָּה. בְּפִי יְשָׁרִים תִּתְהַלָּל, וּבְדִבְרֵי צַדִּיקִים תִּתְבָּרַךְ, וּבִלְשׁוֹן חֲסִידִים תִּתְרוֹמָם, וּבְקֶרֶב קְדושִׁים תִּתְקַדָּשׁ.")

(def eng-cont-4 "Until now Your mercy has helped us, and Your kindness has not forsaken us; and do not abandon us, Lord our God, forever. Therefore, the limbs that You set within us and the spirit and soul that You breathed into our nostrils, and the tongue that You placed in our mouth - verily, they shall thank and bless and praise and glorify, and exalt and revere, and sanctify and coronate Your name, our King. For every mouth shall offer thanks to You; and every tongue shall swear allegiance to You; and every knee shall bend to You; and every upright one shall prostrate himself before You; all hearts shall fear You; and all innermost feelings and thoughts shall sing praises to Your name, as the matter is written (Psalms 35:10), \"All my bones shall say, ‘Lord, who is like You? You save the poor man from one who is stronger than he, the poor and destitute from the one who would rob him.'\" Who is similar to You and who is equal to You and who can be compared to You, O great, strong and awesome Power, O highest Power, Creator of the heavens and the earth. We shall praise and extol and glorify and bless Your holy name, as it is stated (Psalms 103:1), \" [A Psalm] of David. Bless the Lord, O my soul; and all that is within me,  His holy name.\" The Power, in Your powerful boldness; the Great, in the glory of Your name; the Strong One forever; the King who sits on His high and elevated throne. He who dwells always; lofty and holy is His name. And as it is written (Psalms 33:10), \"Sing joyfully to the Lord, righteous ones, praise is beautiful from the upright.\" By the mouth of the upright You shall be praised; By the lips of the righteous shall You be blessed; By the tongue of the devout shall You be exalted; And among the holy shall You be sanctified. ")


(def heb-cont-5 "וּבְמַקְהֲלוֹת רִבְבוֹת עַמְּךָ בֵּית יִשְׂרָאֵל בְּרִנָּה יִתְפָּאֵר שִׁמְךָ, מַלְכֵּנוּ, בְּכָל דּוֹר וָדוֹר, שֶׁכֵּן חוֹבַת כָּל הַיְצוּרִים לְפָנֶיךָ, ה' אֱלהֵינוּ וֵאלֹהֵי אֲבוֹתֵינוּ, לְהוֹדוֹת לְהַלֵּל לְשַׁבֵּחַ, לְפָאֵר לְרוֹמֵם לְהַדֵּר לְבָרֵךְ, לְעַלֵּה וּלְקַלֵּס עַל כָּל דִּבְרֵי שִׁירוֹת וְתִשְׁבְּחוֹת דּוִד בֶּן יִשַׁי עַבְדְּךָ מְשִׁיחֶךָ.")

(def eng-cont-5 "And in the assemblies of the myriads of Your people, the House of Israel, in joyous song will Your name be glorified, our King, in each and every generation; as it is the duty of all creatures, before You, Lord our God, and God of our ancestors, to thank, to praise, to extol, to glorify, to exalt, to  lavish, to bless, to raise high and to acclaim - beyond the words of the songs and praises of David, the son of Yishai, Your servant, Your anointed one.")

(def heb-cont-6 "שְׁתַּבַּח שִׁמְךָ לעַד מַלְכֵּנוּ, הָאֵל הַמֶלֶךְ הַגָּדוֹל וְהַקָּדוֹשׁ בַּשָּׁמַיִם וּבָאָרֶץ, כִּי לְךָ נָאֶה, ה' אֱלֹהֵינוּ וֵאלֹהֵי אֲבוֹתֵינוּ, שִׁיר וּשְׁבָחָה, הַלֵּל וְזִמְרָה, עֹז וּמֶמְשָׁלָה, נֶצַח, גְּדֻלָּה וּגְבוּרָה, תְּהִלָּה וְתִפְאֶרֶת, קְדֻשָּׁה וּמַלְכוּת, בְּרָכוֹת וְהוֹדָאוֹת מֵעַתָּה וְעַד עוֹלָם. בָּרוּךְ אַתָּה ה', אֵל מֶלֶךְ גָּדוֹל בַּתִּשְׁבָּחוֹת, אֵל הַהוֹדָאוֹת, אֲדוֹן הַנִפְלָאוֹת, הַבּוֹחֵר בְּשִׁירֵי זִמְרָה, מֶלֶךְ אֵל חֵי הָעוֹלָמִים.")

(def eng-cont-6 "May Your name be praised forever, our King, the Power, the Great and holy King - in the heavens and in the earth. Since for You it is pleasant - O Lord our God and God of our ancestors - song and lauding, praise and hymn, boldness and dominion, triumph, greatness and strength, psalm and splendor, holiness and kingship, blessings and thanksgivings, from now and forever. Blessed are You Lord, Power, King exalted through laudings, Power of thanksgivings, Master of Wonders, who chooses the songs of hymn - King, Power of the life of the worlds.")

(def praise-and-thanks
  (song "Songs of Praise and Thanks"
                   heb-cont-1 eng-cont-1
                   (song heb-cont-2 eng-cont-2)
                   (song heb-cont-3 eng-cont-3)
                   (song heb-cont-4 eng-cont-4)
                   (song heb-cont-5 eng-cont-5)
                   (song heb-cont-6 eng-cont-6)))
