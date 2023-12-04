package com.example.globalpm.controller;

import com.example.globalpm.entities.Task;
import com.example.globalpm.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    TaskService service;
    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }
    @Operation(summary = "Get a list of all Tasks", description = "Returns a list of all goals across all projects")
    @GetMapping("")
    List<Task> getAllTasks(){
        return service.findAllTask();
    }
    @Operation(summary = "Get a list of all Task assigned to a Goal", description = "Returns a list of all tasks within a specific Goal")
    @GetMapping("/goal/{goalId}")
    List<Task> getAllTasksByGoalId(UUID goalId){
        return service.findTaskByGoalId(goalId);
    }

//    @Operation(summary = "Get a list of all Task assigned to a Goal", description = "Returns a list of all goals within a specific task")
//    @GetMapping("/project/{projectId}")
//    List<Task> getAllTasksByProjectId(UUID projectId){
//        return service.findTaskByProjectId(projectId);
//    }

    @Operation(summary = "Add a Task", description = "Add a task")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addATask(@RequestBody Task goal){
        return service.addANewTask(goal);
    }

    @Operation(summary = "Update a Task", description = "Update a task")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task updateATask(@RequestBody Task goal){
        return service.updateATask(goal);
    }



}
