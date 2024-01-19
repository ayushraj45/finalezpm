package com.example.globalpm.services;
import com.example.globalpm.data.GoalRepository;
import com.example.globalpm.data.ProjectRepository;
import com.example.globalpm.data.TaskRepository;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Task;
import com.example.globalpm.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {



    ProjectRepository projectRepo;
    @Autowired
    public ProjectService(ProjectRepository IProjectRepository) {
        this.projectRepo = IProjectRepository;
    }

//    @Autowired
//    private UserRepository userRepository;
//
    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private GoalService goalService;

    @Autowired
    private TaskRepository taskRepository;

    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    public Optional<Project> getProjectById(UUID projectId) {
        return projectRepo.findById(projectId);
    }

    public List<Task> getAllTasksInProject(UUID projectId) {
        List<Goal> allGoals = goalRepository.findGoalsByProjectId(projectId);
        List<Task> tasksToDisplay = new ArrayList<>();
        for (Goal currentGoal : allGoals) {
             List<Task> allTasks = taskRepository.findAllByGoalId(currentGoal.getId());
            for (Task currentTask: allTasks) {
                    tasksToDisplay.add(currentTask);
            }
        }
        return tasksToDisplay;
    }

    public Project addGoalToProject(UUID projectId, Goal goal) {
        Project projectToAddGoal;
        projectToAddGoal = projectRepo.findById(projectId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Project to add Goal to not found"));
        goal.setProject(projectToAddGoal);
        goalRepository.save(goal);
            projectToAddGoal.addGoal(goal);
            return createProject(projectToAddGoal);
    }

    public List<User> findUsersInAProject(UUID projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        if(project.getUsers().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no users in the project with ID: " + projectId);
        }
        return project.getUsers();
    }

    public Project addAProject(Project project) {
        if (project == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project to add cannot be null");
        }
        else if(project.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project to add cannot have an ID");
        }
        return projectRepo.save(project);
    }

    public Double getProjectProgressWithGoal(UUID projectId) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        if(project.getGoals().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no goals in the project with ID: " + projectId);
        }
        double projectProgress = calcProjectProgress(project);
        project.setProjectProgress(projectProgress);
        return project.getProjectProgress();
    }

    public double calcProjectProgress(Project project){
        List<Goal> goalsToCalc = project.getGoals();
        int numOfGoals = goalsToCalc.size();
        double partialProgress = 0;
        double progressFromEachGoal = 100/numOfGoals;
        List<Goal> completedGoals = new ArrayList<>();
        for (Goal goal: goalsToCalc) {
            if(goal.getCompletionStatus() == true)
            {completedGoals.add(goal);}
            else{
                double goalProgress = goalService.calcGoalProgress(goal);
                partialProgress += (goalProgress/100) * progressFromEachGoal;
            }
        }
        return partialProgress + (progressFromEachGoal * completedGoals.size());
    }
}