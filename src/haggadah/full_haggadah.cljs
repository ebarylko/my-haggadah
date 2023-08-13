(ns haggadah.full-haggadah
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]
            [haggadah.magid :refer [magid]]
            [haggadah.karpas :refer [karpas]]
            [haggadah.yachatz  :refer [yachatz]]
            [haggadah.urchatz  :refer [urchatz]]
            [haggadah.kadesh  :refer [kadesh]]
            [haggadah.rachtzah  :refer [rachtzah]]
            [haggadah.motzi-matzah :refer [motzi-matzah]]
            [haggadah.maror :refer [maror]]
            [haggadah.korech :refer [korech]]
            [haggadah.shulchan-orech :refer [shulchan-orech]]
            ))

(def full-haggadah
  (haggadah "Full Haggadah"
            kadesh
            urchatz
            karpas
            yachatz
            magid
            rachtzah
            motzi-matzah
            maror
            korech
            shulchan-orech
            ))

