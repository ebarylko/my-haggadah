(ns haggadah.fb.auth
  (:require
   ["firebase/auth" :as fb-auth]
   [re-frame.core :as re-frame]
   [haggadah.fb.firestore :as firestore]
   ["firebase/firestore" :as fire]
   [haggadah.db :as db]))


(defonce auth (atom nil))

(goog-define FIREBASE_AUTH_HOST false)
(goog-define FIREBASE_HOSTING_HOST false)

(re-frame/reg-event-db
 ::error
 (fn [db [_ error]]
   (assoc db :error {:error error
                     :code (. error -code)
                     :message (. error -message)})))

(re-frame/reg-event-db
 ::set-haggadot
 (fn [db [_ snap]]
   (let [docs (->> snap (.-docs) js->clj)
         ids (map #(.-id %) docs)]
     (assoc db :haggadot
            (->> docs
                 (map #(.data %))
                 (map #(js->clj % :keywordize-keys true))
                 (map #(assoc %2 :id %1) ids))))))

(re-frame/reg-fx
 ::fetch-collection!
 (fn [{:keys [path on-success on-error]}]
   (-> (firestore/instance)
       (fire/collection  (clojure.string/join "/" path))
       (fire/getDocs)
       (.then on-success)
       (.catch on-error))))

(re-frame/reg-event-fx
 ::fetch-haggadot
 (fn [{:keys [db]} [_ on-success on-error]]
   {::fetch-collection! {:path ["users" (:uid db) "haggadot"] :on-success on-success :on-error on-error}}))


#_(re-frame/reg-event-db
 ::store-user-info
 (fn [db [_ user]]
    (-> db
        (#(assoc % :name (.-email user)))
        (#(assoc % :uid (.-uid user))))))

(re-frame/reg-event-fx
 ::store-user-info
 (fn [{:keys [db] } [_ user]]
   {:db
    (-> db
        (#(assoc % :name (.-email user)))
        (#(assoc % :uid (.-uid user)))
        (assoc :user :registered))
   :fx [[:dispatch [::fetch-haggadot #(re-frame/dispatch [::set-haggadot %])
                    #(js/console.log "the haggadah was not fetched")]]]}))

(re-frame/reg-event-fx
 ::push-state
 (fn [_ [_ & route]]
   {:push-state route}))

(re-frame/reg-event-fx
 ::logout-user
 (fn [_ [_]]
   (println "The user is logging out")
   {:db (assoc db/default-db :user :unregisterd)
    :fx [[:dispatch [::push-state :home]]]}))


(defn auth-user-success
  "Pre: takes a user
  Post: navigates to dashboard and stores user info if the user is logged in, otherwise navigates them to the home page"
  [user]
  (if user
    (re-frame/dispatch [::store-user-info user])
    (re-frame/dispatch [::logout-user user])))


(defn init [firebase-instance]
  (reset! auth (fb-auth/getAuth firebase-instance))
  (when FIREBASE_AUTH_HOST
    (println "Connecting to auth host "FIREBASE_AUTH_HOST)
    (fb-auth/connectAuthEmulator @auth FIREBASE_AUTH_HOST #js{:disableWarnings true}))
  (.onAuthStateChanged ^js @auth auth-user-success))

(defn email-login
  [email password ]
  (fb-auth/signInWithEmailAndPassword @auth email password))

(defn signout
  []
  (fb-auth/signOut @auth))
