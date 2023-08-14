(ns haggadah.hallel
  (:require [haggadah.dsl :as dsl :refer [instruction section general-content bracha-with-more-content general-content-with-instruction]]
            [haggadah.hallel-second :refer [second-half]]
            [haggadah.hallel-praise :refer [praise-and-thanks]]
            ))


(def hallel
  (section "הַלֵּל"
           "Hallel"
           second-half
           praise-and-thanks
           ))
