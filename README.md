<!-- Lab-Bot test run -->

# Prorate: Microservices Architecture Project

## Project Structure

```
prorate/
├── docker/              # Docker configuration
├── prorate-eureka-server/  # Service Discovery
├── prorate-ms-doctors/     # Doctors Microservice
├── prorate-ms-reviews/     # Reviews Microservice
├── prorate-config-server/  # Configuration Server
├── CONTRIBUTING.md       # Contribution guidelines
├── pom.xml               # Main Maven build file
└── README.md             # This file
```

## Technology Stack

### Programming Languages
- **Java 18** - Primary language
- **TypeScript** - Frontend

### Frameworks & Libraries
- **Spring Boot 2.7.2** - Microservices framework
- **Spring Cloud** - Service mesh capabilities
- **Spring Data JPA** - Data access layer
- **Hibernate** - ORM framework

### Infrastructure
- **Docker** - Containerization
- **Eureka Server** - Service discovery
- **Config Server** - Centralized configuration

### Database
- PostgreSQL (configured via properties)

### Testing
- JUnit
- Mockito
- Spring Boot Test

## Architecture Overview

```
┌─────────────────────────────────────────────────────────────────────────────┐
│                        Prorate Microservices Architecture                   │
├─────────────────────────────────────────────────────────────────────────────┤
│                                                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │                          API Gateway                                │   │
│  │  ┌─────────────┐  ┌─────────────┐  ┌─────────────┐                 │   │
│  │  │  Load       │  │  Auth       │  │  Rate       │                 │   │
│  │  │  Balancer   │  │  Gateway    │  │  Limiter    │                 │   │
│  │  └─────────────┘  └─────────────┘  └─────────────┘                 │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
│                                                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │                    Prorate Config Server                            │   │
│  │  ┌──────────────────────────────────────────────────────────────┐   │   │
│  │  │  Config Repository  │  ┌──────────────────────────────────┐  │   │   │
│  │  │  ┌─────────────────┐  │  ┌────────────────────────────┐    │   │   │
│  │  │  │  application.yml│  │  │  application-local.yml     │    │   │   │
│  │  │  │  ┌─────────────┐  │  │  ┌────────────────────────┐  │    │   │   │
│  │  │  │  │  Doctors    │  │  │  │  Reviews               │  │    │   │   │
│  │  │  │  │  Service    │  │  │  │  Service               │  │    │   │   │
│  │  │  │  │  └─────────────┘  │  │  │  └────────────────────────┘  │    │   │   │
│  │  │  │  └─────────────────┘  │  │  └────────────────────────────┘    │   │   │
│  │  │  └──────────────────────────────────────────────────────────────┘   │   │
│  │  └─────────────────────────────────────────────────────────────────────┘   │
│  └──────────────────────────────────────────────────────────────────────────────┘
│                                                                             │
│  ┌─────────────────────────────────────────────────────────────────────┐   │
│  │                    Prorate Eureka Server                            │   │
│  │  ┌──────────────────────────────────────────────────────────────┐   │   │
│  │  │  Service Registry  │  ┌──────────────────────────────────┐  │   │   │
│  │  │  ┌─────────────────┐  │  ┌────────────────────────────┐    │   │   │
│  │  │  │  Doctors        │  │  │  Reviews                   │    │   │   │
│  │  │  │  Service        │  │  │  Service                   │    │   │   │
│  │  │  │  ┌────────────┐  │  │  │  ┌──────────────────────┐  │    │   │   │
│  │  │  │  │  Instance  │  │  │  │  │  Instance            │  │    │   │   │
│  │  │  │  │  (Port 8081)│  │  │  │  │  (Port 8082)        │  │    │   │   │
│  │  │  │  └────────────┘  │  │  │  └──────────────────────┘    │   │   │
│  │  │  └─────────────────┘  │  └────────────────────────────┘    │   │   │
│  │  └──────────────────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
│                                                                             │
└────────────────────────────────────────────────────────────────────────────┘
