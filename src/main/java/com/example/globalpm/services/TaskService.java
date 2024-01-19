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

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    TaskRepository repository;
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    GoalRepository goalRepository;

    @Autowired
    UserRepository userRepository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAllTask(){
        return repository.findAll();
    }

    public List<Task> findTaskByGoalId(UUID goalId){
        return repository.findAllByGoalId(goalId);
    }

    public Task updateATask(Task goal) {
        return repository.save(goal);
    }

    public List<User> findUsersInATask(UUID taskId) {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        if(task.getUsers().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no users in the project with ID: " + taskId);
        }
        return task.getUsers();
    }

    public Task addATask(Task task) {
        if (task == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task to add cannot be null");
        }
        else if(task.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task to add cannot have an ID");
        }
        else if(goalRepository.existsById(task.getGoal().getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot add Task to a Goal that doesn't exist");
        }
        return repository.save(task);
    }

    public Task assignUserToTask(UUID taskId, User user) {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + taskId));
        User userUO= userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with user id: " + user.getId()));
        task.addUser(userUO);
        userUO.assignTask(task);
        userRepository.save(userUO);
        return repository.save(task);
    }

    public Task removeUserToTask(UUID taskId, User user) {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found with id: " + taskId));
        User userUO= userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found with user id: " + user.getId()));
        List<User> taskUsers = task.getUsers();
        Iterator<User> itr = taskUsers.iterator();
        while(itr.hasNext()){
            User userToRemove = itr.next();
            if(userToRemove.equals(userUO)){
                itr.remove();
            }
            else throw new RuntimeException("This user doesn't exist in the task");
        }
        task.setUsers(taskUsers);
        userUO.removeFromTask(task);
        userRepository.save(userUO);
        return repository.save(task);
    }
}
