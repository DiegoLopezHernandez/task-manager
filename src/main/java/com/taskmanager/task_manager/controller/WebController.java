package com.taskmanager.task_manager.controller;

import com.taskmanager.task_manager.model.Task;
import com.taskmanager.task_manager.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.taskmanager.task_manager.controller.dto.TaskRequest;


import java.time.LocalDateTime;

@Controller
@RequestMapping("/")
public class WebController {
    
    private final TaskService taskService;

      private static final String REDIRECT_HOME = "redirect:/";
    
    public WebController(TaskService taskService) {
        this.taskService = taskService;
    }
    
    @GetMapping
    public String index(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        model.addAttribute("newTask", new Task());
        return "index";
    }
    
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
    
    @PostMapping("/tasks/{id}/complete")
    public String completeTask(@PathVariable Long id) {
        taskService.markTaskAsCompleted(id);
        return REDIRECT_HOME;
    }
    
    @PostMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return REDIRECT_HOME;
    }
}