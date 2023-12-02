package com.example.globalpm.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Task {

    //Variables
    @Id
    @GeneratedValue
    UUID id;

    private String taskName;
    @ManyToOne
    // @JoinColumn(name = "project_id")
    private Project project;

    @OneToMany(mappedBy = "task")
    private List<Subtask> subtasks;

    //Constructors and Methods
    public Task(String taskName) {
        // this.project=project;
        this.taskName = taskName;
    }

    public Task() {}

    //Setters and Getters


    public UUID getId() {
        return id;
    }

    public void setSubtasks(List<Subtask> subtasks) {
        this.subtasks = subtasks;
    }

    public Project getProject() {
        return project;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setProject(Project project) {
        this.project=project;
    }
}
