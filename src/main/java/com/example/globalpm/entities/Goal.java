package com.example.globalpm.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Goal {

    //Variables
    @Id
    @GeneratedValue
    UUID id;

    private String goalName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(mappedBy = "goal")
    private List<Task> tasks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(mappedBy = "assignedGoals")
    List<User> users = new ArrayList<>();

    //Constructors and Methods
    public Goal(String goalName) {
        // this.project=project;
        this.goalName = goalName;
    }

    public Goal() {}

    //Setters and Getters


    public UUID getId() {
        return id;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @JsonManagedReference
    public Project getProject() {
        return project;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    @JsonManagedReference
    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
            tasks.add(task);
            setTasks(tasks);
    }


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

    public void setProject(Project project) {
        this.project=project;
    }
}
