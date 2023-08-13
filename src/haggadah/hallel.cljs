(ns haggadah.hallel
  (:require [haggadah.dsl :as dsl :refer [instruction section general-content bracha-with-more-content general-content-with-instruction]]
            [haggadah.hallel-second :refer [second-half]]))


(def hallel
  (section "הַלֵּל"
           "Hallel"
           second-half
           ))
