(ns com.leftrightfold.Trixx
  (:require (com.leftrightfold.trixx))
  (:use com.leftrightfold.trixx)
  (:gen-class 
   :methods [[listExchanges [Object] Object]]
   :main false))

(defn -listExchanges [self vhost]
  (list-exchanges vhost))
