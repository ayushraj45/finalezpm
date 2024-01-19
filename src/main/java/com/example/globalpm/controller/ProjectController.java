package com.example.globalpm.controller;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Task;
import com.example.globalpm.entities.User;
import com.example.globalpm.services.ProjectService;
import com.example.globalpm.services.GoalService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    ProjectService projService;
    @Autowired
    GoalService goalService;


    @Autowired
    public ProjectController(ProjectService projService){this.projService = projService;}

    @Operation(summary = "Get a list of all Projects", description = "Returns a list of all projects")
    @GetMapping("")
    public List<Project> getAllProjects() {
        return projService.getAllProjects();
    }

    @Operation(summary = "Get a list of all Goals", description = "Returns a list of all goals across all projects")
    @GetMapping("/goals")
    public List<Goal> getAllGoals() {
        return goalService.getAllGoals();
    }

    @GetMapping("/tasks/{projectId}")
    public List<Task> getAllTasksWithinAProject(@PathVariable UUID projectId) {
        return projService.getAllTasksInProject(projectId);
    }

    @Operation(summary = "Get a list of all users assigned to a project", description = "Returns a list of users assigned to a project with Project Id")
    @GetMapping("/users/{projectId}")
    public List<User> getAllUsersInAProject(@PathVariable UUID projectId){
        return projService.findUsersInAProject(projectId);
    }

    @Operation(summary = "Get project progress", description = "Returns the project progress with a Project Id")
    @GetMapping("/{projectId}/progress")
    public Double getProjectProgress(@PathVariable UUID projectId){
        return projService.getProjectProgressWithGoal(projectId);
    }

    //POST MAPPING
    @Operation(summary = "Add a new project", description = "Add a new project")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project addProject(@RequestBody @DateTimeFormat(pattern="dd-MM-yyyy") Project project){
        return projService.addAProject(project);
    }


    //PUT MAPPING
    @Operation(summary = "Add a Goal to a project ", description = "Adding a goal to a project.")
    @PutMapping("/addGoal/")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addAgoalToAProject(@RequestBody Goal goal, UUID id){
        return projService.addGoalToProject(id, goal);
    }

    @Operation(summary = "Assign a User to A project and vice-versa", description = "adds a user to project with Project ID and User")
    @PutMapping("{projectID}/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Project addAUserToAProject(@RequestBody User user, UUID projectId){
        return projService.addUserToProject(projectId,user);
    }


}