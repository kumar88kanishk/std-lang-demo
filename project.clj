(defproject std-lang-demo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [org.clj-commons/claypoole "1.2.2"]
                 [org.clojure/core.async "1.6.673"]
                 [org.scicloj/clay "2-beta38-SNAPSHOT"]
                 [xyz.zcaudate/std.lang "4.0.6"]
                 [xyz.zcaudate/code.test "4.0.6"]
                 [xyz.zcaudate/rt.basic "4.0.6"]]
  :repl-options {:init-ns std-lang-demo.core})
