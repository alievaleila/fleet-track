# 🚚 FleetTrack — Vehicle Fleet Management System

FleetTrack is a robust, enterprise-grade monolithic backend platform built with **Java Spring Boot**. It is designed to help organizations efficiently manage their vehicle fleets, track real-time locations, automate maintenance cycles, and optimize resource allocation through high-performance caching and streaming technologies.

---

## 🎯 Key Learning Objectives & Core Concepts

*   **Database Migration & Evolution:** Mastered version-controlled schema updates using **Flyway**.
*   **Real-time Data Streaming:** Implemented live bidirectional communication channels via **WebSockets**.
*   **Stateless Security Architecture:** Secured REST APIs using **JWT Authentication** with strict Role-Based Access Control (RBAC).
*   **Performance Optimization:** Integrated **Redis** for efficient caching strategies and low-latency read operations.
*   **Asynchronous Processing:** Automated background tasks and system alerts using **Spring Scheduler**.

---

## ✨ Features

### 🔐 Security & Access Control
*   Fully stateless **JWT Authentication** filter chain.
*   Role-Based Access Control (RBAC) supporting distinct operations for `ADMIN` and `FLEET_MANAGER`.
*   Global exception handling and robust request data validation.

### 🚘 Fleet & Driver Administration
*   Comprehensive CRUD endpoints for vehicle and driver management.
*   **Dynamic Data Filtering & Sorting:** Advanced API querying with dynamic criteria matching and full pagination support.
*   **MapStruct Integration:** Ultra-fast, compile-time DTO-to-Entity mapping.

### ⚡ Real-Time Tracking & Notifications
*   **Live GPS Streaming:** Dedicated WebSocket endpoints broadcasting live vehicle coordinates to monitoring dashboards.
*   **Redis Pub/Sub:** Event-driven notification ecosystem alerting fleet managers instantly (e.g., vehicle offline alerts).
*   **Automated Maintenance Reminders:** Background cron jobs using Spring Scheduler to trigger maintenance alerts based on historical logs.

### 💾 Performance & Storage Layer
*   Normalized relational schema designed in **PostgreSQL** ensuring strong transactional integrity.
*   **Redis Caching Layer:** Drastically reduced DB load by caching frequently requested vehicle and driver summaries with automated TTL eviction policies.

---

## 🛠️ Tech Stack & Tooling

| Technology | Purpose |
| :--- | :--- |
| **Java 17 / Spring Boot 3.x** | Core Framework & Application Context |
| **PostgreSQL** | Primary Relational Database Store |
| **Redis** | In-Memory Cache & Pub/Sub Event Messaging |
| **Flyway** | Database Schema Migration & Versioning |
| **Spring Security & JWT** | Authentication & Authorization Layer |
| **Spring WebSocket** | Bidirectional Live Location Data Streaming |
| **MapStruct** | High-performance Compile-time Object Mapping |
| **Spring Scheduler** | Automated Background Tasks & Cron Jobs |
| **Swagger / OpenAPI 3** | Interactive REST API Documentation |

---

## 🚀 Getting Started

### Prerequisites
Ensure you have the following installed:
*   Java 17 LTS or higher
*   Maven 3.x
*   Docker & Docker Compose (Recommended for running Postgres & Redis)

### Installation & Setup

1. **Clone the repository:**
```bash
   git clone [https://github.com/yourusername/FleetTrack.git](https://github.com/yourusername/FleetTrack.git)
   cd FleetTrack
