(ns haggadah.tzafun
  (:require [haggadah.dsl :as dsl :refer [instruction section general-content]]))

(def instr-heb-1 "אחר גמר הסעודה לוקח כל אחד מהמסבים כזית מהמצה שהייתה צפונה לאפיקומן ואוכל ממנה כזית בהסבה. וצריך לאוכלה קודם חצות הלילה ")
(def instr-eng-1 "After the end of the meal, all those present take a kazayit from the matsa, that was concealed for the afikoman, and eat a kazayit from it while reclining.")

(def instr-heb-2 "לפני אכילת האפיקומן יאמר: זֵכֶר לְקָרְבָּן פֶּסַח הָנֶאֱכַל עַל הָשוֹׁבַע.")
(def instr-eng-2 "Before eating the afikoman, he should say: \"In memory of the Pesach sacrifice that was eaten upon being satiated.\"")

(def tzafun
  (section
   "צָפוּן"
   "Tzafun"
   (instruction instr-heb-1 instr-eng-1)
   (instruction instr-heb-2 instr-eng-2)))
