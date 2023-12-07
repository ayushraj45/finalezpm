package com.example.globalpm.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    // @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "goal")
    private List<Task> tasks;
    private Double progress;
    private boolean isCompleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime endDate;
    @ManyToMany(mappedBy = "goals")
    private List<User> users;

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

    public Project getProject() {
        return project;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public void setProject(Project project) {
        this.project=project;
    }
}
