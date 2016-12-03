(ns web.components.vote
  (:require
    [rum.core :as rum]))


(rum/defc vote-button-positive < rum/reactive [number on-click state]
          [:button.button.is-success
           {:class    (if state "is-active" "is-outlined")
            :on-click on-click}
           (str "+ " (rum/react number))])

(rum/defc vote-button-negative < rum/reactive [number on-click state]
          [:button.button.is-danger
           {:class    (if state "is-active" "is-outlined")
            :on-click on-click}
           (str "- " (.abs js/Math (rum/react number)))])

