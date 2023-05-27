(defproject my-haggadah "0.1.0-SNAPSHOT"
  :description "Creating a virtual Haggadah that can be easily shared"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [org.apache.pdfbox/pdfbox "1.8.2"]
                 [etaoin/etaoin "1.0.40"]
                 [com.google.firebase/firebase-admin "9.1.1"]
                 [environ "1.2.0"]]
  :repl-options {:init-ns haggadah.core
                 :init (init)})

