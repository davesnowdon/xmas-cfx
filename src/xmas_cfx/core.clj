(ns xmas-cfx.core
  (:require [clojurefx.factory :refer [compile]]
            [clojurefx.clojurefx :refer [run-now]])
  (:import [javafx.scene.control Button Label TextField]
           [javafx.scene.layout VBox HBox]
           [javafx.scene.image Image ImageView]
           [javafx.stage StageBuilder]
           [javafx.scene Scene]))

(def text-message (compile [TextField {:text "Yo ho ho, Merry Christmas"
                                       }]))

(def xmas-msg (compile [Label {:text "I'm Christmas FX"
                               :style "-fx-font-size: 24pt;"}]))

(defn set-xmas-message [message]
  (.setText xmas-msg (.getText text-message)))

(def xmas-image (ImageView.))

(defn set-xmas-image [_]
  (.setImage xmas-image (Image. "merry-christmas-again.png")))

(def graph (compile [VBox {:id "TopLevelVBox"
                           :children [Label {:text "Hi!"}
                                      xmas-image
                                      xmas-msg
                                      text-message
                                      HBox {:id "HorizontalBox"
                                            :children [Button {:text "Alright."
                                                               :action #'set-xmas-message}
                                                       Button {:text "Change image"
                                                               :action #'set-xmas-image}
                                                       Button {:text "Buy now!"}]}]}]))



(def ui-root (atom nil))

(def stage
  (run-now
   (doto
       (.build (StageBuilder/create))
     (.setTitle "Christmas Card Builder")
     (.setScene (Scene. (reset! ui-root graph))))))

(defn start []
  (do
    (.setImage xmas-image (Image. "merry-christmas.jpg"))
    (run-now (.show stage))))

(defn -main
  [& args]
  (start))
