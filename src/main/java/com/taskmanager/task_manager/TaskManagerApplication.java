package com.taskmanager.task_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Task Manager Spring Boot application.
 * This class serves as the entry point for the application and enables
 * Spring Boot auto-configuration and component scanning.
 */
@SpringBootApplication
public class TaskManagerApplication {

    /**
     * Main method that serves as the application entry point.
     * Launches the Spring Boot application and starts the embedded server.
     *
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        SpringApplication.run(TaskManagerApplication.class, args);
    }
}