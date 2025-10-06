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

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    
    private final TaskRepository taskRepository;

      private static final String TASK_NOT_FOUND_MESSAGE = "Task not found with id: ";
    
    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    @Override
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(TASK_NOT_FOUND_MESSAGE + id));
        return convertToResponse(task);
    }
    
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
    
    @Override
    public TaskResponse updateTask(Long id, TaskRequest taskRequest) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(TASK_NOT_FOUND_MESSAGE + id));
        
        existingTask.setTitle(taskRequest.getTitle());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setStatus(taskRequest.getStatus());
        existingTask.setDueDate(taskRequest.getDueDate());
        existingTask.setPriority(taskRequest.getPriority());
        
        Task updatedTask = taskRepository.save(existingTask);
        return convertToResponse(updatedTask);
    }
    
    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException(TASK_NOT_FOUND_MESSAGE + id);
        }
        taskRepository.deleteById(id);
    }
    
    @Override
    public List<TaskResponse> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status).stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    @Override
    public List<TaskResponse> getTasksByPriority(Integer priority) {
        return taskRepository.findByPriority(priority).stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    @Override
    public List<TaskResponse> searchTasks(String keyword) {
        return taskRepository.searchByKeyword(keyword).stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    @Override
    public TaskResponse markTaskAsCompleted(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(TASK_NOT_FOUND_MESSAGE + id));
        
        task.setStatus(TaskStatus.COMPLETED);
        Task updatedTask = taskRepository.save(task);
        return convertToResponse(updatedTask);
    }
    
    @Override
    public List<TaskResponse> getOverdueTasks() {
        LocalDateTime now = LocalDateTime.now();
        return taskRepository.findByDueDateBeforeAndStatusNot(now, TaskStatus.COMPLETED).stream()
                .map(this::convertToResponse)
                .toList();
    }
    
    @Override
    // Este método estaba faltando en la interfaz - lo agregamos
    public List<TaskResponse> getTasksDueBetween(String start, String end) {
        // Para simplificar, por ahora retornamos lista vacía
        // Puedes implementar esta lógica después
        return List.of();
    }
    
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