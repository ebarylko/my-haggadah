(ns haggadah.ki-lo-nae
  (:require [haggadah.dsl :refer [song general-content instruction general-content-with-instruction]]))

(def heb-cont-1 "אַדִּיר בִּמְלוּכָה, בָּחוּר כַּהֲלָכָה, גְּדוּדָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה.")
(def eng-cont-1 "Mighty in rulership,  properly chosen, his troops shall say to Him, \"Yours and Yours, Yours since it is Yours, Yours and even Yours, Yours, Lord is the kingdom; since for Him it is pleasant, for Him it is suited.\"")

(def heb-cont-2 "דָּגוּל בִּמְלוּכָה, הָדוּר כַּהֲלָכָה, וָתִיקָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה.")
(def eng-cont-2 "Noted in rulership, properly splendid, His distinguished ones will say to him, \"Yours and Yours, Yours since it is Yours, Yours and even Yours, Yours, Lord is the kingdom; since for Him it is pleasant, for Him it is suited.\"")

(def heb-cont-3 "זַכַּאי בִּמְלוּכָה, חָסִין כַּהֲלָכָה טַפְסְרָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה.")
(def eng-cont-3 "Meritorious in rulership, properly robust, His scribes shall say to him, \"Yours and Yours, Yours since it is Yours, Yours and even Yours, Yours, Lord is the kingdom; since for Him it is pleasant, for Him it is suited.\"")

(def heb-cont-4 "יָחִיד בִּמְלוּכָה, כַּבִּיר כַּהֲלָכָה לִמּוּדָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה.")
(def eng-cont-4 "Unique in rulership, properly powerful, His wise ones say to Him, \"Yours and Yours, Yours since it is Yours, Yours and even Yours, Yours, Lord is the kingdom; since for Him it is pleasant, for Him it is suited.\"")

(def heb-cont-5 "מוֹשֵׁל בִּמְלוּכָה, נוֹרָא כַּהֲלָכָה סְבִיבָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה.")
(def eng-cont-5 "Reigning in rulership, properly awesome, those around Him say to Him, \"Yours and Yours, Yours since it is Yours, Yours and even Yours, Yours, Lord is the kingdom; since for Him it is pleasant, for Him it is suited.\"")

(def heb-cont-6 "עָנָיו בִּמְלוּכָה, פּוֹדֶה כַּהֲלָכָה, צַדִּיקָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה.")
(def eng-cont-6 "Humble in rulership, properly restoring, His righteous ones say to Him, \"Yours and Yours, Yours since it is Yours, Yours and even Yours, Yours, Lord is the kingdom; since for Him it is pleasant, for Him it is suited.\"")

(def heb-cont-7 "קָּדּוֹשׁ בִּמְלוּכָה, רַחוּם כַּהֲלָכָה שִׁנְאַנָּיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה.")
(def eng-cont-7 "Holy in rulership, properly merciful, His angels say to Him, \"Yours and Yours, Yours since it is Yours, Yours and even Yours, Yours, Lord is the kingdom; since for Him it is pleasant, for Him it is suited.\"")

(def heb-cont-8 "תַּקִיף בִּמְלוּכָה, תּוֹמֵךְ כַּהֲלָכָה תְּמִימָיו יֹאמְרוּ לוֹ: לְךָ וּלְךָ, לְךָ כִּי לְךָ, לְךָ אַף לְךָ, לְךָ ה' הַמַּמְלָכָה, כִּי לוֹ נָאֵה, כִּי לוֹ יָאֶה.")
(def eng-cont-8 "Dynamic in rulership, properly supportive, His innocent ones say to Him, \"Yours and Yours, Yours since it is Yours, Yours and even Yours, Yours, Lord is the kingdom; since for Him it is pleasant, for Him it is suited.\"")

(def ki-lo-nae
  (song "Ki Lo Na'e"
        "כִּי לוֹ נָאֶה, כִּי לוֹ יָאֶה."
        "Since for Him it is pleasant, for Him it is suited."
        (song heb-cont-1 eng-cont-1)
        (song heb-cont-2 eng-cont-2)
        (song heb-cont-3 eng-cont-3)
        (song heb-cont-4 eng-cont-4)
        (song heb-cont-5 eng-cont-5)
        (song heb-cont-6 eng-cont-6)
        (song heb-cont-7 eng-cont-7)
        (song heb-cont-8 eng-cont-8)))
