(ns app.core
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    ["@react-three/fiber" :as r3f]))

(defui app []
  ($ r3f/Canvas #js {:style #js {:height "750px" :width "750px"}}
     ($ :ambientLight #js {:intensity 1.62})
     ($ :mesh
        ($ :boxGeometry #js {:args #js [1 1 1]})
        ($ :meshBasicMaterial #js {:color "#ffff00"})))) 

(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (uix.dom/render-root ($ app) root))

(defn ^:export init []
  (render))
