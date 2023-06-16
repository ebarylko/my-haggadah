(ns haggadah.routes
  (:require
   [bidi.bidi :as bidi]
   [pushy.core :as pushy]
   [reitit.frontend.easy :as rfe]
   [reitit.frontend.controllers :as rfc]
   [reitit.coercion.spec :as rss]
   [reitit.core :as r]
   [reitit.frontend :as rf]
   [re-frame.core :as re-frame]
   [haggadah.events :as events]))

(defmulti panels identity)
(defmethod panels :default [] [:div "No panel found for this route."])


(def routes
  (atom
    ["/" {""      :home
          "about" :about
          "haggadah" :haggadah
          "login" :login
          "dashboard" { ""  :dashboard
                       ["/" :id] :haggadah-view}}
     ]))


(defn parse
  [url]
  (bidi/match-route @routes url))

(defn url-for
  [& args]
  (apply bidi/path-for (into [@routes] args)))

(defn dispatch
  [route]
  (let [panel (keyword (str (name (:handler route)) "-panel"))]
    (re-frame/dispatch [::events/set-active-panel panel])))

(defonce history
  (pushy/pushy dispatch parse))

(defn navigate!
  [handler]
  (pushy/set-token! history (url-for handler)))

(defn start!
  []
  (pushy/start! history))

(re-frame/reg-fx
  :navigate
  (fn [handler]
    (navigate! handler)))
