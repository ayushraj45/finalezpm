package com.example.globalpm.services;
import com.example.globalpm.data.GoalRepository;
import com.example.globalpm.data.ProjectRepository;
import com.example.globalpm.data.TaskRepository;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Goal;
import com.example.globalpm.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void addGoalToProject(UUID projectId, Goal goal) {
        Optional<Project> optionalProject = projectRepo.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.addGoal(goal);
            projectRepo.save(project);
        }
    }

}