(ns haggadah.set-vars
  (:require [clojure.data.json :as json]
            [clojure.java.io :as io]
            #_[babashka.fs :refer [exists?]]))

(def base "127.0.0.1:")
(def emulator-hub "export FIREBASE_EMULATOR_HUB=127.0.0.1:4400\n")
(def fire-config "export FIREBASE_CONFIG={\"projectId\" \"my-haggadah\" \"storageBucket\" \"my-haggadah.appspot.com\" \"databaseURL\" \"https://my-haggadah.firebaseio.com\"}\n")

(defn all-ports
  "Pre: takes a file which includes the emulator ports
  Post: returns all the emulator ports"
  [file]
  #_(try
    (-> file
        ((partial slurp))
        (json/read-str :key-fn keyword)
        (:emulators))
    (catch Exception e (str "The config file passed does not exist "))
    )
  (cond
    (or (nil? file ) (not (.exists (io/file file)) )) (throw (Exception. "The config file does not exist"))
    #_(exists? file) 
    :else (-> file
              ((partial slurp))
              (json/read-str :key-fn keyword)
              (:emulators))))

(defn get-port
  "Pre: takes a collection of emulators and an emulator name
  Post: returns the port of that emulator"
  [emulator name]
(get-in emulator [name :port] "package.json"))

(def num->emu-config
 {1 "firebase.json"
  2 "test/acceptance_emulators/firebase.json"
  3 "functions/cloud_emulators/firebase.json"
  4 "test/unit_emulators/firebase.json"
  5 "local_emulators"})

(defn set-vars
  "Pre: takes a file which contains a collection of key value pairs of the form [emulator-name emulator-port]
  Post: exports the ports of the auth and firestore emulators"
  [file]
  (println "the file " file)
  (let [ports (all-ports file)
        #_#_[fs-port auth-port] (map (partial get-port ports) [:firestore :auth])
        #_#_fs-var (format "export FIREBASE_FIRESTORE_EMULATOR_ADDRESS=%s%d\n" base fs-port)
        #_#_auth-var (format "export FIREBASE_AUTH_EMULATOR_HOST=%s%d\n" base auth-port)]
    (spit ".env.test" "")
    (doseq [var [emulator-hub fire-config #_#_fs-var auth-var]]
      (spit ".env.test" var :append true))))

(set-vars *command-line-args*)
