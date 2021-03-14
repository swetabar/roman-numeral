# Conversion to roman numerals

Conversion to roman numerals is a spring-boot application which exposes an 
endpoint to 
calculate the roman numeral equivalent of a given integer.

## Table of Contents  

* [Purpose](#purpose)
* [References](#references)
* Start off with a docker image
* [Local environment setup](#local-environment-setup)
* [Engineering and testing methodology](#engineering-and-testing-methodology)
* [API response](#api-response)
* Dependencies
* Packaging layout
* Future

## Purpose

Build an HTTP endpoint that provides the roman numeral equivalent of 
an integer value in the range 1-255. The HTTP endpoint accepts a URI in the 
format `http://localhost:8080/romannumeral?query={number}` where `number` is 
an integer in the range 1-255.  
For example, `http://localhost:8080/romannumeral?query=10` would return the 
roman numeral equivalent of 10, which is `X`.

## References

The references below provide a brief background on roman numerals and explain 
how 
integers are converted to 
roman numerals.  
* [Britannica](https://www.britannica.com/topic/Roman-numeral)
* [Math is fun](https://www.mathsisfun.com/roman-numerals.html)

## Local environment setup

### Prerequisites
* Install Apache maven (version 3.6.3 or above)
* Install JDK 11 (version  11.0.8 or above)
* Optional : Install a JSON viewer plugin on your browser. For Chrome, you 
  could install [JSONView](https://chrome.google.com/webstore/detail/jsonview/chklaanhfefbnpoihckbnefhakgolnmc)

### Steps to run the local instance on your machine
* Clone the repo : `git clone https://github.com/swetabar/roman-numeral.git`
* From the root folder, execute : `mvn spring-boot:run`
* Open any browser window and try out the following URL: 
  `http://localhost:8080/romannumeral?query=9` to get started 
  
### Steps to import the project to IntelliJ IDEA Ultimate
You could probably import it into IntelliJ IDEA Community edition as well, 
but that might need a few additional plugins.

* Clone the repo : `git clone https://github.com/swetabar/roman-numeral.git`
* Click on `File -> Open`
* Navigate to the cloned `roman-numeral` folder and import it in
* Within the project in intellij, navigate to : 
  `src/main/java/com/roman/conversion/RomanNumeralApplication.java`
* Run `RomanNumeralApplication.java`
* Open any browser window and try out the following URL:
  `http://localhost:8080/romannumeral?query=10` to get started
  
# Engineering and testing methodology
Spring-boot is used as the backend framework to build out the rest endpoint for 
converting integers 
to roman numerals. There are a few error handling scenarios that had to be 
considered - such as 0 has no roman numeral equivalent.  
[TDD](https://en.wikipedia.org/wiki/Test-driven_development) - Test Driven 
Development was used to build out this application. The process for 
development started out with a backbone of the spring boot application, then 
writing out tests for RomanNumeralController and RomanNumeralService, 
followed by writing out code to make sure the tests pass. The edge cases 
were added soon after, and the error handling as well following the same 
process as mentioned above. Javadoc comments were also added to provide a 
better understanding of each class, method, or interface.

# API response
The detailed API response can be found in the swagger document uploaded with 
the code repository. We've mentioned the successful scenario as well as a 
few error scenarios.  
### Successful Conversion of an integer to a roman numeral
The request could be a curl request : `curl 
http://localhost:8080/romannumeral?query=8` or you could open your favorite 
browser and hit : `http://localhost:8080/romannumeral?query=8`. The server 
responds with an <b>HTTP status code of 200</b> and a json object with the 
input 
and the output as seen below:
```json
{
  "input": "8",
  "output": "VIII"
}
```

### Error scenarios
#### Query parameter field is blank / an empty string
Request : 
`curl http://localhost:8080/romannumeral?query=`
The server responds with an <b> HTTP status code of 400 </b> and a json 
object as seen below:
```json
{
"errorCode": "REQUEST_IS_EMPTY",
"message": "The query parameter is blank."
}
```

### 



  
### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/#build-image)



