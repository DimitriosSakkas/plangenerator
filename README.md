# Plan Generator

## Task Description
The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring Boot
* Maven

Since this is a maven project, Maven Wrapper plugin is available if maven is not installed on the system. 
To build the project, the following command from the root  directory must be executed:
```bash
./mvnw clean install
```

## Running application
Run the application from an IDE as Java application or run in terminal 

```sh
./mvnw spring-boot:run
```

## Descripton 
The application exposes 1 endpoint, which calculates a repayment plan for an annuity loan. 
To import an endpoint as an example in postman, the following curl command is available:
```sh
curl --location --request POST 'localhost:8080/generate-plan' \
--header 'Content-Type: application/json' \
--data-raw '{
    "loanAmount": "5000",
    "nominalRate": "5.0",
    "duration": 24,
    "startDate": "2018-01-01T00:00:01Z"
}'
```

In the request body negative values are not allowed and the date must not be empty.
