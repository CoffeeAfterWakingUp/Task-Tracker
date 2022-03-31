package com.example.tasktracker;

import com.example.tasktracker.model.Project;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.service.ProjectService;
import com.example.tasktracker.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.ArrayList;

@SpringBootApplication
public class TaskTrackerApplication implements CommandLineRunner {

    private TaskService taskService;
    private ProjectService projectService;

    @Autowired
    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    public static void main(String[] args) {
        SpringApplication.run(TaskTrackerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Project project1 = new Project(
                null,
                "Project 1",
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(10),
                1,
                Project.ProjectStatus.ACTIVE,
                new ArrayList<>()
        );

        Project createdProject1 = projectService.createProject(project1);

        Task task1 = new Task(
                null,
                "Task 1",
                "Task 1 description",
                1,
                Task.TaskStatus.TO_DO,
                createdProject1
        );

        Task task2 = new Task(
                null,
                "Task 2",
                "Task 2 description",
                1,
                Task.TaskStatus.TO_DO,
                createdProject1
        );

        Task task3 = new Task(
                null,
                "Task 3",
                "Task 3 description",
                1,
                Task.TaskStatus.IN_PROGRESS,
                null
        );

        taskService.createTask(task1);
        taskService.createTask(task2);
        taskService.createTask(task3);

    }
}
