;;  The contents of this file are subject to the Mozilla Public License
;;  Version 1.1 (the "License"); you may not use this file except in
;;  compliance with the License. You may obtain a copy of the License at
;;  http://www.mozilla.org/MPL/
;;
;;  Software distributed under the License is distributed on an "AS IS" 
;;  basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the 
;;  License for the specific language governing rights and limitations
;;  under the License.
;;
;;  The Original Code is Trixx.
;;
;;  The Initial Developer of the Original Code is Aaron Feng
;;  Portions created by Aaron Feng are Copyright (C) Aaron Feng 2009.
;;
;;  All Rights Reserved.
;;
;;  Contributor(s): ______________________________________.

(ns com.leftrightfold.trixx
  (:import (com.rabbitmq.client ConnectionParameters ConnectionFactory
				MessageProperties QueueingConsumer)
	   (com.ericsson.otp.erlang OtpSelf OtpPeer OtpConnection
				    OtpErlangObject OtpErlangBinary OtpErlangLong
				    OtpErlangList OtpErlangAtom OtpErlangTuple))
  (:use clojure.contrib.seq-utils)
  (:use clojure.contrib.str-utils))

(def *node-name* "trixx")
(def *cookie* (or (System/getProperty "com.leftrightfold.trixx.cookie") "DEFAULT-ERLANG-COOKIE"))
(def *server* (or (System/getProperty "com.leftrightfold.trixx.rabbit-server") "localhost"))
(def *rabbit-instance* (or (System/getProperty "com.leftrightfold.trixx.rabbit-instance") "rabbit@localhost"))

(prn (format "%s: *cookie*=%s" *ns* *cookie*))
(prn (format "%s: *server*=%s" *ns* *server*))
(prn (format "%s: *rabbit-instance*=%s" *ns* *rabbit-instance*))

(defstruct exchange-info :name :vhost :type :durable :auto-delete)

(defstruct queue-info :name :vhost :durable :auto_delete 
           :messages-ready :messages-unacknowledged
	   :messages-uncommitted :messages :acks-uncommitted 
           :consumers :transactions :memory)

(defstruct queue-binding :vhost :exchange :queue :routing-key)
(defstruct connection-info :pid :address :port :peer-address :peer-port 
                           :recv-oct :recv-cnt :send-oct :send-cnt :send-pend
                           :state :channels :user :vhost :timeout :frame-max)

(defstruct user :name :vhost :config :write :read)

;;; erlang helper
(defmulti value class)

(defmethod value OtpErlangBinary [o] (String. (.binaryValue o)))
(defmethod value OtpErlangLong [o] (. Integer parseInt (str o)))
(defmethod value :default [o] (str o))
(defmethod value nil [o] "")

(defmulti as-seq class)
(defmethod as-seq OtpErlangList [o] (seq (.elements o)))
(defmethod as-seq OtpErlangTuple [o] (seq (.elements o)))

;;; helper methods
(defn _1st [item] (.elementAt item 0))
(defn _2nd [item] (.elementAt item 1))
(defn _3rd [item] (.elementAt item 2))
(defn _4th [item] (.elementAt item 3))
(defn _5th [item] (.elementAt item 4))
(defn _6th [item] (.elementAt item 5))
(defn _7th [item] (.elementAt item 6))
(defn _8th [item] (.elementAt item 7))
(defn _9th [item] (.elementAt item 8))
(defn _10th [item] (.elementAt item 9))
(defn _nth [item, i] (.elementAt item i))
(defn _boolean [b] (Boolean/parseBoolean b))

;;; utility functions
(defmacro with-channel [server vhost user password f]
  `(with-open [connection# (create-conn ~server (create-conn-params ~vhost ~user ~password))
	       channel# (.createChannel connection#)]
     (doto channel# ~f)))

(defn create-conn [server params]
  (let [factory (ConnectionFactory. params)] factory
       (.newConnection factory server)))

(defn create-conn-params [vhost user password]
  (doto (ConnectionParameters.)
    (.setVirtualHost vhost)
    (.setUsername user)
    (.setPassword password)))

(defn execute [from-node to-node command args rabbit-instance cookie]
  "*** needs to handle the fact the result returned may not be a OtpErlangList"
  (let [self (OtpSelf. from-node cookie)
	peer (OtpPeer. rabbit-instance)]
    (with-open [conn (.connect self peer)]
      (.sendRPC conn to-node command args)
      (.receiveRPC conn))))

(defn is-successful? [f]
  (try (f) true
  (catch Exception _ false)))

(defn create-args [& args]
  (OtpErlangList. 
   (into-array OtpErlangBinary 
	       (map (fn [x] (OtpErlangBinary. (.getBytes (str x))))
		    args))))

(defn empty-args [] (create-args))

;;; via erlang
(defn list-exchanges [vhost] 
  "list all the exchanges for a given vhost"
  (let [exchanges (as-seq (execute *node-name* 
	                           "rabbit_exchange" 
	                           "info_all"
	                           (create-args vhost)
	                           *rabbit-instance* 
	                           *cookie*))]
    (map (fn [e]
           (let [vhost (value (_2nd (_2nd (_1st e))))
                 name (value (_4th (_2nd (_1st e))))
                 type (value (_2nd (_2nd e)))
		 durable (_boolean (value (_2nd (_3rd e))))
		 auto-delete (_boolean (value (_2nd (_4th e))))
		 ;didn't translate {arguments, []}
		 ]
	     (struct exchange-info name vhost type durable auto-delete)))
         exchanges)))

(defn list-queues [vhost]
  "list all queues for a given vhost"
  (let [queues (as-seq (execute *node-name*
		                "rabbit_amqqueue"
		                "info_all"
		                (create-args vhost)
		                *rabbit-instance*
		                *cookie*))]
  (map (fn [q] 
	 (let [vhost (value (_2nd (_2nd (_1st q))))
               name (value (_4th (_2nd (_1st q))))
	       durable (value (_2nd (_2nd q)))
	       auto-delete (value (_2nd (_3rd q)))
               ;didn't translate {arguments, []}
               ;                 {pid, #Pid<....>}
	       messages-ready (value (_2nd (_6th q)))
	       messages-unacknowledged (value (_2nd (_7th q)))
	       messages-uncommitted (value (_2nd (_8th q)))
	       messages (value (_2nd (_9th q)))
	       ack-uncommitted (value (_2nd (_10th q)))
	       consumers (value (_2nd (_nth q 10)))
	       transactions (value (_2nd (_nth q 11)))
	       memory (value (_2nd (_nth q 12)))]
	   (struct queue-info name vhost durable auto-delete messages-ready messages-unacknowledged
		   messages-uncommitted messages ack-uncommitted consumers transactions memory)))
      queues)))

(defn list-bindings [vhost] 
  (let [result (as-seq (execute *node-name* 
                    "rabbit_exchange"
                    "list_bindings" 
 	            (create-args vhost) 
	            *rabbit-instance* 
                    *cookie*))]
    (map (fn [b] 
           (let [vhost (value (_2nd (_1st b)))
                 exchange (value (_4th (_1st b)))
                 queue (value (_4th (_2nd b)))
                 routing-key (value (_3rd b))
                 ; [] not translated
                 ] 
             (struct queue-binding vhost exchange queue routing-key)))
         result)))

(defn stop-app []
  (is-successful? #(execute *node-name* "rabbit" "stop" (empty-args) *rabbit-instance* *cookie*)))

(defn start-app []
  (is-successful? #(execute *node-name* "rabbit" "start" (empty-args) *rabbit-instance* *cookie*)))

(defn reset []
  (is-successful? #(execute *node-name* "rabbit_mnesia" "reset" (empty-args) *rabbit-instance* 
                     *cookie*)))

(defn add-vhost [vhost]
  (is-successful? #(execute *node-name* "rabbit_access_control" "add_vhost" 
	   (create-args vhost) *rabbit-instance* *cookie*)))

(defn delete-vhost [vhost]
  (is-successful? #(execute *node-name* "rabbit_access_control" "delete_vhost" 
	   (create-args vhost) *rabbit-instance* *cookie*)))

(defn list-vhosts []
  (let [result (as-seq (execute *node-name* "rabbit_access_control" "list_vhosts" 
	                        (empty-args) *rabbit-instance* *cookie*))]
    (map (fn [h] (value h)) result)))

(defn execute-list-permissions [target command]
  "Taget is either user name or host name"
  (execute *node-name* "rabbit_access_control" command
	   (create-args target)
	   *rabbit-instance* *cookie*))

(defn list-vhost-permissions [vhost]
  (map (fn [x] 
	 (struct user 
		 (value (_1st x))
		 vhost
		 (value (_2nd x))
		 (value (_3rd x))
		 (value (_4th x))))
       (as-seq (execute-list-permissions vhost "list_vhost_permissions"))))

(defn list-user-permissions [u]
  (first (remove nil? (map (fn [x] 
                             (if (and (instance? OtpErlangTuple x) 
                                      (= (count (.elements x)) 4))
                               (struct user
                                       u
                                       (value (_1st x))
                                       (value (_2nd x))
                                       (value (_3rd x))
                                       (value (_4th x)))))
                           (as-seq (execute-list-permissions u "list_user_permissions"))))))

(defn list-users []
  (let [users (map (fn [x] (value x)) 
                   (as-seq (execute *node-name*
                                    "rabbit_access_control"
                                    "list_users"
                                    (empty-args)
                                    *rabbit-instance*
                                    *cookie*)))]
    (flatten (map (fn [u] (list-user-permissions u))
                  users))))

(defn add-user [name password]
  (is-successful? #(execute *node-name*
			   "rabbit_access_control"
			   "add_user" 
			   (create-args name password)
			   *rabbit-instance* 
			   *cookie*)))

(defn delete-user [name]
  (is-successful? #(execute *node-name*
			   "rabbit_access_control"
			   "delete_user" 
			   (create-args name)
			   *rabbit-instance* 
			   *cookie*)))

(defn parse-ip [addr_tuple]
  (let [part1 (_1st addr_tuple)
	part2 (_2nd addr_tuple)
	part3 (_3rd addr_tuple)
	part4 (_4th addr_tuple)]
    (str part1 "." part2 "." part3 "." part4)))

(defn list-connections []
 (map (fn [conn] 
        (let [pid (value (_2nd (_1st conn)))
              address (parse-ip (_2nd (_2nd conn)))
              port (value (_2nd (_3rd conn)))
              peer_address (parse-ip (_2nd (_4th conn)))
              peer_port (value (_2nd (_5th conn)))
              recv-oct (value (_2nd (_6th conn)))
              recv-cnt (value (_2nd (_7th conn)))
	            send-oct (value (_2nd (_8th conn)))
              send-cnt (value (_2nd (_9th conn)))
              send-period (value (_2nd (_10th conn)))
	            state (value (_2nd (_nth conn 10)))
              channels (value (_2nd (_nth conn 11)))
              user (value (_2nd (_nth conn 12)))
              vhost (value (_2nd (_nth conn 13)))
              timeout (value (_2nd (_nth conn 14)))
              frame-max (value (_2nd (_nth conn 15)))]
	  (struct connection-info pid address port peer_address
                                  peer_port recv-oct recv-cnt
                                  send-oct send-cnt send-period
                                  state channels user vhost
                                  timeout frame-max)))
      (as-seq (execute *node-name*
	               "rabbit_networking"
	               "connection_info_all"
	               (empty-args)
	               *rabbit-instance*
	               *cookie*))))

(defn set-permissions [user vhost {config_regex :config write_regex :write read_regex :read}]
  (is-successful? #(execute *node-name* "rabbit_access_control" "set_permissions" 
	                   (create-args user vhost config_regex write_regex read_regex)
	                   *rabbit-instance* *cookie*)))

(defn clear-permissions [user vhost]
  (is-successful? #(execute *node-name* "rabbit_access_control" "set_permissions" 
	                  (create-args user vhost "^$" "^$" "^$")
	                  *rabbit-instance* *cookie*)))

;;; via protocol
(defn add-queue [vhost user password queue-name durable]
  (is-successful? #(with-channel *server* vhost user password (.queueDeclare queue-name durable))))

(defn delete-queue [vhost user password queue-name]
  (is-successful? #(with-channel *server* vhost user password (.queueDelete queue-name))))

(defn add-exchange [vhost user password name type durable]
  (is-successful? #(with-channel *server* vhost user password (.exchangeDeclare name type durable))))

(defn delete-exchange [vhost user password name]
  (is-successful? #(with-channel *server* vhost user password (.exchangeDelete name))))

(defn add-binding [vhost user password queue exchange routing-key]
  (is-successful? #(with-channel *server* vhost user password (.queueBind queue exchange routing-key))))
