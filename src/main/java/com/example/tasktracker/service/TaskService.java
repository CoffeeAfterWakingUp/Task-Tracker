package com.example.tasktracker.service;

import com.example.tasktracker.model.Task;

import java.util.List;


/**
 * Interface declares methods which will interact with Task repository
 *
 * @author Olzhas Syrbek
 */
public interface TaskService {
    Task getTaskById(Long id);

    List<Task> getTasksByProjectId(Long projectId);

    Task createTask(Task task);

    Task updateTask(Task task, Long id);

    String deleteTaskById(Long id);

    List<Task> addTaskToProject(Long projectId, Long taskId);

    List<Task> removeTaskFromProject(Long projectId, Long taskId);

    List<Task> getTasksByPriority(Integer priority);

    List<Task> getTasksByStatus(String status);

    List<Task> getAllTasks(String[] sort);

    List<Task> getTaskByName(String name);
}
