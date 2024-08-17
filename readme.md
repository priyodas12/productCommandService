# Spring Boot Application with CQRS, AWS SQS, and PostgreSQL

This Spring Boot application demonstrates the CQRS (Command Query Responsibility Segregation) design
pattern, integrated with AWS SQS for messaging and PostgreSQL for persistent storage. CQRS is a
pattern that separates the read and write operations into different models, which helps in
optimizing performance, scalability, and security.

## Features

- **CQRS Implementation**: Separates command (write) and query (read) responsibilities.
- **AWS SQS Integration**: Utilizes AWS SQS for asynchronous communication and message queuing.
- **PostgreSQL Integration**: Uses PostgreSQL as the relational database for data persistence.
- **Spring Boot**: Provides a robust framework for building and deploying the application.

## Technologies

- **Spring Boot**: Framework for building Java-based applications.
- **AWS SQS**: Managed message queue service.
- **PostgreSQL**: Relational database management system.
- **JPA/Hibernate**: ORM for database interactions.
- **Lombok**: Library for reducing boilerplate code.

## Prerequisites

- Java 22
- Maven
- AWS account with SQS and appropriate permissions
- PostgreSQL docker instance



