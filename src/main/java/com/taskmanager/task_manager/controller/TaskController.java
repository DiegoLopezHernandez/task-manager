package com.taskmanager.task_manager.controller;

import com.taskmanager.task_manager.controller.dto.TaskRequest;
import com.taskmanager.task_manager.controller.dto.TaskResponse;
import com.taskmanager.task_manager.model.Task.TaskStatus;
import com.taskmanager.task_manager.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * REST API controller for task management operations.
 * Provides endpoints for CRUD operations and task-related functionalities.
 * Cross-origin requests are allowed from any domain.
 */
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TaskController {
    
    private final TaskService taskService;
    
    /**
     * Retrieves all tasks from the system.
     *
     * @return ResponseEntity containing a list of all tasks
     */
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    /**
     * Retrieves a specific task by its ID.
     *
     * @param id the ID of the task to retrieve
     * @return ResponseEntity containing the task details
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }
    
    /**
     * Creates a new task in the system.
     *
     * @param taskRequest the task data to create
     * @return ResponseEntity containing the created task details
     */
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.createTask(taskRequest));
    }
    
    /**
     * Updates an existing task with new data.
     *
     * @param id the ID of the task to update
     * @param taskRequest the updated task data
     * @return ResponseEntity containing the updated task details
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id, 
            @Valid @RequestBody TaskRequest taskRequest) {
        return ResponseEntity.ok(taskService.updateTask(id, taskRequest));
    }
    
    /**
     * Deletes a task from the system.
     *
     * @param id the ID of the task to delete
     * @return ResponseEntity with no content (HTTP 204)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
    
    /**
     * Retrieves tasks filtered by their status.
     *
     * @param status the task status to filter by
     * @return ResponseEntity containing a list of tasks with the specified status
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<TaskResponse>> getTasksByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }
    
    /**
     * Retrieves tasks filtered by their priority level.
     *
     * @param priority the priority level to filter by (1=High, 2=Medium, 3=Low)
     * @return ResponseEntity containing a list of tasks with the specified priority
     */
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<TaskResponse>> getTasksByPriority(@PathVariable Integer priority) {
        return ResponseEntity.ok(taskService.getTasksByPriority(priority));
    }
    
    /**
     * Searches tasks by keyword in title or description.
     *
     * @param keyword the search term to look for
     * @return ResponseEntity containing a list of tasks matching the search criteria
     */
    @GetMapping("/search")
    public ResponseEntity<List<TaskResponse>> searchTasks(@RequestParam String keyword) {
        return ResponseEntity.ok(taskService.searchTasks(keyword));
    }
    
    /**
     * Marks a specific task as completed.
     *
     * @param id the ID of the task to mark as completed
     * @return ResponseEntity containing the updated task details
     */
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> markTaskAsCompleted(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.markTaskAsCompleted(id));
    }
    
    /**
     * Retrieves tasks that are overdue (past due date and not completed).
     *
     * @return ResponseEntity containing a list of overdue tasks
     */
    @GetMapping("/overdue")
    public ResponseEntity<List<TaskResponse>> getOverdueTasks() {
        return ResponseEntity.ok(taskService.getOverdueTasks());
    }
}