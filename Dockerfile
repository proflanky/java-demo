# Step 1: Build Stage
FROM maven:3.9.4-eclipse-temurin-17 AS builder

# Set the working directory inside the container for the build process
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml ./
COPY src ./src

# Run Maven to build the application and package it as a jar
RUN mvn clean package -DskipTests


# Step 2: Runtime Stage
FROM openjdk:17-jdk-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the jar file from the builder image
COPY --from=builder /app/target/contactform-0.0.1-SNAPSHOT.jar /app/contact-form.jar

RUN ls -al

# Expose the port that the application will run on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "contact-form.jar"]
