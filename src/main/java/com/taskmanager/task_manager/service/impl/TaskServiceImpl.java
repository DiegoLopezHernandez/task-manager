package com.taskmanager.task_manager.service.impl;

import com.taskmanager.task_manager.controller.dto.TaskRequest;
import com.taskmanager.task_manager.controller.dto.TaskResponse;
import com.taskmanager.task_manager.exception.TaskNotFoundException;
import com.taskmanager.task_manager.model.Task;
import com.taskmanager.task_manager.model.Task.TaskStatus;
import com.taskmanager.task_manager.repository.TaskRepository;
import com.taskmanager.task_manager.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation for task management operations.
 * Handles business logic and data persistence for tasks.
 */
@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;

    /**
     * Error message constant for task not found scenarios.
     */
    private static final String TASK_NOT_FOUND_MESSAGE = "Task not found with id: ";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND_MESSAGE + id));
        return convertToResponse(task);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse createTask(TaskRequest taskRequest) {
        Task task = new Task();
        task.setTitle(taskRequest.getTitle());
        task.setDescription(taskRequest.getDescription());
        task.setStatus(taskRequest.getStatus() != null ? taskRequest.getStatus() : TaskStatus.PENDING);
        task.setDueDate(taskRequest.getDueDate());
        task.setPriority(taskRequest.getPriority());
        
        Task savedTask = taskRepository.save(task);
        return convertToResponse(savedTask);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND_MESSAGE + id));
        
        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setStatus(taskRequest.getStatus());
        existingTask.setDueDate(taskRequest.getDueDate());
        existingTask.setPriority(taskRequest.getPriority());
        
        Task updatedTask = taskRepository.save(existingTask);
        return convertToResponse(updatedTask);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(TASK_NOT_FOUND_MESSAGE + id);
        }
        taskRepository.deleteById(id);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskResponse> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskResponse> getTasksByPriority(Integer priority) {
        return taskRepository.findByPriority(priority).stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskResponse> searchTasks(String keyword) {
        return taskRepository.searchByKeyword(keyword).stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public TaskResponse markTaskAsCompleted(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND_MESSAGE + id));
        
        task.setStatus(TaskStatus.COMPLETED);
        Task updatedTask = taskRepository.save(task);
        return convertToResponse(updatedTask);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public List<TaskResponse> getOverdueTasks() {
        LocalDateTime now = LocalDateTime.now();
        return taskRepository.findByDueDateBeforeAndStatusNot(now, TaskStatus.COMPLETED).stream()
                .map(this::convertToResponse)
                .toList();
    }
    /**
 * Retrieves tasks with due dates within a specified time range.
 * 
 * @param start the start date/time of the range in ISO format (yyyy-MM-ddTHH:mm:ss)
 * @param end the end date/time of the range in ISO format (yyyy-MM-ddTHH:mm:ss)
 * @return List of tasks due between the specified dates
 * @throws IllegalArgumentException if date format is invalid or start date is after end date
 */
@Override
public List<TaskResponse> getTasksDueBetween(String start, String end) {
    try {
        // Parse the date strings to LocalDateTime objects
        LocalDateTime startDateTime = LocalDateTime.parse(start);
        LocalDateTime endDateTime = LocalDateTime.parse(end);
        
        // Validate that start date is before end date
        if (startDateTime.isAfter(endDateTime)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        
        // Retrieve tasks within the date range
        return taskRepository.findByDueDateBetween(startDateTime, endDateTime).stream()
                .map(this::convertToResponse)
                .toList();
                
    } catch (Exception e) {
        throw new IllegalArgumentException("Invalid date format. Please use ISO format (e.g., 2024-12-31T23:59:59)");
    }
}
    
    /**
     * Converts a Task entity to a TaskResponse DTO.
     * 
     * @param task the Task entity to convert
     * @return TaskResponse containing all task data for API responses
     */
    private TaskResponse convertToResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setStatus(task.getStatus());
        response.setDueDate(task.getDueDate());
        response.setPriority(task.getPriority());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }
}