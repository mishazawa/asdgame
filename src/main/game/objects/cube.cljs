(ns game.objects.cube
  (:require ["three" :refer [BoxGeometry Mesh MeshBasicMaterial]]))

(defn Cube [args]
  (let [[x y z] (:size args)
        geo (new BoxGeometry x y z)
        mat (new MeshBasicMaterial #js{:color (:color args)})]
    (new Mesh geo mat)))