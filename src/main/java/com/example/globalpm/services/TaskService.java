package com.example.globalpm.services;

import com.example.globalpm.data.ProjectRepository;
import com.example.globalpm.data.TaskRepository;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    TaskRepository repository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAllTask(){
        return repository.findAll();
    }

    public List<Task> findTaskByGoalId(UUID goalId){
        return repository.findAllByGoalId(goalId);
    }

    public Task addANewTask(Task goal) {
        return repository.save(goal);
    }

    public Task updateATask(Task goal) {
        return repository.save(goal);
    }
}
