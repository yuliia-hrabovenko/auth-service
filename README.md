# 🔐 User Feature Auth Service

A Spring Boot application providing secure OAuth2 authentication with JWT, user management via MongoDB, and a gRPC service for retrieving user features.

---

## 📚 Features

- Google OAuth2 authentication
- JWT token generation
- MongoDB-based user storage
- gRPC API to fetch user features by email
- Spring Security with route protection
- Custom login success handler with token return
- Integration and unit tests with Testcontainers and Mockito

---

## Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Security (OAuth2)**
- **Spring Data MongoDB**
- **gRPC**
- **jjwt**
- **Lombok**
- **Testcontainers**
- **Mockito / JUnit 5**

---

## MongoDB Document

```json
{
  "_id": "abc123",
  "email": "user@example.com",
  "active": true,
  "features": ["BASE", "AUTOCOMPLETE", "SEARCH"]
}
```

## Example of .env file
```
MONGO_USERNAME=admin
MONGO_PASSWORD=secret123
MONGO_DATABASE=auth_db
MONGO_PORT=27017

CLIENT_ID=your-google-client-id
CLIENT_SECRET=your-google-client-secret

JWT_SECRET=your_secret_key
JWT_EXPIRATION=86400000
```

## Running the Application with Gradle
```
./gradlew clean build
./gradlew bootRun
```

## Run Tests with Gradle
```
./gradlew test
```
