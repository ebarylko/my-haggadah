(ns haggadah.dsl)

(defn bracha
  [title hebrew-text english-text]
  {:type :bracha :title title :hebrew hebrew-text :english english-text})

(defn song
  [title hebrew-text english-text]
  {:type :song :title title :hebrew hebrew-text :english english-text})

(defn instruction
  [hebrew-text english-text]
  {:type :instruction :english english-text :hebrew hebrew-text})

(defn cell
  [content]
  [:td content])

(defn row
  [row]
  (apply merge [:tr] (map cell row)))

(defn table
  [title & rows]
  {:type :table :title title :rows rows})

(defn haggadah
  "Pre: takes a title and content for a Haggadah
  Post: returns a model of Haggadah with the same content and title"
  [title & content]
  {:type :haggadah :content content :title title})

(defonce default-haggadah
  (haggadah "Default-haggadah" (bracha "Wine" "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן"
                                       "Blessed are You, Lord our God, King of the universe, who creates the fruit of the vine.")))

(def kadesh-hebrew-text "וַיְהִי עֶרֶב וַיְהִי בֹקֶר יוֹם הַשִּׁשִּׁי. וַיְכֻלּוּ הַשָּׁמַיִם וְהָאָרֶץ וְכָל־צְבָאָם. וַיְכַל אֱלֹהִים בַּיּוֹם הַשְּׁבִיעִי מְלַאכְתּוֹ אֲשֶׁר עָשָׂה וַיִּשְׁבֹּת בַּיּוֹם הַשְּׁבִיעִי מִכָּל מְלַאכְתּוֹ אֲשֶׁר עָשָׂה. וַיְבָרֵךְ אֱלֹהִים אֶת יוֹם הַשְּׁבִיעִי וַיְקַדֵּשׁ אוֹתוֹ כִּי בוֹ שָׁבַת מִכָּל־מְלַאכְתּוֹ אֲשֶׁר בָּרָא אֱלֹהִים לַעֲשׂוֹת.")

(def kadesh-bracha-heb-1 "סַבְרִי מָרָנָן וְרַבָּנָן וְרַבּוֹתַי. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם בּוֹרֵא פְּרִי הַגָּפֶן.")

(def kadesh-bracha-heb-2 "בָּרוּךְ אַתָּה ה', אֱלהֵינוּ מֶלֶךְ הָעוֹלָם אֲשֶׁר בָּחַר בָּנוּ מִכָּל־עָם וְרוֹמְמָנוּ מִכָּל־לָשׁוֹן וְקִדְּשָׁנוּ בְּמִצְוֹתָיו. וַתִּתֶּן לָנוּ ה' אֱלֹהֵינוּ בְּאַהֲבָה (לשבת: שַׁבָּתוֹת לִמְנוּחָה וּ) מוֹעֲדִים לְשִׂמְחָה, חַגִּים וּזְמַנִּים לְשָׂשוֹן, (לשבת: אֶת יוֹם הַשַּׁבָּת הַזֶּה וְ) אֶת יוֹם חַג הַמַּצּוֹת הַזֶּה זְמַן חֵרוּתֵנוּ, (לשבת: בְּאַהֲבָה) מִקְרָא קֹדֶשׁ זֵכֶר לִיצִיאַת מִצְרָיִם. כִּי בָנוּ בָחַרְתָּ וְאוֹתָנוּ קִדַּשְׁתָּ מִכָּל הָעַמִּים, (לשבת: וְשַׁבָּת) וּמוֹעֲדֵי קָדְשֶׁךָ (לשבת: בְּאַהֲבָה וּבְרָצוֹן) בְּשִׂמְחָה וּבְשָׂשוֹן הִנְחַלְתָּנוּ.")

(def kadesh-bracha-heb-3 "בָּרוּךְ אַתָּה ה', מְקַדֵּשׁ (לשבת: הַשַׁבָּת וְ) יִשְׂרָאֵל וְהַזְּמַנִּים.")

(def kadesh-bracha-heb-4
  "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, בּוֹרֵא מְאוֹרֵי הָאֵשׁ. בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם הַמַּבְדִיל בֵּין קֹדֶשׁ לְחֹל, בֵּין אוֹר לְחשֶׁךְ, בֵּין יִשְׂרָאֵל לָעַמִּים, בֵּין יוֹם הַשְּׁבִיעִי לְשֵׁשֶׁת יְמֵי הַמַּעֲשֶׂה. בֵּין קְדֻשַּׁת שַׁבָּת לִקְדֻשַּׁת יוֹם טוֹב הִבְדַּלְתָּ, וְאֶת־יוֹם הַשְּׁבִיעִי מִשֵּׁשֶׁת יְמֵי הַמַּעֲשֶׂה קִדַּשְׁתָּ. הִבְדַּלְתָּ וְקִדַּשְׁתָּ אֶת־עַמְּךָ יִשְׂרָאֵל בִּקְדֻשָּׁתֶךָ.")

(def kadesh-bracha-heb-5 "בָּרוּךְ אַתָּה ה', הַמַּבְדִיל בֵּין קֹדֶשׁ לְקֹדֶשׁ.")

(def kadesh-bracha-heb-6 "בָּרוּךְ אַתָּה ה', אֱלֹהֵינוּ מֶלֶךְ הָעוֹלָם, שֶׁהֶחֱיָנוּ וְקִיְּמָנוּ וְהִגִּיעָנוּ לַזְּמַן הַזֶּה.")


(defn section
  [hebrew-title english-title & content]
  {:type :section :english english-title :hebrew hebrew-title :content content})

(def full-haggadah
  (haggadah "Full Haggadah" (section "Kadesh"
                                     (song "קַדֵּשׁ" kadesh-hebrew-text)
                                     (bracha "" kadesh-bracha-heb-1)
                                     (bracha "" kadesh-bracha-heb-2)
                                     (bracha "" kadesh-bracha-heb-3)
                                     (bracha "" kadesh-bracha-heb-4)
                                     (bracha "" kadesh-bracha-heb-5)
                                     (bracha "" kadesh-bracha-heb-6))))

(defn ->cell
  "Pre: takes a cell from a table
  Post: returns the hiccup representation of the cell"
  [cell]
  [:td cell])

(defn ->row
  "Pre: takes a row from a table
  Post: returns the hiccup representation of the row"
  [row]
  (into [:tr] (map ->cell row)))

(defn haggadaah
  [title & content]
  {:type :haggadah :title title :content content})


(defmulti render-haggadah (comp keyword :type ))

(defmethod render-haggadah :default [args]
  [:div
   [:div "What did you pass me? " (:type args)
    "Original args " args]])

(defmethod render-haggadah :haggadah [{:keys [title content]}]
  [:div.haggadah
   [:div.title title]
   [:div.title
    (apply conj [:div.content] (map render-haggadah content))]])

(defmethod render-haggadah :bracha [{:keys [title english hebrew]}]
  [:div.bracha
   [:div.title title]
   [:div.text.hebrew.pb-3 hebrew]
   [:div.english.text english]])

(defmethod render-haggadah :instruction [{:keys [hebrew english]}]
  [:div.instruction
   [:div.instr.hebrew.pb-3 hebrew]
   [:div.instr.english english]])

(defmethod render-haggadah :song [{:keys [title hebrew english]}]
  [:div.song
   [:div.title title]
   [:div.text.hebrew.pb-3 hebrew]
   [:div.english.text english]])

(defmethod render-haggadah :table [{:keys [title rows]}]
  [:div.table.is-bordered
   [:div.title title]
   [:table.table.table-content
    (apply conj [:tbody] rows)]])


(defmethod render-haggadah :section [{:keys [hebrew english content]}]
  (apply conj
   [:div.section
    [:div.title english]
    [:div.title.hebrew hebrew]]
    (map render-haggadah content)))


