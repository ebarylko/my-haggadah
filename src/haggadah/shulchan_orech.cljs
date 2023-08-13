(ns haggadah.shulchan-orech
  (:require [haggadah.dsl :as dsl :refer [instruction section general-content]]))


(def instr-heb "אוכלים ושותים.")
(def instr-eng "We eat and drink.")

(def shulchan-orech
  (section
   "שֻׁלְחָן עוֹרֵךְ"
   "Shulchan Orech"
   (instruction instr-heb instr-eng)))
