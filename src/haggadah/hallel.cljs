(ns haggadah.hallel
  (:require [haggadah.dsl :as dsl :refer [section]]
            [haggadah.hallel-second :refer [second-half]]
            [haggadah.hallel-praise :refer [praise-and-thanks]]
            [haggadah.fourth-cup :refer [fourth-cup]]
            ))


(def hallel
  (section "הַלֵּל"
           "Hallel"
           second-half
           praise-and-thanks
           fourth-cup))
