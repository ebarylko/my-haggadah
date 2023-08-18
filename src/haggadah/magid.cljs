(ns haggadah.magid
  (:require [haggadah.dsl :as dsl :refer [bracha table row instruction song song-with-instruction section general-content general-content-with-instruction]]
            [haggadah.first-fruits :refer [first-fruits-declaration]]
            [haggadah.dayenu :refer [dayenu]]))


(def ah-lach-manya-heb "הָא לַחְמָא עַנְיָא דִּי אֲכָלוּ אַבְהָתָנָא בְאַרְעָא דְמִצְרָיִם. כָּל דִכְפִין יֵיתֵי וְיֵיכֹל, כָּל דִצְרִיךְ יֵיתֵי וְיִפְסַח. הָשַּׁתָּא הָכָא, לְשָׁנָה הַבָּאָה בְּאַרְעָא דְיִשְׂרָאֵל. הָשַּׁתָּא עַבְדֵי, לְשָׁנָה הַבָּאָה בְּנֵי חוֹרִין.")

(def ah-lach-manya-eng "This is the bread of destitution that our ancestors ate in the land of Egypt. Anyone who is famished should come and eat, anyone who is in need should come and partake of the Pesach sacrifice. Now we are here, next year we will be in the land of Israel; this year we are slaves, next year we will be free people.")

(def instr-heb-1 "מגלה את המצות, מגביה את הקערה ואומר בקול רם:")
(def instr-eng-1 "The leader uncovers the matsot, raises the Seder plate, and says out loud:")

(def mah-nishtana-eng "מַה נִּשְׁתַּנָּה הַלַּיְלָה הַזֶּה מִכָּל הַלֵּילוֹת? שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין חָמֵץ וּמַצָּה, הַלַּיְלָה הַזֶּה – כֻּלּוֹ מַצָּה. שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין שְׁאָר יְרָקוֹת – הַלַּיְלָה הַזֶּה (כֻּלּוֹ) מָרוֹר. שֶׁבְּכָל הַלֵּילוֹת אֵין אָנוּ מַטְבִּילִין אֲפִילוּ פַּעַם אֶחָת – הַלַּיְלָה הַזֶּה שְׁתֵּי פְעָמִים. שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין בֵּין יוֹשְׁבִין וּבֵין מְסֻבִּין – הַלַּיְלָה הַזֶּה כֻּלָּנוּ מְסֻבִּין.")

(def mah-nishtana-heb "What differentiates this night from all [other] nights? On all [other] nights we eat chamets and matsa; this night, only matsa? On all [other] nights we eat other vegetables; tonight (only) marror. On all [other] nights, we don't dip [our food], even one time; tonight [we dip it] twice. On [all] other nights, we eat either sitting or reclining; tonight we all recline.")

(def instr-heb-2 "מסיר את הקערה מעל השולחן. מוזגין כוס שני. הבן שואל:")
(def instr-eng-2 "He removes the plate from the table. We pour a second cup of wine. The son then asks:")

(def avadim-hayinu-heb "עֲבָדִים הָיִינוּ לְפַרְעֹה בְּמִצְרָיִם, וַיּוֹצִיאֵנוּ ה' אֱלֹהֵינוּ מִשָּׁם בְּיָד חֲזָקָה וּבִזְרֹעַ נְטוּיָה. וְאִלּוּ לֹא הוֹצִיא הַקָּדוֹשׁ בָּרוּךְ הוּא אֶת אֲבוֹתֵינוּ מִמִּצְרָיִם, הֲרֵי אָנוּ וּבָנֵינוּ וּבְנֵי בָנֵינוּ מְשֻׁעְבָּדִים הָיִינוּ לְפַרְעֹה בְּמִצְרָיִם. וַאֲפִילוּ כֻּלָּנוּ חֲכָמִים כֻּלָּנוּ נְבוֹנִים כֻּלָּנוּ זְקֵנִים כֻּלָּנוּ יוֹדְעִים אֶת הַתּוֹרָה מִצְוָה עָלֵינוּ לְסַפֵּר בִּיצִיאַת מִצְרָיִם. וְכָל הַמַּרְבֶּה לְסַפֵּר בִּיצִיאַת מִצְרַיִם הֲרֵי זֶה מְשֻׁבָּח.")

(def avadim-hayinu-eng "We were slaves to Pharaoh in the land of Egypt. And the Lord, our God, took us out from there with a strong hand and an outstretched forearm. And if the Holy One, blessed be He, had not taken our ancestors from Egypt, behold we and our children and our children's children would [all] be enslaved to Pharaoh in Egypt. And even if we were all sages, all discerning, all elders, all knowledgeable about the Torah, it would be a commandment upon us to tell the story of the exodus from Egypt. And anyone who adds [and spends extra time] in telling the story of the exodus from Egypt, behold he is praiseworthy.")

(def instr-heb-3 "מחזיר את הקערה אל השולחן. המצות תִהיינה מגלות בִשעת אמירת ההגדה.")
(def instr-eng-3 "He puts the plate back on the table. The matsot should be uncovered during the saying of the Haggadah.")

(def story-of-five-rabs-heb-1 "מַעֲשֶׂה בְּרַבִּי אֱלִיעֶזֶר וְרַבִּי יְהוֹשֻׁעַ וְרַבִּי אֶלְעָזָר בֶּן־עֲזַרְיָה וְרַבִּי עֲקִיבָא וְרַבִּי טַרְפוֹן שֶׁהָיוּ מְסֻבִּין בִּבְנֵי־בְרַק וְהָיוּ מְסַפְּרִים בִּיצִיאַת מִצְרַיִם כָּל־אוֹתוֹ הַלַּיְלָה, עַד שֶׁבָּאוּ תַלְמִידֵיהֶם וְאָמְרוּ לָהֶם רַבּוֹתֵינוּ הִגִּיעַ זְמַן קְרִיאַת שְׁמַע שֶׁל שַׁחֲרִית.")

(def story-of-five-rabs-heb-2 "אָמַר רַבִּי אֶלְעָזָר בֶּן־עֲזַרְיָה הֲרֵי אֲנִי כְּבֶן שִׁבְעִים שָׁנָה וְלֹא זָכִיתִי שֶׁתֵּאָמֵר יְצִיאַת מִצְרַיִם בַּלֵּילוֹת עַד שֶׁדְּרָשָׁהּ בֶּן זוֹמָא, שֶׁנֶּאֱמַר, לְמַעַן תִּזְכֹּר אֶת יוֹם צֵאתְךָ מֵאֶרֶץ מִצְרַיִם כֹּל יְמֵי חַיֶּיךָ. יְמֵי חַיֶּיךָ הַיָּמִים. כֹּל יְמֵי חַיֶּיךָ הַלֵּילוֹת. וַחֲכָמִים אוֹמְרִים יְמֵי חַיֶּיךָ הָעוֹלָם הַזֶּה. כֹּל יְמֵי חַיֶּיךָ לְהָבִיא לִימוֹת הַמָּשִׁיחַ:")

(def story-of-five-rabs-eng-1 "It happened once [on Pesach] that Rabbi Eliezer, Rabbi Yehoshua, Rabbi Elazar ben Azariah, Rabbi Akiva and Rabbi Tarfon were reclining in Bnei Brak and were telling the story of the exodus from Egypt that whole night, until their students came and said to them, 'The time of [reciting] the morning Shema has arrived.'")

(def story-of-five-rabs-eng-2 "Rabbi Elazar ben Azariah said, 'Behold I am like a man of seventy years and I have not merited [to understand why] the exodus from Egypt should be said at night until Ben Zoma explicated it, as it is stated (Deuteronomy 16:3), 'In order that you remember the day of your going out from the land of Egypt all the days of your life;' 'the days of your life' [indicates that the remembrance be invoked during] the days, 'all the days of your life' [indicates that the remembrance be invoked also during] the nights.' But the Sages say, ''the days of your life' [indicates that the remembrance be invoked in] this world, 'all the days of your life' [indicates that the remembrance be invoked also] in the days of the Messiah.'")

(def the-four-sons-bracha-heb "בָּרוּךְ הַמָּקוֹם, בָּרוּךְ הוּא, בָּרוּךְ שֶׁנָּתַן תּוֹרָה לְעַמּוֹ יִשְׂרָאֵל, בָּרוּךְ הוּא. כְּנֶגֶד אַרְבָּעָה בָנִים דִּבְּרָה תוֹרָה: אֶחָד חָכָם, וְאֶחָד רָשָׁע, וְאֶחָד תָּם, וְאֶחָד שֶׁאֵינוֹ יוֹדֵעַ לִשְׁאוֹל.")

(def the-four-sons-bracha-eng "Blessed be the Place [of all], Blessed be He; Blessed be the One who Gave the Torah to His people Israel, Blessed be He. Corresponding to four sons did the Torah speak; one [who is] wise, one [who is] evil, one who is innocent and one who doesn't know to ask.")

(def wise-son-eng "What does the wise [son] say? ''What are these testimonies, statutes and judgments that the Lord our God commanded you?' (Deuteronomy 6:20)' And accordingly you will say to him, as per the laws of the Pesach sacrifice, 'We may not eat an afikoman [a dessert or other foods eaten after the meal] after [we are finished eating] the Pesach sacrifice (Mishnah Pesachim 10:8).'")

(def wise-son-heb "חָכָם מָה הוּא אוֹמֵר? מָה הָעֵדוֹת וְהַחֻקִּים וְהַמִּשְׁפָּטִים אֲשֶׁר צִוָּה ה' אֱלֹהֵינוּ אֶתְכֶם. וְאַף אַתָּה אֱמוֹר לוֹ כְּהִלְכוֹת הַפֶּסַח: אֵין מַפְטִירִין אַחַר הַפֶּסַח אֲפִיקוֹמָן:")

(def evil-son-heb
  "רָשָׁע מָה הוּא אוֹמֵר? מָה הָעֲבוֹדָה הַזּאֹת לָכֶם. לָכֶם – וְלֹא לוֹ. וּלְפִי שֶׁהוֹצִיא אֶת עַצְמוֹ מִן הַכְּלָל כָּפַר בְּעִקָּר. וְאַף אַתָּה הַקְהֵה אֶת שִׁנָּיו וֶאֱמוֹר לוֹ: 'בַּעֲבוּר זֶה עָשָׂה ה' לִי בְּצֵאתִי מִמִּצְרָיִם'. לִי וְלֹא־לוֹ. אִלּוּ הָיָה שָׁם, לֹא הָיָה נִגְאָל:")
(def evil-son-eng
  "What does the evil [son] say? \"'What is this worship to you?' (Exodus 12:26)\" 'To you' and not 'to him.' And since he excluded himself from the collective, he denied a principle [of the Jewish faith]. And accordingly, you will blunt his teeth and say to him,  \"'For the sake of this, did the Lord do [this] for me in my going out of Egypt' (Exodus 13:8).\" 'For me' and not 'for him.' If he had been there, he would not have been saved.")

(def innocent-son-heb
  "תָּם מָה הוּא אוֹמֵר? מַה זּאֹת? וְאָמַרְתָּ אֵלָיו ״בְּחוֹזֶק יָד הוֹצִיאָנוּ ה' מִמִּצְרַיִם מִבֵּית עֲבָדִים״")
(def innocent-son-eng
  "What does the innocent [son] say? \"'What is this?' (Exodus 13:14)\" And you will say to him, \"'With the strength of [His] hand did the Lord take us out from Egypt, from the house of slaves' (Exodus 13:14).'\" ")

(def simple-son-heb
  "וְשֶׁאֵינוֹ יוֹדֵעַ לִשְׁאוֹל – אַתְּ פְּתַח לוֹ, שֶׁנֶּאֱמַר, וְהִגַּדְתָּ לְבִנְךָ בַּיּוֹם הַהוּא לֵאמֹר, בַּעֲבוּר זֶה עָשָׂה ה' לִי בְּצֵאתִי מִמִּצְרָיִם")

(def simple-son-eng
  "And [regarding] the one who doesn't know to ask, you will open [the conversation] for him. As it is stated (Exodus 13:8), \"And you will speak to your son on that day saying, for the sake of this, did the Lord do [this] for me in my going out of Egypt.\"")

(def yechol-merosh-heb
  "יָכוֹל מֵראשׁ חֹדֶשׁ? תַּלְמוּד לוֹמַר בַּיּוֹם הַהוּא. אִי בַּיּוֹם הַהוּא יָכוֹל מִבְּעוֹד יוֹם? תַּלְמוּד לוֹמַר בַּעֲבוּר זֶה – בַּעֲבוּר זֶה לֹא אָמַרְתִּי, אֶלָּא בְּשָׁעָה שֶׁיֵּשׁ מַצָּה וּמָרוֹר מֻנָּחִים לְפָנֶיךָ.")

(def yechol-merosh-eng
  "It could be from Rosh Chodesh [that one would have to discuss the Exodus. However] we learn [otherwise, since] it is stated, \"on that day.\" If it is [written] \"on that day,\" it could be from while it is still day [before the night of the fifteenth of Nissan. However] we learn [otherwise, since] it is stated, \"for the sake of this.\"  I didn't say 'for the sake of this' except [that it be observed] when [this] matsa and maror are resting in front of you [meaning, on the night of the fifteenth].")


(def idol-worship-heb-1 "מִתְּחִלָּה עוֹבְדֵי עֲבוֹדָה זָרָה הָיוּ אֲבוֹתֵינוּ, וְעַכְשָׁיו קֵרְבָנוּ הַמָּקוֹם לַעֲבדָתוֹ, שֶׁנֶּאֱמַר: וַיֹאמֶר יְהוֹשֻעַ אֶל־כָּל־הָעָם, כֹּה אָמַר ה' אֱלֹהֵי יִשְׂרָאֵל: בְּעֵבֶר הַנָּהָר יָשְׁבוּ אֲבוֹתֵיכֶם מֵעוֹלָם, תֶּרַח אֲבִי אַבְרָהָם וַאֲבִי נָחוֹר, וַיַּעַבְדוּ אֱלֹהִים אֲחֵרִים.")

(def idol-worship-heb-2 "וָאֶקַּח אֶת־אֲבִיכֶם אֶת־אַבְרָהָם מֵעֵבֶר הַנָּהָר וָאוֹלֵךְ אוֹתוֹ בְּכָל־אֶרֶץ כְּנָעַן, וָאַרְבֶּה אֶת־זַרְעוֹ וָאֶתֵּן לוֹ אֶת־יִצְחָק, וָאֶתֵּן לְיִצְחָק אֶת־יַעֲקֹב וְאֶת־עֵשָׂו. וָאֶתֵּן לְעֵשָׂו אֶת־הַר שֵּׂעִיר לָרֶשֶׁת אתוֹ, וְיַעֲקֹב וּבָנָיו יָרְדוּ מִצְרָיִם.")

(def idol-worship-heb-3 "בָּרוּךְ שׁוֹמֵר הַבְטָחָתוֹ לְיִשְׂרָאֵל, בָּרוּךְ הוּא. שֶׁהַקָּדוֹשׁ בָּרוּךְ הוּא חִשַּׁב אֶת־הַקֵּץ, לַעֲשׂוֹת כְּמוֹ שֶּׁאָמַר לְאַבְרָהָם אָבִינוּ בִּבְרִית בֵּין הַבְּתָרִים, שֶׁנֶּאֱמַר: וַיֹּאמֶר לְאַבְרָם, יָדֹעַ תֵּדַע כִּי־גֵר יִהְיֶה זַרְעֲךָ בְּאֶרֶץ לֹא לָהֶם, וַעֲבָדוּם וְעִנּוּ אֹתָם אַרְבַּע מֵאוֹת שָׁנָה. וְגַם אֶת־הַגּוֹי אֲשֶׁר יַעֲבֹדוּ דָּן אָנֹכִי וְאַחֲרֵי־כֵן יֵצְאוּ בִּרְכֻשׁ גָּדוֹל.")

(def idol-worship-eng-1
  "From the beginning, our ancestors were idol worshipers. And now, the Place [of all] has brought us close to His worship, as it is stated (Joshua 24:2-4), \"Yehoshua said to the whole people, so said the Lord, God of Israel, 'Over the river did your ancestors dwell from always, Terach the father of Avraham and the father of Nachor, and they worshiped other gods.\"")

(def idol-worship-eng-2 "And I took your father, Avraham, from over the river and I made him walk in all the land of Canaan and I increased his seed and I gave him Yitschak. And I gave to Yitschak, Ya'akov and Esav; and I gave to Esav, Mount Seir [in order that he] inherit it; and Yaakov and his sons went down to Egypt.'")

(def idol-worship-eng-3 "Blessed be the One who keeps His promise to Israel, blessed be He; since the Holy One, blessed be He, calculated the end [of the exile,] to do as He said to Avraham, our father, in the Covenant between the Pieces, as it is stated (Genesis 15:13-14), \"And He said to Avram, 'you should surely know that your seed will be a stranger in a land that is not theirs, and they will enslave them and afflict them four hundred years. And also that nation for which they shall toil will I judge, and afterwards they will go out with much property.'\"")


(def instr-heb-4 "מכסה המצה ומגביה את הכוס בידו, ואומר:")
(def instr-eng-4 "He covers the matsa and lifts up the cup and says:")

(def vehi-sheamda-heb "וְהִיא שֶׁעָמְדָה לַאֲבוֹתֵינוּ וְלָנוּ. שֶׁלֹּא אֶחָד בִּלְבָד עָמַד עָלֵינוּ לְכַלּוֹתֵנוּ, אֶלָּא שֶׁבְּכָל דּוֹר וָדוֹר עוֹמְדִים עָלֵינוּ לְכַלוֹתֵנוּ, וְהַקָּדוֹשׁ בָּרוּךְ הוּא מַצִּילֵנוּ מִיָּדָם.")

(def vehi-sheamda-eng "And it is this that has stood for our ancestors and for us; since it is not [only] one [person or nation] that has stood [against] us to destroy us, but rather in each generation, they stand [against] us to destroy us, but the Holy One, blessed be He, rescues us from their hand.")

(def instr-heb-5 "כשאומר דם ואש ותימרות עשן, עשר המכות ודצ״ך עד״ש באח״ב – ישפוך מן הכוס מעט יין:")
(def instr-eng-5 "And when he says, \"blood and fire and pillars of smoke\" and the ten plagues and \"detsakh,\" \"adash\" and \"ba'achab,\" he should pour out a little wine from his cup.")

(def ten-plagues-heb-1 "דָּם וָאֵשׁ וְתִימְרוֹת עָשָׁן.")
(def ten-plagues-eng-1 "blood and fire and pillars of smoke.")


(def ten-plagues-heb-2 "דָבָר אַחֵר: בְּיָד חֲזָקָה שְׁתַּיִם, וּבִזְרֹעַ נְטוּיָה שְׁתַּיִם, וּבְמֹרָא גָּדֹל – שְׁתַּיִם, וּבְאֹתוֹת – שְׁתַּיִם, וּבְמֹפְתִים – שְׁתַּיִם.")

(def ten-plagues-eng-2 "Another [explanation]: \"With a strong hand\" [corresponds to] two [plagues]; \"and with an outstretched forearm\" [corresponds to] two [plagues]; \"and with great awe\" [corresponds to] two [plagues]; \"and with signs\" [corresponds to] two [plagues]; \"and with wonders\" [corresponds to] two [plagues].")


(def ten-plagues-heb-3 "אֵלּוּ עֶשֶׂר מַכּוֹת שֶׁהֵבִיא הַקָּדוֹשׁ בָּרוּךְ הוּא עַל־הַמִּצְרִים בְּמִצְרַיִם, וְאֵלוּ הֵן:")

(def ten-plagues-eng-3 "These are [the] ten plagues that the Holy One, blessed be He, brought on the Egyptians in Egypt and they are:")

(def plagues-heb (reverse ["מַכַּת בְּכוֹרוֹת" "חשֶׁךְ" "אַרְבֶּה" "בָּרָד" "שְׁחִין" "דֶּבֶר" "עָרוֹב" "כִּנִּים" "צְפַרְדֵּעַ" "דָּם"] ))
(def plagues-eng ["Blood"
                  "Frogs"
                  "Lice"
                  "[The] Mixture [of Wild Animals]"
                  "Pestilence"
                  "Boils"
                  "Hail"
                  "Locusts"
                  "Darkness"
                  "Slaying of [the] Firstborn"])

(def plague-rows (map (comp row vector) plagues-eng plagues-heb))

(def rabbi-yehuda-heb "רַבִּי יְהוּדָה הָיָה נוֹתֵן בָּהֶם סִמָּנִים: דְּצ״ךְ עַד״שׁ בְּאַח״ב.")

(def rabbi-yehuda-eng "Rabbi Yehuda was accustomed to giving [the plagues] mnemonics: Detsakh [the Hebrew initials of the first three plagues], Adash [the Hebrew initials of the second three plagues], Beachav [the Hebrew initials of the last four plagues].")

(def rabbi-yose-heb "רַבִּי יוֹסֵי הַגְּלִילִי אוֹמֵר: מִנַּיִן אַתָּה אוֹמֵר שֶׁלָּקוּ הַמִּצְרִים בְּמִצְרַיִם עֶשֶׂר מַכּוֹת וְעַל הַיָּם לָקוּ חֲמִשִּׁים מַכּוֹת? בְּמִצְרַיִם מַה הוּא אוֹמֵר? וַיֹּאמְרוּ הַחַרְטֻמִּם אֶל פַּרְעֹה: אֶצְבַּע אֱלֹהִים הִוא, וְעַל הַיָּם מָה הוּא אוֹמֵר? וַיַּרְא יִשְׂרָאֵל אֶת־הַיָּד הַגְּדֹלָה אֲשֶׁר עָשָׂה ה' בְּמִצְרַיִם, וַיִּירְאוּ הָעָם אֶת־ה', וַיַּאֲמִינוּ בַּיי וּבְמשֶׁה עַבְדוֹ. כַּמָה לָקוּ בְאֶצְבַּע? עֶשֶׂר מַכּוֹת. אֱמוֹר מֵעַתָּה: בְּמִצְרַים לָקוּ עֶשֶׂר מַכּוֹת וְעַל הַיָּם לָקוּ חֲמִשִּׁים מַכּוֹת.")

(def rabbi-yose-eng "Rabbi Yose Hagelili says, \"From where can you [derive] that the Egyptians were struck with ten plagues in Egypt and struck with fifty plagues at the Sea? In Egypt, what does it state? 'Then the magicians said unto Pharaoh: ‘This is the finger of God' (Exodus 8:15). And at the Sea, what does it state? 'And Israel saw the Lord's great hand that he used upon the Egyptians, and the people feared the Lord; and they believed in the Lord, and in Moshe, His servant' (Exodus 14:31). How many were they struck with with the finger? Ten plagues. You can say from here that in Egypt, they were struck with ten plagues and at the Sea, they were struck with fifty plagues.\" ")

(def rabbi-eliezer-heb "רַבִּי אֱלִיעֶזֲר אוֹמֵר: מִנַּיִן שֶׁכָּל־מַכָּה וּמַכָּה שֶׁהֵבִיא הַקָּדוֹשׁ בָּרוּךְ הוּא עַל הַמִּצְרִים בְּמִצְרַיִם הָיְתָה שֶׁל אַרְבַּע מַכּוֹת? שֶׁנֶּאֱמַר: יְשַׁלַּח־בָּם חֲרוֹן אַפּוֹ, עֶבְרָה וָזַעַם וְצָרָה, מִשְׁלַחַת מַלְאֲכֵי רָעִים. עֶבְרָה – אַחַת, וָזַעַם – שְׁתַּיִם, וְצָרָה – שָׁלשׁ, מִשְׁלַחַת מַלְאֲכֵי רָעִים – אַרְבַּע. אֱמוֹר מֵעַתָּה: בְּמִצְרַיִם לָקוּ אַרְבָּעִים מַכּוֹת וְעַל הַיָּם לָקוּ מָאתַיִם מַכּוֹת.")

(def rabbi-eliezer-eng " Rabbi Eliezer says, \"From where [can you derive] that every plague that the Holy One, blessed be He, brought upon the Egyptians in Egypt was [composed] of four plagues? As it is stated (Psalms 78:49): 'He sent upon them the fierceness of His anger, wrath, and fury, and trouble, a sending of messengers of evil.' 'Wrath' [corresponds to] one; 'and fury' [brings it to] two; 'and trouble' [brings it to] three; 'a sending of messengers of evil' [brings it to] four. You can say from here that in Egypt, they were struck with forty plagues and at the Sea, they were struck with two hundred plagues.\" ")

(def rabbi-akiva-heb "רַבִּי עֲקִיבָא אוֹמֵר: מִנַּיִן שֶׁכָּל־מַכָּה וּמַכָּה שֶהֵבִיא הַקָּדוֹשׁ בָּרוּךְ הוּא עַל הַמִּצְרִים בְּמִצְרַיִם הָיְתָה שֶׁל חָמֵשׁ מַכּוֹת? שֶׁנֶּאֱמַר: יְִשַׁלַּח־בָּם חֲרוֹן אַפּוֹ, עֶבְרָה וָזַעַם וְצַרָה, מִשְׁלַחַת מַלְאֲכֵי רָעִים. חֲרוֹן אַפּוֹ – אַחַת, עֶבְרָה – שְׁתָּיִם, וָזַעַם – שָׁלוֹשׁ, וְצָרָה – אַרְבַּע, מִשְׁלַחַת מַלְאֲכֵי רָעִים – חָמֵשׁ. אֱמוֹר מֵעַתָּה: בְּמִצְרַיִם לָקוּ חֲמִשִּׁים מַכּות וְעַל הַיָּם לָקוּ חֲמִשִּׁים וּמָאתַיִם מַכּוֹת.")

(def rabbi-akiva-eng " Rabbi Akiva says, says, \"From where [can you derive] that every plague that the Holy One, blessed be He, brought upon the Egyptians in Egypt was [composed] of five plagues? As it is stated (Psalms 78:49): 'He sent upon them the fierceness of His anger, wrath, and fury, and trouble, a sending of messengers of evil.' 'The fierceness of His anger' [corresponds to] one; 'wrath' [brings it to] two; 'and fury' [brings it to] three; 'and trouble' [brings it to] four; 'a sending of messengers of evil' [brings it to] five. You can say from here that in Egypt, they were struck with fifty plagues and at the Sea, they were struck with two hundred and fifty plagues.\" ")

(def rabbi-gamliel-heb-1 "רַבָּן גַּמְלִיאֵל הָיָה אוֹמֵר: כָּל שֶׁלֹּא אָמַר שְׁלשָׁה דְּבָרִים אֵלּוּ בַּפֶּסַח, לא יָצָא יְדֵי חוֹבָתוֹ, וְאֵלּוּ הֵן: פֶּסַח, מַצָּה, וּמָרוֹר.")
(def rabbi-gamliel-eng-1 "Rabban Gamliel was accustomed to say, Anyone who has not said these three things on Pesach has not fulfilled his obligation, and these are them: the Pesach sacrifice, matsa and marror.")


(def rabbi-gamliel-heb-2 "פֶּסַח שֶׁהָיוּ אֲבוֹתֵינוּ אוֹכְלִים בִּזְמַן שֶׁבֵּית הַמִּקְדָּשׁ הָיָה קַיָּם, עַל שׁוּם מָה? עַל שׁוּם שֶׁפָּסַח הַקָּדוֹשׁ בָּרוּךְ הוּא עַל בָּתֵּי אֲבוֹתֵינוּ בְּמִצְרַיִם, שֶׁנֶּאֱמַר: וַאֲמַרְתֶּם זֶבַח פֶּסַח הוּא לַיי, אֲשֶׁר פָּסַח עַל בָּתֵּי בְנֵי יִשְׂרָאֵל בְּמִצְרַיִם בְּנָגְפּוֹ אֶת־מִצְרַיִם, וְאֶת־בָּתֵּינוּ הִצִּיל וַיִּקֹּד הָעָם וַיִּשְׁתַּחווּ.")

(def rabbi-gamliel-eng-2 "The Pesach [passover] sacrifice that our ancestors were accustomed to eating when the Temple existed, for the sake of what [was it]? For the sake [to commemorate] that the Holy One, blessed be He, passed over the homes of our ancestors in Egypt, as it is stated (Exodus 12:27); \"And you shall say: 'It is the passover sacrifice to the Lord, for that He passed over the homes of the Children of Israel in Egypt, when He smote the Egyptians, and our homes he saved.’ And the people bowed the head and bowed.\"")

(def instr-heb-6 "אוחז המצה בידו ומראה אותה למסובין:")
(def instr-eng-6 "He holds the matsa in his hand and shows it to the others there.")

(def rabbi-gamliel-heb-3 "מַצָּה זוֹ שֶׁאָנוֹ אוֹכְלִים, עַל שׁוּם מַה? עַל שׁוּם שֶׁלֹּא הִסְפִּיק בְּצֵקָם שֶׁל אֲבוֹתֵינוּ לְהַחֲמִיץ עַד שֶׁנִּגְלָה עֲלֵיהֶם מֶלֶךְ מַלְכֵי הַמְּלָכִים, הַקָּדוֹשׁ בָּרוּךְ הוּא, וּגְאָלָם, שֶׁנֶּאֱמַר: וַיֹּאפוּ אֶת־הַבָּצֵק אֲשֶׁר הוֹצִיאוּ מִמִּצְרַיִם עֻגֹת מַצּוֹּת, כִּי לֹא חָמֵץ, כִּי גֹרְשׁוּ מִמִּצְרַיִם וְלֹא יָכְלוּ לְהִתְמַהְמֵהַּ, וְגַם צֵדָה לֹא עָשׂוּ לָהֶם.")
(def rabbi-gamliel-eng-3 " This matsa that we are eating, for the sake of what [is it]? For the sake [to commemorate] that our ancestors' dough was not yet able to rise, before the King of the kings of kings, the Holy One, blessed be He, revealed [Himself] to them and redeemed them, as it is stated (Exodus 12:39); \"And they baked the dough which they brought out of Egypt into matsa cakes, since it did not rise; because they were expelled from Egypt, and could not tarry, neither had they made for themselves provisions.\"")


(def instr-heb-7 "אוחז המרור בידו ומראה אותו למסובין:")
(def instr-eng-7 "He holds the marror in his hand and shows it to the others there.")


(def rabbi-gamliel-heb-4 "מָרוֹר זֶה שֶׁאָנוּ אוֹכְלִים, עַל שׁוּם מַה? עַל שׁוּם שֶׁמֵּרְרוּ הַמִּצְרִים אֶת־חַיֵּי אֲבוֹתֵינוּ בְּמִצְרַיִם, שֶׁנֶּאֱמַר: וַיְמָרְרוּ אֶת חַיֵּיהם בַּעֲבֹדָה קָשָה, בְּחֹמֶר וּבִלְבֵנִים וּבְכָל־עֲבֹדָה בַּשָּׂדֶה אֶת כָּל עֲבֹדָתָם אֲשֶׁר עָבְדוּ בָהֶם בְּפָרֶךְ.")
(def rabbi-gamliel-eng-4 " This marror [bitter greens] that we are eating, for the sake of what [is it]? For the sake [to commemorate] that the Egyptians embittered the lives of our ancestors in Egypt, as it is stated (Exodus 1:14); \"And they made their lives bitter with hard service, in mortar and in brick, and in all manner of service in the field; in all their service, wherein they made them serve with rigor.\"")


(def rabbi-gamliel-heb-5 "בְּכָל־דּוֹר וָדוֹר חַיָּב אָדָם לִרְאוֹת אֶת־עַצְמוֹ כְּאִלּוּ הוּא יָצָא מִמִּצְרַיִם, שֶׁנֶּאֱמַר: וְהִגַּדְתָּ לְבִנְךָ בַּיּוֹם הַהוּא לֵאמֹר, בַּעֲבוּר זֶה עָשָׂה ה' לִי בְּצֵאתִי מִמִּצְרַיִם. לֹא אֶת־אֲבוֹתֵינוּ בִּלְבָד גָּאַל הַקָּדוֹשׁ בָּרוּךְ הוּא, אֶלָּא אַף אוֹתָנוּ גָּאַל עִמָּהֶם, שֶׁנֶּאֱמַר: וְאוֹתָנוּ הוֹצִיא מִשָּׁם, לְמַעַן הָבִיא אוֹתָנוּ, לָתֶת לָנוּ אֶת־הָאָרֶץ אֲשֶׁר נִשָׁבַּע לַאֲבֹתֵינוּ.")

(def rabbi-gamliel-eng-5 " In each and every generation, a person is obligated to see himself as if he left Egypt, as it is stated (Exodus 13:8); \"And you shall explain to your son on that day: For the sake of this, did the Lord do [this] for me in my going out of Egypt.\" Not only our ancestors did the Holy One, blessed be He, redeem, but rather also us [together] with them did He redeem, as it is stated (Deuteronomy 6:23); \"And He took us out from there, in order to bring us in, to give us the land which He swore unto our fathers.\" ")

(def hallel-heb-1 "לְפִיכָךְ אֲנַחְנוּ חַיָּבִים לְהוֹדוֹת, לְהַלֵּל, לְשַׁבֵּחַ, לְפָאֵר, לְרוֹמֵם, לְהַדֵּר, לְבָרֵךְ, לְעַלֵּה וּלְקַלֵּס לְמִי שֶׁעָשָׂה לַאֲבוֹתֵינוּ וְלָנוּ אֶת־כָּל־הַנִסִּים הָאֵלּוּ: הוֹצִיאָנוּ מֵעַבְדוּת לְחֵרוּת מִיָּגוֹן לְשִׂמְחָה, וּמֵאֵבֶל לְיוֹם טוֹב, וּמֵאֲפֵלָה לְאוֹר גָּדוֹל, וּמִשִּׁעְבּוּד לִגְאֻלָּה. וְנֹאמַר לְפָנָיו שִׁירָה חֲדָשָׁה: הַלְלוּיָהּ.")

(def hallel-eng-1 "Therefore we are obligated to thank, praise, laud, glorify, exalt, lavish, bless, raise high, and acclaim He who made all these miracles for our ancestors and for us: He brought us out from slavery to freedom, from sorrow to joy, from mourning to [celebration of] a festival, from darkness to great light, and from servitude to redemption. And let us say a new song before Him, Halleluyah!")

(def instr-heb-8 "יאחז הכוס בידו ויכסה המצות ויאמר:")

(def instr-eng-8 "He holds the cup in his hand and and he covers the matsa and says:")


(def hallel-heb-2 "הַלְלוּיָהּ הַלְלוּ עַבְדֵי ה', הַלְלוּ אֶת־שֵׁם ה'. יְהִי שֵׁם ה' מְבֹרָךְ מֵעַתָּה וְעַד עוֹלָם. מִמִּזְרַח שֶׁמֶשׁ עַד מְבוֹאוֹ מְהֻלָּל שֵׁם ה'. רָם עַל־כָּל־גּוֹיִם ה', עַל הַשָּׁמַיִם כְּבוֹדוֹ. מִי כַּיי אֱלֹהֵינוּ הַמַּגְבִּיהִי לָשָׁבֶת, הַמַּשְׁפִּילִי לִרְאוֹת בַּשָּׁמַיִם וּבָאָרֶץ? מְקִימִי מֵעָפָר דָּל, מֵאַשְׁפֹּת יָרִים אֶבְיוֹן, לְהוֹשִׁיבִי עִם־נְדִיבִים, עִם נְדִיבֵי עַמּוֹ. מוֹשִׁיבִי עֲקֶרֶת הַבַּיִת, אֵם הַבָּנִים שְׂמֵחָה. הַלְלוּיָהּ.")

(def hallel-eng-2 "Halleluyah! Praise, servants of the Lord, praise the name of the Lord. May the Name of the Lord be blessed from now and forever. From the rising of the sun in the East to its setting, the name of the Lord is praised. Above all nations is the Lord, His honor is above the heavens. Who is like the Lord, our God, Who sits on high; Who looks down upon the heavens and the earth? He brings up the poor out of the dirt; from the refuse piles, He raises the destitute. To seat him with the nobles, with the nobles of his people. He seats a barren woman in a home, a happy mother of children. Halleluyah! (Psalms 113)")


(def hallel-heb-3 "בְּצֵאת יִשְׂרָאֵל מִמִצְרַיִם, בֵּית יַעֲקֹב מֵעַם לֹעֵז, הָיְתָה יְהוּדָה לְקָדְשׁוֹ, יִשְׂרָאֵל מַמְשְׁלוֹתָיו. הַיָּם רָאָה וַיַּנֹס, הַיַּרְדֵּן יִסֹּב לְאָחוֹר. הֶהָרִים רָקְדוּ כְאֵילִים, גְּבַעוֹת כִּבְנֵי צֹאן. מַה לְּךָ הַיָּם כִּי תָנוּס, הַיַּרְדֵּן – תִּסֹּב לְאָחוֹר, הֶהָרִים – תִּרְקְדוּ כְאֵילִים, גְּבַעוֹת כִּבְנֵי־צֹאן. מִלְּפְנֵי אָדוֹן חוּלִי אָרֶץ, מִלְּפְנֵי אֱלוֹהַ יַעֲקֹב. הַהֹפְכִי הַצּוּר אֲגַם־מָיִם, חַלָּמִיש לְמַעְיְנוֹ־מָיִם.")

(def hallel-eng-3 "In Israel's going out from Egypt, the house of Ya'akov from a people of foreign speech. Yehudah became His -holy one, Israel, His dominion. The Sea saw and fled, the Jordan turned to the rear. The mountains danced like rams, the hills like young sheep. What is happening to you, O Sea, that you are fleeing, O Jordan that you turn to the rearO mountains that you dance like rams, O hills like young sheep? From before the Master, tremble O earth, from before the Lord of Ya'akov. He who turns the boulder into a pond of water, the flint into a spring of water. (Psalms 114)")

(def second-cup-heb "בָּרוּךְ אַתָּה ה' אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, אֲשֶׁר גְּאָלָנוּ וְגָאַל אֶת־אֲבוֹתֵינוּ מִמִּצְרַיִם, וְהִגִּיעָנוּ הַלַּיְלָה הַזֶּה לֶאֱכָל־בּוֹ מַצָּה וּמָרוֹר. כֵּן ה' אֱלֹהֵינוּ וֵאלֹהֵי אֲבוֹתֵינוּ יַגִּיעֵנוּ לְמוֹעֲדִים וְלִרְגָלִים אֲחֵרִים הַבָּאִים לִקְרָאתֵנוּ לְשָׁלוֹם, שְׂמֵחִים בְּבִנְיַן עִירֶךְ וְשָׂשִׂים בַּעֲבוֹדָתֶךָ. וְנֹאכַל שָׁם מִן הַזְּבָחִים וּמִן הַפְּסָחִים אֲשֶׁר יַגִּיעַ דָּמָם עַל קִיר מִזְבַּחֲךָ לְרָצון, וְנוֹדֶה לְךָ שִׁיר חָדָש עַל גְּאֻלָּתֵנוּ וְעַל פְּדוּת נַפְשֵׁנוּ. בָּרוּךְ אַתָּה ה', גָּאַל יִשְׂרָאֵל.")

(def second-cup-eng "Blessed are You, Lord our God, King of the universe, who redeemed us and redeemed our ancestors from Egypt, and brought us on this night to eat matsa and marror; so too, Lord our God, and God of our ancestors, bring us to other appointed times and holidays that will come to greet us in peace, joyful in the building of Your city and happy in Your worship; that we shall eat there from the offerings and from the Pesach sacrifices, the blood of which shall reach the wall of Your altar for favor, and we shall thank You with a new song upon our redemption and upon the restoration of our souls. Blessed are you, Lord, who redeemed Israel.")

(def instr-heb-9 "מגביהים את הכוס עד גאל ישראל.")
(def instr-eng-9 "We raise the cup until we reach \"who redeemed Israel\"")


(def instr-heb-10 "שותים את הכוס בהסבת שמאל.")
(def instr-eng-10 "We say the blessing below and drink the cup while reclining to the left")

(def wine-blessing (bracha "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן." "Blessed are You, Lord our God, who creates the fruit of the vine."))

(def magid (section "מַגִּיד"
          "Magid"
          (song-with-instruction "Ha Lachma Anya"
                ah-lach-manya-heb ah-lach-manya-eng
                (instruction instr-heb-1 instr-eng-1))
          (song-with-instruction "The Four Questions"
                mah-nishtana-heb mah-nishtana-eng
                (instruction instr-heb-2 instr-eng-2))
           (song-with-instruction "We Were Slaves in Egypt"
                avadim-hayinu-heb avadim-hayinu-eng
                (instruction instr-heb-3 instr-eng-3))
         (general-content "Story of the Five Rabbis"
                           story-of-five-rabs-heb-1 story-of-five-rabs-eng-1
                           (general-content story-of-five-rabs-heb-2 story-of-five-rabs-eng-2))
       (general-content "The Four Sons"
                           the-four-sons-bracha-heb
                           the-four-sons-bracha-eng
                           (general-content wise-son-heb wise-son-eng)
                           (general-content evil-son-heb evil-son-eng)
                           (general-content innocent-son-heb innocent-son-eng)
                           (general-content simple-son-heb simple-son-eng))
          (general-content "Yechol Me'rosh Chodesh" yechol-merosh-heb yechol-merosh-eng)
          (general-content "In the Beginning Our Fathers Were Idol Worshipers"
                           idol-worship-heb-1
                           idol-worship-eng-1
                           (general-content idol-worship-heb-2 idol-worship-eng-2)
                           (general-content idol-worship-heb-3 idol-worship-eng-3)
                           (instruction instr-heb-4 instr-eng-4)
                           (song vehi-sheamda-heb vehi-sheamda-eng))
          first-fruits-declaration
                   (general-content-with-instruction "The Ten Plagues"
                                                     ten-plagues-heb-1 ten-plagues-eng-1
                                                     (instruction instr-heb-5 instr-eng-5)
                                                    (general-content ten-plagues-heb-2 ten-plagues-eng-2)
                                                     (general-content ten-plagues-heb-3 ten-plagues-eng-3)
                                                     (apply table "" plague-rows)
                                                     (general-content rabbi-yehuda-heb rabbi-yehuda-eng)
                                                     (general-content rabbi-yose-heb rabbi-yose-eng)
                                                     (general-content rabbi-eliezer-heb rabbi-eliezer-eng)
                                                     (general-content rabbi-akiva-heb rabbi-akiva-eng))
                   dayenu
                   (general-content "Rabban Gamliel's Three Things"
                                    rabbi-gamliel-heb-1 rabbi-gamliel-eng-1
                                    (general-content rabbi-gamliel-heb-2 rabbi-gamliel-eng-2)
                                    (instruction instr-heb-6 instr-eng-6)
                                    (general-content rabbi-gamliel-heb-3 rabbi-gamliel-eng-3)
                                    (instruction instr-heb-7 instr-eng-7)
                                    (general-content rabbi-gamliel-heb-4 rabbi-gamliel-eng-4)
                                    (general-content rabbi-gamliel-heb-5 rabbi-gamliel-eng-5)
                                    )
                   (general-content-with-instruction "First Half of Hallel"
                                                     hallel-heb-1 hallel-eng-1
                                                     (instruction instr-heb-8 instr-eng-8)
                                                     (general-content hallel-heb-2 hallel-eng-2)
                                                     (general-content hallel-heb-3 hallel-eng-3)
                                                     )
                   (general-content-with-instruction "Second Cup of Wine"
                                                     second-cup-heb second-cup-eng
                                                     (instruction instr-heb-9 instr-eng-9)
                                                     (instruction instr-heb-10 instr-eng-10)
                                                     wine-blessing)))
