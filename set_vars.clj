(ns haggadah.set-vars
  (:require [clojure.data.json :as json]
            [clojure.java.shell :refer [sh]]))

(def base "127.0.0.1:")
(def emulator-hub "export FIREBASE_EMULATOR_HUB=127.0.0.1:4400\n")
(def fire-config "export FIREBASE_CONFIG={\"projectId\" \"my-haggadah\" \"storageBucket\" \"my-haggadah.appspot.com\" \"databaseURL\" \"https://my-haggadah.firebaseio.com\"}\n")

(defn all-ports
  "Pre: takes a file which includes the emulator ports
  Post: returns all the emulator ports"
  [file]
  (-> file
      (slurp)
      (json/read-str :key-fn keyword)
      (:emulators)))

(defn get-port
  "Pre: takes a collection of emulators and an emulator name
  Post: returns the port of that emulator"
  [emulator name]
(get-in emulator [name :port]))

                                        ; 1 - firebase.json
;2 acceptance-firebase.json

(defn set-vars
  "Pre: takes a file which contains a collection of key value pairs of the form [emulator-name emulator-port]
  Post: exports the ports of the auth and firestore emulators"
  [#_file]
  (let [ports (all-ports "firebase.json"#_file)
        [fs-port auth-port] (map (partial get-port ports) [:firestore :auth])
        fs-var (format "export FIREBASE_FIRESTORE_EMULATOR_ADDRESS=%s%d\n" base fs-port)
        auth-var (format "export FIREBASE_AUTH_EMULATOR_HOST=%s%d\n" base auth-port)]
    [fs-port auth-port]
    [fs-var auth-var]
    (spit ".env.test" "")
      (run! #(spit ".env.test" % :append true) [emulator-hub fire-config fs-var auth-var])
    ))


(set-vars #_*command-line-args*)
