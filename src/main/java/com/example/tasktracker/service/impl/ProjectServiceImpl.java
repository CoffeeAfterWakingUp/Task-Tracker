package com.example.tasktracker.service.impl;

import com.example.tasktracker.model.Project;
import com.example.tasktracker.repo.ProjectRepo;
import com.example.tasktracker.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.tasktracker.util.SortDetails.getOrders;


/**
 * Service layer
 * Service implements methods of ProjectService interface
 *
 * @author Olzhas Syrbek
 */
@Service
@Slf4j
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectServiceImpl(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @Override
    public Project getProjectById(Long id) {
        log.info("Project id: {}", id);
        if (Optional.ofNullable(id).isEmpty()) {
            log.error("Project id is null");
            throw new IllegalArgumentException("Project id is null");
        }
        Optional<Project> project = projectRepo.findById(id);
        log.info("Got Project: {}", project);
        return project.orElseThrow(() -> new NoSuchElementException(String.format("Project with id: %s not found", id)));
    }

    @Override
    public Project createProject(Project project) {
        log.info("Got project for createProject: {}", project);
        if (Optional.ofNullable(project).isEmpty()) {
            log.error("Project for createProject is null");
            throw new IllegalArgumentException("Project for createProject is null");
        }
        Project createdProject = projectRepo.save(project);
        log.info("Created project: {}", createdProject);
        return createdProject;
    }

    @Override
    public Project updateProject(Long id, Project project) {
        log.info("Got project and id for updateProject: {}, {}", project, id);
        if (Optional.ofNullable(project).isEmpty()) {
            log.error("Project for updateProject is null");
            throw new IllegalArgumentException("Project for updateProject is null");
        }
        if (Optional.of(project).map(Project::getId).isEmpty()) {
            log.error("Project has to have a id for updateProject");
            throw new IllegalArgumentException("Project has to have a id for updateProject");
        }
        getProjectById(id);
        if (!project.getId().equals(id)) {
            log.error("Project's id and given id for updateProject has to be equal");
            throw new IllegalArgumentException("Project's id and given id for updateProject has to be equal");
        }
        return projectRepo.save(project);
    }

    @Override
    public String deleteProjectById(Long id) {
        log.info("Got id: {}", id);
        getProjectById(id);
        projectRepo.deleteById(id);
        return String.format("Project with id: %s", id);
    }

    @Override
    public Project getProjectByName(String projectName) {
        log.info("Project Name: {}", projectName);
        if (Optional.ofNullable(projectName).isEmpty()) {
            log.error("Project Name is null");
            throw new IllegalArgumentException("Project Name is null");
        }
        return Optional.ofNullable(projectRepo.findProjectByName(projectName))
                .orElseThrow(() -> new NoSuchElementException(String.format("Project with %s name not found", projectName)));
    }

    @Override
    public List<Project> getProjects(String[] sort) {
        List<Sort.Order> orders = getOrders(sort);
        if (orders.isEmpty()) {
            return projectRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        List<Project> projects = projectRepo.findAll(Sort.by(orders));
        log.info("Tasks: {}", projects);
        return projects;
    }

    @Override
    public List<Project> getProjectsByStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status should not be null");
        }
        return projectRepo.findByStatus(Project.ProjectStatus.valueOf(status.toUpperCase()));
    }

    @Override
    public List<Project> getProjectsByPriority(Integer priority) {
        if (priority == null) {
            throw new IllegalArgumentException("priority should not be null");
        }
        return projectRepo.findByPriority(priority);
    }

    @Override
    public List<Project> getProjectsByStartDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("start date should not be null");
        }
        return projectRepo.findByStartDate(date);
    }

    @Override
    public List<Project> getProjectsByCompletionDate(LocalDateTime date) {
        if (date == null) {
            throw new IllegalArgumentException("completion date should not be null");
        }
        return projectRepo.findByCompletionDate(date);
    }

    @Override
    public List<Project> getProjectsByDateBetween(String date) {
        if (date == null) {
            throw new IllegalArgumentException("date should not be null");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        log.info("dateTime: {}", dateTime);
        return projectRepo.findByDateBetween(dateTime);
    }
}
