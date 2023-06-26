(ns index
  (:require ["three" :refer [PerspectiveCamera
                             Scene
                             WebGLRenderer
                             BoxGeometry
                             Mesh
                             MeshBasicMaterial]]
            [utils.constants :refer [ASPECT FAR FOV H NEAR W]]))

(defn Cube [args]
  (let [[x y z] (:size args)
        geo (new BoxGeometry x y z)
        mat (new MeshBasicMaterial #js{:color (:color args)})]
    (new Mesh geo mat)))

(def rafid (atom 0))

(defn init []
  (let [scene (new Scene)
        camera (new PerspectiveCamera FOV ASPECT NEAR FAR)
        renderer (new WebGLRenderer)
        cube (Cube {:size [1 1 1] :color 0xff0000})]

    (.appendChild js/document.body renderer.domElement)
    (.setSize renderer W H)
    (set! camera.position.z 10)
    (.add scene cube)

    #_{:clj-kondo/ignore [:inline-def]}
    (defn render []
      (set! cube.rotation.x (+ cube.rotation.x 0.1))
      (set! cube.rotation.z (+ cube.rotation.z 0.1))
      (.render renderer scene camera))

    #_{:clj-kondo/ignore [:inline-def]}
    (defn anim []
      (reset! rafid (.requestAnimationFrame js/window anim))
      (render))

    (anim)))


#_{:clj-kondo/ignore [:clojure-lsp/unused-public-var]}
(defn ^:dev/before-load cleanup []
  (set! js/document.body.innerHTML "")
  (.cancelAnimationFrame js/window @rafid))

(init)