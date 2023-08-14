(ns haggadah.fourth-cup
  (:require [haggadah.dsl :as dsl :refer [general-content bracha instruction bracha-with-more-content]]))

(def instr-heb "וְשׁותה בהסיבת שמאל.")
(def instr-eng "We drink while reclining to the left")

(def heb-cont-1 "בָּרוּך אַתָּה ה' אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, עַל הַגֶּפֶן וְעַל פְּרִי הַגֶּפֶן, עַל תְּנוּבַת הַשָּׂדֶה וְעַל אֶרֶץ חֶמְדָּה טוֹבָה וּרְחָבָה שֶׁרָצִיתָ וְהִנְחַלְתָּ לַאֲבוֹתֵינוּ לֶאֱכוֹל מִפִּרְיָהּ וְלִשְׂבֹּעַ מִטּוּבָהּ. רַחֶם נָא ה' אֱלֹהֵינוּ עַל יִשְׂרָאֵל עַמֶּךָ וְעַל יְרוּשָׁלַיִם עִירֶךָ וְעַל צִיּוֹן מִשְׁכַּן כְּבוֹדֶךָ וְעַל מִזְבְּחֶךָ וְעַל הֵיכָלֶךָ וּבְנֵה יְרוּשָׁלַיִם עִיר הַקֹּדֶשׁ בִּמְהֵרָה בְיָמֵינוּ וְהַעֲלֵנוּ לְתוֹכָהּ וְשַׂמְּחֵנוּ בְּבִנְיָנָהּ וְנֹאכַל מִפִּרְיָהּ וְנִשְׂבַּע מִטּוּבָהּ וּנְבָרֶכְךָ עָלֶיהָ בִּקְדֻשָׁה וּבְטָהֳרָה [בשבת: וּרְצֵה וְהַחֲלִיצֵנוּ בְּיוֹם הַשַׁבָּת הַזֶּה] וְשַׂמְּחֵנוּ בְּיוֹם חַג הַמַּצּוֹת הַזֶּה, כִּי אַתָּה ה' טוֹב וּמֵטִיב לַכֹּל, וְנוֹדֶה לְּךָ עַל הָאָרֶץ וְעַל פְּרִי הַגָּפֶן.")
(def eng-cont-1 "Blessed are You, Lord our God, King of the universe, for the vine and for the fruit of the vine; and for the bounty of the field; and for a desirable, good and broad land, which You wanted to give to our fathers, to eat from its fruit and to be satiated from its goodness. Please have mercy, Lord our God upon Israel Your people; and upon Jerusalem, Your city: and upon Zion, the dwelling place of Your glory; and upon Your altar; and upon Your sanctuary; and build Jerusalem Your holy city quickly in our days, and bring us up into it and gladden us in its building; and we shall eat from its fruit, and be satiated from its goodness, and bless You in holiness and purity. [On Shabbat: And may you be pleased to embolden us on this Shabbat day] and gladden us on this day of the Festival of Matsot. Since You, Lord, are good and do good to all, we thank You for the land and for the fruit of the vine.")

(def heb-cont-2 "בָּרוּךְ אַתָּה ה', עַל הָאָרֶץ וְעַל פְּרִי הַגָּפֶן.")
(def eng-cont-2 "Blessed are You, Lord, for the land and for the fruit of the vine")

(def fourth-cup
  (general-content "Fourth Cup of Wine"
                   "בָּרוּךְ אַתָּה ה', אֱלהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן."
                   "Blessed are You, Lord our God, King of the universe, who creates the fruit of the vine."
                   (instruction instr-heb instr-eng)
                   (bracha heb-cont-1 eng-cont-1)
                   (bracha heb-cont-2 eng-cont-2)))

