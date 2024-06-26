# Technical Test

- [Technical Test](#technical-test)
    * [Task statement](#task-statement)
    * [Overview](#overview)
    * [Technology Stack](#technology-stack)
    * [Methodology](#methodology)
    * [Project Structure](#project-structure)
    * [API Documentation](#api-documentation)
    * [How to Run](#how-to-run)
    * [Testing](#testing)
    * [Postman Collection](#postman-collection)
    * [Notes](#notes)

## Task statement

Finish implementing the REST API for the Offer domain entity.

The project is delivered with a simple package structure following a hexagonal architecture and Domain-Driven Design (DDD), including a controller, DTO, entity, and exception for the REST layer. Data should be stored in an in-memory database. Essentially, implement a CRUD for Offer.

The only peculiarity is that the `productPartNumber` in the database should be stored in multiple columns. For example, if the input is 000100233 (TTMMMMQQQ), it should be stored as:

- Size = 00
- Model = 0100
- Quality = 233

Note that the Offer entity is not defined this way initially and can be changed. This data should be displayed in both input and output with this format:

```json
{
  "productPartNumber": "000100233"
}
```

Evaluation criteria:
* Unit and integration testing. 
* Use of hexagonal architecture and DDD. 
* Use of design patterns. 
* Validations. 
* Exception handling.

### Project Details

* The dependencies provided are sufficient to set up everything required, but you can add more if necessary.
* You are free to make changes to the code, packages, etc.
* The project must compile, be operational, and store data in an in-memory database.
* Include a sample Postman collection in the repository.

Example JSON input for creating the Offer entity:

```json
[
    {
        "offerId": 1,
        "brandId": 1,
        "startDate": "2020-06-14T00.00.00Z",
        "endDate": "2020-12-31T23.59.59Z",
        "priceListId": 1,
        "productPartNumber": "000100233",
        "priority": 0,
        "price": 35.50,
        "currencyIso": "EUR"
    }
]
```

## Overview

This project is designed to manage offers using a _RESTful API_. The API supports operations for creating, retrieving, updating, and deleting offers. The project is built using modern Java and Spring Boot technologies, following best practices in software development.

## Technology Stack

The project mainly uses the following technologies and tools:

* **Java 17**: One of the last long-term support (LTS) releases of Java. 
* **Spring Boot 3.3.0**: A robust framework for building microservices and web applications. Along with the following starters:
  * **spring-boot-starter-data-jpa**: For data persistence and ORM with Hibernate. 
  * **spring-boot-starter-web**: For building web, including RESTful, applications. 
  * **spring-boot-starter-validation**: For bean validation. 
  * **spring-boot-starter-test**: For testing the application. 
* **MapStruct 1.5.5.Final**: For generating type-safe bean mappers. 
* **Lombok 1.18.32**: For reducing boilerplate code. 
* **H2 Database**: An in-memory database for development and testing. 
* **fmt-maven-plugin**: For checking and formatting code. 
* **openapi-generator-maven-plugin**: For generating API code and documentation from OpenAPI specifications.

## Methodology

The project is built following SOLID principles and using the following methodologies and architectural principles:
* **API First**: The API is designed and documented using OpenAPI before any code is written. 
* **TDD** (Test-Driven Development): Tests are written before the actual code to ensure functionality and robustness. 
* **DDD** (Domain-Driven Design): The domain model is the core of the application, ensuring a clean separation of concerns. 
* **Hexagonal Architecture**: Also known as Ports and Adapters, this architecture ensures the core logic is decoupled from external systems (like databases and APIs), making the system more maintainable and testable.

## Project Structure

* _src/main/java_: Contains the main application code.
  * _com.kairosds.api_rest_: Contains the API controllers and DTOs. 
  * _com.kairosds.domain_: Contains the domain models and interfaces. 
  * _com.kairosds.application_: Contains the implementations of the use cases and services. 
  * _com.kairosds.infraestructure_: Contains infrastructure-related code, such as database and repository configurations. In this technical test it has not been necessary to implement external service clients.
* _src/test/java_: Contains the test cases for the application. 
* _src/main/resources_: Contains the application configuration files, including the `application.properties` for Spring Boot and `openapi.yml` for the OpenAPI specification.
* _postman_: Contains the Postman collection for testing the API endpoints.

## API Documentation

The API is documented using OpenAPI. The OpenAPI specification file (`openapi.yml`) is located at _src/main/resources/_.

## How to Run

1. Clone the repository.
2. Navigate to the project directory.
3. Build the project using Maven: 
```bash
mvn clean install
```
5. Run the application: 
```bash
mvn spring-boot:run
```
6. Run the requests in the postman collection to interact with the API. See _[Postman Collection](#postman-collection)_.

**Note**: The command to format the code using the fmt plugin is:
```bash
mvn com.spotify.fmt:fmt-maven-plugin:format
```

## Testing

To run the tests, execute the following command: 
```bash
mvn test
```

## Postman Collection

A Postman collection is provided to facilitate testing the API endpoints. Follow these steps to use it:

1. Open Postman.
2. Click on Import in the top left corner.
3. Select the postman folder in the project directory.
4. Import the collection file (`Technical_Test.postman_collection.json`).
5. Run the requests in the collection to interact with the API.

## Notes

* Ensure you have Java 17 installed on your machine.
* The H2 database is used for development and testing purposes.
