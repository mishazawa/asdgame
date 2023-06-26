(ns core.renderer
  (:require ["three" :refer [PerspectiveCamera Scene WebGLRenderer]]
            [core.globals :refer [get-state set-state]]
            [core.window :refer [add-element cancel-raf raf sub get-screen-info]]
            [utils.constants :refer [FAR FOV NEAR]]))

(def ^:private rafid (atom 0))

(defn stop-animation-loop []
  (cancel-raf @rafid))

(defn ^:private update-viewport
  "Update renderer and camera with actual screen sizes."
  []
  (let [[w h a] (get-screen-info)
        ^js/THREE.Camera camera (get-state :camera)
        renderer (get-state :renderer)]
    (set! camera.aspect a)
    (.setSize renderer w h)
    (.updateProjectionMatrix camera)))

(defn ^:private create-render
  "Wrap RAF loop."
  []
  (let [camera (get-state :camera)
        renderer (get-state :renderer)
        scene (get-state :scene)]
    (fn animation [callback]
      (reset! rafid (raf #(animation callback))) ;; mb somehow unwrap #(a cb)
      (callback)
      (.render renderer scene camera))))

(defn init
  "Create renderer and camera. Subscribe to resize window. Return render function for actual game logic."
  []
  (let [scene (new Scene)
        camera (new PerspectiveCamera FOV 1 NEAR FAR)
        renderer (new WebGLRenderer)]
    ;; set globals
    (set-state [:renderer] renderer)
    (set-state [:scene] scene)
    (set-state [:camera] camera)

    ;; append to body
    (add-element renderer.domElement)

    ;; subscribe to resize event to update canvas
    (update-viewport)
    (sub "resize" update-viewport)
    ;; return render function
    (create-render)))