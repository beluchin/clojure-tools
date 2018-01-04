
(ns clojure.test.ext
  (:use clojure.test))

(defmacro defmultitest
  "Imagine you have multiple functions which implement the same abstraction.
  You also have a test applicable to each of the implementations.

  Use this macro to run the test for multiple implementations."
  [name test-fn impl1 & impls]
  `(deftest ~name
            (doseq [impl# ~(conj (vec impls) impl1)]
              (~test-fn impl#))))

