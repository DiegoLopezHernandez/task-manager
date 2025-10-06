package com.taskmanager.task_manager.service;
import com.taskmanager.task_manager.controller.dto.TaskRequest;
import com.taskmanager.task_manager.controller.dto.TaskResponse;
import com.taskmanager.task_manager.model.Task.TaskStatus;
import java.util.List;

public interface TaskService {
    List<TaskResponse> getAllTasks();
    TaskResponse getTaskById(Long id);
    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse updateTask(Long id, TaskRequest taskRequest);
    void deleteTask(Long id);
    List<TaskResponse> getTasksByStatus(TaskStatus status);
    List<TaskResponse> getTasksByPriority(Integer priority);
    List<TaskResponse> searchTasks(String keyword);
    TaskResponse markTaskAsCompleted(Long id);
    List<TaskResponse> getOverdueTasks();
    List<TaskResponse> getTasksDueBetween(String start, String end);
}