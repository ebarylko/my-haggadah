(ns haggadah.dayenu
  (:require [haggadah.dsl :as dsl :refer [table row instruction song song-with-instruction section general-content general-content-with-instruction]])
  )

(def dayenu-heb-1 "כַּמָה מַעֲלוֹת טוֹבוֹת לַמָּקוֹם עָלֵינוּ!")
(def dayenu-eng-1 "How many degrees of good did the Place [of all bestow] upon us!")

(def dayenu-heb-2 "אִלּוּ הוֹצִיאָנוּ מִמִצְרַיִם וְלֹא עָשָׂה בָהֶם שְׁפָטִים, דַּיֵּנוּ.")
(def dayenu-eng-2 "If He had taken us out of Egypt and not made judgements on them; [it would have been] enough for us.")

(def dayenu-heb-3 "אִלּוּ עָשָׂה בָהֶם שְׁפָטִים, וְלֹא עָשָׂה בֵאלֹהֵיהֶם, דַּיֵּנוּ.")
(def dayenu-eng-3 "If He had made judgments on them and had not made [them] on their gods; [it would have been] enough for us.")

(def dayenu-heb-4 "אִלּוּ עָשָׂה בֵאלֹהֵיהֶם, וְלֹא הָרַג אֶת־בְּכוֹרֵיהֶם, דַּיֵּנוּ.")
(def dayenu-eng-4 "If He had made [them] on their gods and had not killed their firstborn; [it would have been] enough for us.")

(def dayenu-heb-5 "אִלּוּ הָרַג אֶת־בְּכוֹרֵיהֶם וְלֹא נָתַן לָנוּ אֶת־מָמוֹנָם, דַּיֵּנוּ.")
(def dayenu-eng-5 "If He had killed their firstborn and had not given us their money; [it would have been] enough for us.") 

(def dayenu-heb-6 "אִלּוּ נָתַן לָנוּ אֶת־מָמוֹנָם וְלֹא קָרַע לָנוּ אֶת־הַיָּם, דַּיֵּנוּ.")
(def dayenu-eng-6 "If He had given us their money and had not split the Sea for us; [it would have been] enough for us.")

(def dayenu-heb-7 "אִלּוּ קָרַע לָנוּ אֶת־הַיָּם וְלֹא הֶעֱבִירָנוּ בְּתוֹכוֹ בֶּחָרָבָה, דַּיֵּנוּ.")
(def dayenu-eng-7 "If He had split the Sea for us and had not taken us through it on dry land; [it would have been] enough for us.")

(def dayenu-heb-8 "אִלּוּ הֶעֱבִירָנוּ בְּתוֹכוֹ בֶּחָרָבָה וְלֹא שִׁקַּע צָרֵנוּ בְּתוֹכוֹ דַּיֵּנוּ.")
(def dayenu-eng-8 "If He had taken us through it on dry land and had not pushed down our enemies in [the Sea]; [it would have been] enough for us.")

(def dayenu-heb-9 "אִלּוּ שִׁקַּע צָרֵנוּ בְּתוֹכוֹ וְלֹא סִפֵּק צָרְכֵּנוּ בַּמִדְבָּר אַרְבָּעִים שָׁנָה דַּיֵּנוּ.")
(def dayenu-eng-9 "If He had pushed down our enemies in [the Sea] and had not supplied our needs in the wilderness for forty years; [it would have been] enough for us.")

(def dayenu-heb-10 "אִלּוּ הוֹצִיאָנוּ מִמִצְרַיִם וְלֹא עָשָׂה בָהֶם שְׁפָטִים, דַּיֵּנוּ.")
(def dayenu-eng-10 "If He had taken us out of Egypt and not made judgements on them; [it would have been] enough for us.")

;; (def dayenu-heb-2 "אִלּוּ הוֹצִיאָנוּ מִמִצְרַיִם וְלֹא עָשָׂה בָהֶם שְׁפָטִים, דַּיֵּנוּ.")
;; (def dayenu-eng-2 "If He had taken us out of Egypt and not made judgements on them; [it would have been] enough for us.")

;; (def dayenu-heb-2 "אִלּוּ הוֹצִיאָנוּ מִמִצְרַיִם וְלֹא עָשָׂה בָהֶם שְׁפָטִים, דַּיֵּנוּ.")
;; (def dayenu-eng-2 "If He had taken us out of Egypt and not made judgements on them; [it would have been] enough for us.")

;; (def dayenu-heb-2 "אִלּוּ הוֹצִיאָנוּ מִמִצְרַיִם וְלֹא עָשָׂה בָהֶם שְׁפָטִים, דַּיֵּנוּ.")
;; (def dayenu-eng-2 "If He had taken us out of Egypt and not made judgements on them; [it would have been] enough for us.")
;; (def dayenu-heb-2 "אִלּוּ הוֹצִיאָנוּ מִמִצְרַיִם וְלֹא עָשָׂה בָהֶם שְׁפָטִים, דַּיֵּנוּ.")
;; (def dayenu-eng-2 "If He had taken us out of Egypt and not made judgements on them; [it would have been] enough for us.")
;; (def dayenu-heb-2 "אִלּוּ הוֹצִיאָנוּ מִמִצְרַיִם וְלֹא עָשָׂה בָהֶם שְׁפָטִים, דַּיֵּנוּ.")
;; (def dayenu-eng-2 "If He had taken us out of Egypt and not made judgements on them; [it would have been] enough for us.")

(def dayenu
  (song "Dayenu"
        dayenu-heb-1 dayenu-eng-1
        (song dayenu-heb-2 dayenu-heb-2)
        (song dayenu-heb-3 dayenu-heb-3)
        (song dayenu-heb-4 dayenu-heb-4)
        (song dayenu-heb-5 dayenu-heb-5)
        (song dayenu-heb-6 dayenu-heb-6)
        (song dayenu-heb-7 dayenu-heb-7)
        ))
