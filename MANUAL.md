# MiniML Syntax Manual

This is a manual for the MiniMl language implemented in this project.

### Internal types

Those type is basic MML types and can be used anywhere in an expression.

* Integer -> `42` represents the integer 42
* List -> `[1, 2, 3]` represents a list with elements 1, 2 and 3 (a list is always terminated by a nil)
* Reference -> `@5` represents an integer reference
* Unit -> `()`
* Nil -> `nil`

### Internal operators and build-in

List of functions and operations that are natively supported by MML.

* Add and sub -> `5 + 8` or `4 - 2`
* List constructor -> `cons(1, cons(2, cons(3, nil)))` represents the same list as `[1, 2, 3]`
* List head and tail -> `head([1, 2, 3])` or `tail([1, 2, 3])`
* Reference creation -> `@5` create an integer reference
* Dereference -> `!@5` is simply an integer (deref of a ref)
* Assignment -> `@5 := 46` affect 46 to the newly created reference

### Language structures

All the language structures

#### Declare a lambda function

Identity : `fn(x){x}` \
Add : `fn(x, y){x + y}`

#### Declare a recursive function

Infinite : `rec my_rec (a) {my_rec(a)}`

#### Apply a function

Lambda : `fn(a){a}(5)`
Named function : `a(5)`

#### If structures

If zero : `ifz(5) {42} else {0}`
If list is empty : `ifem([])`
