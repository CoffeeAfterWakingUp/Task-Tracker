package com.example.tasktracker.rest;

import com.example.tasktracker.dto.ResponseDto;
import com.example.tasktracker.dto.TaskDTO;
import com.example.tasktracker.mapper.TaskMapper;
import com.example.tasktracker.service.TaskService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;


/**
 * Rest Controller contains API's for working with Task's data
 *
 * @author Olzhas Syrbek
 */
@RestController
@Slf4j
@RequestMapping("api/tasks")
public class TaskRestController extends AbstractRestController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskRestController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @GetMapping("/projects/{id}")
    @ApiOperation(value = "Get tasks of the project")
    public ResponseEntity<ResponseDto<List<TaskDTO>>> getTasksByProjectId(@PathVariable Long id) {
        return success(taskMapper.toDto(taskService.getTasksByProjectId(id)));
    }

    @PutMapping("{id}/projects/{projectId}")
    @ApiOperation(value = "Add task to a project")
    public ResponseEntity<ResponseDto<List<TaskDTO>>> addTaskToProject(@PathVariable Long id, @PathVariable Long projectId) {
        return success(taskMapper.toDto(taskService.addTaskToProject(projectId, id)));
    }


    @DeleteMapping("{id}/projects/{projectId}")
    @ApiOperation(value = "Remove task from project")
    public ResponseEntity<ResponseDto<List<TaskDTO>>> removeTaskFromProject(@PathVariable Long id, @PathVariable Long projectId) {
        return success(taskMapper.toDto(taskService.removeTaskFromProject(projectId, id)));
    }

    @PostMapping
    @ApiOperation(value = "Create Task")
    public ResponseEntity<ResponseDto<TaskDTO>> createTask(@RequestBody TaskDTO taskDTO) {
        return success(taskMapper.toDto(taskService.createTask(taskMapper.toEntity(taskDTO))), CREATED);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get Task by id")
    public ResponseEntity<ResponseDto<TaskDTO>> getTaskById(@PathVariable Long id) {
        return success(taskMapper.toDto(taskService.getTaskById(id)));
    }


    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete task by id")
    public ResponseEntity<ResponseDto<String>> deleteTaskById(@PathVariable Long id) {
        return success(taskService.deleteTaskById(id));
    }


    @PutMapping("/{id}")
    @ApiOperation(value = "Update task")
    public ResponseEntity<ResponseDto<TaskDTO>> updateTask(@PathVariable Long id, @RequestBody TaskDTO taskDTO) {
        return success(taskMapper.toDto(taskService.updateTask(taskMapper.toEntity(taskDTO), id)));
    }

    @GetMapping("/priority/{priority}")
    @ApiOperation(value = "Get tasks by priority")
    public ResponseEntity<ResponseDto<List<TaskDTO>>> getTasksByPriority(@PathVariable Integer priority) {
        return success(taskMapper.toDto(taskService.getTasksByPriority(priority)));
    }

    @GetMapping("/status/{status}")
    @ApiOperation(value = "Get tasks by status")
    public ResponseEntity<ResponseDto<List<TaskDTO>>> getTasksByStatus(@PathVariable String status) {
        return success(taskMapper.toDto(taskService.getTasksByStatus(status)));
    }

    @GetMapping
    @ApiOperation(value = "Get sorted tasks")
    public ResponseEntity<ResponseDto<List<TaskDTO>>> getTasks(@RequestParam(required = false) String[] sort) {
        return success(taskMapper.toDto(taskService.getAllTasks(sort)));
    }

    @GetMapping("/name/{name}")
    @ApiOperation(value = "Get task by name")
    public ResponseEntity<ResponseDto<List<TaskDTO>>> getTasksByName(@PathVariable String name) {
        return success(taskMapper.toDto(taskService.getTaskByName(name)));
    }


}
