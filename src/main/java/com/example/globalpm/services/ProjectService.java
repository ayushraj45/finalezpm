package com.example.globalpm.services;
import com.example.globalpm.data.GoalRepository;
import com.example.globalpm.data.ProjectRepository;
import com.example.globalpm.data.TaskRepository;
import com.example.globalpm.data.UserRepository;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Task;
import com.example.globalpm.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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

    @Autowired
    private UserRepository userRepository;

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
        List<Task> numOfTasks = getAllTasksInProject(projectId);
        List<Task> completedTasks = new ArrayList<>();
        double progressFromEachTask = (double) 100 / numOfTasks.size();
        if(numOfTasks.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no tasks in the project, please add some to continue, Project ID: " + projectId);
        }
        else{
            for (Task task : numOfTasks) {
                if(task.isCompletionStatus())
                    completedTasks.add(task);
            }
        }
        double projectProgress = progressFromEachTask * completedTasks.size();
        project.setProjectProgress(projectProgress);
        return project.getProjectProgress();
    }

    public double calcProjectProgress(Project project){
        List<Goal> goalsToCalc = project.getGoals();
        int numOfGoals = goalsToCalc.size();
        double partialProgress = 0;
        double progressFromEachGoal = (double) 100 /numOfGoals;
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

    public Project addUserToProject(UUID projectId, User user) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        User userUO= userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with user id: " + user.getId()));
        project.addUser(userUO);
        userUO.assignProject(project);
        userRepository.save(userUO);
        return projectRepo.save(project);
    }

    public Project removeUserFromProject(UUID projectId, User user) {
        Project project = projectRepo.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found with id: " + projectId));
        User userUO= userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with user id: " + user.getId()));
        List<User> projectUsers = project.getUsers();
        Iterator<User> itr = projectUsers.iterator();
        while(itr.hasNext()){
            User userToRemove = itr.next();
            if(userToRemove.equals(userUO)){
                itr.remove();
            }
            else throw new RuntimeException("This user doesn't exist in the project");
        }
        project.setUsers(projectUsers);
        userUO.removeFromProject(project);
        userRepository.save(userUO);
        return projectRepo.save(project);
    }
}