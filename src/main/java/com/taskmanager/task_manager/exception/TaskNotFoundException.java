package com.taskmanager.task_manager.exception;

/**
 * Custom exception thrown when a requested task is not found in the system.
 * This exception is used to provide more specific error handling for task-related operations.
 */
public class TaskNotFoundException extends RuntimeException {
    
    /**
     * Constructs a new TaskNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public TaskNotFoundException(String message) {
        super(message);
    }
}