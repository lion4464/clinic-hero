# Spring Boot: JWT Auth + Roles/Permissions + Exceptions + Postgres

This starter shows:
- JWT-based stateless authentication
- Roles + fine-grained permissions using Spring Security authorities
- Custom 401/403 JSON responses
- Global exception handling (`@RestControllerAdvice`)
- Postgres + JPA user table

## Requirements
- Java 17
- Postgres running locally

## Configure DB
Create database:

```sql
CREATE DATABASE securitydemo;
```

Update `src/main/resources/application.yml` with your Postgres user/password.

## Configure JWT secret
Update:

```yml
app.jwt.secret: "..."
```

Use a long random string (at least 32 chars).

## Run

```bash
mvn spring-boot:run
```

## API
### Register
`POST /auth/register`

```json
{ "username": "admin", "email": "admin@example.com", "password": "Passw0rd!", "role": "ADMIN" }
```

### Login
`POST /auth/login`

```json
{ "usernameOrEmail": "admin", "password": "Passw0rd!" }
```

Response:

```json
{ "accessToken": "<jwt>", "tokenType": "Bearer" }
```

### Test protected endpoints
- `GET /me` (any authenticated user)
- `GET /admin/metrics` (requires `PERM_ADMIN_METRICS`)
- `POST /admin/users` (requires `PERM_USER_WRITE`)

