package com.example.globalpm.controller;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Task;
import com.example.globalpm.services.ProjectService;
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
    public List<Task> getAllTasksWithinAProject(@PathVariable UUID projectId){
        return projService.getAllTasksInProject(projectId);
    }

}