package com.example.tasktracker.service;

import com.example.tasktracker.model.Project;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Interface declares methods which will interact with Project repository
 *
 * @author Olzhas Syrbek
 */
public interface ProjectService {
    Project getProjectById(Long id);

    Project createProject(Project project);

    Project updateProject(Long id, Project project);

    String deleteProjectById(Long id);

    Project getProjectByName(String projectName);

    List<Project> getProjects(String[] sort);

    List<Project> getProjectsByStatus(String status);

    List<Project> getProjectsByPriority(Integer priority);

    List<Project> getProjectsByStartDate(LocalDateTime date);

    List<Project> getProjectsByCompletionDate(LocalDateTime date);

    List<Project> getProjectsByDateBetween(String date);


}
