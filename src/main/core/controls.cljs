(ns core.controls
  (:require [core.window :refer [raf sub]]))

(def ^:private enabled (atom true))
(def ^:private keystatus (atom {}))

(defn ^:private toggle-key [key val]
  (swap! keystatus assoc key val))

(defn ^:private on-key [event]
  (comment prn (.-code event))
  (when (true? @enabled)
    (let [code (keyword (.-code event))
          type (.-type event)
          val (= type "keydown")]
      (toggle-key code val))))

(defn enable-controls []
  (reset! enabled true))

(defn disable-controls []
  (reset! enabled false)
  (reset! keystatus {}))

(defn get-key [k]
  (get @keystatus k false))

(defn listen-controls []
  (sub "keydown" on-key)
  (sub "keyup" on-key))
