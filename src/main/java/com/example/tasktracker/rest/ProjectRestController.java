package com.example.tasktracker.rest;

import com.example.tasktracker.dto.ProjectDTO;
import com.example.tasktracker.dto.ResponseDto;
import com.example.tasktracker.mapper.ProjectMapper;
import com.example.tasktracker.service.ProjectService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Slf4j
@RequestMapping("api/projects")
public class ProjectRestController extends AbstractRestController {

    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectRestController(ProjectService projectService, ProjectMapper projectMapper) {
        this.projectService = projectService;
        this.projectMapper = projectMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get project by id")
    public ResponseEntity<ResponseDto<ProjectDTO>> getProjectById(@PathVariable Long id) {
        return success(projectMapper.toDto(projectService.getProjectById(id)));
    }


    @PostMapping()
    @ApiOperation(value = "Create project")
    public ResponseEntity<ResponseDto<ProjectDTO>> createProject(@RequestBody ProjectDTO projectDTO) {
        return success(projectMapper.toDto(projectService.createProject(projectMapper.toEntity(projectDTO))), CREATED);
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Update project")
    public ResponseEntity<ResponseDto<ProjectDTO>> updateProject(@RequestBody ProjectDTO projectDTO, @PathVariable Long id) {
        return success(projectMapper.toDto(projectService.updateProject(id, projectMapper.toEntity(projectDTO))));
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete project by id")
    public ResponseEntity<ResponseDto<String>> deleteProjectById(@PathVariable Long id) {
        return success(projectService.deleteProjectById(id));
    }


    @GetMapping("/priority/{priority}")
    @ApiOperation(value = "Get projects by priority")
    public ResponseEntity<ResponseDto<List<ProjectDTO>>> getProjectsByPriority(@PathVariable Integer priority) {
        return success(projectMapper.toDto(projectService.getProjectsByPriority(priority)));
    }

    @GetMapping("/status/{status}")
    @ApiOperation(value = "Get projects by status")
    public ResponseEntity<ResponseDto<List<ProjectDTO>>> getTasksByStatus(@PathVariable String status) {
        return success(projectMapper.toDto(projectService.getProjectsByStatus(status)));
    }

    @GetMapping
    @ApiOperation(value = "Get sorted projects")
    public ResponseEntity<ResponseDto<List<ProjectDTO>>> getTasks(@RequestParam(required = false) String[] sort) {
        return success(projectMapper.toDto(projectService.getProjects(sort)));
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get projects by name")
    public ResponseEntity<ResponseDto<ProjectDTO>> getTasksByName(@PathVariable String name) {
        return success(projectMapper.toDto(projectService.getProjectByName(name)));
    }


    @GetMapping("/startDate/{date}")
    @ApiOperation(value = "Get projects by start date")
    public ResponseEntity<ResponseDto<List<ProjectDTO>>> getProjectsByStartDate(@PathVariable LocalDateTime date) {
        return success(projectMapper.toDto(projectService.getProjectsByStartDate(date)));
    }

    @GetMapping("/completionDate/{date}")
    @ApiOperation(value = "Get projects by completion date")
    public ResponseEntity<ResponseDto<List<ProjectDTO>>> getProjectsByCompletionDate(@PathVariable LocalDateTime date) {
        return success(projectMapper.toDto(projectService.getProjectsByCompletionDate(date)));
    }

    @GetMapping("/date/{date}")
    @ApiOperation(value = "Get projects by between start and end date")
    public ResponseEntity<ResponseDto<List<ProjectDTO>>> getProjectsByBetweenStartAndEndDate(@PathVariable String date) {
        return success(projectMapper.toDto(projectService.getProjectsByDateBetween(date)));
    }


}
