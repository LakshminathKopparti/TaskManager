# Task Manager Backend

A Spring Boot-based Task Management System with JWT Authentication and OAuth2 login support.

##  Features

- **User Management**
  - Registration and Login
  - JWT Authentication
  - OAuth2 Login (Google)
  - Password Encryption

- **Project Management**
  - Create Personal/Collaborative Projects
  - Assign Team Members
  - Track Project Status

- **Team Management**
  - Create Teams
  - Add/Remove Members
  - Team-based Project Access

- **Task Management**
  - Create, Update, Delete Tasks
  - Assign Tasks to Users
  - Track Task Status
  - Task Comments

## 🛠️ Technologies

- Java 21
- Spring Boot 3.x
- Spring Security
- JWT Authentication
- OAuth2
- PostgreSQL
- Maven

## 📋 Prerequisites

- Java 21+
- Maven
- PostgreSQL
- IDE (IntelliJ IDEA recommended)

## ⚙️ Setup & Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/LakshminathKopparti/TaskManager.git
   ```

2. **Configure PostgreSQL**
   - Create a database named 'taskmanager'
   - Update application.properties with your database credentials

3. **Configure OAuth2 (Optional)**
   - Set up Google OAuth2 credentials
   - Update application.properties with OAuth2 settings

4. **Build the project**
   ```bash
   mvn clean install
   ```

5. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

## 🔒 Security

- JWT-based authentication for API endpoints
- Password encryption using BCrypt
- OAuth2 support for Google login

## 👥 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## 📝 License

This project is done for learning purpose