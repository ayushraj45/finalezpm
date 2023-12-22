package com.example.globalpm.controller;

import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Task;
import com.example.globalpm.entities.User;
import com.example.globalpm.services.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Get a list of all users assigned to a Goal", description = "Returns a list of users assigned to a goal with Goal Id")
    @GetMapping("/users/{goalId}")
    public List<User> getAllUsersInATask(@PathVariable UUID goalId){
        return service.findUsersInAGoal(goalId);
    }

    //POST MAPPING
    @Operation(summary = "Add a new Goal", description = "Add a new goal")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Goal addGoal(@RequestBody @DateTimeFormat(pattern="dd-MM-yyyy") Goal goal){
        return service.addAGoalInAProject(goal);
    }

    //Put Mapping
    @Operation(summary = "Add a Task to a Goal ", description = "Adding a task to a Goal.")
    @PutMapping("/addTask/")
    @ResponseStatus(HttpStatus.CREATED)
    public Goal addATasktoAGoal(@RequestBody Task task, UUID id){
        return service.addTaskToAGoal(id, task);
    }




}
