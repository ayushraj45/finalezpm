package com.example.globalpm.controller;

import com.example.globalpm.entities.Goal;
import com.example.globalpm.services.GoalService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoalControllerTest {

    GoalService mockService = mock(GoalService.class);
    GoalController uut = new GoalController(mockService);
    UUID id = UUID.randomUUID();

    Goal goal = new Goal("Test Goal");
    @Test
    void verify_getAllCallsService() {
        uut.getAllGoals();
        verify(mockService,times(1)).getAllGoals();
    }

    @Test
    void test_findAllByProjectId_ValidRequest() {
        List<Goal> goals = new ArrayList<>();
        goals.add(goal);
        when(mockService.getAllGoalInProject(id)).thenReturn(goals);
        List<Goal> actual = uut.getAllGoalByProjectId(id);
        assertEquals(goals, actual);
    }


}