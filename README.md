# MiniEcomm

A mini e-commerce REST API built with Spring Boot. 
I am adding this dummy project to learn all the microservice and database design patterns and concepts.

TODO : Implement Kafka, SAGA pattern, circuit breaker, timeouts and retries using reslience4j, manage idempotency
## Tech Stack

| Layer | Technology |
|---|---|
| Framework | Spring Boot 4.1.0 |
| Language | Java 17 |
| Database | PostgreSQL (JPA/Hibernate) |
| Security | Spring Security + BCrypt |
| Messaging | Apache Kafka + Kafka Streams |
| Resilience | Resilience4j Circuit Breaker |
| Monitoring | Spring Boot Actuator |
| Build | Maven |

## Prerequisites

- Java 17+
- Maven 3.9+
- PostgreSQL instance
- Kafka broker

## Configuration

Create `src/main/resources/application.properties` (or `.yml`) with your environment values:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/miniecomm
spring.datasource.username=<db-user>
spring.datasource.password=<db-password>
spring.jpa.hibernate.ddl-auto=update

spring.kafka.bootstrap-servers=localhost:9092
```

## Running the App

```bash
./mvnw spring-boot:run
```

The server starts on port `8080`.

## API Reference

### Users

#### Register a user
```
POST /api/users
Content-Type: application/json

{
  "username": "johndoe",
  "email": "john@example.com",
  "rawPassword": "secret123",
  "phoneNumber": "+1234567890"
}
```
Returns the new user's `id` (Long).

**Validation rules:**
- `username` — required, non-blank
- `email` — required, valid email format
- `rawPassword` — required, minimum 8 characters
- `phoneNumber` — required, non-blank

#### Get a user by username
```
GET /api/users/{username}
```
Returns:
```json
{
  "id": 1,
  "email": "john@example.com",
  "phoneNumber": "+1234567890"
}
```

### Error Responses

Validation failures return HTTP `400` with a structured body:
```json
{
  "status": 400,
  "message": "Validation failed",
  "errors": {
    "email": "must be a well-formed email address",
    "rawPassword": "size must be between 8 and 2147483647"
  },
  "timestamp": "2026-07-15T10:00:00Z"
}
```

## Project Structure

```
src/main/java/com/demo/miniecomm/
├── MiniecommApplication.java
├── exceptions/
│   ├── GlobalExceptionHandler.java   # @RestControllerAdvice for validation errors
│   └── ErrorResponse.java
└── orders/
    ├── config/
    │   └── SecurityConfig.java       # BCrypt encoder, open /api/** routes
    ├── controller/
    │   ├── UserController.java
    │   └── OrderController.java      # placeholder
    ├── dto/
    │   └── UserDto.java
    ├── model/
    │   └── User.java                 # JPA entity (users table)
    ├── repo/
    │   └── UserRepository.java
    ├── requests/
    │   └── RegisterUserRequest.java  # validated request body
    └── service/
        └── UserService.java
```

## Security Notes

- Passwords are hashed with BCrypt before persistence — raw passwords are never stored.
- All `/api/**` routes are currently public (no authentication required) to simplify local development. Tighten this before deploying to production.
- CSRF protection is disabled for API testing convenience.