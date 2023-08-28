(ns set-vars
  (:require
   [clojure.data.json :as json]
   [babashka.fs :as fs :refer [exists?]]))

(def localhost "127.0.0.1")
(def fire-config {:FIREBASE_CONFIG {:projectId "my-haggadah" :storageBucket "my-haggadah.appspot.com" :databaseURL "https://my-haggadah.firebaseio.com"}})

(def parse-fire-config (comp (partial format "FIREBASE_CONFIG=%s") json/write-str :FIREBASE_CONFIG))

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


(def emulator->var-name
  {:firestore "FIREBASE_FIRESTORE_EMULATOR_ADDRESS"
   :auth  "FIREBASE_AUTH_EMULATOR_HOST"})

(defn emulator->var
  "Pre: takes a en emulator name and the port it runs on
  Post: returns a string which represents the environment variable corresponding for that emulator"
  [[emulator {:keys [port]}]]
  (format "%s=%s:%d" (emulator->var-name emulator) localhost port))

(defn export-from-firebase
  "Pre: takes a string which represents an environment (prod, local, etc)
  Post: Sets the authentication and firestore emulators ports for that environment"
  [file]
  {:pre [(exists? file)]}
    (->> file
         parse-ports
         (filter (fn [[k _]] (emulator->var-name k)))
         (map emulator->var)
         (concat [(parse-fire-config fire-config)])
         (clojure.string/join "\n" )
         (spit ".env.firebase")))


