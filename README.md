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
└─────────────────────────────────────────────────────────────────────────────┘
```

## Getting Started

### Prerequisites

| Requirement | Version |
|-------------|---------|
| Java | 18+ |
| Maven | 3.8.6+ |
| Docker | 20.10+ |
| Docker Compose | 1.29.0+ |
| Node.js | 18+ |
| npm | 9.0.0+ |

### Installation Steps

1. Clone the repository
```bash
git clone https://github.com/abhishek-bhardwaj-lab49/prorate.git
cd prorate
```

2. Build the project
```bash
mvn clean install
```

3. Start Docker containers
```bash
docker-compose up --build
```

## Running Services

### Manual Startup Sequence
```bash
# Start Config Server
cd prorate-config-server
mvn spring-boot:run

# Start Eureka Server
cd ../prorate-eureka-server
mvn spring-boot:run

# Start Doctors Microservice
cd ../prorate-ms-doctors
mvn spring-boot:run

# Start Reviews Microservice
cd ../prorate-ms-reviews
mvn spring-boot:run
```

## Configuration Management

### Config Server Setup
```yaml
# config-server/src/main/resources/bootstrap.yml
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/abhishek-bhardwaj-lab49/prorate-config
          search-paths: classpath:/config
```

### Configuration Properties
```yaml
# Example configuration
Doctors:
  base-fee: 200
  premium-multiplier: 2.5
  discount-threshold: 5

Reviews:
  min-rating-for-discount: 4
  discount-percentage: 10

Eureka:
  instance:
    hostname: localhost
  client:
    service-url:
      defaultZone: http://localhost:8761/eureka/
```

## Deployment

### Docker Deployment
```bash
# Build individual services
docker build -t prorate-config-server -f Dockerfile -t prorate/config-server ./prorate-config-server

docker build -t prorate-eureka-server -f Dockerfile -t prorate/eureka-server ./prorate-eureka-server

docker build -t prorate-doctors -f Dockerfile -t prorate/doctors ./prorate-ms-doctors

docker build -t prorate-reviews -f Dockerfile -t prorate/reviews ./prorate-ms-reviews
```

### Kubernetes Deployment
```bash
kubectl apply -f kubernetes/deployment.yaml
kubectl apply -f kubernetes/service.yaml
kubectl apply -f kubernetes/configmap.yaml
```

## Contribution Guidelines

Please refer to [CONTRIBUTING.md](CONTRIBUTING.md) for detailed contribution guidelines, including:
- Code style and formatting
- Testing requirements
- Pull request process
- Code review guidelines

## License

This project is licensed under the MIT License - see [LICENSE](LICENSE) for details.