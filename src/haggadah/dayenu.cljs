(ns haggadah.dayenu
  (:require [haggadah.dsl :as dsl :refer [song general-content]])
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

(def dayenu-heb-10 "אִלּוּ סִפֵּק צָרְכֵּנוּ בְּמִדְבָּר אַרְבָּעִים שָׁנָה וְלֹא הֶאֱכִילָנוּ אֶת־הַמָּן דַּיֵּנוּ.")
(def dayenu-eng-10 "If He had supplied our needs in the wilderness for forty years and had not fed us the manna; [it would have been] enough for us.")

(def dayenu-heb-11  "אִלּוּ הֶאֱכִילָנוּ אֶת־הַמָּן וְלֹא נָתַן לָנוּ אֶת־הַשַׁבָּת, דַּיֵּנוּ.")
(def dayenu-eng-11 "If He had fed us the manna and had not given us the Shabbat; [it would have been] enough for us.")

(def dayenu-heb-12 "אִלּוּ נָתַן לָנוּ אֶת־הַשַׁבָּת, וְלֹא קֵרְבָנוּ לִפְנֵי הַר סִינַי, דַּיֵּנוּ.")
(def dayenu-eng-12 "If He had given us the Shabbat and had not brought us close to Mount Sinai; [it would have been] enough for us.")

(def dayenu-heb-13 "אִלּוּ קֵרְבָנוּ לִפְנֵי הַר סִינַי, וְלא נָתַן לָנוּ אֶת־הַתּוֹרָה. דַּיֵּנוּ.")
(def dayenu-eng-13 "If He had brought us close to Mount Sinai and had not given us the Torah; [it would have been] enough for us.")

(def dayenu-heb-14 "אִלּוּ נָתַן לָנוּ אֶת־הַתּוֹרָה וְלֹא הִכְנִיסָנוּ לְאֶרֶץ יִשְׂרָאֵל, דַּיֵּנוּ.")
(def dayenu-eng-14 "If He had given us the Torah and had not brought us into the land of Israel; [it would have been] enough for us.")

(def dayenu-heb-15  "אִלּוּ הִכְנִיסָנוּ לְאֶרֶץ יִשְׂרָאֵל וְלֹא בָנָה לָנוּ אֶת־בֵּית הַבְּחִירָה דַּיֵּנוּ.")
(def dayenu-eng-15  "If He had brought us into the land of Israel and had not built us the 'Chosen House' [the Temple; it would have been] enough for us.")

(def dayenu-end-heb "עַל אַחַת, כַּמָה וְכַּמָה, טוֹבָה כְפוּלָה וּמְכֻפֶּלֶת לַמָּקוֹם עָלֵינוּ: שֶׁהוֹצִיאָנוּ מִמִּצְרַיִם, וְעָשָׂה בָהֶם שְׁפָטִים, וְעָשָׂה בֵאלֹהֵיהֶם, וְהָרַג אֶת־בְּכוֹרֵיהֶם, וְנָתַן לָנוּ אֶת־מָמוֹנָם, וְקָרַע לָנוּ אֶת־הַיָּם, וְהֶעֱבִירָנוּ בְּתוֹכוֹ בֶּחָרָבָה, וְשִׁקַּע צָרֵנוּ בְּתוֹכוֹ, וְסִפֵּק צָרְכֵּנוּ בַּמִדְבָּר אַרְבָּעִים שָׁנָה, וְהֶאֱכִילָנוּ אֶת־הַמָּן, וְנָתַן לָנוּ אֶת־הַשַּׁבָּת, וְקֵרְבָנוּ לִפְנֵי הַר סִינַי, וְנָתַן לָנוּ אֶת־הַתּוֹרָה, וְהִכְנִיסָנוּ לְאֶרֶץ יִשְׂרָאֵל, וּבָנָה לָנוּ אֶת־בֵּית הַבְּחִירָה לְכַפֵּר עַל־כָּל־עֲוֹנוֹתֵינוּ.")

(def dayenu-end-eng "How much more so is the good that is doubled and quadrupled that the Place [of all bestowed] upon us [enough for us]; since he took us out of Egypt, and made judgments with them, and made [them] with their gods, and killed their firstborn, and gave us their money, and split the Sea for us, and brought us through it on dry land, and pushed down our enemies in [the Sea], and supplied our needs in the wilderness for forty years, and fed us the manna, and gave us the Shabbat, and brought us close to Mount Sinai, and gave us the Torah, and brought us into the land of Israel and built us the 'Chosen House' [the Temple] to atone upon all of our sins.")

(def dayenu
  (song "Dayenu"
        dayenu-heb-1 dayenu-eng-1
        (song dayenu-heb-2 dayenu-eng-2)
        (song dayenu-heb-3 dayenu-eng-3)
        (song dayenu-heb-4 dayenu-eng-4)
        (song dayenu-heb-5 dayenu-eng-5)
        (song dayenu-heb-6 dayenu-eng-6)
        (song dayenu-heb-7 dayenu-eng-7)
        (song dayenu-heb-8 dayenu-eng-8)
        (song dayenu-heb-8 dayenu-eng-8)
        (song dayenu-heb-9 dayenu-eng-9)
        (song dayenu-heb-10 dayenu-eng-10)
        (song dayenu-heb-11 dayenu-eng-11)
        (song dayenu-heb-12 dayenu-eng-12)
        (song dayenu-heb-13 dayenu-eng-13)
        (song dayenu-heb-14 dayenu-eng-14)
        (song dayenu-heb-15 dayenu-eng-15)
        (general-content dayenu-end-heb dayenu-end-eng)
        ))

