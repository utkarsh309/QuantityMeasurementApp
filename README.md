# Quantity Measurement Application
Trainee – Utkarsh

## 🚀 Deployment Links
- **Frontend Deployed URL:** http://quanment-frontend.s3-website.ap-south-1.amazonaws.com
- **Backend Deployed URL:**  http://13.201.88.166:8761/
- **Swagger URL:** http://13.201.88.166:8080/swagger-ui/index.html

---

## 📌 Project Overview

A scalable **Quantity Measurement System** built on a **microservices architecture** using Spring Boot, designed around Object-Oriented Programming principles.

The core domain logic centers on a generic `Quantity<U extends IMeasurable>` class that models any measurable value and its unit. Measurement categories — **Length**, **Weight**, **Volume**, and **Temperature** — are implemented as enums that each satisfy the `IMeasurable` interface, enabling type-safe unit conversion and arithmetic (compare, convert, add, subtract, divide) across all categories.

The system is exposed as REST APIs through a **QMA Service** and secured end-to-end via an **Auth Service** that supports both **JWT-based login/registration** and **Google OAuth2** social login. An **API Gateway** sits in front of all services, handling JWT validation and routing. Service discovery and inter-service registration is managed by a **Eureka Server**. All operations (compare, convert, add, subtract, divide) are persisted to a **MySQL database** via Spring Data JPA and are scoped per authenticated user, enabling operation history retrieval. The APIs are fully documented via **Swagger / SpringDoc OpenAPI**.

---

## 🏗 Architecture Hierarchy

```
Client (Frontend / Swagger UI)
        │
        ▼
┌─────────────────────────────┐
│        API Gateway          │  Port: 8090
│  • JWT Validation Filter    │
│  • Route: /auth/** → Auth   │
│  • Route: /api/**  → QMA   │
│  • CORS Configuration       │
└────────────┬────────────────┘
             │
     ┌───────┴────────┐
     ▼                ▼
┌──────────────┐  ┌───────────────────────────────┐
│ Auth Service │  │         QMA Service            │
│  Port: 8081  │  │         Port: 8080             │
│              │  │                                │
│ Controllers: │  │ Controller:                    │
│  /auth/login │  │  POST /api/v1/quantities/      │
│  /auth/reg.. │  │    compare | convert           │
│  /auth/oauth │  │    add | subtract | divide     │
│  -success    │  │  GET  /api/v1/quantities/      │
│              │  │    history/{operation}         │
│ JWT (JJWT)   │  │                                │
│ BCrypt       │  │ Core OOP Layer:                │
│ OAuth2/Google│  │  IMeasurable (interface)       │
│ Spring Sec.  │  │  SupportsArithmetic (func.if.) │
│              │  │  Quantity<U> (generic class)   │
│ JPA Entities:│  │  LengthUnit / WeightUnit /     │
│  UserEntity  │  │  VolumeUnit / TemperatureUnit  │
└──────┬───────┘  └──────────────┬────────────────┘
       │                         │
       └────────────┬────────────┘
                    ▼
         ┌──────────────────┐
         │   MySQL Database │
         │  db: utkarsh     │
         │  • users         │
         │  • qty_measures  │
         └──────────────────┘

All services register with:
┌──────────────────────┐
│    Eureka Server     │  Port: 8761
│  Service Discovery   │
└──────────────────────┘
```

### Microservices Breakdown

| Service | Port | Responsibility |
|---|---|---|
| `eureka-server` | 8761 | Service registry & discovery |
| `api-gateway` | 8090 | JWT auth filter, routing, CORS |
| `auth-service` | 8081 | Register, login, Google OAuth2, JWT generation |
| `qma-service` | 8080 | Unit operations (compare/convert/add/subtract/divide), history, Swagger |

### Core OOP Design (inside `qma-service`)

```
IMeasurable (interface)
├── getConversionFactor()
├── convertToBaseUnit(value)
├── convertFromBaseUnit(baseValue)
├── getUnitName()
└── validateOperationSupport(operation)
        ▲
        │ implements
        ├── LengthUnit      (FEET, INCH, YARD, CENTIMETER, METER, KILOMETER, ...)
        ├── WeightUnit      (GRAM, KILOGRAM, POUND, OUNCE, ...)
        ├── VolumeUnit      (LITRE, MILLILITRE, GALLON, ...)
        └── TemperatureUnit (CELSIUS, FAHRENHEIT, KELVIN)
                             (overrides convertToBaseUnit / convertFromBaseUnit
                              for formula-based conversion)

Quantity<U extends IMeasurable>
├── convertTo(U targetUnit)
├── add(Quantity<U> other)
├── subtract(Quantity<U> other)
├── divide(Quantity<U> other)
└── equals()  ← comparison via base-unit normalization
```

---

## 📈 Use Case Evolution (UC1 → UC18)

### 🟢 UC1 – Basic Equality
- Implemented equality comparison for **Feet**
- Overrode `equals()` method
- Ensured null and type safety

### 🟢 UC2 – Multiple Unit Support
- Added **Inches** class
- Separate equality logic for each unit
- Highlighted code duplication issue

### 🟢 UC3 – Generic Design (DRY Principle Applied)
- Introduced `LengthUnit` enum
- Created generic `Length` class
- Enabled cross-unit equality (e.g., 1 ft == 12 inches)

### 🟢 UC4 – Extended Unit Support
- Added **Yards** and **Centimeters**
- No modification required in core logic
- Demonstrated Open-Closed Principle

### 🟢 UC5 – Unit-to-Unit Conversion API
- Added static `convert()` method
- Implemented instance `convertTo()` method
- Added method overloading
- Ensured `equals()` and `hashCode()` consistency
- Implemented rounding to 2 decimal places

### 🟢 UC6 – Addition of Two Length Units  
- Implemented addition of two Length objects
- Used base unit (inches) for internal calculation
- Converted both operands to base unit before addition
- Converted result back to first operand’s unit
- Maintained immutability (returned new object)

### 🟢 UC7 – Addition with Explicit Target Unit
- Added support for specifying target unit in addition
- Implemented add(length1, length2, targetUnit)
- Converted result into desired unit
- Improved flexibility for arithmetic operations

### 🟢 UC8 – Refactoring Unit Enum
- Extracted LengthUnit into standalone enum
- Moved conversion logic from class to enum
- Applied Single Responsibility Principle
- Improved separation of concerns

### 🟢 UC9 – Weight Measurement Support
- Added new category: Weight
- Introduced WeightUnit enum (kg, g, lb)
- Implemented equality, conversion, and addition
- Ensured no comparison between different categories

### 🟢UC10 – Generic Quantity with Unit Interface
- Introduced IMeasurable interface
- Created generic class Quantity<U extends IMeasurable>
- Replaced separate classes with single generic solution
- Ensured type safety across categories
- Applied DRY principle

### 🟢 UC11 – Volume Measurement
- Added new category: Volume
- Introduced VolumeUnit enum (L, mL, gallon)
- Supported equality, conversion, and addition
- No changes required in generic logic

### 🟢 UC12 – Subtraction and Division
- Added subtraction and division operations
- Supported cross-unit arithmetic within same category
- Maintained immutability and consistency

### 🟢 UC13 – Centralized Arithmetic Logic
- Refactored arithmetic logic into common method
- Removed duplicate code from operations
- Improved maintainability and readability

### 🟢 UC14 – Temperature Measurement
- Added new category: Temperature
- Introduced units (Celsius, Fahrenheit, Kelvin)
- Supported only conversion and comparison
- Restricted invalid arithmetic operations

### 🟢 UC15 – N-Tier Architecture Refactoring
- Refactored into layered architecture
- Introduced Controller, Service, Repository layers
- Applied SOLID principles
- Improved scalability and separation of concerns

### 🟢 UC16 – Database Integration (JDBC)
- Integrated database using JDBC
- Replaced in-memory storage
- Implemented CRUD operations
- Enabled data persistence

### 🟢 UC17 – Spring Boot Integration
- Converted project into Spring Boot application
- Exposed REST APIs (GET, POST, PUT, DELETE)
- Integrated Spring Data JPA

### 🟢 UC18 – Spring Security with OAuth2 & JWT
- Implemented authentication and authorization
- Integrated Spring Security framework
- Added JWT-based token authentication
- Implemented OAuth2 login support
- Secured REST APIs with role-based access

### 🟢 UC21 – QMA Microservice Architecture

- Decomposed the monolithic QMA application into independently deployable microservices (eureka-server, api-gateway, auth-service, qma-service)
- Configured Eureka Server for service discovery and registration across all microservices
- Implemented API Gateway with JWT validation filter, dynamic routing (/auth/**, /api/**), and global CORS configuration
- Containerized all services using Dockerfiles and connected them to a shared MySQL database with JPA-managed schemas
