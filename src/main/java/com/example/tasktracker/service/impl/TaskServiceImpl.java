package com.example.tasktracker.service.impl;

import com.example.tasktracker.model.Project;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.repo.TaskRepo;
import com.example.tasktracker.service.ProjectService;
import com.example.tasktracker.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.tasktracker.util.SortDetails.getOrders;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {

    private final TaskRepo taskRepo;
    private final ProjectService projectService;

    @Autowired
    public TaskServiceImpl(TaskRepo taskRepo, ProjectService projectService) {
        this.taskRepo = taskRepo;
        this.projectService = projectService;
    }


    @Override
    public Task getTaskById(Long id) {
        if (Optional.ofNullable(id).isEmpty()) {
            log.error("Id is null for getTaskById");
            throw new IllegalArgumentException("Id is null");
        }
        return taskRepo.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format("Task with id: %s not found", id)));
    }

    @Override
    public List<Task> getTasksByProjectId(Long projectId) {
        log.info("Got Project id: {}", projectId);
        if (Optional.ofNullable(projectId).isEmpty()) {
            log.error("Project id is null");
            throw new IllegalArgumentException("Project id is null");
        }
        return taskRepo.findTasksByProjectId(projectId);
    }

    @Override
    public Task createTask(Task task) {
        log.info("Got task for createTask: {}", task);
        if (Optional.ofNullable(task).isEmpty()) {
            log.error("Task is null for createTask");
            throw new IllegalArgumentException("Task is null");
        }
        return taskRepo.save(task);
    }

    @Override
    public Task updateTask(Task task, Long id) {
        if (Optional.ofNullable(id).isEmpty()) {
            log.error("Id is null for updateTask");
            throw new IllegalArgumentException("Id is null");
        }
        if (Optional.ofNullable(task).isEmpty()) {
            log.error("Task is null for updateTask");
            throw new IllegalArgumentException("Task is null");
        }
        if (Optional.of(task).map(Task::getId).isEmpty()) {
            log.error("Task's id is null for updateTask");
            throw new IllegalArgumentException("Task's id is null");
        }
        if (!task.getId().equals(id)) {
            log.error("Task's id and given id has to be equal for updateTask");
            throw new IllegalArgumentException("Task's id and given id has to be equal");
        }
        return taskRepo.save(task);
    }

    @Override
    public String deleteTaskById(Long id) {
        log.info("Id is {}", id);
        if (Optional.ofNullable(id).isEmpty()) {
            log.error("Id is null for deleteTaskById");
            throw new IllegalArgumentException("Id is null");
        }
        taskRepo.deleteById(id);
        return "Task deleted";
    }

    @Override
    public List<Task> addTaskToProject(Long projectId, Long taskId) {
        Project project = projectService.getProjectById(projectId);
        Task task = getTaskById(taskId);
        project.addTask(task);
        taskRepo.save(task);
        return project.getTasks();
    }

    @Override
    public List<Task> removeTaskFromProject(Long projectId, Long taskId) {
        Project project = projectService.getProjectById(projectId);
        Task task = getTaskById(taskId);
        if (Optional.ofNullable(project.getTasks()).isPresent() && project.getTasks().contains(task)) {
            project.removeTask(task);
            updateTask(task, taskId);
        }
        return project.getTasks();
    }

    @Override
    public List<Task> getTasksByPriority(Integer priority) {
        if (priority == null) {
            throw new IllegalArgumentException("priority should not be null");
        }
        return taskRepo.findTasksByPriority(priority);
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        if (status == null) {
            throw new IllegalArgumentException("Status should not be null");
        }
        return taskRepo.findTasksByStatus(Task.TaskStatus.valueOf(status.toUpperCase()));
    }

    @Override
    public List<Task> getAllTasks(String[] sort) {
        List<Sort.Order> orders = getOrders(sort);
        if (orders.isEmpty()) {
            return taskRepo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        }
        List<Task> tasks = taskRepo.findAll(Sort.by(orders));
        log.info("Tasks: {}", tasks);
        return tasks;
    }

    @Override
    public List<Task> getTaskByName(String name) {
        log.info("name: {}", name);
        if (name == null) {
            throw new IllegalArgumentException("name should not be null");
        }
        List<Task> tasks = taskRepo.findByName(name);
        log.info("tasks: {}", tasks);
        return tasks;
    }
}
