package com.example.tasktracker.repo;

import com.example.tasktracker.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JPA repository, for DML queries which manages Task entity
 *
 * @author Olzhas Syrbek
 */
public interface TaskRepo extends JpaRepository<Task, Long> {

    List<Task> findTasksByProjectId(Long projectId);

    List<Task> findTasksByPriority(Integer priority);

    List<Task> findTasksByStatus(Task.TaskStatus status);

    List<Task> findByName(String name);


}
