# ☕ Java Fundamentals Portfolio
### Continuous Engineering & Structural Logic Portfolio
🔗 [GitHub Profile Profile](https://github.com) | ✉️ sunshinedaily2288@gmail.com

---

# 🚀 Java Subsystem Monorepo Portfolio

Welcome to my centralized engineering sandbox. This repository showcases production-ready backend development architectures, covering multiple build systems, security frameworks, multi-threaded pipelines, and relational database integrations.

## 📡 Architecture Blueprint & Port Allocations
Every microservice inside this monorepo is fully isolated and operates on a dedicated local network port to simulate real-world service boundaries. Complete port routing details can be found in our central [PORTS.md](./PORTS.md) tracking sheet.

## 🛠️ Main Portfolio Highlights

### 1. 📊 Relational Order Analytics Subsystem (`order_analytics_sql`)
* **Build System**: Maven (Spring Boot 3.3, Data JPA)
* **Core Concepts**: Object-Relational Mapping (ORM), Embedded SQL persistence, Data-tier abstraction, Custom business metric computations.
* **UI Gateway**: Built-in analytics dashboard running on **Port 8088**.

### 2. 🔐 Stateless JWT Identity Token Broker (`auth_jwt_maven`)
* **Build System**: Maven (Spring Boot 3.3, JJWT API)
* **Core Concepts**: Cryptographic key management (SHA-256 HMAC), Stateless user sessions, Bearer authorization parsing, Secure claim injection.
* **UI Gateway**: Secure single sign-on form panel running on **Port 8087**.

### 3. 📣 Asynchronous Multi-Tenant Notification Hub (`notification_service_gradle`)
* **Build System**: Gradle Kotlin DSL (Spring Boot 3.3)
* **Core Concepts**: Non-blocking concurrent processing, High-throughput thread pooling (`ExecutorService`), Asynchronous producers/consumers via `BlockingQueue`.
* **UI Gateway**: Interactive live dispatch console running on **Port 8086**.

---