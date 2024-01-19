package com.example.globalpm.controller;

import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Task;
import com.example.globalpm.entities.User;
import com.example.globalpm.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
    @Operation(summary = "Get a list of all Tasks", description = "Returns a list of all tasks across all projects")
    @GetMapping("")
    List<Task> getAllTasks(){
        return service.findAllTask();
    }
    @Operation(summary = "Get a list of all Task assigned to a Goal", description = "Returns a list of all tasks within a specific Goal")
    @GetMapping("/goal/{goalId}")
    List<Task> getAllTasksByGoalId(UUID goalId){
        return service.findTaskByGoalId(goalId);
    }

    @Operation(summary = "Get a list of all users assigned to a Task", description = "Returns a list of users assigned to a task with Task Id")
    @GetMapping("/users/{taskId}")
    public List<User> getAllUsersInATask(@PathVariable UUID taskId){
        return service.findUsersInATask(taskId);
    }

    @Operation(summary = "Update a Task", description = "Update a task")
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task updateATask(@RequestBody Task goal){
        return service.updateATask(goal);
    }

    //POST MAPPING
    @Operation(summary = "Add a new Task in a Goal", description = "Add a new Task To A Goal")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Task addTask(@RequestBody @DateTimeFormat(pattern="dd-MM-yyyy") Task task){
        return service.addATask(task);
    }

    //Put Mapping
    @Operation(summary = "Assign a User to A Task and vice-versa", description = "adds a user to Task with Task ID and User")
    @PutMapping("{taskId}/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Task addAUserToATask(@RequestBody User user, UUID taskId){
        return service.assignUserToTask(taskId,user);
    }

    @Operation(summary = "Remove a User from a task and vice-versa", description = "removes a user to task with Task ID and User")
    @PutMapping("{taskId}/removeUser")
    @ResponseStatus(HttpStatus.CREATED)
    public Task removeAFromToATask(@RequestBody User user, UUID taskId){
        return service.removeUserToTask(taskId,user);
    }
}
