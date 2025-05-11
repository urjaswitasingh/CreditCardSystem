# Credit Card Activation System

## Project Description
The Credit Card Activation System is a Spring Boot-based REST application designed to handle two critical operations for activating credit cards: credit score evaluation and OTP-based user verification. It integrates with Apache Kafka for asynchronous messaging and uses a relational database to store credit scores and OTP data securely.

## Key Features

### Credit Score Features:
- Receive customer financial data via Kafka.
- Calculate credit score based on salary and number of existing credit cards.
- Persist credit score if not already stored for the user.
- Send credit score result back to Kafka for further processing.

### OTP Features:
- Generate secure, random OTP for user verification.
- Validate OTPs during the activation process.
- Ensure OTPs are one-time-use by removing them post-validation.

### System Validation:
- Avoid re-processing credit score if already present.
- Reject OTP validation if OTP is expired or incorrect.
- Ensure OTP is linked to the correct user and used only once.

---

## Technologies Used

This project is built using the following technologies:

- **Java 17**: For programming logic and functionality
- **Spring Boot**: For building and managing REST APIs
- **Apache Kafka**: For handling asynchronous messaging between microservices
- **PostgreSQL**: For storing credit scores and OTP records
- **Spring Data JPA**: For database access and ORM
- **Jackson**: For parsing and generating JSON
- **JUnit**: For unit testing the application logic
- **Lombok**: For reducing boilerplate code in model classes

---

## Features

### Credit Score Module:
- Listens to Kafka topic for incoming credit check requests.
- Parses the data and checks if a credit score already exists.
- Calculates a credit score using the following rules:
  - +300 if number of credit cards ≥ 2
  - +500 if salary > 200000
  - +150 if salary > 50000
  - +50 if salary ≤ 50000
- Sends the result as a Kafka message to the response topic.

### OTP Module:
- Generates a 6-digit random OTP for each user.
- Saves OTPs linked to the user ID in the database.
- Validates the OTP for the given user ID.
- Deletes OTP after successful verification.

---

## To-Do List

Future improvements and additional features for the system include:

- **OTP Expiry Time**: Add a timeout feature to invalidate OTPs after a duration.
- **Kafka Retry Mechanism**: Improve resilience against message delivery failures.
- **RESTful APIs**: Expose endpoints for manual OTP requests and credit check status.
- **Security Enhancements**: Encrypt OTPs before storing in the database.
- **Admin Dashboard**: Allow monitoring of Kafka messages and OTP logs.

---

## Getting Started

### Prerequisites:

Before running the project, ensure you have the following installed on your machine:

- **Java 17 or higher**: Required to compile and run the application.
- **Apache Kafka**: Required to send and receive messages between services.
- **PostgreSQL**: To store credit score and OTP data.
- **IntelliJ IDEA**: Recommended for running the Spring Boot project.
- **Maven**: For building the project and managing dependencies.

---

### Installation:

**Clone the Repository**  
Clone the GitHub repository to your local machine by running:

```bash
git clone https://github.com/yourusername/CreditCardActivationSystem.git
cd CreditCardActivationSystem
