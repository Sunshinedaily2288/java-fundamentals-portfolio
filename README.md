# ☕ Java Fundamentals Portfolio
### Continuous Engineering & Structural Logic Portfolio
🔗 [GitHub Profile Profile](https://github.com) | ✉️ sunshinedaily2288@gmail.com

---

## 🚀 # Java Engineering & Infrastructure Fundamentals Portfolio

A multi-module corporate repository engineered using Java 17 and Apache Maven, demonstrating production-ready backend 
workflows, multi-system enterprise integrations, automated test validation pipelines, and architectural security 
patterns.

| Core Foundational Track (Pure Java) | Upgraded Professional Module | Framework / Stack | Build System | Database / Engine |
| :--- | :--- | :--- | :--- | :--- |
| `enterprise.logistics` (Banking Console) | 🏦 **banking_ledger_maven** | Spring Boot 3.x | 🟦 **Maven (XML)** | Local File H2 SQL |
| `algorithms.microservices` (Snake Logic) | 🎮 **snake_game_maven** | Spring Boot 3.x | 🟦 **Maven (XML)** | Local File H2 SQL |
| `algorithms.microservices` (Blog Core) | 🌐 **blog_api_maven** | Spring Boot 3.x | 🟦 **Maven (XML)** | Local File H2 SQL |
| `enterprise.logistics` (Data Mocking) | 📚 **online_library_gradle** | Spring Boot 3.x | 🐘 **Gradle (Kotlin)** | In-Memory RAM H2 |
| `enterprise.logistics` (Inventory GUI) | 🚀 **inventory_sql_maven** | Spring Boot 3.x | 🟦 **Maven (XML)** | Local File H2 + Datafaker |
| `gui.security` (Swing Form & Math) | 📦 **ecommerce-catalog** | Spring Boot 3.x | 🟦 **Maven (XML)** | Thread-Safe Concurrent Maps |
| `gui.security` (Password Match Node) | 🛡️ **security_crypto_maven** | Spring Boot 3.x | 🟦 **Maven (XML)** | Standalone Cryptographic Subsystem |

---

## 📦 Active System Architecture Matrix

### 1. Enterprise E-Commerce Catalog Service (`ecommerce-catalog`)
A robust web service designed around standard layered architectural patterns (Controller-Service-Repository) managing inventory assets.
- **Defensive API Rate Limiting**: Employs a thread-safe sliding window algorithm utilizing `ConcurrentHashMap` to monitor network access metrics. Brute-force scrapers are intercepted and blocked via a custom `RateLimitExceededException` throwing an `HTTP 429 Too Many Requests` state.
- **SAP Multi-Currency Converter Engine**: Integrates a decoupled financial service tier that maps data arrays on the fly to process live foreign exchange recalculations (USD, EUR, GBP) using Java Stream pipelines.
- **Dynamic Frontend Dashboard**: A clean web application frontend operating on Port `8085` that processes asynchronous AJAX requests to execute high-speed keyword search matches across data structures.

### 2. Security Access Management Gateway (`security_crypto_maven`)
A standalone cybersecurity utility module focusing on cryptographic storage protection and data masking.
- **SHA-256 Cryptographic Subsystem**: Utilizes Java's native `MessageDigest` arrays to permanently transform plain-text authentication tokens into irreversible 64-character hexadecimal security strings.
- **Randomized Salt Injection**: Leverages cryptographically strong `SecureRandom` number generators to create unique Base64-encoded salt components for every simulation, preventing rainbow-table decoding vectors.
- **High-Contrast Corporate Interface**: Exposes a responsive, light/dark hybrid dashboard operating on Port `8086` designed to process real-time credential security verifications and simulate login attack diagnostics.

---

## 🧪 Quality Assurance & Automation Pipelines

Every module features an independent automated testing tier to enforce strict business rules and input validation constraints before application runtime compilation.

- **System Quality Gate**: Run automated verification tests globally across entire codebase with native Maven lifecycles:
  ```bash
  mvn clean test
  ```
- **Isolated Module Validation**: Target your cryptographic subsystem assertions specifically by executing a focused pl-project test run:
  ```bash
  mvn test -pl security_crypto_maven
  ```

---

## 📋 Architectural CV Skills Matrix Correlation
This repository acts as structural evidence for the following technical engineering competencies:
- **Backend Architecture**: Layered Microservice Design, Component Scanning, Request Interception, Spring Boot Core Framework.
- **Security & Infrastructure**: Cryptographic Hashing Algorithms, Secure Token Generation, Traffic Throttling, Dependency Lifecycle Management.
- **Concurrent System Design**: Managing state tracking inside thread-safe memory maps (`ConcurrentHashMap`) to support high-performance enterprise workloads.

