(ns com.leftrightfold.services
  (:require (com.leftrightfold.trixx))
  (:use com.leftrightfold.trixx)
  (:use clojure.contrib.json.write)
  (:use compojure)
  (:use clojure.contrib.str-utils))


(defn get-exchanges [vhost]
  (json-str (list-exchanges vhost))) 

(defn get-queues [vhost]
  (json-str (list-queues vhost)))

(defn get-bindings [vhost]
  (json-str (list-bindings vhost)))

(defroutes exchanges
  (GET "/exchanges"
       (get-exchanges "/"))
  (GET "/exchanges/*"
       (get-exchanges (str "/" (params :*))))
  (GET "/queues"
       (get-queues "/"))
  (GET "/queues/*"
       (get-queues (str "/" (params :*))))
  (GET "/bindings"
       (get-bindings "/"))
  (GET "/bindings/*"
       (get-bindings (str "/" (params :*))))

  (PUT "/rabbit/stop"
       (stop-app))
  (PUT "/rabbit/stop/"
       (stop-app))

  (PUT "/rabbit/start"
       (start-app))
  (PUT "/rabbit/start/"
       (start-app))
  (PUT "/rabbit/reset"
       (reset))
  (PUT "/rabbit/reset/"
       (reset))
)
  
(run-server {:port 8080}
"/*" (servlet exchanges))
