(ns com.leftrightfold.services
  (:use com.leftrightfold.trixx)
  (:use clojure.contrib.json.write)
  (:use compojure)
  (:use clojure.contrib.str-utils)
  (:import java.net.URLDecoder))


(defn trim-slash [s] 
  (if (and (> (count s) 1)
           (= (str (first s)) "/"))
    (apply str (rest s))
    s))

(defn get-exchanges [vhost]
  (json-str (list-exchanges vhost))) 

(defn get-queues [vhost]
  (json-str (list-queues vhost)))

(defn get-bindings [vhost]
  (json-str (list-bindings vhost)))
	
(defn add-user-with-permissions [name password vhost config write read]
  (if (and (add-user name password)
           (set-permissions name vhost {:config config :write write :read read}))
    200 500))

(defn set-user-permissions [name vhost config write read]
  (set-permissions name vhost {:config config :write write :read read}))

(defn user-permissions [name]
  (let [user (list-user-permissions name)]
      (if user [200 (json-str user)] [500 (str "User " name " can't be found.")])))

(defn user-delete [name]
  ;;; figure out how to use compojure.http.request.decodeurl
  (if (delete-user (URLDecoder/decode name "UTF-8"))
    200 500))

(defn verify-login [name password]
  (if (valid-user name password)
    200 500))

(defroutes webservice
  (GET "/exchanges"
    (get-exchanges "/"))
  (GET "/exchanges/*"
    (get-exchanges (trim-slash (params :*))))

  (POST "/exchanges"
    (if (add-exchange (params :vhost)
                      (params :user)
                      (params :password)
                      (params :name)
                      (params :type)
                      (Boolean/parseBoolean (params :durable)))
      200
      500))

  (GET "/queues"
    (get-queues "/"))
  (GET "/queues/*"
    (get-queues (trim-slash (params :*))))

  (GET "/bindings"
    (get-bindings "/"))
  (GET "/bindings/*"
    (get-bindings (trim-slash (params :*))))

  (GET "/vhosts"
    (json-str (list-vhosts)))

  (POST "/vhosts"
(prn params)
        (if (add-vhost (params :name))
          200
          500))

  (GET "/connections"
    (json-str (list-connections)))

  (GET "/users"
    (json-str (list-users)))

  (PUT "/users/:user"
    (set-user-permissions (params :name)
                          (params :vhost)
                          (params :config)
                          (params :write)
                          (params :read)))

  (POST "/queues"
        (if (add-queue (params :vhost)
                       (params :user)
                       (params :password)
                       (params :name)
                       (Boolean/parseBoolean (params :durable)))
          200
          500))
  (POST "/users"
    (add-user-with-permissions (params :name) 
                               (params :password)
                               (params :vhost)
                               (params :config)
                               (params :write)
                               (params :read)))

  (DELETE "/users/:user"
    (user-delete (params :user)))

  ;;; needs to handle when user can't be found in trixx
  (GET "/users/:user/permissions"
    (json-str (list-user-permissions (params :user))))

  (GET "/rabbit/status"
    (json-str (status)))
  (PUT "/rabbit/stop"
    (if (stop-app) 200 500))
  (PUT "/rabbit/start"
    (if (start-app) 200 500))
  (PUT "/rabbit/reset"
    (if (reset) 200 500))

  (POST "/sessions/authenticate"
    (verify-login (params :name)
                  (params :password)))

  (ANY "*" 
    [404 "Page not found"]))
  
(run-server {:port 8080}
            "/*" (servlet webservice))
