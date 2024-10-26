# Blog API
## Overview
The Blog API is a RESTful web service that allows users to perform CRUD (Create, Read, Update, Delete) operations on blog posts.

## Features
- Create, Read, Update, and Delete blog posts.
- Validate input using annotations.
- Detailed logging for monitoring and debugging.
- Swagger UI for interactive API documentation.


## Technologies Used
- Java 17
- Spring Boot 3.3.5
- PostgreSQL (for the database)
- Maven (for dependency management)
- Docker (for containerization)
- Testcontainers (for integration testing)
- Swagger (for API documentation)


## Getting Started
### Prerequisites
- Java 17 or higher
- Docker (for containerization)


#### Dockerfile
The Dockerfile defines how to build the Docker image for the Blog API application. It specifies the base image, sets the working directory, copies the application files, exposes the necessary ports, and defines the command to run the application.
#### Docker Compose
The docker-compose.yml file orchestrates the application and its dependencies. It defines services for the application and the PostgreSQL database, allowing both to run in isolated containers.

#### Integration Testing
Integration tests are written to verify that different parts of the application work together as expected. Using Testcontainers, we can spin up a PostgreSQL container during the tests, ensuring a clean and isolated environment for each test run. This approach provides confidence that the application behaves correctly in a production-like environment.

### Installation
1. Clone the Repository
```
git clone https://github.com/ksoumi/BlogAssignment.git
```
2. Build the Application
```
   mvn clean package
```
3. Set Up the Database:
```
docker-compose up --build
```
This command will build the application container and start both the application and the PostgreSQL database.

4. Access the Application:

Once the application is running, you can access the API at:
```
http://localhost:8080/blog
```

## API Documentation with Swagger
Once the application is running, you can access the Swagger UI at:
```
http://localhost:8080/swagger-ui/index.html
```

This interface provides an interactive way to explore and test the API endpoints.

API Endpoints

|HTTP Method | Endpoint	| Description|
|------------|----------|------------|
|POST	|/blog/create|	Create a new blog post|
|GET	|/blog/{id}|	Retrieve a blog post by ID|
|PUT|	/blog/update/{id}|	Update an existing blog post|
|DELETE|	/blog/delete/{id}|	Delete a blog post|
|GET|	/blog/all|	Retrieve all blog posts|