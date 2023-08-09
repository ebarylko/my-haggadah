(ns haggadah.yachatz
  (:require [haggadah.dsl :as dsl :refer [haggadah bracha instruction song section general-content]]))


(def instr-heb-8 "חותך את המצה האמצעית לשתים, ומצפין את הנתח הגדול לאפיקומן")
(def instr-eng-8 "Split the middle matsah in two, and conceal the larger piece to use it for the afikoman.")

(def yachatz
 (section "יַחַץ"
          "Yachatz"
          (instruction instr-heb-8 instr-eng-8)))
