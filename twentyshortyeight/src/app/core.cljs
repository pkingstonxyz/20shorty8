(ns app.core
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]))

(defui app []
  ($ :p "hello"))

(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (uix.dom/render-root ($ app) root))

(defn ^:export init []
  (render))
