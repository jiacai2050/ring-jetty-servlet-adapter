(defproject ring-jetty-servlet-adapter "1.6.3"
  :description "Fork of official Ring Jetty adapter, enhanced with servlet-mapping support"
  :url "https://github.com/jiacai2050/ring-jetty-servlet-adapter"
  :scm {:url "https://github.com/jiacai2050/ring-jetty-servlet-adapter"
        :name "github"}
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-servlet "1.6.3"]
                 [org.eclipse.jetty/jetty-server "9.2.21.v20170120"]
                 [org.eclipse.jetty/jetty-servlet "9.2.21.v20170120"]]
  :aliases {"test-all" ["with-profile" "default:+1.6:+1.7:+1.8" "test"]}
  :profiles
  {:dev {:dependencies [[clj-http "2.2.0"]]
         :java-source-paths ["test/servlet"]
         :jvm-opts ["-Dorg.eclipse.jetty.server.HttpChannelState.DEFAULT_TIMEOUT=500"]}
   :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
   :1.7 {:dependencies [[org.clojure/clojure "1.7.0"]]}
   :1.8 {:dependencies [[org.clojure/clojure "1.8.0"]]}}
  :deploy-repositories [["releases" :clojars]])
