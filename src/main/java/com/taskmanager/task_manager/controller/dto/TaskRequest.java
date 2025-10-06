package com.taskmanager.task_manager.controller.dto;

import com.taskmanager.task_manager.model.Task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for receiving task data in API requests.
 * Used for creating and updating tasks with validation constraints.
 */
@Data
public class TaskRequest {
    
    /**
     * Title of the task. This field is mandatory.
     */
    @NotBlank(message = "Title is required")
    private String title;
    
    /**
     * Detailed description of the task. Optional field.
     */
    private String description;
    
    /**
     * Current status of the task. If not provided, defaults to PENDING.
     */
    private TaskStatus status;
    
    /**
     * Due date and time for task completion. This field is mandatory.
     */
    @NotNull(message = "Due date is required")
    private LocalDateTime dueDate;
    
    /**
     * Priority level of the task (1=High, 2=Medium, 3=Low).
     */
    private Integer priority;
}