(ns game.internals.lifecycle)

(defn create
  [constr & args]
  (apply constr args))

(defn destroy
  [^js/THREE.Object3D obj]
  (.removeFromParent obj))