# FROM amazoncorretto:8-alphine-jdk
FROM amazoncorretto:17-alphine-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/my-spring-boot-app.jar app.jar

# Expose the port that the Spring Boot application will run on
EXPOSE 8080

# Define the command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "my-spring-boot-app.jar"]