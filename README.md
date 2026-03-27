# Prorate - Microservices Architecture

## Project Structure

```
├── docker
│   ├── docker-compose.yaml
│   └── individual service Dockerfiles
├── prorate-config-server
├── prorate-eureka-server
├── prorate-ms-doctors
└── prorate-ms-reviews
```

## Technology Stack
- Java 17
- Spring Boot
- Spring Cloud
- Spring Data JPA
- PostgreSQL
- Docker
- Maven

## Getting Started
```bash
cp docker/docker-compose.yaml docker-compose.override.yaml

docker-compose up --build
```

## Services
### Doctors Service
Manages doctor data including:
- Doctor information
- Availability
- Specializations

### Reviews Service
Handles patient reviews:
- Review creation
- Rating aggregation
- Doctor reputation

### Config Server
Central configuration management

### Eureka Server
Service discovery component

## API Documentation
Access Swagger UI at: http://localhost:8080/swagger-ui/

## License
MIT License

This is a starting README - I'll continue populating details.