package com.example.globalpm.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Project {

    //variables
    @Id
    @GeneratedValue
    UUID id;

    private String name;

    @OneToMany (mappedBy = "project")
    List<Goal> goals;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(mappedBy = "assignedProjects")
    List<User> users = new ArrayList<>();

    private Double projectProgress;
    @Value("false")
    private boolean completionStatus = false;

    //Setters and Getters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getProjectProgress() {
        return projectProgress;
    }

    public void setProjectProgress(Double projectProgress) {
        this.projectProgress = projectProgress;
    }

    public boolean getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    @JsonManagedReference
    public List<Goal> getGoals() {
        return goals;
    }

    //Constructors and Other Methods
    Project(){}
    public Project(String name) {
        this.name = name;
    }

    public void addGoal(Goal goal) {
        if (goals == null) {
            goals = new ArrayList<>();
            goals.add(goal);
        }
        else
            goals.add(goal);
    }

    @JsonManagedReference
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
        setUsers(users);
    }

}

