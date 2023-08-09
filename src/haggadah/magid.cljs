(ns haggadah.magid
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]))


(def ah-lach-manya-heb "הָא לַחְמָא עַנְיָא דִּי אֲכָלוּ אַבְהָתָנָא בְאַרְעָא דְמִצְרָיִם. כָּל דִכְפִין יֵיתֵי וְיֵיכֹל, כָּל דִצְרִיךְ יֵיתֵי וְיִפְסַח. הָשַּׁתָּא הָכָא, לְשָׁנָה הַבָּאָה בְּאַרְעָא דְיִשְׂרָאֵל. הָשַּׁתָּא עַבְדֵי, לְשָׁנָה הַבָּאָה בְּנֵי חוֹרִין.")

(def ah-lach-manya-eng "This is the bread of destitution that our ancestors ate in the land of Egypt. Anyone who is famished should come and eat, anyone who is in need should come and partake of the Pesach sacrifice. Now we are here, next year we will be in the land of Israel; this year we are slaves, next year we will be free people.")


(def instr-heb-9 "מגלה את המצות, מגביה את הקערה ואומר בקול רם:")
(def instr-eng-9 "The leader uncovers the matsot, raises the Seder plate, and says out loud:")


(def mah-nishtana-eng "מַה נִּשְׁתַּנָּה הַלַּיְלָה הַזֶּה מִכָּל הַלֵּילוֹת? שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין חָמֵץ וּמַצָּה, הַלַּיְלָה הַזֶּה – כֻּלּוֹ מַצָּה. שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין שְׁאָר יְרָקוֹת – הַלַּיְלָה הַזֶּה (כֻּלּוֹ) מָרוֹר. שֶׁבְּכָל הַלֵּילוֹת אֵין אָנוּ מַטְבִּילִין אֲפִילוּ פַּעַם אֶחָת – הַלַּיְלָה הַזֶּה שְׁתֵּי פְעָמִים. שֶׁבְּכָל הַלֵּילוֹת אָנוּ אוֹכְלִין בֵּין יוֹשְׁבִין וּבֵין מְסֻבִּין – הַלַּיְלָה הַזֶּה כֻּלָּנוּ מְסֻבִּין.")

(def mah-nishtana-heb "What differentiates this night from all [other] nights? On all [other] nights we eat chamets and matsa; this night, only matsa? On all [other] nights we eat other vegetables; tonight (only) marror. On all [other] nights, we don't dip [our food], even one time; tonight [we dip it] twice. On [all] other nights, we eat either sitting or reclining; tonight we all recline.")


(def instr-heb-10 "מסיר את הקערה מעל השולחן. מוזגין כוס שני. הבן שואל:")
(def instr-eng-10 "He removes the plate from the table. We pour a second cup of wine. The son then asks:")


(def avadim-hayinu-heb "עֲבָדִים הָיִינוּ לְפַרְעֹה בְּמִצְרָיִם, וַיּוֹצִיאֵנוּ ה' אֱלֹהֵינוּ מִשָּׁם בְּיָד חֲזָקָה וּבִזְרֹעַ נְטוּיָה. וְאִלּוּ לֹא הוֹצִיא הַקָּדוֹשׁ בָּרוּךְ הוּא אֶת אֲבוֹתֵינוּ מִמִּצְרָיִם, הֲרֵי אָנוּ וּבָנֵינוּ וּבְנֵי בָנֵינוּ מְשֻׁעְבָּדִים הָיִינוּ לְפַרְעֹה בְּמִצְרָיִם. וַאֲפִילוּ כֻּלָּנוּ חֲכָמִים כֻּלָּנוּ נְבוֹנִים כֻּלָּנוּ זְקֵנִים כֻּלָּנוּ יוֹדְעִים אֶת הַתּוֹרָה מִצְוָה עָלֵינוּ לְסַפֵּר בִּיצִיאַת מִצְרָיִם. וְכָל הַמַּרְבֶּה לְסַפֵּר בִּיצִיאַת מִצְרַיִם הֲרֵי זֶה מְשֻׁבָּח.")

(def avadim-hayinu-eng "We were slaves to Pharaoh in the land of Egypt. And the Lord, our God, took us out from there with a strong hand and an outstretched forearm. And if the Holy One, blessed be He, had not taken our ancestors from Egypt, behold we and our children and our children's children would [all] be enslaved to Pharaoh in Egypt. And even if we were all sages, all discerning, all elders, all knowledgeable about the Torah, it would be a commandment upon us to tell the story of the exodus from Egypt. And anyone who adds [and spends extra time] in telling the story of the exodus from Egypt, behold he is praiseworthy.")


(def instr-heb-11 "מחזיר את הקערה אל השולחן. המצות תִהיינה מגלות בִשעת אמירת ההגדה.")
(def instr-eng-11 "He puts the plate back on the table. The matsot should be uncovered during the saying of the Haggadah.")


(def story-of-five-rabs-heb " מַעֲשֶׂה בְּרַבִּי אֱלִיעֶזֶר וְרַבִּי יְהוֹשֻׁעַ וְרַבִּי אֶלְעָזָר בֶּן־עֲזַרְיָה וְרַבִּי עֲקִיבָא וְרַבִּי טַרְפוֹן שֶׁהָיוּ מְסֻבִּין בִּבְנֵי־בְרַק וְהָיוּ מְסַפְּרִים בִּיצִיאַת מִצְרַיִם כָּל־אוֹתוֹ הַלַּיְלָה, עַד שֶׁבָּאוּ תַלְמִידֵיהֶם וְאָמְרוּ לָהֶם רַבּוֹתֵינוּ הִגִּיעַ זְמַן קְרִיאַת שְׁמַע שֶׁל שַׁחֲרִית.
  אָמַר רַבִּי אֶלְעָזָר בֶּן־עֲזַרְיָה הֲרֵי אֲנִי כְּבֶן שִׁבְעִים שָׁנָה וְלֹא זָכִיתִי שֶׁתֵּאָמֵר יְצִיאַת מִצְרַיִם בַּלֵּילוֹת עַד שֶׁדְּרָשָׁהּ בֶּן זוֹמָא, שֶׁנֶּאֱמַר, לְמַעַן תִּזְכֹּר אֶת יוֹם צֵאתְךָ מֵאֶרֶץ מִצְרַיִם כֹּל יְמֵי חַיֶּיךָ. יְמֵי חַיֶּיךָ הַיָּמִים. כֹּל יְמֵי חַיֶּיךָ הַלֵּילוֹת. וַחֲכָמִים אוֹמְרִים יְמֵי חַיֶּיךָ הָעוֹלָם הַזֶּה. כֹּל יְמֵי חַיֶּיךָ לְהָבִיא לִימוֹת הַמָּשִׁיחַ:")


(def story-of-five-rabs-eng "It happened once [on Pesach] that Rabbi Eliezer, Rabbi Yehoshua, Rabbi Elazar ben Azariah, Rabbi Akiva and Rabbi Tarfon were reclining in Bnei Brak and were telling the story of the exodus from Egypt that whole night, until their students came and said to them, 'The time of [reciting] the morning Shema has arrived.'
Rabbi Elazar ben Azariah said, 'Behold I am like a man of seventy years and I have not merited [to understand why] the exodus from Egypt should be said at night until Ben Zoma explicated it, as it is stated (Deuteronomy 16:3), 'In order that you remember the day of your going out from the land of Egypt all the days of your life;' 'the days of your life' [indicates that the remembrance be invoked during] the days, 'all the days of your life' [indicates that the remembrance be invoked also during] the nights.' But the Sages say, ''the days of your life' [indicates that the remembrance be invoked in] this world, 'all the days of your life' [indicates that the remembrance be invoked also] in the days of the Messiah.'")


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

(section "מַגִּיד"
                     "Magid"
                     (song "Ha Lachma Anya"
                           ah-lach-manya-heb ah-lach-manya-eng
                           (instruction instr-heb-9 instr-eng-9))
                     (song "The Four Questions"
                           mah-nishtana-heb mah-nishtana-eng
                           (instruction instr-heb-10 instr-eng-10))
                     (song "We Were Slaves in Egypt"
                           avadim-hayinu-heb avadim-hayinu-eng
                           (instruction instr-heb-11 instr-eng-11))
                     (general-content "Story of the Five Rabbis" story-of-five-rabs-heb story-of-five-rabs-eng)
                     (general-content "The Four Sons"
                                      the-four-sons-bracha-heb
                                      the-four-sons-bracha-eng
                                      (general-content wise-son-heb wise-son-eng)
                                      (general-content evil-son-heb evil-son-eng)
                                      (general-content innocent-son-heb innocent-son-eng)
                                      (general-content simple-son-heb simple-son-eng))
                     (general-content "Yechol Me'rosh Chodesh" yechol-merosh-heb yechol-merosh-eng))
