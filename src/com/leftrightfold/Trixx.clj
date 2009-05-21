(ns com.leftrightfold.Trixx
  (:require (com.leftrightfold.trixx))
  (:use com.leftrightfold.trixx)
  (:use clojure.contrib.json.write)
  (:gen-class 
   :methods [[listExchanges [Object] Object]
             [listQueues [Object] Object]
             [listBindings [Object] Object]
             [stopApp [Object] Object]
             [startApp [Object] Object]
             [reset [Object] Object]
             [addVhost [Object] Object]
             [deleteVhost [Object] Object]
             [listVhosts [Object] Object]
             [addUser [Object] Object]
             [deleteUser [Object] Object]
             [listConnections [Object] Object]
             [setPermissions [Object] Object]
             [clearPermissions [Object] Object]
             [listVhostPermissions [Object] Object]
             [listUserPermissions [Object] Object]
             [addQueue [Object Object Object Object Object] Object]
             [deleteQueue [Object] Object]
             [addExchange [Object] Object]
             [deleteExchange [Object] Object]
             [addBinding [Object] Object]]
   :main false))

(defn -listExchanges [self vhost]
  (list-exchanges vhost))

(defn -listQueues [self vhost]
  (list-queues vhost))

(defn -listBindings [self vhost]
  (list-bindings vhost))

(defn -stopApp [self]
  (stop-app))

(defn -startApp [self]
  (start-app))

(defn -reset [self]
  (reset))

(defn -addVhost [self vhost]
  (add-vhost vhost))

(defn -deleteVhost [self vhost]
  (delete-vhost vhost))

(defn -listVhosts [self]
  (list-vhosts))

(defn -addUser [self name password]
  (add-user name password))

(defn -deleteUser [self name]
  (delete-user name))

(defn -listConnections [self]
  (list-connections))

(defn -setPermissions [self vhost config write read]
  (set-permissions vhost {:config_regex config :write_regex write :read_regex read}))

(defn -clearPermissions [self user vhost]
  (clear-permissions user vhost))

(defn -listVhostPermissions [self vhost]
  (list-vhost-permissions vhost))

(defn -listUserPermissions [self user]
  (list-user-permissions user))

(defn -addQueue [self vhost user password queueName durable]
  (add-queue vhost user password queueName durable))

(defn -deleteQueue [self user password queueName]
  (delete-queue user password queueName))

(defn -addExchange [self user password name type durable]
  (add-exchange user password name type durable))

(defn -deleteExchange [self user password name]
  (delete-exchange user password name))

(defn -addBinding [self user password queue exchange routingKey]
  (add-binding user password queue exchange routingKey))