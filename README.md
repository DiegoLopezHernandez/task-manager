📝 Task Manager Application

A complete and professional Task Management web application built with Spring Boot, featuring both REST API and modern web interface.

🚀 Quick Start • 📖 Features • 🔧 API • 🏗️ Architecture

✨ Features
Feature	Description
🎯 Task Management	Full CRUD operations with intuitive interface
📊 Smart Organization	Priority levels & status tracking
🔍 Advanced Search	Find tasks by keyword in titles/descriptions
⏰ Due Date Tracking	Never miss a deadline with date management
📱 Dual Interface	Web UI + REST API for maximum flexibility
🎨 Modern Design	Responsive Bootstrap 5 interface
🛠️ Tech Stack

Backend:

    🍃 Spring Boot 3.x

    🗄️ Spring Data JPA

    🌐 Spring Web MVC

    ✅ Bean Validation

Frontend:

    🎨 Bootstrap 5.3

    📄 Thymeleaf Templates

    📱 Responsive Design

Database & Tools:

    💾 H2 Database (in-memory)

    🔄 ModelMapper

    🛠️ Maven

🚀 Quick Start
Prerequisites

    Java 17 or higher

    Maven 3.6 or higher

Installation & Run
bash

# 1. Clone the repository
git clone https://github.com/DiegoLopezHernandez/task-manager.git

# 2. Navigate to project directory
cd task-manager

# 3. Run the application
mvn spring-boot:run

Access Points
Service	URL	Credentials
🌐 Web Application	http://localhost:8080	-
🔗 REST API	http://localhost:8080/api/tasks	-
🗄️ H2 Console	http://localhost:8080/h2-console	URL: jdbc:h2:mem:taskdb
User: sa
Pass: (empty)
📖 API Documentation
🔑 Core Endpoints
Method	Endpoint	Description	Example
GET	/api/tasks	Get all tasks	GET /api/tasks
GET	/api/tasks/{id}	Get task by ID	GET /api/tasks/1
POST	/api/tasks	Create new task	See example
PUT	/api/tasks/{id}	Update task	PUT /api/tasks/1
DELETE	/api/tasks/{id}	Delete task	DELETE /api/tasks/1
🎯 Filtering & Search
Method	Endpoint	Description
GET	/api/tasks/status/{status}	Filter by status
GET	/api/tasks/priority/{priority}	Filter by priority
GET	/api/tasks/search?keyword=term	Search tasks
PATCH	/api/tasks/{id}/complete	Mark as completed
GET	/api/tasks/overdue	Get overdue tasks
📝 Request Example
json

{
  "title": "Complete project documentation",
  "description": "Write comprehensive documentation for the Task Manager app",
  "dueDate": "2024-12-31T23:59:59",
  "priority": 1,
  "status": "IN_PROGRESS"
}

🏗️ Architecture
text

task-manager/
├── 📁 src/main/java/com/taskmanager/task_manager/
│   ├── ⚙️ config/           # Configuration classes
│   ├── 🎮 controller/       # Web & REST controllers
│   │   └── 📋 dto/         # Data Transfer Objects
│   ├── 🚨 exception/       # Custom exceptions
│   ├── 🏗️ model/           # Entity classes
│   ├── 💾 repository/      # Data access layer
│   └── ⚡ service/         # Business logic layer
│       └── 🔧 impl/        # Service implementations
├── 📁 src/main/resources/
│   ├── 🎨 templates/       # Thymeleaf templates
│   └── ⚙️ application.properties
└── 📄 pom.xml

🎨 Web Interface
✨ Key Features

    📱 Fully Responsive - Works perfectly on all devices

    🎯 Visual Priority System - Color-coded priorities (Red/High, Yellow/Medium, Green/Low)

    📊 Real-time Statistics - Dashboard with task counts and status overview

    ⚡ Instant Updates - Automatic page refreshes after actions

    🎪 Intuitive Design - Clean, modern interface with smooth interactions

🖼️ Interface Preview

The web interface provides a clean, modern experience for managing your tasks efficiently.
🔧 Development
Building the Project
bash

# Compile and package
mvn clean package

# Run tests
mvn test

# Run with custom profile
mvn spring-boot:run -Dspring-boot.run.profiles=dev

Configuration

Key settings in application.properties:
properties

# Server Configuration
server.port=8080

# Database
spring.datasource.url=jdbc:h2:mem:taskdb
spring.h2.console.enabled=true

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# Templates
spring.thymeleaf.cache=false

🐛 Troubleshooting
Issue	Solution
Port 8080 in use	Change server.port in application.properties
Java version error	Ensure Java 17+ is installed and configured
Database connection	Verify H2 console URL and empty password
Build failures	Check Maven and Java installation
🤝 Contributing

We love contributions! Here's how you can help:

    Fork the repository

    Create a feature branch (git checkout -b feature/amazing-feature)

    Commit your changes (git commit -m 'Add amazing feature')

    Push to the branch (git push origin feature/amazing-feature)

    Open a Pull Request

📄 License

This project is licensed under the MIT License - see the LICENSE file for details.
👨‍💻 Author

Diego López Hernández

