(ns haggadah.events
  (:require
   [re-frame.core :as re-frame]
   [haggadah.fb.firestore :as firestore]
   ["firebase/firestore" :as fire]
   [haggadah.db :as db]
   [haggadah.dsl :as dsl]
   [haggadah.magid :refer [magid]]
   [haggadah.magid-part-2 :refer [magid-part-2]]
   [haggadah.karpas :refer [karpas]]
   [haggadah.yachatz  :refer [yachatz]]
   [haggadah.urchatz  :refer [urchatz]]
   [haggadah.kadesh  :refer [kadesh]]
   [haggadah.rachtzah  :refer [rachtzah]]
   [haggadah.motzi-matzah :refer [motzi-matzah]]
   [haggadah.maror :refer [maror]]
   [haggadah.korech :refer [korech]]
   [haggadah.shulchan-orech :refer [shulchan-orech]]
   [haggadah.tzafun :refer [tzafun]]
   [haggadah.barech :refer [barech]]
   [haggadah.hallel :refer [hallel]]
   [haggadah.nirtzah :refer [nirtzah]]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [haggadah.fb.auth :as auth]
   [haggadah.fb.functions :as func]))

(def interceptors [re-frame/trim-v])


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
            db/default-db))

(re-frame/reg-event-fx
 ::call-func
 interceptors
 (fn [_ [fn-url text on-success on-error]]
   (println "This is for the url and text" fn-url "---" text)
   {::call-func! {:fn-url fn-url
                  :text text
                  :on-success on-success
                  :on-error on-error}}))

(re-frame/reg-fx
 ::call-func!
 (fn [{:keys [fn-url text on-success on-error]}]
   (let [f (func/callable-from-url fn-url)]
     (-> (f text)
         (.then on-success)
         (.catch on-error)))))

(re-frame/reg-fx
 ::email-login!
 (fn [{:keys [email password on-success on-error]}]
   (-> (auth/email-login email password)
       (.then (fn [user]  (on-success (.-user user))))
       (.catch (fn [error]  (on-error error) )))))

(defn ordered-coll
  "Pre: takes a path to a collection and a function which will order the collection
  Posts: returns the collection or an error"
  [path order-by]
  (-> (firestore/instance)
      (fire/collection  (clojure.string/join "/" path))
      order-by
      (fire/getDocs)))

(re-frame/reg-fx
 ::query!
 (fn [{:keys [path on-success on-error order-by] :or {order-by identity}}]
   (-> (ordered-coll path order-by)
     (.then on-success)
     (.catch on-error))))


(defn keyword->func
  [key]
  (cond
    (fn? key) key
    (vector? key) #(re-frame/dispatch (conj key %))
    :else #(re-frame/dispatch [key %])))


(re-frame/reg-event-fx
 ::fetch-haggadot
 (fn [{:keys [db]} [_ {:keys [on-success on-error] :or {on-error :error}}]]
   (if (:uid db)
     {::query! {:path ["users" (:uid db) "haggadot"]
                :order-by #(fire/query % (fire/orderBy "createdAt" "desc"))
                :on-success (keyword->func on-success)
                :on-error (keyword->func on-error)}}
     {})))

(re-frame/reg-event-fx
 ::fetch-haggadah
 (fn [{:keys [db]} [_ {:keys [on-success on-error] :or {on-error :error}}]]
   (let [id (get-in db [:current-route :path-params :id])]
     (when (:uid db)
       {::fetch-doc {:path (clojure.string/join "/" ["users" (:uid db) "haggadot" id] )
                     :on-success (keyword->func on-success)
                     :on-error (keyword->func on-error)}}))))

(re-frame/reg-event-fx
 ::fetch-haggadah-sections
 interceptors
 (fn [{:keys [db]} [on-success haggadah]]
   {::query! {:path ["haggadah"]
              :order-by #(fire/query % (fire/orderBy "order"))
              :on-success (keyword->func [on-success (.-title haggadah)])}}
   ))


(re-frame/reg-event-fx
 ::fetch-sedarim
 (fn [{:keys [db]} [_ {:keys [on-success on-error] :or {on-error :error}}]]
   (if (:uid db)
    {::query! {:path ["users" (:uid db) "seders"]
               :order-by #(fire/query % (fire/orderBy "createdAt" "desc"))
               :on-success (keyword->func on-success)
               :on-error (keyword->func on-error)}}
    {})))

(re-frame/reg-event-db
 ::create-seder-modal
 (fn [db [_ id]]
   (assoc db :seder-modal id)))

(re-frame/reg-event-db
 ::hide-seder-modal
 (fn [db [_]]
   (dissoc db :seder-modal)))

(re-frame/reg-event-fx
 ::seder-success
 (fn [{:keys [db]} [_ seder]]
   {:db db
    :fx [[:dispatch [::fetch-sedarim {:on-success [::set-collection :sedarim]}]]
         [:dispatch [::hide-seder-modal]]]}))


(re-frame/reg-event-fx
 ::create-seder
 (fn [{:keys [db]} [_ haggadah-id title]]
   {::add-doc! {:document-path ["users" (:uid db) "seders"]
                :content {:haggadah-path (clojure.string/join "/" ["users" (:uid db) "haggadot" haggadah-id])
                          :title title
                          :createdAt (js/Date.)}
                :on-success #(re-frame/dispatch [::seder-success %])}}))

(re-frame/reg-event-db
 ::link-modal
 (fn [db [_ id]]
   (assoc db :seder-id id)))

(re-frame/reg-event-db
 ::show-link
 (fn [db [_ id]]
   (assoc db :seder-link id)))

(re-frame/reg-event-db
 ::hide-link-modal
 (fn [db [_]]
   (dissoc db :seder-id :seder-link)))

(re-frame/reg-event-fx
 ::signout
 (fn [_ [_]]
   {::signout! {:on-success #(re-frame/dispatch [::push-state :home %])
                :on-error (keyword->func ::error)}}))

(re-frame/reg-fx
 ::signout!
 (fn [{:keys [on-success on-error]}]
     (-> (auth/signout)
         (.then on-success)
         (.catch on-error))))


(def route-events
  {:dashboard [[::fetch-haggadot {:on-success [::set-collection :haggadot ]}]
               [::fetch-sedarim {:on-success [::set-collection :sedarim]}]]
   :haggadah-view [[::fetch-haggadah {:on-success ::set-haggadah }]]
   :haggadah-edit [[::fetch-haggadah {:on-success ::set-haggadah }]]})

;; [::fetch-haggadah {:on-success [::fetch-haggadah-sections {:on-success ::set-haggadah}]}]
; fetch-haggadah->fetch-sections->set-haggadah
(defn setup-events
  "Pre: takes a collection of events
  Post: returns a collection of events waiting to be dispatched"
  [events]
  (mapv (fn [event] [:dispatch event]) events))

(setup-events (:dashboard route-events))

(re-frame/reg-event-fx
 ::store-user-info
 (fn [{:keys [db]}[_ user]]
   (if user 
     (let [route (get-in db [:current-route :data :name])
           fx (->> (get route-events route [])
                setup-events)]
       {:db (-> db
                (assoc :name (.-email user)  :uid (.-uid user) :user :registered))
        :fx fx})
     {:db (assoc db :name nil :uid nil :user :unregistered)})))

;; :order 1, :content content 
(def orders (map zipmap (repeat [:order]) (map vector (range 1 16) )))
(def sections  [kadesh
                urchatz
                karpas
                yachatz
                magid
                rachtzah
                motzi-matzah
                maror
                korech
                shulchan-orech
                tzafun
                barech
                hallel
                nirtzah])

(defn prepare-section
  "Pre: takes a section from the Haggadah and a number which represents the position of the section in the Haggadah 
  Post: returns the section with the number and the path to the section in firestore added"
  [{:keys [english] :as section} pos]
  (-> {}
      (merge pos)
      (assoc :content section :path (str "haggadah/" (cond
                                                       (nil? english) "Magid-part-2"
                                                       :else english)))))

(def haggadah-sections
  (map prepare-section sections orders))

(re-frame/reg-event-fx
 ::add-full-haggadah
 (fn [_ _]
   (println "Adding the haggadah ")
  {::add-full-haggadah! {:content haggadah-sections :on-success #(println "The batch worked" %)}}))



(re-frame/reg-fx
 ::add-full-haggadah!
 (fn [{:keys [content on-success on-error] :or {on-error ::error}}]
   (let [db (firestore/instance)
         batch (-> db
                   (fire/writeBatch))
         filled-batch (for [{:keys [path content]} content]
                        (.set batch (fire/doc db path) (clj->js content)))]
     (println "This is the batch " filled-batch)
     (-> batch
         (.commit)
         (.then on-success)
         (.catch on-error)))))


(re-frame/reg-fx
 ::add-doc!
 (fn [{:keys [document-path content on-success on-error] :or {on-error ::error }}]
   (let [doc (-> (firestore/instance)
                 (fire/collection (clojure.string/join "/" document-path))
                 (fire/addDoc (clj->js content))
                 (.then (fn [doc]
                          (let [id (.-id doc)]
                            (-> (fire/updateDoc doc (clj->js {:id id}))
                                (.then on-success)
                                (.catch on-error))))))])))


(re-frame/reg-event-fx
 ::add-haggadah
 (fn [{:keys [db]} [_ title]]
   {::add-doc! {:document-path ["users" (:uid db) "haggadot"]
                :content {:title title :createdAt (js/Date.)}
                :on-success #(re-frame/dispatch [::push-state :haggadah-success])
                :on-error (keyword->func ::error)}}))

(re-frame/reg-event-fx
 ::login
 interceptors
 (fn [_ [email pwd]]
   {::email-login! {:email email :password pwd :on-success #(re-frame/dispatch [::push-state :dashboard]) :on-error #(re-frame/dispatch [::error %])}}))


(re-frame/reg-fx
 ::fetch-doc
 (fn [{:keys [path on-success on-error] :or {on-error ::error }}]
   (println "Here's the path" path)
   (-> (firestore/instance)
       (fire/doc path)
       (fire/getDoc)
       (.then (keyword->func on-success))
       (.catch (keyword->func on-error)))))

(re-frame/reg-fx
 ::fetch-seder!
 (fn [{:keys [seder-id on-success on-error]}]
   (-> (firestore/instance)
       (fire/collectionGroup "seders")
       (fire/query (fire/where "id" "==" seder-id))
       (fire/getDocs)
       (.then (keyword->func on-success))
       (.catch (keyword->func on-error)))))


(re-frame/reg-event-fx
 ::fetch-seder
 (fn [db [_ {:keys [seder-id on-success on-error] :or {on-error ::error}} ]]
   {::fetch-seder! {:seder-id seder-id :on-success on-success :on-error on-error}}))


(re-frame/reg-event-db
 ::set-seder
 (fn [db [_ seder-title haggadah-snap]]
   (let [haggadah (-> haggadah-snap
                      (. data)
                      (js->clj :keywordize-keys true)
                      dsl/render-haggadah)]
     (assoc db :seder {:title seder-title :haggadah haggadah}))))

(re-frame/reg-event-fx
 ::haggadah-from-seder
 (fn [db [_ snap]]
   (let [{:keys [haggadah-path title]} (->> snap
                                            (.-docs)
                                            js->clj
                                            (map #(.data %))
                                            (map #(js->clj % :keywordize-keys true))
                                            first)]
     {::fetch-doc {:path haggadah-path
                   :on-success [::set-seder title]}})))

(re-frame/reg-event-db
 ::error
 (fn [db [_ error]]
   (assoc db :error {:error error
                     :code (. error -code)
                     :message (. error -message)})))

; fetch cada parte de la haggadah, y despues meterlo adentro de un wa

(re-frame/reg-event-db
 ::set-haggadah
 (fn [db [_ title snap]]
   (assoc db :haggadah-text
          (-> snap
              (. data)
              (js->clj :keywordize-keys true)
              dsl/render-haggadah))))

(re-frame/reg-event-db
 ::set-preview
 (fn [db [_ preview?]]
   (assoc db :preview preview?)))

(re-frame/reg-event-fx
 ::push-state
 (fn [_ [_ & route]]
   {:push-state route}))

(re-frame/reg-event-fx
 ::set-user
 (fn-traced [{:keys [db]} [_ user]]
            {:db 
             (-> db
                 (#(assoc % :name (.-email user)))
                 (#(assoc % :uid (.-uid user))))
             :fx [[:dispatch [::push-state :dashboard]]]}))

(defn auth-user-success
  "Pre: takes a user
  Post: triggers an event which stores the user info "
  [user]
  (re-frame/dispatch [::store-user-info user]))

(re-frame/reg-event-db
 ::set-collection
 (fn [db [_ field snap]]
   (let [docs (->> snap (.-docs) js->clj)
         ids (map #(.-id %) docs)]
     (assoc db field
            (->> docs
                 (map #(.data %))
                 (map #(js->clj % :keywordize-keys true))
                 (map #(assoc %2 :id %1) ids))))))

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


(re-frame/reg-event-db
 ::set-dropdown
 (fn [db [_]]
   (let [active? (not (:dropdown db))]
     (assoc db :dropdown active?))))

(re-frame/reg-event-db
 ::active-menu
 (fn [db [_]]
   (let [active? (not (:active-menu? db))]
     (assoc db :active-menu? active?))))

(def example-haggadah
  "## Hello Why, who are you
  ### This is the example haggadah
  ### Look at all we `can show you`")



(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
