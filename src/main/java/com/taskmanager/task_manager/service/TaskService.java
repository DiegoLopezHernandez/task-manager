package com.taskmanager.task_manager.service;

import com.taskmanager.task_manager.controller.dto.TaskRequest;
import com.taskmanager.task_manager.controller.dto.TaskResponse;
import com.taskmanager.task_manager.model.Task.TaskStatus;
import java.util.List;

/**
 * Service interface for task management operations.
 * Defines the contract for all task-related business logic.
 */
public interface TaskService {
    
    /**
     * Retrieves all tasks from the system.
     * 
     * @return List of all tasks as TaskResponse objects
     */
    List<TaskResponse> getAllTasks();
    
    /**
     * Finds a specific task by its unique identifier.
     * 
     * @param id the task ID to search for
     * @return TaskResponse containing the task details
     * @throws TaskNotFoundException if no task is found with the given ID
     */
    TaskResponse getTaskById(Long id);
    
    /**
     * Creates a new task in the system.
     * 
     * @param taskRequest the task data to create
     * @return TaskResponse containing the created task details
     */
    TaskResponse createTask(TaskRequest taskRequest);
    
    /**
     * Updates an existing task with new data.
     * 
     * @param id the ID of the task to update
     * @param taskRequest the updated task data
     * @return TaskResponse containing the updated task details
     * @throws TaskNotFoundException if no task is found with the given ID
     */
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    
    /**
     * Deletes a task from the system.
     * 
     * @param id the ID of the task to delete
     * @throws TaskNotFoundException if no task is found with the given ID
     */
    void deleteTask(Long id);
    
    /**
     * Retrieves tasks filtered by their status.
     * 
     * @param status the task status to filter by
     * @return List of tasks with the specified status
     */
    List<TaskResponse> getTasksByStatus(TaskStatus status);
    
    /**
     * Retrieves tasks filtered by their priority level.
     * 
     * @param priority the priority level to filter by (1=High, 2=Medium, 3=Low)
     * @return List of tasks with the specified priority
     */
    List<TaskResponse> getTasksByPriority(Integer priority);
    
    /**
     * Searches tasks by keyword in title or description.
     * 
     * @param keyword the search term to look for
     * @return List of tasks matching the search criteria
     */
    List<TaskResponse> searchTasks(String keyword);
    
    /**
     * Marks a task as completed.
     * 
     * @param id the ID of the task to mark as completed
     * @return TaskResponse containing the updated task details
     * @throws TaskNotFoundException if no task is found with the given ID
     */
    TaskResponse markTaskAsCompleted(Long id);
    
    /**
     * Retrieves tasks that are overdue (past due date and not completed).
     * 
     * @return List of overdue tasks
     */
    List<TaskResponse> getOverdueTasks();
    
    /**
     * Retrieves tasks with due dates within a specified time range.
     * 
     * @param start the start date/time of the range (ISO format)
     * @param end the end date/time of the range (ISO format)
     * @return List of tasks due between the specified dates
     */
    List<TaskResponse> getTasksDueBetween(String start, String end);
}