Project Title

A simple ATM simulator application written in JAVA.

Getting Started
 
The project can be directly imported into the eclipse project and the main class can be run as a Java application.

The project also can be build and deployed with Maven.

Prerequisites

* JAVA 8
* Maven
* Junit

How to Use:

The application comes with pre configured users and their pin which can be found in the AccountStore.java file. ATMDriver.java is the entry point for the application.

The application requires the account number and pin to validate the access to the system.
Upon successful validation a simple set of banking operations can be done against the account.A Sample account that can be used is A/C No: 12345 and pin: 1234

Below operations can be performed by the Account holder:
* Deposit
* Withdraw
* View Balance
* Mini statement (Last 10)
* Exit

Validations and Exception handling are done wherever applicable.

The following Design patters are used and SOLID principles are followed in most of the places.

* State pattern used to maintain the ATM workflow
* Decorator pattern is used in place where there are more than one actions needs to be done.
* Chain of responsibility used for dispensing cash.

Unit testing:

Adequate unit testing is written to cover both positive and negative tests.The same can be used as Junit tests/suites and is integrated with Maven builds.

