(ns xmas-cfx.core
  (:require [clojurefx.factory :refer [compile]]
            [clojurefx.clojurefx :refer [run-now]])
  (:import [javafx.scene.control Button Label TextField]
           [javafx.scene.layout VBox HBox]
           [javafx.stage StageBuilder]
           [javafx.scene Scene]))

(def text-message (compile [TextField {:text "Yo ho ho, Merry Christmas"}]))

(def xmas-msg (compile [Label {:text "I'm Christmas FX"}]))

(def graph (compile [VBox {:id "TopLevelVBox"
                           :children [Label {:text "Hi!"}
                                      xmas-msg
                                      text-message
                                      HBox {:id "HorizontalBox"
                                            :children [Button {:text "Alright."
                                                               :action (fn [e] (.setText xmas-msg (.getText text-message)))}]}]}]))

(def ui-root (atom nil))

(def stage
  (run-now
   (doto
       (.build (StageBuilder/create))
     (.setTitle "Christmas Card Builder")
     (.setScene (Scene. (reset! ui-root graph))))))

(defn start []
  (do
    
    (run-now (.show stage))))

(defn -main
  [& args]
  (start))
