(ns app.core
  (:require
    [uix.core :as uix :refer [defui $]]
    [uix.dom]
    [refx.alpha :as rfx]
    ["@react-three/fiber" :as r3f]))

(defn init-board []
  (vec (for [i (range 16)]
         {:tilekey i :pos i :tileval 0 :type :number})))

(rfx/reg-event-db
  :initialize
  (fn [_ _]
    {:board (init-board)
     :keynum 16
     :movecount 0
     :score 0
     :time 0}))

(rfx/reg-sub :board (fn [db _] (:board db)))

(defui tile [{:keys [info]}]
  (let [dist 1.3 
        xpos (quot (:pos info) 4) ypos (rem (:pos info) 4)]
    ($ :mesh {:position #js [(- (* dist xpos) 1.95) 
                             (- (* dist ypos) 1.95) 0]}
       ($ :boxGeometry #js {:args #js [1 1 1]})
       ($ :meshStandardMaterial #js {:color "#ffffff"}))))

(defui app []
  (let [board (rfx/use-sub [:board])]
    ($ r3f/Canvas #js {:style #js {:height "750px" :width "750px"}}
        ($ :ambientLight #js {:intensity 1.3}) 
        ($ :pointLight #js {:intensity 1.62 :position #js [0 0 1.5]})
        (for [tileinfo board]
          ($ tile {:key (:tilekey tileinfo) :info tileinfo})))))

(defonce root
  (uix.dom/create-root (js/document.getElementById "root")))

(defn render []
  (rfx/dispatch-sync [:initialize])
  (uix.dom/render-root ($ app) root))

(defn ^:export init []
  (render))
