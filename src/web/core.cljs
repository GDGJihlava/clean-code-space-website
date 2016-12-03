(ns web.core
  (:require [rum.core :as rum]
            [web.components.comments :as c]))

(enable-console-print!)

(let [element (.getElementById js/document "app")]
  (rum/mount (c/comment-form #() #()) element))
