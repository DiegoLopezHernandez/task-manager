package com.taskmanager.task_manager.controller;

import com.taskmanager.task_manager.model.Task;
import com.taskmanager.task_manager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.taskmanager.task_manager.controller.dto.TaskRequest;


import java.time.LocalDateTime;

/**
 * Web controller for handling task management UI operations.
 * Provides endpoints for the web interface of the Task Manager application.
 */
@Controller
@RequestMapping("/")
public class WebController {
    
    private final TaskService taskService;

    /**
     * Constant for redirecting to the home page.
     */
    private static final String REDIRECT_HOME = "redirect:/";
    
    /**
     * Constructor for dependency injection of TaskService.
     *
     * @param taskService the task service to handle business logic
     */
    public WebController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    /**
     * Displays the main task management page with all tasks.
     *
     * @param model the Spring MVC model to add attributes for the view
     * @return the name of the view template to render (index.html)
     */
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("newTask", new Task());
        return "index";
    }
    
    /**
     * Handles creation of a new task from the web form.
     *
     * @param task the task data submitted from the form
     * @return redirect to the home page after task creation
     */
    @PostMapping("/tasks")
    public String createTask(@ModelAttribute Task task) {
        TaskRequest request = new TaskRequest();
        request.setTitle(task.getTitle());
        request.setDescription(task.getDescription());
        request.setDueDate(task.getDueDate() != null ? task.getDueDate() : LocalDateTime.now().plusDays(1));
        request.setPriority(task.getPriority() != null ? task.getPriority() : 2);
        
        taskService.createTask(request);
        return REDIRECT_HOME;
    }
    
    /**
     * Marks a specific task as completed.
     *
     * @param id the ID of the task to mark as completed
     * @return redirect to the home page after updating the task
     */
    @PostMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
        taskService.markTaskAsCompleted(id);
        return REDIRECT_HOME;
    }
    
    /**
     * Deletes a specific task from the system.
     *
     * @param id the ID of the task to delete
     * @return redirect to the home page after deletion
     */
    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return REDIRECT_HOME;
    }
}