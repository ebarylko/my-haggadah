(ns haggadah.db
  (:require [haggadah.dsl :as dsl]))

(def default-db
  {:name "(Unknown)"
   :haggadah-text "The default haggadah"
   :uid nil
   :haggadot nil
   :error "none"
   :user :unloaded
   :template dsl/haggadah})
