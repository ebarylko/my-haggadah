(defproject my-haggadah "0.1.0-SNAPSHOT"
  :description "Creating a virtual Haggadah that can be easily shared"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [clj-http "3.12.3"]
                 [org.clojure/data.json "2.4.0"]
                 [etaoin/etaoin "1.0.40"]
                 [babashka/fs "0.4.19"]
                 [com.google.firebase/firebase-admin "9.1.1"]
                 [environ "1.2.0"]]
  :jvm-opts ["-Dclojure.main.report=stderr"]
  :repl-options {:init-ns haggadah.core
                 :init (init)})

