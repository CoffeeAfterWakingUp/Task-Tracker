package com.example.tasktracker.mapper;

import com.example.tasktracker.dto.ProjectDTO;
import com.example.tasktracker.model.Project;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Mapper for Project, to convert to DTO and to Entity
 *
 * @author Olzhas Syrbek
 */
@Component
@Slf4j
public class ProjectMapper {

    private TaskMapper taskMapper;

    @Autowired
    public void setTaskMapper(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    public ProjectDTO toDto(Project project) {
        ProjectDTO projectDTO = ProjectDTO.builder()
                .id(project.getId())
                .startDate(project.getStartDate())
                .completionDate(project.getCompletionDate())
                .name(project.getName())
                .status(project.getStatus().toString())
                .priority(project.getPriority())
                .tasks(
                        Optional.ofNullable(project.getTasks())
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(task -> taskMapper.toDto(task))
                                .collect(Collectors.toList())
                )
                .build();

        log.info("ProjectDTO: {}", projectDTO);
        return projectDTO;
    }


    public List<ProjectDTO> toDto(List<Project> projects) {
        return Optional.ofNullable(projects)
                .orElse(Collections.emptyList())
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Project toEntity(ProjectDTO projectDTO) {
        return new Project(
                projectDTO.getId(),
                projectDTO.getName(),
                projectDTO.getStartDate(),
                projectDTO.getCompletionDate(),
                projectDTO.getPriority(),
                Project.ProjectStatus.valueOf(projectDTO.getStatus()),
                Optional.ofNullable(projectDTO.getTasks())
                        .orElse(Collections.emptyList())
                        .stream()
                        .map(taskDTO -> taskMapper.toEntity(taskDTO))
                        .collect(Collectors.toList())
        );
    }
}
