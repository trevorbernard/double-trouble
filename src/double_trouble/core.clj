(ns double-trouble.core
  (:require [zeromq.zmq :as zmq]))

(defn parse-args [args]
  (reduce (fn [acc [k v]]
            (assoc acc (keyword (.replaceAll k "--" "")) (keyword v))) {} (partition 2 args)))

(defn -main [& args]
  (let [args (parse-args args)]
    (with-open [context (zmq/zcontext)]
      (case (:type args)
        :server
        (with-open [socket (doto (zmq/socket context :pub)
                             (zmq/bind "tcp://*:2345"))]
          (while true
            (zmq/send socket (.getBytes "A") zmq/send-more)
            (zmq/send socket (.getBytes "PING") 0)
            (Thread/sleep 2000)))
        :client
        (with-open [socket (doto (zmq/socket context :sub)
                             (zmq/connect "tcp://127.0.0.1:2345")
                             (zmq/connect "tcp://127.0.0.1:2345")
                             (zmq/subscribe "A"))]
          (Thread/sleep 200)
          (while true
            (zmq/receive socket) ;; eat topic
            (println (or (zmq/receive-str socket) ""))))
        nil))))
