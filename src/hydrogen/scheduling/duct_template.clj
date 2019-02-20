(ns hydrogen.scheduling.duct-template
    (:require [clojure.java.io :as io]))

(defn resource [path]
  (io/resource (str "hydrogen/scheduling/" path)))

(defn twarc-profile [_]
  {:deps '[[magnet/scheduling.twarc "0.3.0"]]
   :templates {"test/{{dirs}}/test_scheduling.clj" (resource "test_scheduling.clj")}
   :config-mods [(fn [config]
                   (merge-with merge config
                               (clojure.edn/read-string
                                 {:default tagged-literal}
                                 (slurp (resource "merge-with-config.edn")))))]})
