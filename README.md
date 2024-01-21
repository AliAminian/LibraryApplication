# Library Management System

This is a simple library management system implemented using Java, Spring Boot, Maven, and Swagger for API documentation.

## Features

- Create, delete, update, and manage books in a library using Restful APIs.
- Use Spring frameworks including Boot, JPA, and Security.
- Document APIs with Swagger.
- Use H2 in-memory database for the library.

## Getting Started

1. Clone the repository.
2. Build the project using Maven.
3. Run the application.

## API Documentation

Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

Explore and test the RESTful APIs interactively using the Swagger UI.

## Database
H2 in file: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## Configuration

Configure the application properties in `src/main/resources/application.properties`.

## Testing

Unit tests are available for the `BookController` and `BookService` classes. Run the tests using your preferred testing tool.

## Dependencies

- Java 8 or higher
- Maven

## Build and Run

```bash
mvn clean install -Dmaven.test.skip=true
java -jar target/library-management-1.0.0.jar
