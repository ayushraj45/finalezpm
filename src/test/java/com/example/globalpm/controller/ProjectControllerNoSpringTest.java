package com.example.globalpm.controller;

import com.example.globalpm.entities.Project;
import com.example.globalpm.services.ProjectService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class ProjectControllerNoSpringTest {

    ProjectService mockService = Mockito.mock(ProjectService.class);
    ProjectController uut = new ProjectController(mockService);
    Project project = new Project("New Project");
    UUID id = UUID.randomUUID();

    @Test
    void verify_getAllCallsService() {
        uut.getAllProjects();
        verify(mockService,times(1)).getAllProjects();
    }

}