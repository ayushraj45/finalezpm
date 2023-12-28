package com.example.globalpm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;


    @OneToMany(mappedBy = "goal")
    private List<Task> tasks;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToMany(mappedBy = "assignedGoals")
    List<User> users = new ArrayList<>();

    private Double goalProgress = 0.0;
    private boolean completionStatus = false;

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

    @JsonIgnore
    public Project getProject() {
        return project;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

    public void addTask(Task task) {
            tasks.add(task);
            setTasks(tasks);
    }
    @JsonIgnore
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

    public Double getGoalProgress() {
        calcGoalProgress();
        return goalProgress;
    }

    public void setGoalProgress(Double goalProgress) {
        this.goalProgress = goalProgress;
    }

    public boolean getCompletionStatus() {
        return completionStatus;
    }

    public void setCompletionStatus(boolean completionStatus) {
        this.completionStatus = completionStatus;
    }

    public void calcGoalProgress(){
        int numOfTask = tasks.size();
        double progressFromEachTask = 100/numOfTask;
        List<Task> completedTasks = new ArrayList<>();
        for (Task task: tasks) {
            if(task.isCompletionStatus()==true)
                completedTasks.add(task);
        }
          double goalProgress = progressFromEachTask * completedTasks.size();
            setGoalProgress(goalProgress);
    }
}
