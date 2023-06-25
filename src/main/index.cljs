(ns index)


(defn log [& args]
  (do
    (apply prn args)
    (apply (.-log js/console) args)))

(println "It is works!")

(-> (.getElementById js/document "root")
    log)

