(ns game.objects.animations)

(defn rotating-object
  [^js/THREE.Object3D obj]

  (fn [dt]
    (set! obj.rotation.x (+ obj.rotation.x (* 0.001 dt)))
    (set! obj.rotation.z (+ obj.rotation.z (* dt 0.001)))))
