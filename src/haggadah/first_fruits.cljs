(ns haggadah.first-fruits
  (:require [haggadah.dsl :as dsl :refer [instruction general-content general-content-with-instruction]]))


(def instr-heb-4 "יניח הכוס מידו ויגלה אֶת הַמצות.")
(def instr-eng-4 "He puts down the cup from his hand and uncovers the matsa.")

(def first-fruit-heb-1 "צֵא וּלְמַד מַה בִּקֵּשׁ לָבָן הָאֲרַמִּי לַעֲשׂוֹת לְיַעֲקֹב אָבִינוּ: שֶׁפַּרְעֹה לֹא גָזַר אֶלָּא עַל הַזְּכָרִים, וְלָבָן בִּקֵּשׁ לַעֲקֹר אֶת־הַכֹּל. שֶׁנֶּאֱמַר: אֲרַמִּי אֹבֵד אָבִי, וַיֵּרֶד מִצְרַיְמָה וַיָּגָר שָׁם בִּמְתֵי מְעָט, וַיְהִי שָׁם לְגוֹי גָּדוֹל, עָצוּם וָרָב.")

(def first-fruit-eng-1 "Go out and learn what Lavan the Aramean sought to do to Ya'akov, our father; since Pharaoh only decreed [the death sentence] on the males but Lavan sought to uproot the whole [people]. As it is stated (Deuteronomy 26:5), \"An Aramean was destroying my father and he went down to Egypt, and he resided there with a small number and he became there a nation, great, powerful and numerous.\"")


(def first-fruit-heb-2 "וַיֵּרֶד מִצְרַיְמָה – אָנוּס עַל פִּי הַדִּבּוּר. וַיָּגָר שָׁם. מְלַמֵּד שֶׁלֹא יָרַד יַעֲקֹב אָבִינוּ לְהִשְׁתַּקֵּעַ בְּמִצְרַיִם אֶלָּא לָגוּר שָׁם, שֶׁנֶּאֱמַר: וַיֹּאמְרוּ אֶל־פַּרְעֹה, לָגוּר בָּאָרֶץ בָּאנוּ, כִּי אֵין מִרְעֶה לַצֹּאן אֲשֶׁר לַעֲבָדֶיךָ, כִּי כָבֵד הָרָעָב בְּאֶרֶץ כְּנָעַן. וְעַתָּה יֵשְׁבוּ־נָא עֲבָדֶיךָ בְּאֶרֶץ גֹּשֶן.")

(def first-fruit-eng-2 " \"And he went down to Egypt\" - helpless on account of the word [in which God told Avraham that his descendants would have to go into exile]. \"And he resided there\" - [this] teaches that Ya'akov, our father, didn't go down to settle in Egypt, but rather [only] to reside there, as it is stated (Genesis 47:4), \"And they said to Pharaoh, 'To reside in the land have we come, since there is not enough pasture for your servant's flocks, since the famine is heavy in the land of Canaan, and now please grant that your servants should dwell in the Land of Goshen.'\"")


(def first-fruit-heb-3 "בִּמְתֵי מְעָט. כְּמָה שֶּׁנֶּאֱמַר: בְּשִׁבְעִים נֶפֶשׁ יָרְדוּ אֲבוֹתֶיךָ מִצְרָיְמָה, וְעַתָּה שָׂמְךָ ה' אֱלֹהֶיךָ כְּכוֹכְבֵי הַשָּׁמַיִם לָרֹב.")

(def first-fruit-eng-3 "\"As a small number\" - as it is stated (Deuteronomy 10:22), \"With seventy souls did your ancestors come down to Egypt, and now the Lord your God has made you as numerous as the stars of the sky.\" ")


(def first-fruit-heb-4 "וַיְהִי שָׁם לְגוֹי. מְלַמֵד שֶׁהָיוּ יִשְׂרָאֵל מְצֻיָּנִים שָׁם. גָּדוֹל עָצוּם – כְּמָה שֶּׁנֶּאֱמַר: וּבְנֵי יִשְׂרָאֵל פָּרוּ וַיִּשְׁרְצוּ וַיִּרְבּוּ וַיַּעַצְמוּ בִּמְאֹד מְאֹד, וַתִּמָּלֵא הָאָרֶץ אֹתָם.")

(def first-fruit-eng-4 "\"And he became there a nation\" - [this] teaches that Israel [became] distinguishable there. \"Great, powerful\" - as it is stated (Exodus 1:7), \"And the Children of Israel multiplied and swarmed and grew numerous and strong, most exceedingly and the land became full of them.\"")


(def first-fruit-heb-5 "וָרָב. כְּמָה שֶּׁנֶּאֱמַר: רְבָבָה כְּצֶמַח הַשָּׂדֶה נְתַתִּיךְ, וַתִּרְבִּי וַתִּגְדְּלִי וַתָּבֹאִי בַּעֲדִי עֲדָיִים, שָׁדַיִם נָכֹנוּ וּשְׂעָרֵךְ צִמֵּחַ, וְאַתְּ עֵרֹם וְעֶרְיָה. וָאֶעֱבֹר עָלַיִךְ וָאֶרְאֵךְ מִתְבּוֹסֶסֶת בְּדָמָיִךְ, וָאֹמַר לָךְ בְּדָמַיִךְ חֲיִי, וָאֹמַר לָךְ בְּדָמַיִךְ חֲיִי.")

(def first-fruit-eng-5 " \"And numerous\" - as it is stated (Ezekiel 16:7), \"I have given you to be numerous as the vegetation of the field, and you increased and grew and became highly ornamented, your breasts were set and your hair grew, but you were naked and barren.\" \"And when I passed by thee, and saw thee weltering in thy blood, I said to thee, In thy blood live! yea, I said to thee, In thy blood live!\" (Ezekiel 16:6).")

(def first-fruit-heb-6 "וַיָּרֵעוּ אֹתָנוּ הַמִּצְרִים וַיְעַנּוּנוּ, וַיִתְּנוּ עָלֵינוּ עֲבֹדָה קָשָׁה. וַיָּרֵעוּ אֹתָנוּ הַמִּצְרִים – כְּמָה שֶּׁנֶּאֱמַר: הָבָה נִתְחַכְּמָה לוֹ פֶּן יִרְבֶּה, וְהָיָה כִּי תִקְרֶאנָה מִלְחָמָה וְנוֹסַף גַּם הוּא עַל שֹׂנְאֵינוּ וְנִלְחַם־בָּנוּ, וְעָלָה מִן־הָאָרֶץ. ")


(def first-fruit-eng-6" \"And the Egyptians did bad to us\" (Deuteronomy 26:6) - as it is stated (Exodus 1:10), \"Let us be wise towards him, lest he multiply and it will be that when war is called, he too will join with our enemies and fight against us and go up from the land.\"")


(def first-fruit-heb-7 "וַיְעַנּוּנוּ. כְּמָה שֶּׁנֶּאֱמַר: וַיָּשִׂימוּ עָלָיו שָׂרֵי מִסִּים לְמַעַן עַנֹּתוֹ בְּסִבְלֹתָם. וַיִּבֶן עָרֵי מִסְכְּנוֹת לְפַרְעֹה. אֶת־פִּתֹם וְאֶת־רַעַמְסֵס.")


(def first-fruit-eng-7 "\"And afflicted us\" - as is is stated (Exodus 1:11); \"And they placed upon him leaders over the work-tax in order to afflict them with their burdens; and they built storage cities, Pithom and Ra'amses.\"")

(def first-fruit-heb-8 "וַיִתְּנוּ עָלֵינוּ עֲבֹדָה קָשָׁה. כְּמָה שֶֹׁנֶּאֱמַר: וַיַּעֲבִדוּ מִצְרַיִם אֶת־בְּנֵי יִשְׂרָאֵל בְּפָרֶךְ.")

(def first-fruit-eng-8 "\"And put upon us hard work\" - as it is stated (Exodus 1:11), \"And they enslaved the children of Israel with breaking work.\"")


(def first-fruit-heb-9 "וַנִּצְעַק אֶל־ה' אֱלֹהֵי אֲבֹתֵינוּ, וַיִּשְׁמַע ה' אֶת־קֹלֵנוּ, וַיַּרְא אֶת־עָנְיֵנוּ וְאֶת עֲמָלֵנוּ וְאֶת לַחֲצֵנוּ.")

(def first-fruit-eng-9 "\"And we we cried out to the Lord, the God of our ancestors, and the Lord heard our voice, and He saw our affliction, and our toil and our duress\" (Deuteronomy 26:7).")

(def first-fruit-heb-10 "וַנִּצְעַק אֶל־ה' אֱלֹהֵי אֲבֹתֵינוּ – כְּמָה שֶּׁנֶּאֱמַר: וַיְהִי בַיָּמִים הָרַבִּים הָהֵם וַיָּמָת מֶלֶךְ מִצְרַיִם, וַיֵּאָנְחוּ בְנֵי־יִשְׂרָאֵל מִ־הָעֲבוֹדָה וַיִּזְעָקוּ, וַתַּעַל שַׁוְעָתָם אֶל־הָאֱלֹהִים מִן הָעֲבֹדָה.")

(def first-fruit-eng-10 "\"And we cried out to the Lord, the God of our ancestors\" - as it is stated (Exodus 2:23); \"And it was in those great days that the king of Egypt died and the Children of Israel sighed from the work and yelled out, and their supplication went up to God from the work.\"")


(def first-fruit-heb-11 "וַיִּשְׁמַע ה' אֶת קלֵנוּ. כְּמָה שֶּׁנֶּאֱמַר: וַיִּשְׁמַע אֱלֹהִים אֶת־נַאֲקָתָם, וַיִּזְכֹּר אֱלֹהִים אֶת־בְּרִיתוֹ אֶת־אַבְרָהָם, אֶת־יִצְחָק וְאֶת־יַעֲקֹב.")

(def first-fruit-eng-11 " \"And the Lord heard our voice\" - as it is stated (Exodus 2:24); \"And God heard their groans and God remembered His covenant with Avraham and with Yitschak and with Ya'akov.\" ")


(def first-fruit-heb-12 "וַיַּרְא אֶת־עָנְיֵנוּ. זוֹ פְּרִישׁוּת דֶּרֶךְ אֶרֶץ, כְּמָה שֶּׁנֶּאֱמַר: וַיַּרְא אֱלֹהִים אֶת בְּנֵי־יִשְׂרָאֵל וַיֵּדַע אֱלֹהִים.")

(def first-fruit-eng-12 " \"And He saw our affliction\" - this [refers to] the separation from the way of the world, as it is stated (Exodus 2:25); \"And God saw the Children of Israel and God knew.\" ")


(def first-fruit-heb-13 "וְאֶת־עֲמָלֵנוּ. אֵלּוּ הַבָּנִים. כְּמָה שֶּׁנֶּאֱמַר: כָּל־הַבֵּן הַיִּלּוֹד הַיְאֹרָה תַּשְׁלִיכֻהוּ וְכָל־הַבַּת תְּחַיּוּן.")

(def first-fruit-eng-13 " \"And our toil\" - this [refers to the killing of the] sons, as it is stated (Exodus 1:22); \"Every boy that is born, throw him into the Nile and every girl you shall keep alive.\" ")


(def first-fruit-heb-14 "וְאֶת לַחָצֵנוּ. זֶו הַדְּחַק, כְּמָה שֶּׁנֶּאֱמַר: וְגַם־רָאִיתִי אֶת־הַלַּחַץ אֲשֶׁר מִצְרַיִם לֹחֲצִים אֹתָם.")

(def first-fruit-eng-14 "\"And our duress\" - this [refers to] the pressure, as it is stated (Exodus 3:9); \"And I also saw the duress that the Egyptians are applying on them.\" ")


(def first-fruit-heb-15 "וַיּוֹצִאֵנוּ ה' מִמִצְרַיִם בְּיָד חֲזָקָה, וּבִזְרֹעַ נְטוּיָה, וּבְמֹרָא גָּדֹל, וּבְאֹתוֹת וּבְמֹפְתִים.")

(def first-fruit-eng-15 "\"And the Lord took us out of Egypt with a strong hand and with an outstretched forearm and with great awe and with signs and with wonders\" (Deuteronomy 26:8). ")

(def first-fruit-heb-16 "וַיּוֹצִאֵנוּ ה' מִמִּצְרַיִם. לֹא עַל־יְדֵי מַלְאָךְ, וְלֹא עַל־יְדֵי שָׂרָף, וְלֹא עַל־יְדֵי שָׁלִיחַ, אֶלָּא הַקָּדוֹשׁ בָּרוּךְ הוּא בִּכְבוֹדוֹ וּבְעַצְמוֹ. שֶׁנֶּאֱמַר: וְעָבַרְתִּי בְאֶרֶץ מִצְרַיִם בַּלַּיְלָה הַזֶּה, וְהִכֵּיתִי כָּל־בְּכוֹר בְּאֶרֶץ מִצְרַיִם מֵאָדָם וְעַד בְּהֵמָה, וּבְכָל אֱלֹהֵי מִצְרַיִם אֶעֱשֶׂה שְׁפָטִים. אֲנִי ה'.")

(def first-fruit-eng-16 " \"And the Lord took us out of Egypt\" - not through an angel and not through a seraph and not through a messenger, but [directly by] the Holy One, blessed be He, Himself, as it is stated (Exodus 12:12); \"And I will pass through the Land of Egypt on that night and I will smite every firstborn in the Land of Egypt, from men to animals; and with all the gods of Egypt, I will make judgments, I am the Lord.\"")


(def first-fruit-heb-17 "וְעָבַרְתִּי בְאֶרֶץ מִצְרַיִם בַּלַּיְלָה הַזֶּה – אֲנִי וְלֹא מַלְאָךְ; וְהִכֵּיתִי כָל בְּכוֹר בְּאֶרֶץ־מִצְרַים. אֲנִי וְלֹא שָׂרָף; וּבְכָל־אֱלֹהֵי מִצְרַיִם אֶעֱשֶׂה שְׁפָטִים. אֲנִי וְלֹא הַשָּׁלִיחַ; אֲנִי ה'. אֲנִי הוּא וְלֹא אַחֵר.")

(def first-fruit-eng-17 " \"And I will pass through the Land of Egypt\" - I and not an angel. \"And I will smite every firstborn\" - I and not a seraph. \"And with all the gods of Egypt, I will make judgments\" - I and not a messenger. \"I am the Lord\" - I am He and there is no other. ")


(def first-fruit-heb-18 "בְּיָד חֲזָקָה. זוֹ הַדֶּבֶר, כְּמָה שֶּׁנֶּאֱמַר: הִנֵּה יַד־ה' הוֹיָה בְּמִקְנְךָ אֲשֶׁר בַּשָּׂדֶה, בַּסּוּסִים, בַּחֲמֹרִים, בַּגְּמַלִים, בַּבָּקָר וּבַצֹּאן, דֶּבֶר כָּבֵד מְאֹד.")

(def first-fruit-eng-18 " \"With a strong hand\" - this [refers to] the pestilence, as it is stated (Exodus 9:3); \"Behold the hand of the Lord is upon your herds that are in the field, upon the horses, upon the donkeys, upon the camels, upon the cattle and upon the flocks, [there will be] a very heavy pestilence.\" ")


(def first-fruit-heb-19 "וּבִזְרֹעַ נְטוּיָה. זוֹ הַחֶרֶב, כְּמָה שֶּׁנֶּאֱמַר: וְחַרְבּוֹ שְׁלוּפָה בְּיָדוֹ, נְטוּיָה עַל־יְרוּשָלָיִם.")

(def first-fruit-eng-19 " \"And with an outstretched forearm\" - this [refers to] the sword, as it is stated (I Chronicles 21:16); \"And his sword was drawn in his hand, leaning over Jerusalem.\" ")


(def first-fruit-heb-20 "וּבְמוֹרָא גָּדֹל. זוֹ גִּלּוּי שְׁכִינָה. כְּמָה שֶּׁנֶּאֱמַר, אוֹ הֲנִסָּה אֱלֹהִים לָבוֹא לָקַחַת לוֹ גּוֹי מִקֶּרֶב גּוֹי בְּמַסֹּת בְּאֹתֹת וּבְמוֹפְתִים וּבְמִלְחָמָה וּבְיָד חֲזָקָה וּבִזְרוֹעַ נְטוּיָה וּבְמוֹרָאִים גְּדוֹלִים כְּכֹל אֲשֶׁר־עָשָׂה לָכֶם ה' אֱלֹהֵיכֶם בְּמִצְרַיִם לְעֵינֶיךָ.")

(def first-fruit-eng-20 " \"And with great awe\" - this [refers to the revelation of] the Divine Presence, as it is stated (Deuteronomy 4:34), \"Or did God try to take for Himself a nation from within a nation with enigmas, with signs and with wonders and with war and with a strong hand and with an outstretched forearm and with great and awesome acts, like all that the Lord, your God, did for you in Egypt in front of your eyes?\" ")


(def first-fruit-heb-21 "וּבְאֹתוֹת. זֶה הַמַּטֶּה, כְּמָה שֶּׁנֶּאֱמַר: וְאֶת הַמַּטֶּה הַזֶּה תִּקַּח בְּיָדְךָ, אֲשֶׁר תַּעֲשֶׂה־בּוֹ אֶת הָאֹתוֹת.")

(def first-fruit-eng-21 " \"And with signs\" - this [refers to] the staff, as it is stated (Exodus 4:17); \"And this staff you shall take in your hand, that with it you will perform signs.\" ")


(def first-fruit-heb-22 "וּבְמֹפְתִים. זֶה הַדָּם, כְּמָה שֶּׁנֶּאֱמַר: וְנָתַתִּי מוֹפְתִים בַּשָּׁמַיִם וּבָאָרֶץ.")

(def first-fruit-eng-22 " And with wonders\" - this [refers to] the blood, as it is stated (Joel 3:3); \"And I will place my wonders in the skies and in the earth: ")

(def first-fruits-declaration (general-content-with-instruction "First Fruits Declaration"
                                            first-fruit-heb-1 first-fruit-eng-1
                                           (instruction instr-heb-4 instr-eng-4) 
                                            (general-content first-fruit-heb-2 first-fruit-eng-2)
                                            (general-content first-fruit-heb-3 first-fruit-eng-3)
                                            (general-content first-fruit-heb-4 first-fruit-eng-4)
                                            (general-content first-fruit-heb-5 first-fruit-eng-5)
                                            (general-content first-fruit-heb-6 first-fruit-eng-6)
                                            (general-content first-fruit-heb-7 first-fruit-eng-7)
                                            (general-content first-fruit-heb-8 first-fruit-eng-8)
                                            (general-content first-fruit-heb-9 first-fruit-eng-9)
                                            (general-content first-fruit-heb-10 first-fruit-eng-10)
                                            (general-content first-fruit-heb-11 first-fruit-eng-11)
                                            (general-content first-fruit-heb-12 first-fruit-eng-12)
                                            (general-content first-fruit-heb-13 first-fruit-eng-13)
                                            (general-content first-fruit-heb-14 first-fruit-eng-14)
                                            (general-content first-fruit-heb-15 first-fruit-eng-15)
                                            (general-content first-fruit-heb-16 first-fruit-eng-16)
                                            (general-content first-fruit-heb-17 first-fruit-eng-17)
                                            (general-content first-fruit-heb-18 first-fruit-eng-18)
                                            (general-content first-fruit-heb-19 first-fruit-eng-19)
                                            (general-content first-fruit-heb-20 first-fruit-eng-20)
                                            (general-content first-fruit-heb-21 first-fruit-eng-21)
                                            (general-content first-fruit-heb-22 first-fruit-eng-22)
                                            )

  )
