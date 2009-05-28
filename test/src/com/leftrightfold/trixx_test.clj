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

(defn getprop
  "Lookup as a system property (namespaced by
  'com.leftrightfold.trixx-test'), returning the given default if the
  property is not set."
  [k def]
  (or (System/getProperty (str "com.leftrightfold.trixx-test." k))
      def))

(def *rabbit-user*     (getprop "user"     "guest"))
(def *rabbit-password* (getprop "password" "guest"))
(def *vhost*           (getprop "vhost"    "/"))
(def *queue-name*      (getprop "queue"    "com.leftrightfold.trixx-test.test-queue"))
(def *exchange-name*   (getprop "exchange" "com.leftrightfold.trixx-test.test-queue"))

(def *test-suite*   (atom []))
(def *test-results* (atom { :passed 0 :failed 0 }))

(defn passed!
  "Track that a test passed."
  [name]
  (reset! *test-results*
          (assoc @*test-results* :passed (+ 1 (:passed @*test-results*)))))

(defn failed!
  "Track that a test failed."
  [name]
  (reset! *test-results*
          (assoc @*test-results* :failed (+ 1 (:failed @*test-results*)))))


(defmacro deftest
  "Define a test (adding it to the suite)."
  [name & body]
  `(do
     (reset! *test-suite*
             (cons ['~name
                    '~body
                    (fn []
                      ~@body)]
                   @*test-suite*))))

(defmacro assert=
  "Assert equality."
  [left right & [msg]]
  `(let [lres# ~left
         rres# ~right
         msg# ~msg]
     (if (= lres# rres#)
       true
       (if msg#
         (throw (RuntimeException. (format "assert= failure: %s : expected '%s' got '%s' left=%s right=%s"
                                           msg# lres# rres# '~left '~right)))
         (throw (RuntimeException. (format "assert= failure: expected '%s' got '%s' left=%s right=%s"
                                           lres# rres# '~left '~right)))))))

(defmacro assert-true
  "Assert truth."
  [expr & [msg]]
  `(let [res# ~expr
         msg# ~msg]
     (if (not res#)
       (if msg#
         (throw (RuntimeException. (format "assert= failure: %s : expected true, res='%s' expr=%s"
                                           msg# res# '~expr)))
         (throw (RuntimeException. (format "assert= failure: expected true, res='%s' expr=%s"
                                           res# '~expr)))))))

(defmacro assert-false
  "Assert falsity."
  [expr & [msg]]
  `(let [res# ~expr
         msg# ~msg]
     (if res#
       (if msg#
         (throw (RuntimeException. (format "assert= failure: %s : expected false, res='%s' expr=%s"
                                           msg# res# '~expr)))
         (throw (RuntimeException. (format "assert= failure: expected false, res='%s' expr=%s"
                                           res# '~expr)))))))

;; (com.leftrightfold.trixx/delete-queue *vhost* *rabbit-user* *rabbit-password* *queue-name*)
;; (com.leftrightfold.trixx/add-queue *vhost* *rabbit-user* *rabbit-password* *queue-name* true)
;; (map :name (com.leftrightfold.trixx/list-queues *vhost*))

(defn queue-exists?    [vhost name] (some #(= (:name %) name)        (list-queues    vhost)))
(defn exchange-exists? [vhost name] (some #(= (:name %) name)        (list-exchanges vhost)))
(defn binding-exists?  [vhost name] (some #(= (:routing-key %) name) (list-bindings  vhost)))

(deftest add-delete-and-list-queues
  (add-queue *vhost* *rabbit-user* *rabbit-password* *queue-name* true)
  (assert-true (queue-exists? *vhost* *queue-name*))
  (delete-queue *vhost* *rabbit-user* *rabbit-password* *queue-name*)
  (assert-false (queue-exists? *vhost* *queue-name*)))

;; (map :name (list-exchanges *vhost*))
(deftest add-delete-and-list-exchanges
  (add-exchange *vhost* *rabbit-user* *rabbit-password* *exchange-name* "direct" true)
  (assert-true (exchange-exists? *vhost* *exchange-name*))
  (delete-exchange *vhost* *rabbit-user* *rabbit-password* *exchange-name*)
  (assert-false (exchange-exists? *vhost* *exchange-name*)))


;; (map :routing-key (list-bindings "/"))
;; (binding-exists? "/" "my-key")
(deftest add-delete-and-list-bindings
  (do (add-queue *vhost* *rabbit-user* *rabbit-password* *queue-name* true)
      (assert-true (queue-exists? *vhost* *queue-name*)))
  (do (add-exchange *vhost* *rabbit-user* *rabbit-password* *exchange-name* "direct" true)
      (assert-true (exchange-exists? *vhost* *queue-name*)))
  (do (add-binding *vhost* *rabbit-user* *rabbit-password* *queue-name* *exchange-name* "my-key")
      (assert-true (binding-exists? *vhost* "my-key")))
  (do (delete-exchange *vhost* *rabbit-user* *rabbit-password* *exchange-name*)
      (delete-queue    *vhost* *rabbit-user* *rabbit-password* *queue-name*)
      (assert-false    (binding-exists? *vhost* "my-key"))  
      (assert-false    (exchange-exists? *vhost* *queue-name*))
      (assert-false    (queue-exists? *vhost* *queue-name*))))

(deftest add-delete-and-list-vhosts
  ; one for default '/' vhost
  (assert (= 1 (count (list-vhosts))))
  (assert (add-vhost "my-vhost"))
  (assert (= 2 (count (list-vhosts))))
  (assert (delete-vhost "my-vhost")))

(deftest add-delete-and-list-users
  ; default 'guest'
  (assert (= 1 (count (list-users))))
  (assert (add-user "my-user" "password"))
  (assert (= 2 (count (list-users))))
  (assert (delete-user "my-user")))

(deftest set-clear-permissions
  ; when the user is first created, it is not associted with vhost until permissions are set
  ; ?how do you query for that user?
  (assert (add-user "my-user" "password"))
  (assert (set-permissions "my-user" *vhost* {:config "c" :write "w" :read "r"}))
  ;;; needs to find my-user
  
  (let [user (list-user-permissions "my-user")]
    (assert (= "c" (:config user)))
    (assert (= "w" (:write user)))
    (assert (= "r" (:read user))))

  (assert (clear-permissions "my-user" *vhost*))
  (assert (list-user-permissions "my-user"))
  (assert (delete-user "my-user")))

(deftest test-list-connections
  (assert (= 0 (count (list-connections)))))

(deftest erlang-cookie
  (let [orig-value @*cookie*]
    (assert-false (empty? orig-value))
    (clear-cookie!)
    (assert-true (empty? @*cookie*))
    (set-erlang-cookie!)
    (assert= orig-value @*cookie*)))

(defn run-tests 
  "Run the suite of registered tests."
  []
  (doseq [[name form test] @*test-suite*]
    (prn (format "running: %s" name))
    (try
     (do
       (test)
       (passed! name))
     (catch Exception e
       (failed! name)
       (prn (format "%s failed: %s : %s" name e form))
       (.printStackTrace e)))
    (test)))

(defn test-report
  "Report on the results of the test suite."
  []
  (let [passed (:passed @*test-results*)
        failed (:failed @*test-results*)
        total  (+ passed failed)]
    (prn (format "TESTS PASSED: %s (%3.2f%%)" passed (/ (* 100.0 passed) total)))
    (prn (format "TESTS FAILED: %s (%3.2f%%)" failed (/ (* 100.0 failed) total)))
    (prn (format "TEST SUITE %s" (if (zero? failed)
                                   "PASSED"
                                   "FAILED")))))

(do 
  (run-tests)
  (test-report))

