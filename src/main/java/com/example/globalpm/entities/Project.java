package com.example.globalpm.entities;
import jakarta.persistence.*;
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