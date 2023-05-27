(ns haggadah.events
  (:require
   [re-frame.core :as re-frame]
   [goog.object :as gobj]
   ["react" :as react]
   [reagent.core :as reagent]
   [reagent.dom :as rdom]
   [haggadah.views :as view]
   [haggadah.db :as db]
   [day8.re-frame.tracing :refer-macros [fn-traced]]
   [haggadah.fb.auth :as auth]
   ))

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

;; (def ^js pdfjs (gobj/get js/window "pdfjs-dist/build/pdf"))

;; (defn pdf-canvas [{:keys [url]}]
;;   ;; ref
;;   (let [canvas-ref (react/useRef nil)]

;;     ;; initialize and attach pdfjs when the canvas is mounted
;;     (react/useEffect
;;       (fn []
;;         (-> (.getDocument pdfjs url)
;;             (.-promise)
;;             (.then (fn [^js pdf]
;;                      (js/console.log "pdf" pdf)
;;                      (.getPage pdf 1)))
;;             (.then (fn [^js page]
;;                      (js/console.log "page" page)
;;                      (let [viewport (.getViewport page #js {:scale 1.5})
;;                            canvas (.-current canvas-ref)
;;                            context (.getContext canvas "2d")

;;                            render-context
;;                            #js {:canvasContext context
;;                                 :viewport viewport}]

;;                        (set! canvas -height (.-height viewport))
;;                        (set! canvas -width (.-width viewport))

;;                        (-> (.render page render-context)
;;                            (.-promise)
;;                            (.then (fn []
;;                                     (js/console.log "Page rendered."))))

;;                        ))))

;;         (fn []
;;           ;; not sure if there is supposed to be any cleanup for the pdfjs objects
;;           ;; might need to store those somewhere and dispose of them properly here
;;           (js/console.log "cleanup")))

;;       ;; ensure this only re-runs when url changes
;;       #js [url])

;;     [:canvas {:ref canvas-ref}]))

(re-frame/reg-event-fx
 ::render-login-text
 (fn [_ [_ url]]
   (println "Hello, how are you")))

(re-frame/reg-event-fx
  ::navigate
  (fn-traced [_ [_ handler]]
   {:navigate handler}))

(re-frame/reg-event-fx
 ::set-active-panel
 (fn-traced [{:keys [db]} [_ active-panel]]
   {:db (assoc db :active-panel active-panel)}))
