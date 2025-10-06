package com.taskmanager.task_manager.controller.dto;

import com.taskmanager.task_manager.model.Task.TaskStatus;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for returning task data in API responses.
 * Contains all task information that should be exposed to clients.
 */
@Data
public class TaskResponse {
    
    /**
     * Unique identifier of the task
     */
    private Long id;
    
    /**
     * Title of the task
     */
    private String title;
    
    /**
     * Detailed description of the task
     */
    private String description;
    
    /**
     * Current status of the task
     */
    private TaskStatus status;
    
    /**
     * Due date and time for task completion
     */
    private LocalDateTime dueDate;
    
    /**
     * Priority level of the task (1=High, 2=Medium, 3=Low)
     */
    private Integer priority;
    
    /**
     * Timestamp when the task was created
     */
    private LocalDateTime createdAt;
    
    /**
     * Timestamp when the task was last updated
     */
    private LocalDateTime updatedAt;
}