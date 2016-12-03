(ns web.core
  (:require [net.cgrand.enlive-html :refer [deftemplate html-content]]
            [rum.core :as rum]
            [web.components.comments :as c])
  (:use ring.util.response
        ring.adapter.jetty)
  (:gen-class))


(deftemplate app "../resources/public/index.html"
             [app-content]
             [:#app] (html-content app-content))

(defn handler [request]
  (let [content (rum/render-html (c/comment-form #() #()))
        html-string (apply str (app content))]
    (-> (response html-string)
        (content-type "text/html"))))

(defn -main []
  (run-jetty handler {:port 8001}))
