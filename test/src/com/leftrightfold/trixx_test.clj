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

(ns com.leftrightfold.trixx-test
  (:use com.leftrightfold.trixx))

(defn add-delete-and-list-queues []
  (assert (zero? (count (list-queues "/"))))
  (add-queue "/" "guest" "guest" "my-queue" true)
  (assert (= 1 (count (list-queues "/"))))
  (delete-queue  "/" "guest" "guest" "my-queue")
  (assert (zero? (count (list-queues "/")))))

(defn add-delete-and-list-exchanges []
  ; 7 default exchanges ships with rabbit by default
  (assert (= 7 (count (list-exchanges "/"))))
  (add-exchange "/" "guest" "guest" "my-exchange" "direct" true)
  (assert (= 8 (count (list-exchanges "/"))))
  (delete-exchange  "/" "guest" "guest" "my-exchange")
  (assert (= 7 (count (list-exchanges "/")))))

(defn add-delete-and-list-bindings []
  (assert (zero? (count (list-bindings "/"))))
  (assert (add-queue "/" "guest" "guest" "my-queue" true))
  (assert (add-exchange "/" "guest" "guest" "my-exchange" "direct" true))
  (assert (add-binding "/" "guest" "guest" "my-queue" "my-exchange" "my-key"))
  ; when creating a queue, a default binding is created without routing key.
  (assert (= 2 (count (list-bindings "/"))))
  (assert (delete-exchange  "/" "guest" "guest" "my-exchange"))
  (assert (delete-queue  "/" "guest" "guest" "my-queue")))

(defn add-delete-and-list-vhosts []
  ; one for default '/' vhost
  (assert (= 1 (count (list-vhosts))))
  (assert (add-vhost "my-vhost"))
  (assert (= 2 (count (list-vhosts))))
  (assert (delete-vhost "my-vhost")))

(defn add-delete-and-list-users []
  ; default 'guest'
  (assert (= 1 (count (list-users))))
  (assert (add-user "my-user" "password"))
  (assert (= 2 (count (list-users))))
  (assert (delete-user "my-user")))

(defn set-clear-permissions []
  ; when the user is first created, it is not associted with vhost until permissions are set
  ; ?how do you query for that user?
  (assert (add-user "my-user" "password"))
  (assert (set-permissions "my-user" "/" {:config "c" :write "w" :read "r"}))
  ;;; needs to find my-user
  
  (let [user (list-user-permissions "my-user")]
    (assert (= "c" (:config user)))
    (assert (= "w" (:write user)))
    (assert (= "r" (:read user))))

  (assert (clear-permissions "my-user" "/"))
  (assert (list-user-permissions "my-user"))
  (assert (delete-user "my-user")))

(defn monitor-suite []
;;   (assert (stop-app))
;;   (assert (reset))
;;   (assert (start-app))
  (add-delete-and-list-queues)
  (add-delete-and-list-exchanges)
  (add-delete-and-list-bindings)
  (add-delete-and-list-vhosts)
  (add-delete-and-list-users)
  ;;; test list-connections
  (set-clear-permissions))

(monitor-suite)
