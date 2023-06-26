(ns index
  (:require [core.controls :refer [listen-controls]]
            [core.globals :refer [get-state]]
            [core.renderer :refer [init stop-animation-loop]]
            [core.window :refer [clear-element]]
            [game.objects.cube :refer [Cube]]))


(defn init-gameloop []
  (let [render-fn (init)
        scene (get-state :scene)
        camera (get-state :camera)
        cube (Cube {:size [1 1 1] :color 0xaaaaaa})]

    (set! camera.position.z 10)

    (-> scene
        (.add cube))

    (fn []
      (render-fn (fn []
                   (set! cube.rotation.x (+ cube.rotation.x 0.1))
                   (set! cube.rotation.z (+ cube.rotation.z 0.1)))))))


(defn start []
  (let [gameloop (init-gameloop)]
    (gameloop)
    (listen-controls)))


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^:dev/before-load cleanup []
  (clear-element js/document.body)
  (stop-animation-loop))

(start)
