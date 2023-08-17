(ns haggadah.magid-part-2
  (:require [haggadah.dsl :as dsl :refer [bracha table row instruction song song-with-instruction section general-content general-content-with-instruction]]
            [haggadah.dayenu :refer [dayenu]]))


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

                                        ;map cada tr a una coleccion de td.
; {:tr {td1 :content td2 ;more content}}


(defn process-cell
  [cell]
(into [] cat cell))


(defn process-row
  "Pre: takes a a row
  Post: returns a hiccup representation of a row"
  [row]
  (into [:tr]
         (map process-cell (:tr row))))

(row [:a :aa])
(process-row (row [:a :aa]))

                                        ; array, cada row va a ser un mapa de la estructura {:tr [td-content]}
;; {:tr {:td } }
(def plague-rows (map (comp row vector) plagues-eng plagues-heb))
(map process-row plague-rows)

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

(def magid-part-2
  (general-content nil nil nil
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
