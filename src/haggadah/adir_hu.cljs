(ns haggadah.adir-hu
  (:require [haggadah.dsl :refer [song]]))

(def heb-cont-1 "אַדִּיר הוּא יִבְנֶה בֵּיתוֹ בְּקָרוֹב. בִּמְהֵרָה, בִּמְהֵרָה, בְּיָמֵינוּ בְּקָרוֹב. אֵל בְּנֵה, אֵל בְּנֵה, בְּנֵה בֵּיתְךָ בְּקָרוֹב.")
(def eng-cont-1 "Mighty is He, may He build His house soon. Quickly, quickly, in our days, soon. God build, God build, build Your house soon.")

(def heb-cont-2 "בָּחוּר הוּא, גָּדוֹל הוּא, דָּגוּל הוּא יִבְנֶה בֵּיתוֹ בְּקָרוֹב. בִּמְהֵרָה, בִּמְהֵרָה, בְּיָמֵינוּ בְּקָרוֹב. אֵל בְּנֵה, אֵל בְּנֵה, בְּנֵה בֵּיתְךָ בְּקָרוֹב.")
(def eng-cont-2 "Chosen is He, great is He, noted is He. Quickly, quickly, in our days, soon. God build, God build, build Your house soon.")

(def heb-cont-3 "הָדוּר הוּא, וָתִיק הוּא, זַכַּאי הוּא יִבְנֶה בֵּיתוֹ בְּקָרוֹב. בִּמְהֵרָה, בִּמְהֵרָה, בְּיָמֵינוּ בְּקָרוֹב. אֵל בְּנֵה, אֵל בְּנֵה, בְּנֵה בֵּיתְךָ בְּקָרוֹב.")
(def eng-cont-3 "Splendid is He, distinguished is He, meritorious is He. Quickly, quickly, in our days, soon. God build, God build, build Your house soon.")

(def heb-cont-4 "חָסִיד הוּא, טָהוֹר הוּא, יָחִיד הוּא יִבְנֶה בֵּיתוֹ בְּקָרוֹב. בִּמְהֵרָה, בִּמְהֵרָה, בְּיָמֵינוּ בְּקָרוֹב. אֵל בְּנֵה, אֵל בְּנֵה, בְּנֵה בֵּיתְךָ בְּקָרוֹב.")
(def eng-cont-4 "Pious is He, pure is He, unique is He. Quickly, quickly, in our days, soon. God build, God build, build Your house soon.")

(def heb-cont-5 "כַּבִּיר הוּא, לָמוּד הוּא, מֶלֶךְ הוּא יִבְנֶה בֵּיתוֹ בְּקָרוֹב. בִּמְהֵרָה, בִּמְהֵרָה, בְּיָמֵינוּ בְּקָרוֹב. אֵל בְּנֵה, אֵל בְּנֵה, בְּנֵה בֵּיתְךָ בְּקָרוֹב.")
(def eng-cont-5 "Powerful is He, wise is He, A king is He. Quickly, quickly, in our days, soon. God build, God build, build Your house soon.")

(def heb-cont-6 "נוֹרָא הוּא, סַגִּיב הוּא, עִזּוּז הוּא יִבְנֶה בֵּיתוֹ בְּקָרוֹב. בִּמְהֵרָה, בִּמְהֵרָה, בְּיָמֵינוּ בְּקָרוֹב. אֵל בְּנֵה, אֵל בְּנֵה, בְּנֵה בֵּיתְךָ בְּקָרוֹב.")
(def eng-cont-6 "Awesome is He, exalted is He, heroic is He. Quickly, quickly, in our days, soon. God build, God build, build Your house soon.")

(def heb-cont-7 "פּוֹדֶה הוּא, צַדִּיק הוּא, קָּדוֹשׁ הוּא יִבְנֶה בֵּיתוֹ בְּקָרוֹב. בִּמְהֵרָה, בִּמְהֵרָה, בְּיָמֵינוּ בְּקָרוֹב. אֵל בְּנֵה, אֵל בְּנֵה, בְּנֵה בֵּיתְךָ בְּקָרוֹב.")
(def eng-cont-7 "A restorer is He, righteous is He, holy is He. Quickly, quickly, in our days, soon. God build, God build, build Your house soon.")

(def heb-cont-8 "רַחוּם הוּא, שַׁדַּי הוּא, תַּקִּיף הוּא יִבְנֶה בֵּיתוֹ בְּקָרוֹב. בִּמְהֵרָה, בִּמְהֵרָה, בְּיָמֵינוּ בְּקָרוֹב. אֵל בְּנֵה, אֵל בְּנֵה, בְּנֵה בֵּיתְךָ בְּקָרוֹב.")
(def eng-cont-8 "Merciful is He, the Omnipotent is He, dynamic is He. Quickly, quickly, in our days, soon. God build, God build, build Your house soon.")


(def adir-hu
  (song "Adir Hu"
        heb-cont-1 eng-cont-1
        (song heb-cont-2 eng-cont-2)
        (song heb-cont-3 eng-cont-3)
        (song heb-cont-4 eng-cont-4)
        (song heb-cont-5 eng-cont-5)
        (song heb-cont-6 eng-cont-6)
        (song heb-cont-7 eng-cont-7)
        (song heb-cont-8 eng-cont-8)
        ))
