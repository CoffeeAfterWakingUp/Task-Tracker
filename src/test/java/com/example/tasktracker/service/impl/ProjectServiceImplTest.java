package com.example.tasktracker.service.impl;

import com.example.tasktracker.model.Project;
import com.example.tasktracker.repo.ProjectRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static com.example.tasktracker.util.SortDetails.getOrders;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProjectServiceImplTest {
    @Mock
    ProjectRepo projectRepo;
    @InjectMocks
    ProjectServiceImpl projectServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetProjectById(){
        Long id = 1L;
        Project project = new Project(1L, null, null, null, null, null, null);
        Optional<Project> expected = Optional.of(project);
        when(projectRepo.findById(id)).thenReturn(expected);
        Project actual = projectServiceImpl.getProjectById(id);
        assertEquals(expected.get(), actual);
    }

    @Test
    void testGetProjectByIdShouldThrowIllegalArgumentException(){
        assertThrows(IllegalArgumentException.class, () -> projectServiceImpl.getProjectById(null));
    }

    @Test
    void testGetProjectByIdShouldThrowNoSuchElementException(){
        when(projectRepo.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> projectServiceImpl.getProjectById(anyLong()));
    }

    @Test
    void testCreateProject() {
        Project project = new Project(1L, null, null, null, null, null, null);
        Project createdProject = new Project(2L, null, null, null, null, null, null);
        when(projectRepo.save(project)).thenReturn(createdProject);
        Project actual = projectServiceImpl.createProject(project);
        assertEquals(actual, createdProject);
    }

    @Test
    void testCreateProjectThrowsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> projectServiceImpl.createProject(null));
    }

    @Test
    void testGetProjects() {
        List<Project> projects = Arrays.asList(
          new Project(1L, null, null, null, 0, null, null),
          new Project(2L, null, null, null, 1, null, null)
        );
        String[] sort = {"priority,desc", "id,asc"};
        List<Sort.Order> orders = getOrders(sort);
        when(projectRepo.findAll(Sort.by(orders))).thenReturn(projects);
        List<Project> actual = projectServiceImpl.getProjects(sort);
        assertEquals(actual, projects);

        List<Project> projects1 = Arrays.asList(
                new Project(2L, null, null, null, 0, null, null),
                new Project(1L, null, null, null, 1, null, null)
        );
        when(projectRepo.findAll(Sort.by(Sort.Direction.DESC, "id"))).thenReturn(projects1);
        List<Project> actual1 = projectServiceImpl.getProjects(new String[]{});
        assertEquals(actual1, projects1);
    }


}