# Login Form Backend
A simple Java-based login and signup system with MySQL database integration.

## Features
- **Signup**: Create a new user account with username, gmail, and password
- **Login**: Authenticate users with gmail and password
- **Database**: Uses MySQL to store and retrieve user credentials

## Prerequisites

- Java 8+
- MySQL Server
- Maven

## Setup Instructions

### 1. Database Setup

Create a MySQL database named `world`:

```sql
CREATE DATABASE world;
USE world;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    gmail VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL
);
```

### 2. Configure Database Connection

Update the database credentials in `DatabaseLogin.java`:

```java
String url = "jdbc:mysql://localhost:3306/world";
String user = "adi";  // Replace with your MySQL username
String password = "";  // Add your MySQL password if needed
```

### 3. Build & Run

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="com.login.DatabaseLogin"
```

## Usage
1. Run the application
2. Press **1** for Signup or **2** for Login
3. Enter required credentials
4. The system will validate and store/retrieve data from MySQL

## Project Structure
```
login/
├── src/main/java/com/login/
│   └── DatabaseLogin.java
├── pom.xml
└── README.md
```

## Technologies Used
- Java
- MySQL
- JDBC
- Maven

## Author

Aditya Singh Rajput
