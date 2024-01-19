package com.example.globalpm.misc;

import com.example.globalpm.data.ProjectRepository;
import com.example.globalpm.data.TaskRepository;
import com.example.globalpm.data.GoalRepository;
import com.example.globalpm.data.UserRepository;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Task;
import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Populator {

    @Autowired
    ProjectRepository ProjectRepository;

    @Autowired
    GoalRepository goalRepo;

    @Autowired
    TaskRepository goalRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public Populator(ProjectRepository IProjectRepository) {
        this.ProjectRepository = IProjectRepository;
    }

    //EventListener(ContextRefreshedEvent.class)
    public void populate(){
        Project p1 = new Project("New Project");
        ProjectRepository.save(p1);
        Goal t1 = new Goal("goal1");
        Goal t2 = new Goal("goal2");
        goalRepo.save(t1);
        goalRepo.save(t2);
        Task st1 = new Task("ST1", "des", t1);
        Task st2 = new Task("St2", "des", t2);
        goalRepository.save(st1);
        goalRepository.save(st2);

        p1.addGoal(t1);
        p1.addGoal(t2);
        t1.setProject(p1);
        t2.setProject(p1);

        User u1 = new User("Ayush");
        u1.assignProject(p1);
        userRepository.save(u1);
        p1.addUser(u1);
        goalRepo.save(t1);
        goalRepo.save(t2);
        ProjectRepository.save(p1);
    }
}