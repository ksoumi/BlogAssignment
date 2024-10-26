# Use a base image with Java
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from target directory
COPY target/crudassignment-0.0.1-SNAPSHOT.jar crudassignment-0.0.1-SNAPSHOT.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "crudassignment-0.0.1-SNAPSHOT.jar"]
