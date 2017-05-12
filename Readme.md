# Introduction
[![CircleCI](https://circleci.com/gh/mszalbach/IssuerSelfService.svg?style=svg)](https://circleci.com/gh/mszalbach/IssuerSelfService)

The Issuer Self Service is an example project to manage securities for an exchange platform. 
The server is written in Java and uses Spring Boot. The sourcesode can be found at [github](https://github.com/mszalbach/IssuerSelfService).

# Precondition
The project needs Java, Maven and Docker installed on your host. Node.js and Yarn are installed by Maven. 
Just run `mvn verify` to install Node.js and Yarn and to see if everything is working.

# Starting locally
To start the Server part execute the following command:
```
cd server
mvn spring-boot:run
```
or start the `de.blogspot.mszalbach.iss.Application` class from your IDE.

To start the Client part execute the following command:
```
cd client
myarn start
```
