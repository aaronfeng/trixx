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

  (GET "/users"
       (json-str list-users))
  (GET "/users/:user/permissions"
       (json-str (list-user-permissions :user)))

  (PUT "/rabbit/stop"
       (if stop-app 200 500))
  (PUT "/rabbit/stop/"
       (if stop-app 200 500))
  (PUT "/rabbit/start"
       (if start-app 200 500))
  (PUT "/rabbit/start/"
       (if stop-app 200 500))
  (PUT "/rabbit/reset"
       (reset))
  (PUT "/rabbit/reset/"
       (reset))

  (ANY "*" 
       [404 "Page not found"])
)
  
(run-server {:port 8080}
"/*" (servlet exchanges))