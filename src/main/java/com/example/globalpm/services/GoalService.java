package com.example.globalpm.services;
import com.example.globalpm.data.GoalRepository;
import com.example.globalpm.entities.Goal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id cannot be null");
        }
        return goalRepo.findGoalsByProjectId(id);
    }


}