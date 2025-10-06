package com.taskmanager.task_manager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Entity class representing a Task in the system.
 * Maps to the 'tasks' table in the database and contains task-related data.
 */
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    
    /**
     * Unique identifier for the task.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * Title of the task. This field is mandatory.
     */
    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    private String title;
    
    /**
     * Detailed description of the task. Optional field.
     */
    private String description;
    
    /**
     * Current status of the task.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;
    
    /**
     * Due date and time for task completion. This field is mandatory.
     */
    @NotNull(message = "Due date is required")
    @Column(nullable = false)
    private LocalDateTime dueDate;
    
    /**
     * Priority level of the task (1=High, 2=Medium, 3=Low).
     */
    private Integer priority;
    
    /**
     * Timestamp when the task was created.
     */
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    /**
     * Timestamp when the task was last updated.
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    /**
     * JPA lifecycle callback that executes before persisting a new entity.
     * Sets creation timestamp, update timestamp, and default status.
     */
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        if (status == null) {
            status = TaskStatus.PENDING;
        }
    }
    
    /**
     * JPA lifecycle callback that executes before updating an entity.
     * Updates the modification timestamp.
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
    
    /**
     * Enumeration representing possible states of a task.
     */
    public enum TaskStatus {
        /** Task is waiting to be started */
        PENDING,
        /** Task is currently being worked on */
        IN_PROGRESS,
        /** Task has been completed */
        COMPLETED,
        /** Task has been cancelled */
        CANCELLED
    }
}