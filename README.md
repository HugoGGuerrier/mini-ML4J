# Mini-ML for Java

Author : Hugo GUERRIER \
Licence : MIT \
Version : 0.6

## Description

This project is a Mini-ML Java implementation with all needed features and easily extensible.
The parser uses ANTLR4 and everything is in pure Java.
You can run the unit tests and start the REPL to test the implementation yourself.
Enjoy!

## Requirements

* Java >= 8
* Apache Ant >= 1.10.4

## How to compile, run and test

* Compile : `ant build`
* Create a JAR : `ant jar` (jar is now in the bin folder)
* Run the REPL : `java -jar bin/mini-ML4J.jar`
* Start all unit tests : `ant test` (if there is a library error : `ant test -lib lib`)
* Clean the generated files : `ant clean`

## Todos

* Add the record types