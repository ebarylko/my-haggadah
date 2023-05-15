(ns events-view-test
  (:require  [clojure.test :as t]
             [etaoin.api :as e]))

(def driver (e/firefox))

(def default-message
  "Hello from (Unknown). This is the Home Page.We're glad to see you.")

(def admin-login-message
"Hello from han@skywalker.com. This is the Home Page.We're glad to see you.")

;; (e/go driver "http://localhost:8280/")

;; (e/get-element-text driver {:class :haggadah-styles-level1})

;; (e/click driver {:tag :button})
;; (e/get-element-text driver {:class :haggadah-styles-level1})
;; (e/refresh driver)

(doto driver
  (e/go  "http://localhost:8280/")
  (e/get-element-text  {:class :haggadah-styles-level1})
  (e/click  {:tag :button})
  (e/get-element-text  {:class :haggadah-styles-level1})
  #_(e/refresh ))

;; (let [_
;;       (doto driver
;;            (e/go  "http://localhost:8280/")
;;            (e/get-element-text  {:class :haggadah-styles-level1})
;;            (e/click  {:tag :button})
;;            (e/get-element-text  {:class :haggadah-styles-level1})
;;            #_(e/refresh ))
;;       ])

;; (defn message []
;;   (e/go  driver "http://localhost:8280/")
;;   (let [default-text (e/get-element-text driver {:class :haggadah-styles-level1})]
;;        (t/is (= default-text default-message)))
;;         (let [admin-text (e/get-element-text driver {:class :haggadah-styles-level1})]
;;   (t/is (= admin-text admin-login-message))))

  

#_(t/deftest logging-in-as-admin
  (let [_ (e/go  driver "http://localhost:8280/")
        default-text (do (e/get-element-text driver {:class :haggadah-styles-level1}))
        _ (do (e/click  driver {:tag :button}))
        admin-text  (do (e/get-element-text driver {:class :haggadah-styles-level1}))]
    (t/is (= default-message default-text))
    #_(t/is (= admin-text admin-login-message))))

    

