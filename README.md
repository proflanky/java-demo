# Java Maven Contact Form App

This project demonstrates a simple Java Spring Boot application that includes a contact form, built using Maven and containerized for deployment. It serves as an example for demonstrating Continuous Integration (CI) practices on a Java Maven application. The application also showcases how to avoid Docker-in-Docker (dind) by utilizing a Kubernetes pod as the build agent.

## Features

- Spring Boot application with a simple contact form.
- Demonstrates CI setup with Maven and containerization.
- Docker multi-stage build for efficient image creation.
- Uses Kubernetes pod as a build agent to avoid Docker-in-Docker (dind).

## Project Structure

The project follows a standard Maven structure:

```bash
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.contactform
│   │   │       ├── ContactFormApplication.java   # Main application class
│   │   │       ├── model
│   │   │       │   └── Contact.java              # Model class for contact form data
│   │   │       ├── controller
│   │   │       │   └── ContactController.java    # Controller for form submission
│   │   │       └── service
│   │   │           └── EmailService.java         # Service for sending emails
│   └── test                                      # Unit tests
├── Dockerfile                                    # Dockerfile for building the container
├── pom.xml                                       # Maven build configuration
└── README.md                                     # Project documentation
```
## Setup
Clone the Repository
```bash
git clone https://github.com/proflanky/java-demo.git
cd java-demo
```
## Build the Project
Use Maven to build the application:

```bash

mvn clean package
```
Run the Application Locally
To run the application locally, use:

```bash

mvn spring-boot:run
```
The application will be available at http://localhost:8080.

## Continuous Integration
This project demonstrates how to set up CI using a Kubernetes pod as the build agent, avoiding Docker-in-Docker (dind).

Key Points:

Kubernetes Pod as Build Agent: We use a Kubernetes pod to run the Maven build and Docker containerization steps, avoiding the complexity and security issues associated with dind.
CI Pipeline: The pipeline includes building and packaging the application as a Docker image.
Avoiding Docker-in-Docker (dind)
Instead of using Docker-in-Docker, we use Kubernetes pods as build agents. This simplifies the pipeline, enhances security, and ensures the containerization process works seamlessly with Kubernetes-native environments.

License
This project is licensed under the MIT License.

This README.md provides an overview of the project, setup instructions, CI details, and how Kubernetes is used to avoid dind
