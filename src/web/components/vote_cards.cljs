(ns web.components.vote-cards
  (:require
    [web.components.vote :as v])
  (:require-macros
    [devcards.core :as dc :refer [defcard defcard-doc deftest]]))

(enable-console-print!)

(let [positive (atom 5)]

  (defcard vote-button (v/vote-button-positive positive #(swap! positive inc) false))
  (defcard vote-button-voted (v/vote-button-positive positive #(swap! positive inc) true)))


(let [negative (atom -5)]

  (defcard vote-button (v/vote-button-negative negative #(swap! negative dec) false))
  (defcard vote-button-voted (v/vote-button-negative negative #(swap! negative dec) true)))
