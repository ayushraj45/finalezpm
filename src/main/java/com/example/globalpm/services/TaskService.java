package com.example.globalpm.services;
import com.example.globalpm.data.TaskRepository;
import com.example.globalpm.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    public TaskService(TaskRepository taskRepo) {
        this.taskRepo = taskRepo;
    }

    public List<Task> getAllTasks(){
        return taskRepo.findAll();
    }

    public List<Task> getAllTaskInProject(UUID id) {
        return taskRepo.findTasksByProjectId(id);
    }
}