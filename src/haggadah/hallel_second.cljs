(ns haggadah.hallel-second
  (:require [haggadah.dsl :as dsl :refer [instruction section general-content bracha-with-more-content general-content-with-instruction]]))

(def heb-cont-1 "לֹא לָנוּ, ה', לֹא לָנוּ, כִּי לְשִׁמְךָ תֵּן כָּבוֹד, עַל חַסְדְּךָ עַל אֲמִתֶּךָ. לָמָּה יֹאמְרוּ הַגּוֹיִם אַיֵּה נָא אֱלֹהֵיהֶם. וְאֱלֹהֵינוּ בַּשָּׁמַיִם, כֹּל אֲשֶׁר חָפֵץ עָשָׂה. עֲצַבֵּיהֶם כֶּסֶף וְזָהָב מַעֲשֵׂה יְדֵי אָדָם. פֶּה לָהֶם וְלֹא יְדַבֵּרוּ, עֵינַיִם לָהֶם וְלֹא יִרְאוּ. אָזְנָיִם לָהֶם וְלֹא יִשְׁמָעוּ, אַף לָהֶם וְלֹא יְרִיחוּן. יְדֵיהֶם וְלֹא יְמִישׁוּן, רַגְלֵיהֶם וְלֹא יְהַלֵּכוּ, לׁא יֶהְגּוּ בִּגְרוֹנָם. כְּמוֹהֶם יִהְיוּ עֹשֵׂיהֶם, כֹּל אֲשֶׁר בֹּטֵחַ בָּהֶם. יִשְׂרָאֵל בְּטַח בַּיי, עֶזְרָם וּמָגִנָּם הוּא. בֵּית אַהֲרֹן בִּטְחוּ בַיי, עֶזְרָם וּמָגִנָּם הוּא. יִרְאֵי ה' בִּטְחוּ בַיי, עֶזְרָם וּמָגִנָּם הוּא. יי זְכָרָנוּ יְבָרֵךְ. יְבָרֵךְ אֶת בֵּית יִשְׂרָאֵל, יְבָרֵךְ אֶת בֵּית אַהֲרֹן, יְבָרֵךְ יִרְאֵי ה', הַקְּטַנִים עִם הַגְּדֹלִים. יֹסֵף ה' עֲלֵיכֶם, עֲלֵיכֶם וְעַל בְּנֵיכֶם. בְּרוּכִים אַתֶּם לַיי, עֹשֵׂה שָׁמַיִם וָאָרֶץ. הַשָּׁמַיִם שָׁמַיִם לַיי וְהָאָרֶץ נָתַן לִבְנֵי אָדָם. לֹא הַמֵּתִים יְהַלְלוּ יָהּ וְלֹא כָּל יֹרְדֵי דוּמָה. וַאֲנַחְנוּ נְבָרֵךְ יָהּ מֵעַתָּה וְעַד עוֹלָם. הַלְלוּיָהּ.")

(def eng-cont-1 "Not to us, not to us, but rather to Your name, give glory for your kindness and for your truth. Why should the nations say, \"Say, where is their God?\" But our God is in the heavens, all that He wanted, He has done. Their idols are silver and gold, the work of men's hands. They have a mouth but do not speak; they have eyes but do not see. They have ears but do not hear; they have a nose but do not smell. Hands, but they do not feel; feet, but do not walk; they do not make a peep from their throat. Like them will be their makers, all those that trust in them. Israel, trust in the Lord; their help and shield is He. House of Aharon, trust in the Lord; their help and shield is He. Those that fear the Lord, trust in the Lord; their help and shield is He. The Lord who remembers us, will bless; He will bless the House of Israel; He will bless the House of Aharon. He will bless those that fear the Lord, the small ones with the great ones. May the Lord bring increase to you, to you and to your children. Blessed are you to the Lord, the maker of the heavens and the earth. The heavens, are the Lord's heavens, but the earth He has given to the children of man. It is not the dead that will praise the Lord, and not those that go down to silence. But we will bless the Lord from now and forever. Halleluyah! (Psalms 115)")

(def instr-heb-1 "מוזגין כוס רביעי וגומרין עליו את ההלל")
(def instr-eng-1 "We pour the fourth cup and complete the Hallel")

(def heb-cont-2 "אָהַבְתִּי כִּי יִשְׁמַע ה' אֶת קוֹלִי תַּחֲנוּנָי. כִּי הִטָּה אָזְנוֹ לִי וּבְיָמַי אֶקְרָא. אֲפָפוּנִי חֶבְלֵי מָוֶת וּמְצָרֵי שְׁאוֹל מְצָאוּנִי, צָרָה וְיָגוֹן אֶמְצָא. וּבְשֵׁם ה' אֶקְרָא: אָנָּא ה' מַלְּטָה נַפְשִׁי. חַנוּן ה' וְצַדִּיק, וֵאֱלֹהֵינוּ מְרַחֵם. שֹׁמֵר פְּתָאִים ה', דַּלוֹתִי וְלִי יְהושִׁיעַ. שׁוּבִי נַפְשִׁי לִמְנוּחָיְכִי, כִּי ה' גָּמַל עָלָיְכִי. כִּי חִלַּצְתָּ נַפְשִׁי מִמָּוֶת, אֶת עֵינִי מִן דִּמְעָה, אֶת רַגְלִי מִדֶּחִי. אֶתְהַלֵךְ לִפְנֵי ה' בְּאַרְצוֹת הַחַיִּים. הֶאֱמַנְתִּי כִּי אֲדַבֵּר, אֲנִי עָנִיתִי מְאֹד. אֲנִי אָמַרְתִּי בְחָפְזִי כָּל הָאָדָם כּזֵֹב.")

(def eng-cont-2 "I have loved the Lord - since He hears my voice, my supplications. Since He inclined His ear to me - and in my days, I will call out. The pangs of death have encircled me and the straits of the Pit have found me and I found grief. And in the name of the Lord I called, \"Please Lord, Spare my soul.\" Gracious is the Lord and righteous, and our God acts mercifully. The Lord watches over the silly; I was poor and He has saved me. Return, my soul to your tranquility, since the Lord has favored you. Since You have rescued my soul from death, my eyes from tears, my feet from stumbling. I will walk before the Lord in the lands of the living. I have trusted, when I speak - I am very afflicted. I said in my haste, all men are hypocritical. (Psalms 116:1-11)")


(def heb-cont-3 "מָה אָשִׁיב לַיי כֹּל תַּגְמוּלוֹהִי עָלָי. כּוֹס יְשׁוּעוֹת אֶשָּׂא וּבְשֵׁם ה' אֶקְרָא. נְדָרַי לַיי אֲשַׁלֵּם נֶגְדָה נָּא לְכָל עַמּוֹ. יָקָר בְּעֵינֵי ה' הַמָּוְתָה לַחֲסִידָיו. אָנָּה ה' כִּי אֲנִי עַבְדֶּךָ, אֲנִי עַבְדְּךָ בֶּן אֲמָתֶךָ, פִּתַּחְתָּ לְמוֹסֵרָי. לְךָ אֶזְבַּח זֶבַח תּוֹדָה וּבְשֵׁם ה' אֶקְרָא. נְדָרַי לַיי אֲשַׁלֵּם נֶגְדָה נָּא לְכָל עַמּוֹ. בְּחַצְרוֹת בֵּית ה', בְּתוֹכֵכִי יְרוּשָלַיִם. הַלְלוּיָהּ.")

(def eng-cont-3 "What can I give back to the Lord for all that He has favored me? A cup of salvations I will raise up and I will call out in the name of the Lord. My vows to the Lord I will pay, now in front of His entire people. Precious in the eyes of the Lord is the death of His pious ones. Please Lord, since I am Your servant, the son of Your maidservant; You have opened my chains. To You will I offer a thanksgiving offering and I will call out in the name of the Lord. My vows to the Lord I will pay, now in front of His entire people. In the courtyards of the house of the Lord, in your midst, Jerusalem. Halleluyah!  (Psalms 116:12-19)")


(def heb-cont-4 "הַלְלוּ אֶת ה' כָּל גּוֹיִם, שַׁבְּחוּהוּ כָּל הָאֻמִּים. כִּי גָבַר עָלֵינוּ חַסְדּוֹ, וֶאֱמֶת ה' לְעוֹלָם. הַלְלוּיָהּ. הוֹדוּ לַיי כִּי טוֹב כִּי לְעוֹלָם חַסְדּוֹ. יֹאמַר נָא יִשְׂרָאֵל כִּי לְעוֹלָם חַסְדּוֹ. יֹאמְרוּ נָא בֵית אַהֲרֹן כִּי לְעוֹלָם חַסְדּוֹ. יֹאמְרוּ נָא יִרְאֵי ה' כִּי לְעוֹלָם חַסְדּוֹ.")

(def eng-cont-4 "Praise the name of the Lord, all nations; extol Him all peoples. Since His kindness has overwhelmed us and the truth of the Lord is forever. Halleluyah! Thank the Lord, since He is good, since His kindness is forever. Let Israel now say, \"Thank the Lord, since He is good, since His kindness is forever.\" Let the House of Aharon now say, \"Thank the Lord, since He is good, since His kindness is forever.\" Let those that fear the Lord now say, \"Thank the Lord, since He is good, since His kindness is forever.\" (Psalms 117-118:4)")

(def heb-cont-5 "מִן הַמֵּצַר קָרָאתִי יָּהּ, עָנָנִי בַמֶּרְחַב יָהּ. ה' לִי, לֹא אִירָא – מַה יַּעֲשֶׂה לִי אָדָם, ה' לִי בְּעֹזְרָי וַאֲנִי אֶרְאֶה בְּשׂנְאָי. טוֹב לַחֲסוֹת בַּיי מִבְּטֹחַ בָּאָדָם. טוֹב לַחֲסוֹת בַּיי מִבְּטֹחַ בִּנְדִיבִים. כָּל גּוֹיִם סְבָבוּנִי, בְּשֵׁם ה' כִּי אֲמִילַם. סַבּוּנִי גַם סְבָבוּנִי, בְּשֵׁם ה' כִּי אֲמִילַם. סַבּוּנִי כִדְּבֹרִים, דֹּעֲכוּ כְּאֵשׁ קוֹצִים, בְּשֵׁם ה' כִּי אֲמִילַם. דָּחֹה דְּחִיתַנִי לִנְפֹּל, וַיי עֲזָרָנִי. עָזִּי וְזִמְרָת יָהּ וַיְהִי לִי לִישׁוּעָה. קוֹל רִנָּה וִישׁוּעָה בְּאָהֳלֵי צַדִּיקִים: יְמִין ה' עֹשָׂה חָיִל, יְמִין ה' רוֹמֵמָה, יְמִין ה' עֹשָׂה חָיִל. לֹא אָמוּת כִּי אֶחְיֶה, וַאֲסַפֵּר מַעֲשֵׂי יָהּ. יַסֹּר יִסְּרַנִי יָּהּ, וְלַמָּוֶת לֹא נְתָנָנִי. פִּתְחוּ לִי שַׁעֲרֵי צֶדֶק, אָבֹא בָם, אוֹדֶה יָהּ. זֶה הַשַּׁעַר לַיי, צַדִּיקִים יָבֹאוּ בוֹ.")

(def eng-cont-5 "From the strait I have called, Lord; He answered me from the wide space, the Lord. The Lord is for me, I will not fear, what will man do to me? The Lord is for me with my helpers, and I shall glare at those that hate me. It is better to take refuge with the Lord than to trust in man. It is better to take refuge with the Lord than to trust in nobles. All the nations surrounded me - in the name of the Lord, as I will chop them off. They surrounded me, they also encircled me - in the name of the Lord, as I will chop them off. They surrounded me like bees, they were extinguished like a fire of thorns - in the name of the Lord, as I will chop them off. You have surely pushed me to fall, but the Lord helped me. My boldness and song is the Lord, and He has become my salvation. The sound of happy song and salvation is in the tents of the righteous, the right hand of the Lord acts powerfully. I will not die but rather I will live and tell over the acts of the Lord. The Lord has surely chastised me, but He has not given me over to death. Open up for me the gates of righteousness; I will enter them, thank the Lord. This is the gate of the Lord, the righteous will enter it. (Psalms 118:5-20)")


(def heb-cont-6 "אוֹדְךָ כִּי עֲנִיתָנִי וַתְּהִי לִי לִישׁוּעָה. אוֹדְךָ כִּי עֲנִיתָנִי וַתְּהִי לִי לִישׁוּעָה. אֶבֶן מָאֲסוּ הַבּוֹנִים הָיְתָה לְראשׁ פִּנָּה. אֶבֶן מָאֲסוּ הַבּוֹנִים הָיְתָה לְראשׁ פִּנָּה. מֵאֵת ה' הָיְתָה זֹּאת הִיא נִפְלָאת בְּעֵינֵינוּ. מֵאֵת ה' הָיְתָה זֹּאת הִיא נִפְלָאת בְּעֵינֵינוּ. זֶה הַיּוֹם עָשָׂה ה'. נָגִילָה וְנִשְׂמְחָה בוֹ. זֶה הַיּוֹם עָשָׂה ה'. נָגִילָה וְנִשְׂמְחָה בוֹ.")

(def eng-cont-6 "I will thank You, since You answered me and You have become my salvation. The stone that was left by the builders has become the main cornerstone. From the Lord was this, it is wondrous in our eyes. This is the day of the Lord, let us exult and rejoice upon it. (Psalms 118:21-24)")


(def heb-cont-7 "אָנָּא ה', הוֹשִיעָה נָּא. אָנָּא ה', הוֹשִיעָה נָּא. אָנָּא ה', הַצְלִיחָה נָא. אָנָּא ה', הַצְלִיחָה נָא.")

(def eng-cont-7 "Please, Lord, save us now; please, Lord, give us success now! (Psalms 118:25)")

(def heb-cont-8 "בָּרוּךְ הַבָּא בְּשֵׁם ה', בֵּרַכְנוּכֶם מִבֵּית ה'. בָּרוּךְ הַבָּא בְּשֵׁם ה', בֵּרַכְנוּכֶם מִבֵּית ה'. אֵל ה' וַיָּאֶר לָנוּ. אִסְרוּ חַג בַּעֲבֹתִים עַד קַרְנוֹת הַמִּזְבֵּחַ. אֵל ה' וַיָּאֶר לָנוּ. אִסְרוּ חַג בַּעֲבֹתִים עַד קַרְנוֹת הַמִּזְבֵּחַ. אֵלִי אַתָּה וְאוֹדֶךָּ, אֱלֹהַי – אֲרוֹמְמֶךָּ. אֵלִי אַתָּה וְאוֹדֶךָּ, אֱלֹהַי – אֲרוֹמְמֶךָּ. הוֹדוּ לַיי כִּי טוֹב, כִּי לְעוֹלָם חַסְדּוֹ. הוֹדוּ לַיי כִּי טוֹב, כִּי לְעוֹלָם חַסְדּוֹ.")

(def eng-cont-8 "Blessed be the one who comes in the name of the Lord, we have blessed you from the house of the Lord. God is the Lord, and He has illuminated us; tie up the festival offering with ropes until it reaches the corners of the altar. You are my Power and I will Thank You; my God and I will exalt You. Thank the Lord, since He is good, since His kindness is forever.(Psalms 118:26-29) ")


(def heb-cont-9 "יְהַלְלוּךָ ה' אֱלֹהֵינוּ כָּל מַעֲשֶׂיךָ, וַחֲסִידֶיךָ צַדִּיקִים עוֹשֵׂי רְצוֹנֶךָ, וְכָל עַמְּךָ בֵּית יִשְׂרָאֵל בְּרִנָה יוֹדוּ וִיבָרְכוּ, וִישַׁבְּחוּ וִיפָאֲרוּ, וִירוֹמְמוּ וְיַעֲרִיצוּ, וְיַקְדִּישׁוּ וְיַמְלִיכוּ אֶת שִׁמְךָ, מַלְכֵּנוּ. כִּי לְךָ טוֹב לְהוֹדותֹ וּלְשִׁמְךָ נָאֶה לְזַמֵּר, כִּי מֵעוֹלָם וְעַד עוֹלָם אַתָּה אֵל.")

(def eng-cont-9 "All of your works shall praise You, Lord our God, and your pious ones, the righteous ones who do Your will; and all of Your people, the House of Israel will thank and bless in joyful song: and extol and glorify, and exalt and acclaim, and sanctify and coronate Your name, our King. Since, You it is good to thank, and to Your name it is pleasant to sing, since from always and forever are you the Power.")

(def second-half
  (general-content-with-instruction "Second Half of Hallel"
                                    heb-cont-1 eng-cont-1
                                    (instruction instr-heb-1 instr-eng-1)
                                    (general-content heb-cont-2 eng-cont-2)
                                    (general-content heb-cont-3 eng-cont-3)
                                    (general-content heb-cont-4 eng-cont-4)
                                    (general-content heb-cont-5 eng-cont-5)
                                    (general-content heb-cont-6 eng-cont-6)
                                    (general-content heb-cont-7 eng-cont-7)
                                    (general-content heb-cont-8 eng-cont-8)
                                    (general-content heb-cont-9 eng-cont-9)))

