(ns haggadah.full-haggadah
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]))


(def kadesh-cont-heb "וַיְהִי עֶרֶב וַיְהִי בֹקֶר יוֹם הַשִּׁשִּׁי. וַיְכֻלּוּ הַשָּׁמַיִם וְהָאָרֶץ וְכָל־צְבָאָם. וַיְכַל אֱלֹהִים בַּיּוֹם הַשְּׁבִיעִי מְלַאכְתּוֹ אֲשֶׁר עָשָׂה וַיִּשְׁבֹּת בַּיּוֹם הַשְּׁבִיעִי מִכָּל מְלַאכְתּוֹ אֲשֶׁר עָשָׂה. וַיְבָרֵךְ אֱלֹהִים אֶת יוֹם הַשְּׁבִיעִי וַיְקַדֵּשׁ אוֹתוֹ כִּי בוֹ שָׁבַת מִכָּל־מְלַאכְתּוֹ אֲשֶׁר בָּרָא אֱלֹהִים לַעֲשׂוֹת.")
(def kadesh-cont-eng "And there was evening and there was morning, the sixth day. And the heaven and the earth were finished, and all their host. And on the seventh day God finished His work which He had done; and He rested on the seventh day from all His work which He had done. And God blessed the seventh day, and sanctified it; because He rested on it from all of His work which God created in doing (Genesis 1:31-2:3).")

(def kadesh-bracha-heb-1 "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן.")
(def kadesh-bracha-eng-1 "Blessed are You, Lord our God, King of the universe, who creates the fruit of the vine.")

(def kadesh-bracha-heb-2 "בָּרוּךְ אַתָּה ה', אֱלהֵינוּ מֶלֶךְ הָעוֹלָם אֲשֶׁר בָּחַר בָּנוּ מִכָּל־עָם וְרוֹמְמָנוּ מִכָּל־לָשׁוֹן וְקִדְּשָׁנוּ בְּמִצְוֹתָיו. וַתִּתֶּן לָנוּ ה' אֱלֹהֵינוּ בְּאַהֲבָה (לשבת: שַׁבָּתוֹת לִמְנוּחָה וּ) מוֹעֲדִים לְשִׂמְחָה, חַגִּים וּזְמַנִּים לְשָׂשוֹן, (לשבת: אֶת יוֹם הַשַּׁבָּת הַזֶּה וְ) אֶת יוֹם חַג הַמַּצּוֹת הַזֶּה זְמַן חֵרוּתֵנוּ, (לשבת: בְּאַהֲבָה) מִקְרָא קֹדֶשׁ זֵכֶר לִיצִיאַת מִצְרָיִם. כִּי בָנוּ בָחַרְתָּ וְאוֹתָנוּ קִדַּשְׁתָּ מִכָּל הָעַמִּים, (לשבת: וְשַׁבָּת) וּמוֹעֲדֵי קָדְשֶׁךָ (לשבת: בְּאַהֲבָה וּבְרָצוֹן) בְּשִׂמְחָה וּבְשָׂשוֹן הִנְחַלְתָּנוּ.")
(def kadesh-bracha-eng-2 "Blessed are You, Lord our God, King of the universe, who has chosen us from all peoples and has raised us above all tongues and has sanctified us with His commandments. And You have given us, Lord our God, [Sabbaths for rest], appointed times for happiness, holidays and special times for joy, [this Sabbath day, and] this Festival of Matsot, our season of freedom [in love] a holy convocation in memory of the Exodus from Egypt. For You have chosen us and sanctified us above all peoples. In Your gracious love, You granted us Your [holy Sabbath, and] special times for happiness and joy.")

(def kadesh-bracha-heb-3 "בָּרוּךְ אַתָּה ה', מְקַדֵּשׁ (לשבת: הַשַׁבָּת וְ) יִשְׂרָאֵל וְהַזְּמַנִּים.")
(def kadesh-bracha-eng-3 "Blessed are You, O Lord, who sanctifies [the Sabbath,] Israel, and the appointed times.")

(def kadesh-bracha-heb-4
  "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, בּוֹרֵא מְאוֹרֵי הָאֵשׁ. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם הַמַּבְדִיל בֵּין קֹדֶשׁ לְחֹל, בֵּין אוֹר לְחשֶׁךְ, בֵּין יִשְׂרָאֵל לָעַמִּים, בֵּין יוֹם הַשְּׁבִיעִי לְשֵׁשֶׁת יְמֵי הַמַּעֲשֶׂה. בֵּין קְדֻשַּׁת שַׁבָּת לִקְדֻשַּׁת יוֹם טוֹב הִבְדַּלְתָּ, וְאֶת־יוֹם הַשְּׁבִיעִי מִשֵּׁשֶׁת יְמֵי הַמַּעֲשֶׂה קִדַּשְׁתָּ. הִבְדַּלְתָּ וְקִדַּשְׁתָּ אֶת־עַמְּךָ יִשְׂרָאֵל בִּקְדֻשָּׁתֶךָ.")
(def kadesh-bracha-eng-4 "Blessed are You, Lord our God, King of the universe, who creates the light of the fire. Blessed are You, Lord our God, King of the universe, who distinguishes between the holy and the profane, between light and darkness, between Israel and the nations, between the seventh day and the six working days. You have distinguished between the holiness of the Sabbath and the holiness of the Festival, and You have sanctified the seventh day above the six working days. You have distinguished and sanctified Your people Israel with Your holiness.")

(def kadesh-bracha-heb-5 "בָּרוּךְ אַתָּה ה', הַמַּבְדִיל בֵּין קֹדֶשׁ לְקֹדֶשׁ.")
(def kadesh-bracha-eng-5 "Blessed are You, O Lord, who distinguishes between the holy and the holy.")

(def kadesh-bracha-heb-6 "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, שֶׁהֶחֱיָנוּ וְקִיְּמָנוּ וְהִגִּיעָנוּ לַזְּמַן הַזֶּה.")
(def kadesh-bracha-eng-6 "Blessed are You, Lord our God, King of the universe, who has granted us life and sustenance and permitted us to reach this season.")


(def instr-heb-1 "מוזגים כוס ראשון. המצּות מכוסות.")
(def instr-eng-1 "We pour the first cup. The matsot are covered")
(def instr-heb-2 "בְּשַׁבָּת מַתְחִילִין")
(def instr-eng-2 "On Shabbat, begin here:")
(def instr-heb-3 "בחול מתחילין:")
(def instr-eng-3 "On weekdays, begin here:")
(def instr-heb-4 "בּמוצאי שבת מוסיפים:")
(def instr-eng-4  "On Saturday night add the following two paragraphs:")
(def instr-heb-5 "שותה בהסיבת שמאל ואינו מברך ברכה אחרונה.")
(def instr-eng-5 "Drink while reclining to the left and do not recite a blessing after drinking.")

(def instr-heb-6 "נוֹטְלִין אֶת הַיָדַיִם וְאֵין מְבָרְכִין ״עַל נְטִילַת יָדַיִם״")
(def instr-eng-6 "Wash your hands but do not say the blessing 'on the washing of the hands.'")

(def instr-heb-7 "לוקח מן הכרפס פחות מכזית – כדי שלא יתחייב בברכה אחרונה – טובל במי מלח, מברך בורא פרי האדמה, ומכווין לפטור בברכה גם את המרור. אוכל בלא הסבה.")
(def instr-eng-7 "Take from the greens less than a kazayit - so that you will not need to say the blessing after eating it; dip it into the salt water; say the blessing 'who creates the fruit of the earth;' and have in mind that this blessing will also be for the bitter herbs.  Eat without reclining.")


(def instr-heb-8 "חותך את המצה האמצעית לשתים, ומצפין את הנתח הגדול לאפיקומן")
(def instr-eng-8 "Split the middle matsah in two, and conceal the larger piece to use it for the afikoman.")

(def instr-heb-9 "מגלה את המצות, מגביה את הקערה ואומר בקול רם:")
(def instr-eng-9 "The leader uncovers the matsot, raises the Seder plate, and says out loud:")

(def instr-heb-10 "מסיר את הקערה מעל השולחן. מוזגין כוס שני. הבן שואל:")
(def instr-eng-10 "He removes the plate from the table. We pour a second cup of wine. The son then asks:")

(def instr-heb-11 "מחזיר את הקערה אל השולחן. המצות תִהיינה מגלות בִשעת אמירת ההגדה.")
(def instr-eng-11 "He puts the plate back on the table. The matsot should be uncovered during the saying of the Haggadah.")

(def urchatz-bracha-heb "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, בּוֹרֵא פְּרִי הָאֲדָמָה.")
(def urchatz-bracha-eng "Blessed are you, Lord our God, King of the universe, who creates the fruit of the earth.")

(def ah-lach-manya-heb "הָא לַחְמָא עַנְיָא דִּי אֲכָלוּ אַבְהָתָנָא בְאַרְעָא דְמִצְרָיִם. כָּל דִכְפִין יֵיתֵי וְיֵיכֹל, כָּל דִצְרִיךְ יֵיתֵי וְיִפְסַח. הָשַּׁתָּא הָכָא, לְשָׁנָה הַבָּאָה בְּאַרְעָא דְיִשְׂרָאֵל. הָשַּׁתָּא עַבְדֵי, לְשָׁנָה הַבָּאָה בְּנֵי חוֹרִין.")

(def ah-lach-manya-eng "This is the bread of destitution that our ancestors ate in the land of Egypt. Anyone who is famished should come and eat, anyone who is in need should come and partake of the Pesach sacrifice. Now we are here, next year we will be in the land of Israel; this year we are slaves, next year we will be free people.")

(def mah-nishtana-eng "מַה נִּשְׁתַּנָּה הַלַּיְלָה הַזֶּה מִכָּל הַלֵּילוֹת? שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין חָמֵץ וּמַצָּה, הַלַּיְלָה הַזֶּה – כֻּלּוֹ מַצָּה. שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין שְׁאָר יְרָקוֹת – הַלַּיְלָה הַזֶּה (כֻּלּוֹ) מָרוֹר. שֶׁבְּכָל הַלֵּילוֹת אֵין אָנוּ מַטְבִּילִין אֲפִילוּ פַּעַם אֶחָת – הַלַּיְלָה הַזֶּה שְׁתֵּי פְעָמִים. שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין בֵּין יוֹשְׁבִין וּבֵין מְסֻבִּין – הַלַּיְלָה הַזֶּה כֻּלָּנוּ מְסֻבִּין.")

(def mah-nishtana-heb "What differentiates this night from all [other] nights? On all [other] nights we eat chamets and matsa; this night, only matsa? On all [other] nights we eat other vegetables; tonight (only) marror. On all [other] nights, we don't dip [our food], even one time; tonight [we dip it] twice. On [all] other nights, we eat either sitting or reclining; tonight we all recline.")

(def avadim-hayinu-heb "עֲבָדִים הָיִינוּ לְפַרְעֹה בְּמִצְרָיִם, וַיּוֹצִיאֵנוּ ה' אֱלֹהֵינוּ מִשָּׁם בְּיָד חֲזָקָה וּבִזְרֹעַ נְטוּיָה. וְאִלּוּ לֹא הוֹצִיא הַקָּדוֹשׁ בָּרוּךְ הוּא אֶת אֲבוֹתֵינוּ מִמִּצְרָיִם, הֲרֵי אָנוּ וּבָנֵינוּ וּבְנֵי בָנֵינוּ מְשֻׁעְבָּדִים הָיִינוּ לְפַרְעֹה בְּמִצְרָיִם. וַאֲפִילוּ כֻּלָּנוּ חֲכָמִים כֻּלָּנוּ נְבוֹנִים כֻּלָּנוּ זְקֵנִים כֻּלָּנוּ יוֹדְעִים אֶת הַתּוֹרָה מִצְוָה עָלֵינוּ לְסַפֵּר בִּיצִיאַת מִצְרָיִם. וְכָל הַמַּרְבֶּה לְסַפֵּר בִּיצִיאַת מִצְרַיִם הֲרֵי זֶה מְשֻׁבָּח.")

(def avadim-hayinu-eng "We were slaves to Pharaoh in the land of Egypt. And the Lord, our God, took us out from there with a strong hand and an outstretched forearm. And if the Holy One, blessed be He, had not taken our ancestors from Egypt, behold we and our children and our children's children would [all] be enslaved to Pharaoh in Egypt. And even if we were all sages, all discerning, all elders, all knowledgeable about the Torah, it would be a commandment upon us to tell the story of the exodus from Egypt. And anyone who adds [and spends extra time] in telling the story of the exodus from Egypt, behold he is praiseworthy.")

(def full-haggadah
  (haggadah "Full Haggadah"
            (section "קַדֵּשׁ"
                     "Kadesh"
                     (instruction instr-heb-1 instr-eng-1)
                     (instruction instr-heb-2 instr-eng-2)
                     (general-content "" kadesh-cont-heb kadesh-cont-eng)
                     (instruction instr-heb-3 instr-eng-3)
                     (bracha "" kadesh-bracha-heb-1 kadesh-bracha-eng-1)
                     (bracha "" kadesh-bracha-heb-2 kadesh-bracha-eng-2)
                     (bracha "" kadesh-bracha-heb-3 kadesh-bracha-eng-3)
                     (instruction instr-heb-4 instr-eng-4)
                     (bracha "" kadesh-bracha-heb-4 kadesh-bracha-eng-4)
                     (bracha "" kadesh-bracha-heb-5 kadesh-bracha-eng-5)
                     (bracha "" kadesh-bracha-heb-6 kadesh-bracha-eng-6)
                     (instruction instr-heb-5 instr-eng-5))
            (section "וּרְחַץ"
                     "Urchatz"
                     (instruction instr-heb-6 instr-eng-6))
            (section "כַּרְפַּס"
                     "Karpas"
                     (instruction instr-heb-7 instr-eng-7)
                     (bracha "" urchatz-bracha-heb urchatz-bracha-eng))
            (section "יַחַץ"
                     "Yachatz"
                     (instruction instr-heb-8 instr-eng-8)
                     )
            (section "מַגִּיד"
                     "Magid"
                     (song "Ha Lachma Anya" "" "")
                     (instruction instr-heb-9 instr-eng-9)
                     (song "" ah-lach-manya-heb ah-lach-manya-eng)
                     (song "The Four Questions" "" "")
                     (instruction instr-heb-10 instr-eng-10)
                     (song "" mah-nishtana-heb mah-nishtana-eng)
                     (song "We Were Slaves in Egypt" "" "")
                     (instruction instr-heb-11 instr-eng-11)
                     (song "" avadim-hayinu-heb avadim-hayinu-eng)
                     )))
