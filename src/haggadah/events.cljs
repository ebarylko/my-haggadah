(ns haggadah.events
  (:require
   [re-frame.core :as re-frame]
   [goog.object :as gobj]
   ["marked" :as mark]
   ["react" :as react]
   [haggadah.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [haggadah.fb.auth :as auth]

   [re-frame.core :as rf]))

(def interceptors [re-frame/trim-v])


(re-frame/reg-event-db
 ::initialize-db
 (fn-traced [_ _]
   db/default-db))

(re-frame/reg-fx
 ::email-login!
 (fn [{:keys [email password on-success on-error]}]
   (-> (auth/email-login email password)
       (.then (fn [user]  (on-success (.-user user))))
       (.catch on-error))))


(re-frame/reg-event-fx
 ::login
 interceptors
 (fn [_ [_]]
   {::email-login! {:email "han@skywalker.com" :password "123456789" :on-success #(re-frame/dispatch [::set-user %] ) :on-error #(js/console.log % :error)}}))

(re-frame/reg-event-db
 ::set-user
 (fn-traced [db [_ user]]
            (assoc db :name (.-email user))))

(def ^js pdfjs (gobj/get js/window "pdfjs-dist/build/pdf"))

(defn pdf-canvas [{:keys [url]}]
  ;; ref
  (let [canvas-ref (react/useRef nil)]

    ;; initialize and attach pdfjs when the canvas is mounted
    (react/useEffect
      (fn []
        (-> (.getDocument pdfjs url)
            (.-promise)
            (.then (fn [^js pdf]
                     (js/console.log "pdf" pdf)
                     (.getPage pdf 1)))
            (.then (fn [^js page]
                     (js/console.log "page" page)
                     (let [viewport (.getViewport page #js {:scale 1.5})
                           canvas (.-current canvas-ref)
                           context (.getContext canvas "2d")

                           render-context
                           #js {:canvasContext context
                                :viewport viewport}]

                       (set! canvas -height (.-height viewport))
                       (set! canvas -width (.-width viewport))

                       (-> (.render page render-context)
                           (.-promise)
                           (.then (fn []
                                    (js/console.log "Page rendered."))))

                       ))))

        (fn []
          ;; not sure if there is supposed to be any cleanup for the pdfjs objects
          ;; might need to store those somewhere and dispose of them properly here
          (js/console.log "cleanup")))

      ;; ensure this only re-runs when url changes
      #js [url])

    [:canvas {:ref canvas-ref}]))

(defn haggadah-text
  []
  [:div
   [:h1 "Welcome to the promised Haggadah"]]
  [:div
   [:a {:on-click #(re-frame/dispatch [::navigate :home])}
    "Render text"]])

(re-frame/reg-event-fx
 ::render-login-text
 (fn [_ [_ file]]
   (haggadah-text)))


(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
