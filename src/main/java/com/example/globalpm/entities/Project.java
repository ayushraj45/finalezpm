package com.example.globalpm.entities;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
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
    private List<Goal> goals;

    private Double progress;
    private boolean isCompleted;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime endDate;
    @ManyToMany(mappedBy = "projects")
    private List<User> users;
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

    public Project(String name) {
        this.name = name;
    }

    //Constructors and Other Methods
    Project(){}

    public void addGoal(Goal goal) {
        if (goals == null) {
            goals = new ArrayList<>();
            goals.add(goal);
        }
        else
            goals.add(goal);
    }
}