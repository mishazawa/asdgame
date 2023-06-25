(ns index)

(defn log [& args]
  (apply prn args)
  (apply (.-log js/console) args))

(println "It is works!")

(-> (.getElementById js/document "root")
    log)

