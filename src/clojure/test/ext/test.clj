(ns clojure.test.ext.test
  (:require [clojure.test.ext :as ext]))

(use 'clojure.test)

(defn substract-from-9 [x] (- 9 x))

(defn verify [f] (is (= (f 3) 6)))

(deftest with-letfn
  (letfn [(verify [f] (is (= (f 3) 6)))]
    (doseq [impl
            [#(+ 3 %)
             (partial * 2)
             (constantly 6)
             substract-from-9]]
      (verify impl))))

(deftest without-letfn-1
  (doseq [impl
          [#(+ 3 %)
           (partial * 2)
           (constantly 6)
           substract-from-9]]
    ((fn [f] (is (= (f 3) 6))) impl)))

(deftest without-letfn-2
  (doseq [impl
          [#(+ 3 %)
           (partial * 2)
           (constantly 6)
           substract-from-9]]
    (#(is (= (% 3) 6)) impl)))

(deftest without-letfn-3
  (doseq [impl
          [#(+ 3 %)
           (partial * 2)
           (constantly 6)
           substract-from-9]]
    (verify impl)))

(ext/defmultitest ha
              verify
              #(+ 3 %)
              (partial * 2)
              (constantly 6)
              substract-from-9)