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

  (GET "/stop-app"
	     (stop-app))
	(GET "/stop-app/"
	     (stop-app))
	(GET "/start-app"
	     (start-app))
	(GET "/start-app/"
	     (start-app))
	(GET "/reset"
	     (reset))
	(GET "/reset/"
	     (reset))
	(GET "/add-vhost/:vhost"
	     (add-vhost (params :vhost)))
	(GET "/delete-vhost/:vhost"
	     (delete-vhost (params :vhost)))

	(GET "/list-vhosts"
	     (list-vhosts))
	(GET "/list-vhosts/"
	     (list-vhosts))

	(GET "/add-user/:name/:password"
	     (add-user (params :name) (params :password)))
	(GET "/delete-user/:name"
	     (delete-user (params :name)))
	(GET "/list-users"
	     (list-users))
	(GET "/list-users/"
	     (list-users))
	(GET "/list-connections"
	     (list-connections))
	(GET "/list-connections/"
	     (list-connections))
	)
  
(run-server {:port 8080}
  "/*" (servlet exchanges))
