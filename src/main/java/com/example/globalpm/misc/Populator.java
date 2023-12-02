package com.example.globalpm.misc;

import com.example.globalpm.data.ProjectRepository;
import com.example.globalpm.data.SubtaskRepository;
import com.example.globalpm.data.TaskRepository;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Subtask;
import com.example.globalpm.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Populator {

    @Autowired
    ProjectRepository ProjectRepository;

    @Autowired
    TaskRepository taskRepo;

    @Autowired
    SubtaskRepository subtaskRepository;

    @Autowired
    public Populator(ProjectRepository IProjectRepository) {
        this.ProjectRepository = IProjectRepository;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void populate(){
        Project p1 = new Project("New Project");
        ProjectRepository.save(p1);
        Task t1 = new Task("task1");
        Task t2 = new Task("task2");
        taskRepo.save(t1);
        taskRepo.save(t2);
        Subtask st1 = new Subtask("ST1", "des", t1);
        Subtask st2 = new Subtask("St2", "des", t2);
        subtaskRepository.save(st1);
        subtaskRepository.save(st2);

//        p1.addTask(t1);
//        p1.addTask(t2);
//        t1.setProject(p1);
//        t2.setProject(p1);

        taskRepo.save(t1);
        taskRepo.save(t2);
        ProjectRepository.save(p1);
    }
}