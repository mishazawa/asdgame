(ns core.window)

(defn sub [event f]
  (js/window.addEventListener event f)
  ;; unsub fn
  (fn []
    (js/window.removeEventListener f)))

(defn add-element
  ([el]
   (.appendChild js/document.body el))
  ([el dest]
   ((.appendChild dest el))))

(defn clear-element [el]
  (set! el.innerHTML ""))

(defn raf [cb]
  (.requestAnimationFrame js/window cb))

(defn cancel-raf [id]
  (.cancelAnimationFrame js/window id))

(defn get-element
  ([id]
   (.getElementById js/window id)))