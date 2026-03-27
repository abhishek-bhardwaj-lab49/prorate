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
│  │  │  │  └─────────────┘  │  │  └────────────────────────┘  │    │   │   │
│  │  │  └─────────────────┘  │  └────────────────────────────┘    │   │   │
│  │  └──────────────────────────────────────────────────────────────┘   │   │
│  └─────────────────────────────────────────────────────────────────────┘   │
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

### Installation

```sh
# Clone the repository
git clone https://github.com/abhishek-bhardwaj-lab49/prorate.git
cd prorate

# Build the project
mvn clean install

cd frontend
npm install
npm run build
```

### Configuration

Create or modify the environment configuration file:

```sh
cp .env.example .env
nano .env
```

### Running the Project

#### Option 1: Using Docker (Recommended)

```sh
docker-compose up --build
```

#### Option 2: Manual Setup

```sh
cd prorate-eureka-server
mvn spring-boot:run &

cd ../prorate-config-server
mvn spring-boot:run &

cd ../prorate-ms-doctors
mvn spring-boot:run &

cd ../prorate-ms-reviews
mvn spring-boot:run &
```

### Accessing the Applications

| Service | URL | Description |
|---------|-----|-------------|
| Eureka Server | [http://localhost:9000](http://localhost:9000) | Service Registry |
| Config Server | [http://localhost:8888](http://localhost:8888) | Configuration Management |
| Doctors Service | [http://localhost:8081](http://localhost:8081) | Doctor Management |
| Reviews Service | [http://localhost:8082](http://localhost:8082) | Review Management |

## Key API Endpoints

### Doctors Service (Port 8081)

```
GET  /api/doctors          - List all doctors
GET  /api/doctors/{id}     - Get doctor by ID
POST  /api/doctors         - Create new doctor
PUT  /api/doctors/{id}     - Update doctor
DELETE /api/doctors/{id}   - Delete doctor
```

### Reviews Service (Port 8082)

```
GET  /api/reviews          - List all reviews
GET  /api/reviews/{id}     - Get review by ID
POST  /api/reviews         - Create new review
PUT  /api/reviews/{id}     - Update review
DELETE /api/reviews/{id}   - Delete review
```

## Testing the Architecture

```sh
# Run all tests
npm test

# Run specific test suite
cd prorate-ms-doctors
mvn test -Dtest=DoctorServiceTests

# Check test coverage
npm run coverage
```

## Project Documentation

| Document | Location |
|----------|----------|
| API Docs | [http://localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html) |
| Architecture Diagram | [docs/architecture.md](docs/architecture.md) |
| Deployment Guide | [docs/deployment.md](docs/deployment.md) |
| Security Guide | [docs/security.md](docs/security.md) |

## Contribution Guidelines

### Branching Convention

```
feature/short-description
fix/issue-slug
hotfix/critical-issue
```

### Commit Message Format

```
type(scope): short description

body

footer
```

### Pull Request Process
1. Create a branch from `main`
2. Commit with meaningful messages
3. Push changes to your branch
4. Create a PR with clear description
5. Wait for review and testing
6. Address feedback
7. Merge once approved

### Code Quality Standards

| Metric | Threshold |
|--------|-----------|
| Code Coverage | ≥80% |
| Code Climate Score | B+ or higher |
| Checkstyle Errors | 0 |
| Security Vulnerabilities | 0 |

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

**Documentation Status**: alpha
**Release Status**: development