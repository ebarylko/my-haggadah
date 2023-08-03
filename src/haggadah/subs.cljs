(ns haggadah.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 ::active-panel
 (fn [db _]
   (:active-panel db)))

(re-frame/reg-sub
 ::haggadah-text
 (fn [db _]
   (:haggadah-text db)))

(re-frame/reg-sub
 ::uid
 (fn [db _]
   (:uid db)))

(re-frame/reg-sub
 ::haggadot
 (fn [db _]
   (:haggadot db)))

(re-frame/reg-sub
 ::src-preview
 (fn [db _]
   (:preview db)))

(re-frame/reg-sub
 ::error
 (fn [db _]
   (:error db)))

(re-frame/reg-sub
 ::user
 (fn [db _]
   (:user db)))

(re-frame/reg-sub
 ::active-menu?
 (fn [db _]
   (:active-menu? db)))

(re-frame/reg-sub
 ::dropdown
 (fn [db _]
   (:dropdown db)))

(re-frame/reg-sub
 ::sedarim
 (fn [db _]
   (:sedarim db)))

(re-frame/reg-sub
 ::seder-modal
 (fn [db _]
   (:seder-modal db)))

(re-frame/reg-sub
 ::seder-id
 (fn [db _]
   (:seder-id db)))

(re-frame/reg-sub
 ::seder-link
 (fn [db _]
   (:seder-link db)))

(re-frame/reg-sub
 ::seder
 (fn [db _]
   (:seder db)))
