(defproject double-trouble "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.zeromq/jzmq "3.0.1"]
                 [org.zeromq/cljzmq "0.1.3" :exclusions [jzmq]]]
  :main double-trouble.core)
