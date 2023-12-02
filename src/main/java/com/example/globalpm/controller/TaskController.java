package com.example.globalpm.controller;

import com.example.globalpm.entities.Task;
import com.example.globalpm.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("")
    public List<Task> getAllTasks (){
        return service.getAllTasks();
    }

    @GetMapping("/projectId/{id}")
    public List<Task> getAllTaskByProjectId(@PathVariable UUID id){
        return service.getAllTaskInProject(id);
    }

}
