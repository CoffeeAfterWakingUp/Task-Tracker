package com.example.tasktracker.mapper;

import com.example.tasktracker.dto.TaskDTO;
import com.example.tasktracker.model.Project;
import com.example.tasktracker.model.Task;
import com.example.tasktracker.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Mapper for Task, to convert to DTO and to Entity
 * @author Olzhas Syrbek
 */
@Component
@Slf4j
public class TaskMapper {

    private ProjectMapper projectMapper;
    private ProjectService projectService;


    @Autowired
    public void setProjectService(ProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setProjectMapper(ProjectMapper projectMapper) {
        this.projectMapper = projectMapper;
    }

    public TaskDTO toDto(Task task) {
        TaskDTO taskDTO = TaskDTO.builder()
                .id(task.getId())
                .priority(task.getPriority())
                .name(task.getName())
                .description(task.getDescription())
                .status(task.getStatus().toString())
                .build();
        if (Optional.ofNullable(task.getProject()).map(Project::getName).isPresent()) {
            taskDTO.setProjectName(task.getProject().getName());
        }
        log.info("TaskDTO: {}", taskDTO);
        return taskDTO;
    }

    public List<TaskDTO> toDto(List<Task> tasks) {
        return Optional.ofNullable(tasks)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());

    }

    public Task toEntity(TaskDTO taskDTO) {

        Task task = new Task(
                taskDTO.getId(),
                taskDTO.getName(),
                taskDTO.getDescription(),
                taskDTO.getPriority(),
                Task.TaskStatus.valueOf(taskDTO.getStatus())
        );

        if (Optional.ofNullable(taskDTO.getProjectName()).isPresent()) {
            task.setProject(projectService.getProjectByName(taskDTO.getProjectName()));
        }
        log.info("Task: {}", task);

        return task;
    }

}
