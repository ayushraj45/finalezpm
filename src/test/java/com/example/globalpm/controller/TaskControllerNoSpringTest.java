package com.example.globalpm.controller;

import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Task;
import com.example.globalpm.services.ProjectService;
import com.example.globalpm.services.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskControllerNoSpringTest {
    TaskService mockService = Mockito.mock(TaskService.class);
    TaskController uut = new TaskController(mockService);
    Task task = new Task();
    UUID id = UUID.randomUUID();

    @Test
    void verify_getAllCallsService() {
        uut.getAllTasks();
        verify(mockService,times(1)).findAllTask();
    }
    @Test
    void getAllTasksByGoalId() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        when(mockService.findTaskByGoalId(id)).thenReturn(tasks);
        List<Task> actual = uut.getAllTasksByGoalId(id);
        assertEquals(tasks, actual);
    }
}