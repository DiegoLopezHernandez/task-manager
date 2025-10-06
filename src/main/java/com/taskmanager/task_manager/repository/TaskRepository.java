package com.taskmanager.task_manager.repository;

import com.taskmanager.task_manager.model.Task;
import com.taskmanager.task_manager.model.Task.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    List<Task> findByStatus(TaskStatus status);
    
    List<Task> findByPriority(Integer priority);
    
    List<Task> findByDueDateBeforeAndStatusNot(LocalDateTime dueDate, TaskStatus status);
    
    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> searchByKeyword(@Param("keyword") String keyword);
    
    List<Task> findByDueDateBetween(LocalDateTime start, LocalDateTime end);
}