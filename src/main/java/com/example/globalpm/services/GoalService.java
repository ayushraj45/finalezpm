package com.example.globalpm.services;
import com.example.globalpm.data.GoalRepository;
import com.example.globalpm.data.ProjectRepository;
import com.example.globalpm.data.TaskRepository;
import com.example.globalpm.data.UserRepository;
import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Task;
import com.example.globalpm.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class GoalService {

    @Autowired
    GoalRepository goalRepo;

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    ProjectRepository projRepo;

    @Autowired
    UserRepository userRepository;

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

    public List<User> findUsersInAGoal(UUID goalId) {
        Goal goal = goalRepo.findById(goalId)
                .orElseThrow(() -> new RuntimeException("Goal not found with id: " + goalId));
        if(goal.getUsers().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no users in the project with ID: " + goalId);
        }
        return goal.getUsers();
    }

    public Goal addAGoalInAProject(Goal goal) {
        if (goal == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Goal to add cannot be null");
        }
        else if(goal.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Goal to add cannot have an ID");
        }
        return goalRepo.save(goal);
    }

    public Goal addTaskToAGoal(UUID id, Task task) {

            Goal goalToAddTheTaskIn;
            goalToAddTheTaskIn = goalRepo.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Goal to add task in not found"));
            task.setGoal(goalToAddTheTaskIn);
            taskRepository.save(task);
            goalToAddTheTaskIn.addTask(task);
            return goalRepo.save(goalToAddTheTaskIn);
        }

    public double calcGoalProgress(Goal goal){
        List<Task> tasksToCalc = goal.getTasks();
        List<Task> completedTasks = new ArrayList<>();
        int numOfTask = tasksToCalc.size();
        double progressFromEachTask;
        if(numOfTask == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No tasks in the Goal.");
        }
        else {
            progressFromEachTask = 100 / numOfTask;
            for (Task task : tasksToCalc) {
                if (task.isCompletionStatus() == true)
                    completedTasks.add(task);
            }
        }
        double goalProgress = progressFromEachTask * completedTasks.size();
        goal.setGoalProgress(goalProgress);
        return goalProgress;
    }

    public Goal assignUserToGoal(UUID goalId, User user) {
        Goal goal = goalRepo.findById(goalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Goal not found with id: " + goalId));
        User userUO= userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with user id: " + user.getId()));
        goal.addUser(userUO);
        userUO.assignGoal(goal);
        userRepository.save(userUO);
        return goalRepo.save(goal);
    }

    public Goal removeUserToGoal(UUID goalId, User user) {
        Goal goal = goalRepo.findById(goalId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Goal not found with id: " + goalId));
        User userUO= userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with user id: " + user.getId()));
        List<User> goalUsers= goal.getUsers();
        Iterator<User> itr = goalUsers.iterator();
        while(itr.hasNext()){
            User userToRemove = itr.next();
            if(userToRemove.equals(userUO)){
                itr.remove();
            }
            else throw new RuntimeException("This user doesn't exist in the goal");
        }
        goal.setUsers(goalUsers);
        userUO.removeFromGoal(goal);
        userRepository.save(userUO);
        return goalRepo.save(goal);
    }
    }