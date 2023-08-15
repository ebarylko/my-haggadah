(ns haggadah.urchatz
  (:require [haggadah.dsl :as dsl :refer [instruction section]]))


(def instr-heb-6 "נוֹטְלִין אֶת הַיָדַיִם וְאֵין מְבָרְכִין ״עַל נְטִילַת יָדַיִם״")
(def instr-eng-6 "Wash your hands but do not say the blessing 'on the washing of the hands.'")

(def urchatz
 (section "וּרְחַץ"
          "Urchatz"
          (instruction instr-heb-6 instr-eng-6)))
