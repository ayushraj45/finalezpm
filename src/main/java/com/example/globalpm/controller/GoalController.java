package com.example.globalpm.controller;

import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Task;
import com.example.globalpm.services.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goals")
public class GoalController {

    GoalService service;

    @Autowired
    public GoalController(GoalService service) {
        this.service = service;
    }
    @Operation(summary = "Get a list of all Goals", description = "Returns a list of all goals")
    @GetMapping("")
    public List<Goal> getAllGoals (){
        return service.getAllGoals();
    }

    @Operation(summary = "Get a list of all Goals in a Project", description = "Returns a list of all goals within a project")
    @GetMapping("/projectId/{id}")
    public List<Goal> getAllGoalByProjectId(@PathVariable UUID id){
        return service.getAllGoalInProject(id);
    }



}
