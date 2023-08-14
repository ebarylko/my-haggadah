(ns haggadah.at-midnight
  (:require [haggadah.dsl :as dsl :refer [section general-content bracha instruction general-content-with-instruction]]))

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
                                    ))

