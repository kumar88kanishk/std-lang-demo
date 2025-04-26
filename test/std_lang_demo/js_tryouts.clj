(ns std-lang-demo.js-tryouts
  (:use code.test)
  (:require [std.lang :as l]
            [clojure.math :as math]
            [scicloj.kindly.v4.kind :as kind]))

(l/script- :js
           {:runtime :basic
            :require [[xt.lang.base-lib :as k]
                      [xt.lang.base-macro :as km]]})

(k/capitalize "hello")

(!.js 3)

(!.js (fn []
        (return (+ 1 2 3))))

(!.js (fn []
        (return (- 8 7 6 5))))

(!.js (fn []
        (return (* 2 3 4))))

(!.js (fn []
        (return (/ 8 4))))

(!.js (fn []
        (return (mod 8 3))))

;; Using clojure pow which should not work
;; This demonstrates an attempt to use Clojure's `math.pow` function in a JavaScript context.
;; Since `math.pow` is a Clojure function, it is not compatible with JavaScript execution.

(!.js (fn []
        (return (math.pow 2 3))))

;; Using javascript math.pow instead of Math/pow
;; This demonstrates the correct way to use JavaScript's `Math.pow` function in the script.

(!.js (fn []
        (return (math.pow 2 3))))

;; Using std lang pow method
;; This demonstrates the use of the `k/pow` function from the `std.lang` library, which is likely
;; a cross-language utility for performing power calculations.

(!.js (fn []
        (return (k/pow 2 3))))

(!.js (== 3 "3"))
(!.js (=== 3 "3"))

;; Setting a var in js
(def.js a 10)
a


;; Defining a JavaScript function using the `defn.js` macro.
;; This macro enables the creation of JavaScript functions directly in Clojure code.
;; The function can then be invoked as if it were a native JavaScript function.
(defn.js hello
  "Hello, world!"
  []
  (return "Hello, world!"))

(hello)

;; Defining an equal function and using k/pow fn
;; This defines a JavaScript function (`equal?`) using the `defn.js` macro to check if two values are equal.
;; It also demonstrates the use of the `k/pow` function for power calculations.


(defn.js equal?
  "Check if two values are equal."
  [a b]
  (return (k/eq a b)))

(equal? 1 1)
(k/eq 1 2)

(k/starts-with? "Hello" "he")

(defn.js match-capitalize
  "Check if a value is a string."
  [s m1 m2]
  (if (and (k/starts-with? s m1)
           (k/ends-with? s m2))
    (return (k/capitalize s))
    (return false)))

(match-capitalize "hello" "he" "lo")

;; Fun with arrays
;; This section explores various operations on arrays using the `std.lang` library.
;; Arrays are immutable and of variable length, and the operations include mapping, reversing,
;; slicing, pushing, and grouping elements.

;; Arrays are immutable and of variable length.
(def a (k/arrayify ["hello" "he" "lo" "world"]))
a

(defn.js arr-fun
  "Capitalize and reverse the array."
  [arr]
  (return (k/arr-reverse (k/arr-map arr
                                    (fn [x]
                                      (return (k/capitalize x)))))))


(arr-fun a)
;; Push element from start
;; Demonstrates adding an element to the start of an array using `k/arr-pushr`.

(k/arr-pushr a "new")
;; Push element from end
;; Demonstrates adding an element to the end of an array using `k/arr-pushl`.

(k/arr-pushl a "new")
;; Rest of array except first
;; Demonstrates slicing an array to exclude the first element using `k/arr-slice`.

(k/arr-slice a 1 (k/len a))

;; Pop element from start
;; Demonstrates retrieving the first element of an array using `k/first`.

(k/first a)

(k/arr-group-by [["a" 1] ["a" 2] ["b" 3] ["b" 4]]
                k/first
                k/second)

(k/arr-lookup a)
;; Checks that every element fulfills the predicate
;; Demonstrates checking if all elements in an array satisfy a predicate using `k/arr-every`.

(!.js
 [(k/arr-every [1 2 3] km/odd?)
  (k/arr-every [1 3] km/odd?)])

;; Checks that the array contains an element
;; Demonstrates checking if any element in an array satisfies a predicate using `k/arr-some`.

(!.js
 [(k/arr-some [1 2 3] km/even?)
  (k/arr-some [1 3] km/even?)])

;; Performs a function call for each element
;; Demonstrates iterating over an array and applying a function to each element using `k/arr-each`.

(!.js
 (var a := [])
 (k/arr-each [1 2 3 4 5] (fn [e]
                           (k/arr-pushr a (+ 1 e))))
 a)

;; Assign Values to javascript objects
;; Demonstrates assigning values to a JavaScript object and checking if it is empty using `k/obj-empty?`.

(!.js
 (var a := {:key1 "Hello" :key2 "World"})
 (if (k/obj-empty? a)
   (k/obj-assign a {:empty "empty"})
   (k/obj-assign a {:not-empty "not empty"}))
 a)

;; Merges objects at a nesting level
;; Demonstrates merging two objects at a nested level using `k/obj-assign-nested`.

(!.js
 [(k/obj-assign-nested {:a 1}
                       {:b 2})
  (k/obj-assign-nested {:a {:b {:c 1}}}
                       {:a {:b {:d 1 :e {:f 1}}}})])
;; Creates a nested object
;; Demonstrates creating a nested object from a list of keys and a value using `k/obj-nest`.

(!.js (k/obj-nest ["a" "b" "c"] 23))


;; Delete multiple keys
;; Demonstrates deleting multiple keys from an object using `k/obj-del`.

(!.js (k/obj-del {:a 1 :b 2 :c 3}
                 ["a" "b"]))
;; Delete a key
;; Demonstrates deleting a single key from an object using `k/obj-del`.

(!.js (k/obj-del {:a 1 :b 2 :c 3}
                 "a"))

;; Delete all keys
;; Demonstrates deleting all keys from an object using `k/obj-del-all`.

(!.js (k/obj-del-all {:a 1 :b 2 :c 3}))


;; Select keys in object
;; Demonstrates selecting specific keys from an object using `k/obj-pick`.


(!.js (k/obj-pick {:a 1 :b 2 :c 3}
                  ["a" "b"]))
;; New object with missing keys
;; Demonstrates creating a new object by omitting specific keys using `k/obj-omit`.


(!.js (k/obj-omit {:a 1 :b 2 :c 3}
                  ["a" "b"]))
;; Obj-transposes a map
;; Demonstrates transposing the keys and values of a map using `k/obj-transpose`.

(!.js (k/obj-transpose {:a "x" :b "y" :c "z"}))

;; Maps a function across the values of an object
;; Demonstrates applying a function to all values in an object using `k/obj-map`.


(!.js
 (k/obj-map {:a 1 :b 2 :c 3}
            km/inc))

;; Facing issue with for loop
;; (!.js
;;  (var a:= "")
;;  (k/for:array [[i] [1 2 3]]
;;               (+ a i))
;;  (return a))

;; Facing issue with switch
;; (!.js
;;  (var a := "B")
;;  (return (cond
;;            (= a "A") (console.log "A")
;;            (= a "B") (console.log "B")
;;            (= a "C") (console.log "C"))))

;; Need more clarity
(!.js
 (setTimeout (fn [x] x)
                5000))

(comment a
         (k/second a)
         (k/len a)
         (k/arr-slice a 1 (k/len a)))