# Conversion to roman numerals

Conversion to roman numerals is a spring-boot application which exposes an 
endpoint to 
calculate the roman numeral equivalent of a given integer.

## Table of Contents  

* [Purpose](purpose)
* [References](references)
* Start off with a docker image
* Dependencies
* Local environment setup
* Engineering methodology
* Testing methodology
* Packaging layout
* Future

### Purpose

Build an HTTP endpoint that provides the roman numeral equivalent of 
an integer value in the range 1-255. The HTTP endpoint accepts a URI in the 
format `http://localhost:8080/romannumeral?query={number}` where `number` is 
an integer in the range 1-255.  
For example, `http://localhost:8080/romannumeral?query=10` would return the 
roman numeral equivalent of 10, which is `X`.

### References


### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/#build-image)

