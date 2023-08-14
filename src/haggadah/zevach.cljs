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

(def zevach-pesach
  (general-content-with-instruction "Zevach Pesach"
                                    heb-cont-1 eng-cont-1
                                    (instruction instr-heb instr-eng)
                                    (general-content heb-cont-2 eng-cont-2)
                                    (general-content heb-cont-3 eng-cont-3)

                                    ))
