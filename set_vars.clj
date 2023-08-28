(ns haggadah.set-vars
  (:require [clojure.data.json :as json]
            [babashka.fs :refer [exists?]]))

(def base "127.0.0.1:")
(def emulator-hub "export FIREBASE_EMULATOR_HUB=127.0.0.1:4400\n")
(def fire-config "export FIREBASE_CONFIG={\"projectId\" \"my-haggadah\" \"storageBucket\" \"my-haggadah.appspot.com\" \"databaseURL\" \"https://my-haggadah.firebaseio.com\"}\n")

(def not-valid (every-pred nil? (complement string?)))

(defn parse-ports
  "Pre: takes a file which includes the emulator ports
  Post: returns all the emulator ports"
  [file]
  (-> file
      slurp
      (json/read-str :key-fn keyword)
      :emulators))

(defn get-port
  "Pre: takes a collection of emulators and an emulator name
  Post: returns the port of that emulator"
  [emulator name]
(get-in emulator [name :port]))

(def env->emu-config
 {:prod "firebase.json"
  :acceptance "test/acceptance_emulators/firebase.json"
  :functions "functions/cloud_emulators/firebase.json"
  :unit "test/unit_emulators/firebase.json"
  :local "local_emulators"})

(def mk-key (comp keyword first))

(def emu-config (comp env->emu-config mk-key))

(defn set-vars
  "Pre: takes a string which represents an environment (prod, local, etc)
  Post: Sets the authentication and firestore emulators ports for that environment"
  [[file]]
  {:pre [(exists? file)]}
  (let [ports (parse-ports file)
        [fs-port auth-port] (map (partial get-port ports) [:firestore :auth])
        fs-var (format "export FIREBASE_FIRESTORE_EMULATOR_ADDRESS=%s%d\n" base fs-port)
        auth-var (format "export FIREBASE_AUTH_EMULATOR_HOST=%s%d\n" base auth-port)]
    (spit ".env.test" "")
    (doseq [var [emulator-hub fire-config fs-var auth-var]]
      (spit ".env.test" var :append true))))

(set-vars *command-line-args*)
