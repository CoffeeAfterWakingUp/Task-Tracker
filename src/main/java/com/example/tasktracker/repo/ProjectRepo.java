package com.example.tasktracker.repo;

import com.example.tasktracker.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

/**
 * JPA repository, for DML queries which manages Project entity
 *
 * @author Olzhas Syrbek
 */
public interface ProjectRepo extends JpaRepository<Project, Long> {

    Project findProjectByName(String name);

    List<Project> findByStatus(Project.ProjectStatus status);

    List<Project> findByPriority(Integer priority);

    List<Project> findByStartDate(LocalDateTime startDate);

    List<Project> findByCompletionDate(LocalDateTime completionDate);

    @Query("SELECT p FROM Project p WHERE :date BETWEEN p.startDate AND p.completionDate")
    List<Project> findByDateBetween(LocalDateTime date);


}
