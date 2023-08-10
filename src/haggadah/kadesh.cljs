(ns haggadah.kadesh
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]))


(def kadesh-cont-heb "וַיְהִי עֶרֶב וַיְהִי בֹקֶר יוֹם הַשִּׁשִּׁי. וַיְכֻלּוּ הַשָּׁמַיִם וְהָאָרֶץ וְכָל־צְבָאָם. וַיְכַל אֱלֹהִים בַּיּוֹם הַשְּׁבִיעִי מְלַאכְתּוֹ אֲשֶׁר עָשָׂה וַיִּשְׁבֹּת בַּיּוֹם הַשְּׁבִיעִי מִכָּל מְלַאכְתּוֹ אֲשֶׁר עָשָׂה. וַיְבָרֵךְ אֱלֹהִים אֶת יוֹם הַשְּׁבִיעִי וַיְקַדֵּשׁ אוֹתוֹ כִּי בוֹ שָׁבַת מִכָּל־מְלַאכְתּוֹ אֲשֶׁר בָּרָא אֱלֹהִים לַעֲשׂוֹת.")
(def kadesh-cont-eng "And there was evening and there was morning, the sixth day. And the heaven and the earth were finished, and all their host. And on the seventh day God finished His work which He had done; and He rested on the seventh day from all His work which He had done. And God blessed the seventh day, and sanctified it; because He rested on it from all of His work which God created in doing (Genesis 1:31-2:3).")

(def kadesh-bracha-heb-1 "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן.")
(def kadesh-bracha-eng-1 "Blessed are You, Lord our God, King of the universe, who creates the fruit of the vine.")

(def kadesh-bracha-heb-2 "בָּרוּךְ אַתָּה ה', אֱלהֵינוּ מֶלֶךְ הָעוֹלָם אֲשֶׁר בָּחַר בָּנוּ מִכָּל־עָם וְרוֹמְמָנוּ מִכָּל־לָשׁוֹן וְקִדְּשָׁנוּ בְּמִצְוֹתָיו. וַתִּתֶּן לָנוּ ה' אֱלֹהֵינוּ בְּאַהֲבָה (לשבת: שַׁבָּתוֹת לִמְנוּחָה וּ) מוֹעֲדִים לְשִׂמְחָה, חַגִּים וּזְמַנִּים לְשָׂשוֹן, (לשבת: אֶת יוֹם הַשַּׁבָּת הַזֶּה וְ) אֶת יוֹם חַג הַמַּצּוֹת הַזֶּה זְמַן חֵרוּתֵנוּ, (לשבת: בְּאַהֲבָה) מִקְרָא קֹדֶשׁ זֵכֶר לִיצִיאַת מִצְרָיִם. כִּי בָנוּ בָחַרְתָּ וְאוֹתָנוּ קִדַּשְׁתָּ מִכָּל הָעַמִּים, (לשבת: וְשַׁבָּת) וּמוֹעֲדֵי קָדְשֶׁךָ (לשבת: בְּאַהֲבָה וּבְרָצוֹן) בְּשִׂמְחָה וּבְשָׂשוֹן הִנְחַלְתָּנוּ.")
(def kadesh-bracha-eng-2 "Blessed are You, Lord our God, King of the universe, who has chosen us from all peoples and has raised us above all tongues and has sanctified us with His commandments. And You have given us, Lord our God, [Sabbaths for rest], appointed times for happiness, holidays and special times for joy, [this Sabbath day, and] this Festival of Matsot, our season of freedom [in love] a holy convocation in memory of the Exodus from Egypt. For You have chosen us and sanctified us above all peoples. In Your gracious love, You granted us Your [holy Sabbath, and] special times for happiness and joy.")

(def kadesh-bracha-heb-3 "בָּרוּךְ אַתָּה ה', מְקַדֵּשׁ (לשבת: הַשַׁבָּת וְ) יִשְׂרָאֵל וְהַזְּמַנִּים.")
(def kadesh-bracha-eng-3 "Blessed are You, O Lord, who sanctifies [the Sabbath,] Israel, and the appointed times.")

(def kadesh-bracha-heb-4
  "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, בּוֹרֵא מְאוֹרֵי הָאֵשׁ. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם הַמַּבְדִיל בֵּין קֹדֶשׁ לְחֹל, בֵּין אוֹר לְחשֶׁךְ, בֵּין יִשְׂרָאֵל לָעַמִּים, בֵּין יוֹם הַשְּׁבִיעִי לְשֵׁשֶׁת יְמֵי הַמַּעֲשֶׂה. בֵּין קְדֻשַּׁת שַׁבָּת לִקְדֻשַּׁת יוֹם טוֹב הִבְדַּלְתָּ, וְאֶת־יוֹם הַשְּׁבִיעִי מִשֵּׁשֶׁת יְמֵי הַמַּעֲשֶׂה קִדַּשְׁתָּ. הִבְדַּלְתָּ וְקִדַּשְׁתָּ אֶת־עַמְּךָ יִשְׂרָאֵל בִּקְדֻשָּׁתֶךָ.")
(def kadesh-bracha-eng-4 "Blessed are You, Lord our God, King of the universe, who creates the light of the fire. Blessed are You, Lord our God, King of the universe, who distinguishes between the holy and the profane, between light and darkness, between Israel and the nations, between the seventh day and the six working days. You have distinguished between the holiness of the Sabbath and the holiness of the Festival, and You have sanctified the seventh day above the six working days. You have distinguished and sanctified Your people Israel with Your holiness.")

(def kadesh-bracha-heb-5 "בָּרוּךְ אַתָּה ה', הַמַּבְדִיל בֵּין קֹדֶשׁ לְקֹדֶשׁ.")
(def kadesh-bracha-eng-5 "Blessed are You, O Lord, who distinguishes between the holy and the holy.")

(def kadesh-bracha-heb-6 "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, שֶׁהֶחֱיָנוּ וְקִיְּמָנוּ וְהִגִּיעָנוּ לַזְּמַן הַזֶּה.")
(def kadesh-bracha-eng-6 "Blessed are You, Lord our God, King of the universe, who has granted us life and sustenance and permitted us to reach this season.")

(def instr-heb-1 "מוזגים כוס ראשון. המצּות מכוסות.")
(def instr-eng-1 "We pour the first cup. The matsot are covered")
(def instr-heb-2 "בְּשַׁבָּת מַתְחִילִין")
(def instr-eng-2 "On Shabbat, begin here:")
(def instr-heb-3 "בחול מתחילין:")
(def instr-eng-3 "On weekdays, begin here:")
(def instr-heb-4 "בּמוצאי שבת מוסיפים:")
(def instr-eng-4  "On Saturday night add the following two paragraphs:")
(def instr-heb-5 "שותה בהסיבת שמאל ואינו מברך ברכה אחרונה.")
(def instr-eng-5 "Drink while reclining to the left and do not recite a blessing after drinking.")

(general-content kadesh-cont-heb kadesh-cont-eng)

(def kadesh
 (section "קַדֵּשׁ"
          "Kadesh"
         (instruction instr-heb-1 instr-eng-1)
          (instruction instr-heb-2 instr-eng-2)
          (general-content kadesh-cont-heb kadesh-cont-eng)
          (instruction instr-heb-3 instr-eng-3)
          (bracha kadesh-bracha-heb-1 kadesh-bracha-eng-1)
          (bracha kadesh-bracha-heb-2 kadesh-bracha-eng-2)
          (bracha kadesh-bracha-heb-3 kadesh-bracha-eng-3)
          (instruction instr-heb-4 instr-eng-4)
          (bracha kadesh-bracha-heb-4 kadesh-bracha-eng-4)
          (bracha kadesh-bracha-heb-5 kadesh-bracha-eng-5)
          (bracha kadesh-bracha-heb-6 kadesh-bracha-eng-6)
          (instruction instr-heb-5 instr-eng-5)))
