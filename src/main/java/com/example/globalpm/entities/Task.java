package com.example.globalpm.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
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
    @ManyToOne
    private Goal goal;
    private Double progress;
    private boolean isCompleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime endDate;
    @ManyToMany(mappedBy = "tasks")
    private List<User> users;
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

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}