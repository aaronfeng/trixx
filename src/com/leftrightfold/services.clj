(ns com.leftrightfold.services
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
	
(defn add-user-with-permissions [name password vhost config write read]
  (and (add-user name password)
       (set-permissions name vhost {:config config :write write :read read})))

(defn set-user-permissions [name vhost config write read]
  (set-permissions name vhost {:config config :write write :read read}))

(defn user-permissions [name]
  (let [user (list-user-permissions name)]
      (if user [200 (json-str user)] [500 (str "User " name " can't be found.")])))

(defroutes webservice
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
    (json-str (list-users)))
  (GET "/users/"
    (json-str (list-users)))
  (GET "/users/:user"
    (user-permissions (params :user)))
  (GET "/users/:user/"
    (user-permissions (params :user)))
  (PUT "/users/:user"
    (set-user-permissions (params :name)
                          (params :vhost)
                          (params :config_permission)
                          (params :write_permission)
                          (params :read_permission)))
  (PUT "/users/:user/"
    (set-user-permissions (params :name)
                          (params :vhost)
                          (params :config_permission)
                          (params :write_permission)
                          (params :read_permission)))
  (POST "/users"
    (if (add-user-with-permissions (params :name) 
                                   (params :password)
                                   (params :vhost)
                                   (params :config_permission)
                                   (params :write_permission)
                                   (params :read_permission))
      200 500))
  (POST "/users/"
    (if (add-user-with-permissions (params :name) 
                                   (params :password)
                                   (params :vhost)
                                   (params :config_permission)
                                   (params :write_permission)
                                   (params :read_permission))
      200 500))

  ;;; needs to handle when user can't be found in trixx
  (GET "/users/:user/permissions"
    (json-str (list-user-permissions (params :user))))
  (GET "/users/:user/permissions/"
    (json-str (list-user-permissions (params :user))))

  (PUT "/rabbit/stop"
       (if (stop-app) 200 500))
  (PUT "/rabbit/stop/"
       (if (stop-app) 200 500))
  (PUT "/rabbit/start"
       (if (start-app) 200 500))
  (PUT "/rabbit/start/"
       (if (stop-app) 200 500))
  (PUT "/rabbit/reset"
       (if (reset) 200 500))
  (PUT "/rabbit/reset/"
       (if (reset) 200 500))

  (ANY "*" 
       [404 "Page not found"])
)
  
(run-server {:port 8080}
"/*" (servlet webservice))