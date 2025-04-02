# Portfolio API

A Spring Boot-based RESTful API for managing user portfolios with JWT authentication. This project uses an in-memory H2 database for development and includes Swagger UI for API documentation.

## Features
- User registration and login with JWT authentication.
- CRUD operations for users and portfolios.
- In-memory H2 database for development and testing.
- API documentation via Springdoc OpenAPI and Swagger UI.

## Tech Stack
- **Framework**: Spring Boot 3.4.4
- **Language**: Java 21
- **Database**: H2 (in-memory)
- **Security**: Spring Security with JWT
- **API Documentation**: Springdoc OpenAPI with Swagger UI
- **Dependencies**: Lombok, JPA, PostgreSQL (optional), H2

## Prerequisites
- **Java**: 21 or higher
- **Maven**: 3.6+
- **Postman** or **curl**: For testing API endpoints

## Setup Instructions

### 1. Clone the Repository
```bash
git clone <repository-url>
cd PortfolioApi
```

### 2. Build the Project
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```
- The application will start on `http://localhost:8080`.

### 4. Configuration
The project uses an in-memory H2 database by default. Configuration is in `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:portfolioapi;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.hibernate.ddl-auto=update
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.api-docs.path=/v3/api-docs
```

- **H2 Console**: Access at `http://localhost:8080/h2-console` (JDBC URL: `jdbc:h2:mem:portfolioapi`, Username: `sa`, Password: blank).
- **Swagger UI**: Access at `http://localhost:8080/swagger-ui.html`.

## API Endpoints

### Authentication
#### Register a User
- **Method**: `POST`
- **URL**: `http://localhost:8080/api/auth/register`
- **Headers**: `Content-Type: application/json`
- **Body**:
  ```json
  {
      "username": "testuser",
      "email": "test@example.com",
      "password": "password123"
  }
  ```
- **Response** (200 OK):
  ```json
  {
      "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
  ```

#### Login
- **Method**: `POST`
- **URL**: `http://localhost:8080/api/auth/login`
- **Headers**: `Content-Type: application/json`
- **Body**:
  ```json
  {
      "username": "testuser",
      "password": "password123"
  }
  ```
- **Response** (200 OK):
  ```json
  {
      "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
  ```

### Users
#### Create a User
- **Method**: `POST`
- **URL**: `http://localhost:8080/api/users`
- **Headers**:
  - `Content-Type: application/json`
  - `Authorization: Bearer <token>`
- **Body**:
  ```json
  {
      "username": "newuser",
      "email": "newuser@example.com",
      "password": "newpass123"
  }
  ```
- **Response** (201 Created):
  ```json
  {
      "id": 2,
      "username": "newuser",
      "email": "newuser@example.com",
      "password": "$2a$10$..."
  }
  ```

#### Get All Users
- **Method**: `GET`
- **URL**: `http://localhost:8080/api/users`
- **Headers**: `Authorization: Bearer <token>`
- **Response** (200 OK):
  ```json
  [
      {
          "id": 1,
          "username": "testuser",
          "email": "test@example.com",
          "password": "$2a$10$..."
      },
      {
          "id": 2,
          "username": "newuser",
          "email": "newuser@example.com",
          "password": "$2a$10$..."
      }
  ]
  ```

## Testing with Postman
1. **Register**: Send a `POST` request to `/api/auth/register` to create a user and get a token.
2. **Login**: Send a `POST` request to `/api/auth/login` with the same credentials to retrieve a JWT token.
3. **Set Token**: In Postman, go to the "Authorization" tab, select "Bearer Token," and paste the token.
4. **Test Endpoints**: Send requests to `/api/users` or `/api/portfolios` with the token in the `Authorization` header.

## H2 Database
- **Access**: `http://localhost:8080/h2-console`
- **JDBC URL**: `jdbc:h2:mem:portfolioapi`
- **Username**: `sa`
- **Password**: (blank)
- **Note**: Data is in-memory and resets on application restart.

## Swagger UI
- **URL**: `http://localhost:8080/swagger-ui.html`
- **API Docs**: `http://localhost:8080/v3/api-docs`
- Use the "Authorize" button to input `Bearer <token>` for testing protected endpoints.

## Security
- **JWT**: Tokens expire after 1 hour (configurable in `JwtUtil`).
- **Protected Endpoints**: Require `Authorization: Bearer <token>` header.
- **Public Endpoints**: `/api/auth/register`, `/api/auth/login`, `/h2-console/**`, `/swagger-ui/**`, `/v3/api-docs/**`.

## Project Structure
```
PortfolioApi/
├── src/
│   ├── main/
│   │   ├── java/com/portfolioapi/
│   │   │   ├── config/          # OpenApiConfig
│   │   │   ├── controller/      # REST controllers
│   │   │   ├── dto/            # Data transfer objects
│   │   │   ├── model/          # JPA entities
│   │   │   ├── repository/     # JPA repositories
│   │   │   ├── security/       # JWT and security config
│   │   │   ├── service/        # Service interfaces
│   │   │   └── service/impl/   # Service implementations
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## Note
This README is Ai generated.
