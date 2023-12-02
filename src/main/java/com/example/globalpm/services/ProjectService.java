package com.example.globalpm.services;
import com.example.globalpm.data.ProjectRepository;
import com.example.globalpm.entities.Project;
import com.example.globalpm.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
//    @Autowired
//    private TaskRepository taskRepository;



    public List<Project> getAllProjects() {
        return projectRepo.findAll();
    }

    public Project createProject(Project project) {
        return projectRepo.save(project);
    }

    public Optional<Project> getProjectById(UUID projectId) {
        return projectRepo.findById(projectId);
    }

    public void addTaskToProject(UUID projectId, Task task) {
        Optional<Project> optionalProject = projectRepo.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            project.addTask(task);
            projectRepo.save(project);
        }
    }

}