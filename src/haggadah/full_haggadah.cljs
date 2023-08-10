(ns haggadah.full-haggadah
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]
            [haggadah.magid :refer [magid]]
            [haggadah.karpas :refer [karpas]]
            [haggadah.yachatz  :refer [yachatz]]
            [haggadah.urchatz  :refer [urchatz]]
            [haggadah.kadesh  :refer [kadesh]]))

(def full-haggadah
  (haggadah "Full Haggadah"
            kadesh
           #_#_#_#_ urchatz
            karpas
            yachatz
            magid))

