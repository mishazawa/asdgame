(ns core.globals)

(def state (atom {}))

(defn set-state
  [k v]
  (swap! state assoc-in k v))

(defn get-state [& path]
  (get-in @state path))

(defn with-state [f & path]
  (let [res (apply get-state path)]
    (when (not (nil? res))
      (f res))))

