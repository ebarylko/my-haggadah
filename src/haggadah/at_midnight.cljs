(ns haggadah.at-midnight
  (:require [haggadah.dsl :as dsl :refer [general-content instruction general-content-with-instruction]]))

(def heb-cont-1 "אָז רוֹב נִסִּים הִפְלֵאתָ בַּלַּיְלָה, בְּרֹאשׁ אַשְׁמוֹרֶת זֶה הַלַּיְלָה.")
(def eng-cont-1 "Then, most of the miracles did You wondrously do at night, at the first of the watches this night.")

(def heb-cont-2 "גֵר צֶדֶק נִצַּחְתּוֹ כְּנֶחֶלַק לוֹ לַיְלָה, וַיְהִי בַּחֲצִי הַלַּיְלָה.")
(def eng-cont-2 "A righteous convert did you make victorious when it was divided for him at night [referring to Avraham in his war against the four kings - Genesis 14:15], and it was in the middle of the night.")

(def heb-cont-3 "דַּנְתָּ מֶלֶךְ גְּרָר בַּחֲלוֹם הַלַּיְלָה, הִפְחַדְתָּ אֲרַמִּי בְּאֶמֶשׁ לַיְלָה.")
(def eng-cont-3 "You judged the king of Gerrar [Avimelekh] in a dream of the night; you frightened an Aramean [Lavan] in the dark of the night;")

(def heb-cont-4 "וַיָּשַׂר יִשְׂרָאֵל לְמַלְאָךְ וַיּוּכַל לוֹ לַיְלָה, וַיְהִי בַּחֲצִי הַלַּיְלָה.")
(def eng-cont-4 "and Yisrael dominated an angel and was able to withstand Him at night [Genesis 32:25-30], and it was in the middle of the night.")

(def heb-cont-5 "זֶרַע בְּכוֹרֵי פַתְרוֹס מָחַצְתָּ בַּחֲצִי הַלַּיְלָה, חֵילָם לֹא מָצְאוּ בְּקוּמָם בַּלַּיְלָה, טִיסַת נְגִיד חֲרֹשֶׁת סִלִּיתָ בְּכוֹכְבֵי לַיְלָה, וַיְהִי בַּחֲצִי הַלַּיְלָה.")
(def eng-cont-5 "You crushed the firstborn of Patros [Pharaoh, as per Ezekiel 30:14] in the middle of the night, their wealth they did not find when they got up at night; the attack of the leader Charoshet [Sisera] did you sweep away by the stars of the night [Judges 5:20], and it was in the middle of the night.")


(def heb-cont-6 "יָעַץ מְחָרֵף לְנוֹפֵף אִוּוּי, הוֹבַשְׁתָּ פְגָרָיו בַּלַּיְלָה, כָּרַע בֵּל וּמַצָּבוֹ בְּאִישׁוֹן לַיְלָה, לְאִישׁ חֲמוּדוֹת נִגְלָה רָז חֲזוֹת לַיְלָה, וַיְהִי בַּחֲצִי הַלַּיְלָה.")
(def eng-cont-6 "The blasphemer [Sancheriv whose servants blasphemed when trying to discourage the inhabitants of Jerusalem] counseled to wave off the desired ones, You made him wear his corpses on his head at night [II Kings 19:35]; Bel and his pedestal were bent in the pitch of night [in Nevuchadnezar's dream in Daniel 2]; to the man of delight [Daniel] was revealed the secret visions at night,  and it was in the middle of the night.")


(def heb-cont-7 "מִשְׁתַּכֵּר בִּכְלֵי קֹדֶשׁ נֶהֱרַג בּוֹ בַלַּיְלָה, נוֹשַׁע מִבּוֹר אֲרָיוֹת פּוֹתֵר בִּעֲתוּתֵי לַיְלָה, שִׂנְאָה נָטַר אֲגָגִי וְכָתַב סְפָרִים בַּלַּיְלָה, וַיְהִי בַּחֲצִי הַלַּיְלָה.")
(def eng-cont-7 "The one who got drunk [Balshatsar] from the holy vessels was killed on that night [Daniel 5:30], the one saved from the pit of lions [Daniel] interpreted the scary visions of the night; hatred was preserved by the Agagite [Haman] and he wrote books at night, and it was in the middle of the night.")

(def heb-cont-8 "עוֹרַרְתָּ נִצְחֲךָ עָלָיו בְּנֶדֶד שְׁנַת לַיְלָה. פּוּרָה תִדְרוֹךְ לְשׁוֹמֵר מַה מִּלַיְלָה, צָרַח כַּשּׁוֹמֵר וְשָׂח אָתָא בֹקֶר וְגַם לַיְלָה, וַיְהִי בַּחֲצִי הַלַּיְלָה.")
(def eng-cont-8 "You aroused your victory upon him by disturbing the sleep of night [of Achashverosh], You will stomp the wine press for the one who guards from anything at night [Esav/Seir as per Isaiah 21:11]; He yelled like a guard and spoke, \"the morning has come and also the night,\" and it was in the middle of the night. ")

(def heb-cont-9 "קָרֵב יוֹם אֲשֶׁר הוּא לֹא יוֹם וְלֹא לַיְלָה, רָם הוֹדַע כִּי לְךָ הַיּוֹם אַף לְךָ הַלַּיְלָה, שׁוֹמְרִים הַפְקֵד לְעִירְךָ כָּל הַיּוֹם וְכָל הַלַּיְלָה, תָּאִיר כְּאוֹר יוֹם חֶשְׁכַּת לַיְלָה, וַיְהִי בַּחֲצִי הַלַּיְלָה.")
(def eng-cont-9 "Bring close the day which is not day and not night [referring to the end of days - Zechariah 14:7], High One, make known that Yours is the day and also Yours is the night, guards appoint for Your city all the day and all the night, illuminate like the light of the day, the darkness of the night, and it was in the middle of the night.")

(def midnight
  (general-content-with-instruction "And It Happened at Midnight"
                                    "וּבְכֵן וַיְהִי בַּחֲצִי הַלַּיְלָה."
                                    "And so, it was in the middle of the night."
                                    (instruction "בליל רִאשון אומרים:" "On the first night we say:")
                                    (general-content heb-cont-1 eng-cont-1)
                                    (general-content heb-cont-2 eng-cont-2)
                                    (general-content heb-cont-3 eng-cont-3)
                                    (general-content heb-cont-4 eng-cont-4)
                                    (general-content heb-cont-5 eng-cont-5)
                                    (general-content heb-cont-6 eng-cont-6)
                                    (general-content heb-cont-7 eng-cont-7)
                                    (general-content heb-cont-8 eng-cont-8)
                                    (general-content heb-cont-9 eng-cont-9)
                                    ))

