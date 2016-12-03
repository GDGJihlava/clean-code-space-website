(ns web.components.comments
  (:require
    [rum.core :as rum]))

(rum/defc comment-form
          ([on-submit on-cancel] (comment-form nil on-submit on-cancel))
          ([reply-to on-submit on-cancel]
            (let [comment-text (atom "")]
              [:div.card.is-fullwidth
               [:div.card-content
                [:div.content
                 [:label.label (if-not reply-to "New comment:" "New Reply:")]
                 [:p.control
                  [:textarea.textarea {:placeholder "Textarea"
                                       :value       @comment-text
                                       :on-change   #(reset! comment-text (.-value (.-target %)))}]]
                 [:p.control
                  [:button.button.is-primary
                   {:on-click #(on-submit {:reply-to reply-to
                                           :text     @comment-text})}
                   "Submit"]
                  [:button.button.is-link
                   {:on-click #(on-cancel)}
                   "Cancel"]
                  ]
                 ]]]
              )))


(rum/defc comment-component [comment-data on-reply-click & footerContent]
          [:div.card.is-fullwidth
           {:id (:id comment-data)}
           [:header.card-header
            [:p.card-header-title
             [:span.author (get-in comment-data [:author :name])]
             (if (:reply-to comment-data)
               [:span.in-reply-to
                "in reply to "
                [:a {:href (str "#comment-" (:reply-to comment-data))}
                 (:reply-to comment-data)]])
             ":"]
            [:a.card-header-icon
             {:on-click #(on-reply-click (:id comment-data))}
             [:i.fa.fa-reply]
             ]]
           [:div.card-content
            [:div.content
             (:text comment-data)
             [:br]
             [:small "11:09 PM - 1 Jan 2016"]
             ]]
           (if footerContent
             [:footer.card-footer
              footerContent])
           ])

(rum/defcs comments < (rum/local nil ::reply-to)
           [state comments-data]
           (let [reply-to (::reply-to state)]
             (letfn [(on-reply-click [id]
                       (reset! reply-to id))
                     (comment-reply-form-factory [comment reply-to]
                       (if (= (:id comment) @reply-to)
                         (comment-form @reply-to (fn []) (fn [] (reset! reply-to nil)))))
                     (comment-component-factory [comment]
                       (comment-component comment
                                          on-reply-click
                                          (comment-reply-form-factory comment reply-to)))
                     ]
               [:div.comments
                (map #(rum/with-key (comment-component-factory %) (:id %))
                     comments-data)
                (if-not @reply-to (comment-form #() #()))])))