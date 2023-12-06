package com.example.globalpm.services;
import com.example.globalpm.data.GoalRepository;
import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepo;

    @Autowired
    public GoalService(GoalRepository goalRepo) {
        this.goalRepo = goalRepo;
    }

    public List<Goal> getAllGoals(){
        return goalRepo.findAll();
    }

    public List<Goal> getAllGoalInProject(UUID id) {
        return goalRepo.findGoalsByProjectId(id);
    }


}