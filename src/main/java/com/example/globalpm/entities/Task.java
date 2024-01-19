package com.example.globalpm.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Task {

    //Variables
    @Id
    @GeneratedValue
    UUID id;

    String name;

    String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    private Goal goal;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(mappedBy = "assignedTasks")
    List<User> users = new ArrayList<>();

    private boolean completionStatus = false;

    //Constructors and other methods
    @Autowired
    public Task(String name, String description, Goal goal) {
        this.name = name;
        this.goal = goal;
        this.description = description;
    }
    public Task(){}

    //Setters and Getters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    @JsonManagedReference
    public List<User> getUsers() {
        return users;
    }
    public void addUser(User user){
        users.add(user);
        setUsers(users);
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }

    @JsonIgnore
    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }
}