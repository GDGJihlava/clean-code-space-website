(defproject cleancodespace "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.293"]
                 [devcards "0.2.2"]
                 [rum "0.10.7"]
                 [enlive "1.1.6"]
                 [ring "1.5.0"]]

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-figwheel "0.5.8"]]
  :main ^:skip-aot web.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}}

  :clean-targets ^{:protect false} ["resources/public/js/compiled"
                                    "target"]

  :source-paths ["src"]

  :cljsbuild {
              :builds [{:id           "devcards"
                        :source-paths ["src"]
                        :figwheel     {:devcards            true
                                       :load-warninged-code true}
                        :compiler     {:main                 "web.devcards"
                                       :asset-path           "js/compiled/devcards_out"
                                       :output-to            "resources/public/js/compiled/web_devcards.js"
                                       :output-dir           "resources/public/js/compiled/devcards_out"
                                       :source-map-timestamp true}}
                       {:id           "devel"
                        :source-paths ["src"]
                        :figwheel     {:load-warninged-code true}
                        :compiler     {:main                 "web.core"
                                       :asset-path           "js/compiled/web_out"
                                       :output-to            "resources/public/js/compiled/web_core.js"
                                       :output-dir           "resources/public/js/compiled/web_out"
                                       :source-map-timestamp true}}
                       ;;                        {:id "prod"
                       ;;                         :source-paths ["src"]
                       ;;                         :compiler {:main       "dev.core"
                       ;;                                    :asset-path "js/compiled/out"
                       ;;                                    :output-to  "resources/public/js/compiled/dev.js"
                       ;;                                    :optimizations :advanced}}
                       ]}

  :figwheel {:css-dirs    ["resources/public/css"]
             :server-port 5000}
  :aliases {"devel"    ["figwheel" "devel"]
            "devcards" ["figwheel" "devcards"]
            })

