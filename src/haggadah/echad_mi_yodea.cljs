(ns haggadah.echad-mi-yodea
  (:require [haggadah.dsl :refer [song]]))

(def one-heb "אֶחָד מִי יוֹדֵעַ? אֶחָד אֲנִי יוֹדֵעַ: אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")
(def one-eng "Who knows two? I know two: two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def two-heb "שְׁנַיִם מִי יוֹדֵעַ? שְׁנַיִם אֲנִי יוֹדֵעַ: שְׁנֵי לֻחוֹת הַבְּרִית. אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")
(def two-eng "Who knows two? I know two: two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def three-heb "שְׁנַיִם מִי יוֹדֵעַ? שְׁנַיִם אֲנִי יוֹדֵעַ: שְׁנֵי לֻחוֹת הַבְּרִית. אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")
(def three-eng "Who knows three? I know three: three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def four-heb "אַרְבַּע מִי יוֹדֵעַ? אַרְבַּע אֲנִי יוֹדֵעַ: אַרְבַּע אִמָּהוֹת, שְׁלשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")
(def four-eng "Who knows four? I know four: four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def five-heb "חֲמִשָּׁה מִי יוֹדֵעַ? חֲמִשָּׁה אֲנִי יוֹדֵעַ: חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")
(def five-eng "Who knows five? I know five: five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def six-heb "שִׁשָּׂה מִי יוֹדֵעַ? שִׁשָּׂה אֲנִי יוֹדֵעַ: שִׁשָּׁה סִדְרֵי מִשְׁנָה, חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלֹשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")


(def six-eng "Who knows six? I know six: six are the orders of the Mishnah, five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")


(def seven-eng "Who knows seven? I know seven: seven are the days of the week, six are the orders of the Mishnah, five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def seven-heb
 "שִׁבְעָה מִי יוֹדֵעַ? שִׁבְעָה אֲנִי יוֹדֵעַ: שִׁבְעָה יְמֵי שַׁבָּתָא, שִׁשָּׁה סִדְרֵי מִשְׁנָה, חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")


(def eight-heb
 "שְׁמוֹנָה מִי יוֹדֵעַ? שְׁמוֹנָה אֲנִי יוֹדֵעַ: שְׁמוֹנָה יְמֵי מִילָה, שִׁבְעָה יְמֵי שַׁבָּתָא, שִׁשָּׁה סִדְרֵי מִשְׁנָה, חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")


(def eight-eng "Who knows eight? I know eight: eight are the days of circumcision, seven are the days of the week, six are the orders of the Mishnah, five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def nine-eng "Who knows nine? I know nine: nine are the months of birth, eight are the days of circumcision, seven are the days of the week, six are the orders of the Mishnah, five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def nine-heb "תִּשְׁעָה מִי יוֹדֵעַ? תִּשְׁעָה אֲנִי יוֹדֵעַ: תִּשְׁעָה יַרְחֵי לֵדָה, שְׁמוֹנָה יְמֵי מִילָה, שִׁבְעָה יְמֵי שַׁבָּתָא, שִׁשָּׁה סִדְרֵי מִשְׁנָה, חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלֹשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")


(def ten-heb "עֲשָֹרָה מִי יוֹדֵעַ? עֲשָֹרָה אֲנִי יוֹדֵעַ: עֲשָׂרָה דִבְּרַיָא, תִּשְׁעָה יַרְחֵי לֵדָה, שְׁמוֹנָה יְמֵי מִילָה, שִׁבְעָה יְמֵי שַׁבָּתָא, שִׁשָּׁה סִדְרֵי מִשְׁנָה, חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ. עֲשָֹרָה אַחַד עָשָׂר מִי יוֹדֵעַ? אַחַד עָשָׂר אֲנִי יוֹדֵעַ: אַחַד עָשָׂר כּוֹכְבַיָּא, עֲשָׂרָה דִבְּרַיָא, תִּשְׁעָה יַרְחֵי לֵדָה, שְׁמוֹנָה יְמֵי מִילָה, שִׁבְעָה יְמֵי שַׁבָּתָא, שִׁשָּׁה סִדְרֵי מִשְׁנָה, חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")


(def ten-eng "Who knows ten? I know ten: ten are the statements, nine are the months of birth, eight are the days of circumcision, seven are the days of the week, six are the orders of the Mishnah, five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")


(def eleven-eng "Who knows eleven? I know eleven: eleven are the stars, ten are the statements, nine are the months of birth, eight are the days of circumcision, seven are the days of the week, six are the orders of the Mishnah, five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def eleven-heb "שְׁנֵים עָשָׂר מִי יוֹדֵעַ? שְׁנֵים עָשָׂר אֲנִי יוֹדֵעַ: שְׁנֵים עָשָׂר שִׁבְטַיָּא, אַחַד עָשָׂר כּוֹכְבַיָּא, עֲשָׂרָה דִבְּרַיָא, תִּשְׁעָה יַרְחֵי לֵדָה, שְׁמוֹנָה יְמֵי מִילָה, שִׁבְעָה יְמֵי שַׁבָּתָא, שִׁשָּׁה סִדְרֵי מִשְׁנָה, חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")


(def twelve-heb "שְׁלשָׁה עֶשָׂר מִי יוֹדֵעַ? שְׁלשָׁה עָשָׂר אֲנִי יוֹדֵעַ: שְׁלשָׁה עָשָׂר מִדַּיָּא. שְׁנֵים עָשָׂר שִׁבְטַיָּא, אַחַד עָשָׂר כּוֹכְבַיָּא, עֲשָׂרָה דִבְּרַיָא, תִּשְׁעָה יַרְחֵי לֵדָה, שְׁמוֹנָה יְמֵי מִילָה, שִׁבְעָה יְמֵי שַׁבָּתָא, שִׁשָּׁה סִדְרֵי מִשְׁנָה, חֲמִשָּׁה חוּמְשֵׁי תוֹרָה, אַרְבַּע אִמָּהוֹת, שְׁלשָׁה אָבוֹת, שְׁנֵי לֻחוֹת הַבְּרִית, אֶחָד אֱלֹהֵינוּ שֶׁבַּשָּׁמַיִם וּבָאָרֶץ.")

(def twelve-eng "Who knows twelve? I know twelve: twelve are the tribes, eleven are the stars, ten are the statements, nine are the months of birth, eight are the days of circumcision, seven are the days of the week, six are the orders of the Mishnah, five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.  Who knows thirteen? I know thirteen: thirteen are the characteristics, twelve are the tribes, eleven are the stars, ten are the statements, nine are the months of birth, eight are the days of circumcision, seven are the days of the week, six are the orders of the Mishnah, five are the books of the Torah, four are the mothers, three are the fathers, two are the tablets of the covenant, One is our God in the heavens and the earth.")

(def echad-mi-yodea
  (song "Echad Mi Yodea"
        one-heb one-eng
        (song two-heb two-eng)
        (song three-heb three-eng)
        (song four-heb four-eng)
        (song five-heb five-eng)
        (song six-heb six-eng)
        (song seven-heb seven-eng)
        (song eight-heb eight-eng)
        (song nine-heb nine-eng)
        (song ten-heb ten-eng)
        (song eleven-heb eleven-eng)
        (song twelve-heb twelve-eng)))
