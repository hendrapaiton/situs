# Content Management System (CMS) SITUS

## Overview

This project is a dedicated **Content Management System (CMS)** designed to manage and maintain a **Company Profile Website**. Built with robustness and scalability in mind, it leverages the power of Java Spring Boot for the backend and PostgreSQL for reliable data storage.

## Purpose

The primary goal of this application is to provide a seamless interface for administrators to update company information, manage content, and ensure the company profile remains dynamic and up-to-date without requiring direct code modifications.

## Technology Stack

- **Backend Framework**: Java Spring Boot
- **Database**: PostgreSQL
- **Build Tool**: Maven

## Key Features

- **Content Management**: Easy-to-use tools for updating text, images, and other media.
- **Scalable Architecture**: Designed to grow with the company's needs.
- **Secure**: Implements industry-standard security practices provided by the Spring ecosystem.

## Getting Started

1. Clone the repository.
2. Start the PostgreSQL database using either Docker/Podman commands or helper scripts:

   Using Docker:
   ```bash
   docker compose -f container/compose.yml up -d
   ```

   Using Podman:
   ```bash
   podman-compose -f container/compose.yml up -d
   ```

   Or using helper scripts (these automatically change to the container directory):

   On Linux/macOS:
   ```bash
   ./start-db.sh
   ```

   On Windows:
   ```cmd
   start-db.bat
   ```

   To stop the database:

   Using Docker:
   ```bash
   docker compose -f container/compose.yml down
   ```

   Using Podman:
   ```bash
   podman-compose -f container/compose.yml down
   ```

   Or using helper scripts:

   On Linux/macOS:
   ```bash
   ./stop-db.sh
   ```

   On Windows:
   ```cmd
   stop-db.bat
   ```
3. Run the application using Maven:
   ```bash
   ./mvnw spring-boot:run
   ```

   Or run with the development profile:
   ```bash
   ./mvnw spring-boot:run -Dspring.profiles.active=dev
   ```

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
