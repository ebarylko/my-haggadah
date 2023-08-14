(ns haggadah.chad-gadya
  (:require [haggadah.dsl :refer [song]]))

(def heb-cont-1 "וְאָתָא שׁוּנְרָא וְאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-1 "Then came a cat and ate the kid that my father bought for two zuz, one kid, one kid.")

(def heb-cont-2 "וְאָתָא כַלְבָּא וְנָשַׁךְ לְשׁוּנְרָא, דְּאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-2 "Then came a dog and bit the cat, that ate the kid that my father bought for two zuz, one kid, one kid.")

(def heb-cont-3 "וְאָתָא חוּטְרָא והִכָּה לְכַלְבָּא, דְּנָשַׁךְ לְשׁוּנְרָא, דְּאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-3 "Then came a stick and hit the dog, that bit the cat, that ate the kid that my father bought for two zuz, one kid, one kid.")

(def heb-cont-4 "וְאָתָא נוּרָא וְשָׂרַף לְחוּטְרָא, דְּהִכָּה לְכַלְבָּא, דְּנָשַׁךְ לְשׁוּנְרָא, דְּאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-4 "Then came fire and burnt the stick, that hit the dog, that bit the cat, that ate the kid that my father bought for two zuz, one kid, one kid.")

(def heb-cont-5 "וְאָתָא מַיָא וְכָבָה לְנוּרָא, דְּשָׂרַף לְחוּטְרָא, דְּהִכָּה לְכַלְבָּא, דְּנָשַׁךְ לְשׁוּנְרָא, דְּאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-5 "Then came water and extinguished the fire, that burnt the stick, that hit the dog, that bit the cat, that ate the kid that my father bought for two zuz, one kid, one kid.")

(def heb-cont-6 "וְאָתָא תורָא וְשָׁתָה לְמַיָא, דְּכָבָה לְנוּרָא, דְּשָׂרַף לְחוּטְרָא, דְּהִכָּה לְכַלְבָּא, דְּנָשַׁךְ לְשׁוּנְרָא, דְּאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-6 "Then came a bull and drank the water, that extinguished the fire, that burnt the stick, that hit the dog, that bit the cat, that ate the kid that my father bought for two zuz, one kid, one kid.")

(def heb-cont-7 "וְאָתָא הַשׁוחֵט וְשָׁחַט לְתורָא, דְּשָּׁתָה לְמַיָא, דְּכָבָה לְנוּרָא, דְּשָׂרַף לְחוּטְרָא, דְּהִכָּה לְכַלְבָּא, דְּנָשַׁךְ לְשׁוּנְרָא, דְּאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-7 "Then came the schochet and slaughtered the bull, that drank the water, that extinguished the fire, that burnt the stick, that hit the dog, that bit the cat, that ate the kid that my father bought for two zuz, one kid, one kid.")

(def heb-cont-8 "וְאָתָא מַלְאָךְ הַמָּוֶת וְשָׁחַט לְשׁוחֵט, דְּשָׁחַט לְתורָא, דְּשָּׁתָה לְמַיָא, דְּכָבָה לְנוּרָא, דְּשָׂרַף לְחוּטְרָא, דְּהִכָּה לְכַלְבָּא, דְּנָשַׁךְ לְשׁוּנְרָא, דְּאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-8 "Then came the angel of death and slaughtered the schochet, who slaughtered the bull, that drank the water, that extinguished the fire, that burnt the stick, that hit the dog, that bit the cat, that ate the kid that my father bought for two zuz, one kid, one kid.")

(def heb-cont-9 "וְאָתָא הַקָּדושׁ בָּרוּךְ הוּא וְשָׁחַט לְמַלְאַךְ הַמָּוֶת, דְּשָׁחַט לְשׁוחֵט, דְּשָׁחַט לְתורָא, דְּשָּׁתָה לְמַיָא, דְּכָבָה לְנוּרָא, דְּשָׂרַף לְחוּטְרָא, דְּהִכָּה לְכַלְבָּא, דְּנָשַׁךְ לְשׁוּנְרָא, דְּאַָכְלָה לְגַדְיָא, דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי. חַד גַּדְיָא, חַד גַּדְיָא.")
(def eng-cont-9 "Then came the Holy One, blessed be He and slaughtered the angel of death, who slaughtered the schochet, who slaughtered the bull, that drank the water, that extinguished the fire, that burnt the stick, that hit the dog, that bit the cat, that ate the kid that my father bought for two zuz, one kid, one kid.")

(def chad-gadya
  (song "Chad Gadya"
        "חַד גַּדְיָא, חַד גַּדְיָא דְּזַבִּין אַבָּא בִּתְרֵי זוּזֵי, חַד גַּדְיָא, חַד גַּדְיָא."
        "One kid, one kid that my father bought for two zuz, one kid, one kid."
        (song heb-cont-1 eng-cont-1)
        (song heb-cont-2 eng-cont-2)
        (song heb-cont-3 eng-cont-3)
        (song heb-cont-4 eng-cont-4)
        (song heb-cont-5 eng-cont-5)
        (song heb-cont-6 eng-cont-6)
        (song heb-cont-7 eng-cont-7)
        (song heb-cont-8 eng-cont-8)
        (song heb-cont-9 eng-cont-9)
        ))

