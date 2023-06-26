(ns index
  (:require [core.controls :refer [listen-controls]]
            [core.globals :refer [get-state]]
            [core.renderer :refer [animate init stop-animation-loop]]
            [core.window :refer [clear-element]]
            [game.internals.lifecycle :refer [create destroy]]
            [game.objects.animations :refer [rotating-object]]
            [game.objects.cube :refer [Cube]]))


(defn init-gameloop []
  (let [render-fn (init)
        scene (get-state :scene)
        camera (get-state :camera)
        cube (create Cube {:size [1 1 1] :color 0xaaaaaa})
        stop-cube-anim (animate (rotating-object cube))]

    (set! camera.position.z 10)

    (-> scene
        (.add cube))

    (js/setTimeout
     (fn []
       (stop-cube-anim)
       (destroy cube)) 1000)

    (render-fn 0)))


(defn start []
  (init-gameloop)
  (listen-controls))


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^:dev/before-load cleanup []
  (clear-element js/document.body)
  (stop-animation-loop))

(start)
