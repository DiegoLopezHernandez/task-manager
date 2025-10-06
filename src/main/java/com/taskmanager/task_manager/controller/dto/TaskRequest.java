package com.taskmanager.task_manager.controller.dto;


import com.taskmanager.task_manager.model.Task.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TaskRequest {
    
    @NotBlank(message = "El título es obligatorio")
    private String title;
    
    private String description;
    
    private TaskStatus status;
    
    @NotNull(message = "La fecha de vencimiento es obligatoria")
    private LocalDateTime dueDate;
    
    private Integer priority;
}