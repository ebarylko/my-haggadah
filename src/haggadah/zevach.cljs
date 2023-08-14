(ns haggadah.zevach
  (:require [haggadah.dsl :as dsl :refer [general-content instruction general-content-with-instruction]])
  )


(def instr-heb "בְליל שני בחו״ל: וּבְכֵן וַאֲמַרְתֶּם זֶבַח פֶּסַח.")
(def instr-eng "On the second night, outside of Israel: And so \"And you shall say, 'it is the Pesach sacrifice'\"(Exodus 12:27).")

(def heb-cont-1 "אֹמֶץ גְּבוּרוֹתֶיךָ הִפְלֵאתָ בַּפֶּסַח, בְּרֹאשׁ כָּל מוֹעֲדוֹת נִשֵּׂאתָ פֶּסַח. גִּלִיתָ לְאֶזְרָחִי חֲצוֹת לֵיל פֶּסַח, וַאֲמַרְתֶּם זֶבַח פֶּסַח.")
(def eng-cont-1 "The boldness of Your strong deeds did you wondrously show at Pesach; at the head of all the holidays did You raise Pesach; You revealed to the Ezrachite [Avraham], midnight of the night of Pesach. \"And you shall say, 'it is the Pesach sacrifice.'\"")

(def heb-cont-2 "דְּלָתָיו דָּפַקְתָּ כְּחֹם הַיּוֹם בַּפֶּסַח, הִסְעִיד נוֹצְצִים עֻגּוֹת מַצּוֹת בַּפֶּסַח, וְאֵל הַבָּקָר רָץ זֵכֶר לְשׁוֹר עֵרֶךְ פֶּסַח, וַאֲמַרְתֶּם זֶבַח פֶּסַח.")
(def eng-cont-2 "Upon his doors did You knock at the heat of the day on Pesach [Genesis 18:1]; he sustained shining ones [angels] with cakes of matsa on Pesach; and to the cattle he ran, in commemoration of the bull that was set up for Pesach.  \"And you shall say, 'it is the Pesach sacrifice.'\"")

(def heb-cont-3 "זוֹעֲמוּ סְדוֹמִים וְלוֹׁהֲטוּ בָּאֵשׁ בַּפֶּסַח, חֻלַּץ לוֹט מֵהֶם וּמַצּוֹת אָפָה בְּקֵץ פֶּסַח, טִאטֵאתָ אַדְמַת מוֹף וְנוֹף בְּעָבְרְךָ בַּפֶּסַח. וַאֲמַרְתֶּם זֶבַח פֶּסַח.")
(def eng-cont-3 "The Sodomites caused Him indignation and He set them on fire on Pesach; Lot was rescued from them and matsot did he bake at the end of Pesach; He swept the land of Mof and Nof [cities in Egypt] on Pesach. \"And you shall say, 'it is the Pesach sacrifice.'\"")

(def heb-cont-4 "יָהּ רֹאשׁ כָּל הוֹן מָחַצְתָּ בְּלֵיל שִׁמּוּר פֶּסַח, כַּבִּיר, עַל בֵּן בְּכוֹר פָּסַחְתָּ בְּדַם פֶּסַח, לְבִלְתִּי תֵּת מַשְׁחִית לָבֹא בִּפְתָחַי בַּפֶּסַח, וַאֲמַרְתֶּם זֶבַח פֶּסַח.")
(def eng-cont-4 "The head of every firstborn did You crush on the guarded night of Pesach; Powerful One, over the firstborn son did You pass over with the blood on Pesach; so as to not let the destroyer come into my gates on Pesach. \"And you shall say, 'it is the Pesach sacrifice.'\"")

(def heb-cont-5 "מְסֻגֶּרֶת סֻגָּרָה בְּעִתּוֹתֵי פֶּסַח, נִשְׁמְדָה מִדְיָן בִּצְלִיל שְׂעוֹרֵי עֹמֶר פֶּסַח, שׂוֹרָפוּ מִשְׁמַנֵּי פּוּל וְלוּד בִּיקַד יְקוֹד פֶּסַח, וַאֲמַרְתֶּם זֶבַח פֶּסַח.")
(def eng-cont-5 "The enclosed one [Jericho] was enclosed in the season of Pesach; Midian was destroyed with a portion of the omer-barley on Pesach [via Gideon as per Judges 7]; from the fat of Pul and Lud [Assyrian soldiers of Sancheriv] was burnt in pyres on Pesach. \"And you shall say, 'it is the Pesach sacrifice'\"")


(def heb-cont-6 "עוֹד הַיּוֹם בְּנֹב לַעֲמוֹׁד עַד גָּעָה עוֹנַת פֶּסַח, פַּס יַד כָּתְבָה לְקַעֲקֵעַ צוּל בַּפֶּסַח, צָפֹה הַצָּפִית עֲרוֹךְ הַשֻּׁלְחָן בַּפֶּסַח, וַאֲמַרְתֶּם זֶבַח פֶּסַח.")
(def eng-cont-6 "Still today [Sancheriv will go no further than] to stand in Nov [Isaiah 10:32], until he cried at the time of Pesach; a palm of the hand wrote [Daniel 5:5] to rip up the deep one [ the Bayblonian one - Balshatsar] on Pesach; set up the watch, set the table [referring to Balshatsar, based on Psalms 21:5] on Pesach. \"And you shall say, 'it is the Pesach sacrifice'\"")

(def heb-cont-7 "קָהָל כִּנְּסָה הֲדַּסָּה לְשַׁלֵּשׁ צוֹם בַּפֶּסַח, רֹאשׁ מִבֵּית רָשָׁע מָחַצְתָּ בְּעֵץ חֲמִשִּׁים בַּפֶּסַח, שְׁתֵּי אֵלֶּה רֶגַע תָּבִיא לְעוּצִית בַּפֶּסַח, תָּעֹז יָדְךָ תָּרוּם יְמִינְךָ כְּלֵיל הִתְקַדֵּשׁ חַג פֶּסַח, וַאֲמַרְתֶּם זֶבַח פֶּסַח.")
(def eng-cont-7 "The congregation did Hadassah [Esther] bring in to triple a fast on Pesach; the head of the house of evil [Haman] did you crush on a tree of fifty [amot] on Pesach; these two [plagues as per Isaiah 47:9] will you bring in an instant to the Utsi [Esav] on Pesach; embolden Your hand, raise Your right hand, as on the night You were sanctified on the festival of Pesach. \"And you shall say, 'it is the Pesach sacrifice'\"")

(def zevach-pesach
  (general-content-with-instruction "Zevach Pesach"
                                    heb-cont-1 eng-cont-1
                                    (instruction instr-heb instr-eng)
                                    (general-content heb-cont-2 eng-cont-2)
                                    (general-content heb-cont-3 eng-cont-3)
                                    (general-content heb-cont-4 eng-cont-4)
                                    (general-content heb-cont-5 eng-cont-5)
                                    (general-content heb-cont-6 eng-cont-6)
                                    (general-content heb-cont-7 eng-cont-7)))
