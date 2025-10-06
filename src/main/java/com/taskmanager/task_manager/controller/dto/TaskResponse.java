package com.taskmanager.task_manager.controller.dto;

import com.taskmanager.task_manager.model.Task.TaskStatus;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskResponse {
    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime dueDate;
    private Integer priority;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}