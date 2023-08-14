(ns haggadah.barech
  (:require [haggadah.dsl :as dsl :refer [instruction section bracha general-content-with-instruction]]
            [haggadah.birkat-hamazon :refer [birkat-hamazon]]))

(def bracha-heb "בָּרוּךְ אַתָּה ה', אֱלהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן.")
(def bracha-eng  "Blessed are You, Lord our God, King of the universe, who creates the fruit of the vine.")

(def instr-heb-1 "ושותים בהסיבה ואינו מברך ברכה אחרונה.")
(def instr-eng-1 "We drink while reclining and do not say a blessing afterwards.")

(def instr-heb-2 "מוזגים כוס של אליהו ופותחים את הדלת:")
(def instr-eng-2 "We pour the cup of Eliyahu and open the door.")
(def cont-heb "שְׁפֹךְ חֲמָתְךָ אֶל־הַגּוֹיִם אֲשֶׁר לֹא יְדָעוּךָ וְעַל־מַמְלָכוֹת אֲשֶׁר בְּשִׁמְךָ לֹא קָרָאוּ. כִּי אָכַל אֶת־יַעֲקֹב וְאֶת־נָוֵהוּ הֵשַׁמּוּ. שְׁפָךְ־עֲלֵיהֶם זַעֲמֶךָ וַחֲרוֹן אַפְּךָ יַשִּׂיגֵם. תִּרְדֹף בְּאַף וְתַשְׁמִידֵם מִתַּחַת שְׁמֵי ה'.")
(def cont-eng "Pour your wrath upon the nations that did not know You and upon the kingdoms that did not call upon Your Name! Since they have consumed Ya'akov and laid waste his habitation (Psalms 79:6-7). Pour out Your fury upon them and the fierceness of Your anger shall reach them (Psalms 69:25)! You shall pursue them with anger and eradicate them from under the skies of the Lord (Lamentations 3:66).")

(defn bracha-with-more-content
  ([hebrew-text english-text & more-content] (apply bracha nil hebrew-text english-text more-content)))

(def barech
  (section
   "בָּרֵךְ"
   "Barech"
   birkat-hamazon
   (bracha-with-more-content bracha-heb bracha-eng
                             (instruction instr-heb-1 instr-eng-1))
   (general-content-with-instruction "Pour Out Thy Wrath"
                                     cont-heb cont-eng
                                     (instruction instr-heb-2 instr-eng-2)
                                     )
   ))

