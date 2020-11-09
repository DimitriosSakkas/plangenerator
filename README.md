# Plan Generator

## Task Description
The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring Boot
* Maven

The project contains Maven wrapper to compile and run the application.

## Running application

Run the application from an IDE as Java application or run in terminal 

```sh
./mvnw spring-boot:run
```

Call the endpoint, as a post request with url:

```sh
localhost:8080/generate-plan
```

and request body, e.g.,

```sh
{
    "loanAmount": "5000", 
    "nominalRate": "5.0",
    "duration": 24,
    "startDate": "2018-01-01T00:00:01Z"
}
```
