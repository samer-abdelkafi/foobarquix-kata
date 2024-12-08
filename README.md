# foobarquix-kata

### Description

This project is a Java implementation of the FooBarQuix Kata. 
It provides both a REST API and a batch processing functionality to transform numbers 
into strings based on the rules bellow:
- If the number is divisible by 3 or contains the digit 3, the result contains "FOO".
- If the number is divisible by 5 or contains the digit 5, the result contains "BAR".
- If the number contains the digit 7, the result contains "QUIX".
- The "divisible by" rule takes precedence over the "contains" rule.
- The input is analyzed from left to right.
- If none of the rules are satisfied, the result is the number itself as a string.

### Features

#### 1 - REST API

Exposes an endpoint to process a single number.

URL: http://localhost:8080/api/foobarquix/{number}

Example: curl http://localhost:8080/api/foobarquix/15

Response: "FOOBARBAR"

#### 2 - Batch Processing

- Accepts a .txt file containing numbers (one per line) as input.
- Processes each number according to the rules and generates an output file containing 
the original number and its transformation.

### Requirements
- Java 17
- Maven
- Git

### How to Run
1 - Clone the Repository
Clone the project from GitHub to your local machine.

git clone https://github.com/your-username/foobarquix-kata.git

cd foobarquix-kata

2 - Build the Project
Use Maven to compile the project and package it into a JAR file.

mvn clean install

3 - Run the Application

java -jar target/foobarquix-kata-1.0-SNAPSHOT.jar src/test/resources/input-test-file.txt

The result will be saved in the same directory as the input file with a ".out" extension.

After starting the application, you can use your browser to test the API.
Copy and paste this url in your browser : http://localhost:8080/api/foobarquix/15