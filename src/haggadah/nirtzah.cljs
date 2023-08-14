(ns haggadah.nirtzah
  (:require [haggadah.dsl :as dsl :refer [section general-content bracha instruction bracha-with-more-content]]
            [haggadah.at-midnight :refer [midnight]]
            [haggadah.zevach :refer [zevach-pesach]]
            ))

(def heb-cont "חֲסַל סִדּוּר פֶּסַח כְּהִלְכָתוֹ, כְּכָל מִשְׁפָּטוֹ וְחֻקָּתוֹ. כַּאֲשֶׁר זָכִינוּ לְסַדֵּר אוֹתוֹ כֵּן נִזְכֶּה לַעֲשׂוֹתוֹ. זָךְ שׁוֹכֵן מְעוֹנָה, קוֹמֵם קְהַל עֲדַת מִי מָנָה. בְּקָרוֹב נַהֵל נִטְעֵי כַנָּה פְּדוּיִם לְצִיּוֹן בְּרִנָּה.")
(def eng-cont "Completed is the Seder of Pesach according to its law, according to all its judgement and statute. Just as we have merited to arrange it, so too, may we merit to do [its sacrifice]. Pure One who dwells in the habitation, raise up the congregation of the community, which whom can count. Bring close, lead the plantings of the sapling, redeemed, to Zion in joy.")

(def nirtzah
  (section "נִרְצָה" "Nirtzah"
           (general-content "Chasal Siddur Pesach"
                            heb-cont eng-cont)
           (general-content "L'Shana HaBaa"
                            "לְשָׁנָה הַבָּאָה בִּירוּשָלָיִם הַבְּנוּיָה."
                            "Next year, let us be in the built Jerusalem!")
           midnight
           zevach-pesach))

