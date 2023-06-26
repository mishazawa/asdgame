(ns utils.constants)

(def W js/window.innerWidth)
(def H js/window.innerHeight)
(def ASPECT (/ W H))
(def FOV 55)
(def NEAR 1)
(def FAR 1000)

(defn get-screen-info []
  [js/window.innerWidth
   js/window.innerHeight
   (/ js/window.innerWidth js/window.innerHeight)])