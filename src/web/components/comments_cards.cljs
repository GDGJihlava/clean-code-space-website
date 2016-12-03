(ns web.components.comments-cards
  (:require [web.components.comments :as c])
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-doc deftest]]))

(enable-console-print!)

(def comments-data [{:id     1
                     :author {:uid  "123"
                              :name "User 01"}
                     :date   "1.12.2016 20:29"
                     :text   "come text"}
                    {:id       2
                     :author   {:uid  "123"
                                :name "User 01"}
                     :date     "2.12.2016 20:29"
                     :text     "come text"
                     :reply-to 1}
                    {:id     3
                     :author {:uid  "123"
                              :name "User 01"}
                     :date   "3.12.2016 20:29"
                     :text   "come text"}])

(defcard comment-form-new
         (c/comment-form (partial println "submit") (partial println "cancel")))

(defcard comment-form-reply
         (c/comment-form 123 (partial println "submit") (partial println "cancel")))

(defcard comment-example
         (c/comment-component (first comments-data) #(println "onReply" %)))

(defcard comment-reply-example
         (c/comment-component (second comments-data) #(println "onReply" %)))

(defcard comment-reply-example
         (c/comments comments-data))


