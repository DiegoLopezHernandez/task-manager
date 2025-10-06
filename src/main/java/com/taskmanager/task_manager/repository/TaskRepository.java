package com.taskmanager.task_manager.repository;

import com.taskmanager.task_manager.model.Task;
import com.taskmanager.task_manager.model.Task.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Task entity operations.
 * Extends JpaRepository to provide CRUD operations and custom query methods.
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    /**
     * Finds all tasks with the specified status.
     * 
     * @param status the task status to filter by
     * @return List of tasks with the given status
     */
    List<Task> findByStatus(TaskStatus status);
    
    /**
     * Finds all tasks with the specified priority level.
     * 
     * @param priority the priority level to filter by (1=High, 2=Medium, 3=Low)
     * @return List of tasks with the given priority
     */
    List<Task> findByPriority(Integer priority);
    
    /**
     * Finds tasks that are overdue (due date has passed and not completed).
     * 
     * @param dueDate the reference date/time to compare against
     * @param status the status to exclude (typically COMPLETED)
     * @return List of overdue tasks
     */
    List<Task> findByDueDateBeforeAndStatusNot(LocalDateTime dueDate, TaskStatus status);
    
    /**
     * Searches tasks by keyword in title or description (case-insensitive).
     * 
     * @param keyword the search term to look for
     * @return List of tasks matching the search criteria
     */
    @Query("SELECT t FROM Task t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :keyword, '%')) OR LOWER(t.description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> searchByKeyword(@Param("keyword") String keyword);
    
    /**
     * Finds tasks with due dates within the specified date range.
     * 
     * @param start the start date/time of the range (inclusive)
     * @param end the end date/time of the range (inclusive)
     * @return List of tasks due between the specified dates
     */
    List<Task> findByDueDateBetween(LocalDateTime start, LocalDateTime end);
}