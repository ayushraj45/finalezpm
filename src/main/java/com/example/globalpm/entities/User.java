package com.example.globalpm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Embedded
    Location userLocation;

    private String name;

    private String status = "online";

    @ManyToMany
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    List<Project> assignedProjects = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_goal",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "goal_id")
    )
    List<Goal> assignedGoals = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "user_task",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    List<Task> assignedTasks = new ArrayList<>();

    //Constructors
    public User() {}

    public User(String name){
        this.name=name;
    }

    //Getters and Setters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonIgnore
    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }
    @JsonIgnore
    public List<Goal> getAssignedGoals() {
        return assignedGoals;
    }
    @JsonIgnore
    public List<Task> getAssignedTasks() {
        return assignedTasks;
    }

    public void setAssignedGoals(List<Goal> assignedGoals) {
        this.assignedGoals = assignedGoals;
    }

    public void setAssignedTasks(List<Task> assignedTasks) {
        this.assignedTasks = assignedTasks;
    }

    public void setAssignedProjects(List<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public void assignProject(Project project){
        assignedProjects.add(project);
        setAssignedProjects(assignedProjects);
    }
    public void assignGoal(Goal goal){
        assignedGoals.add(goal);
        setAssignedGoals(assignedGoals);
    }
    public void assignTask(Task task){
        assignedTasks.add(task);
        setAssignedTasks(assignedTasks);
    }
   // @JsonManagedReference
    public Location getUserLocation() {
        return userLocation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }
}
