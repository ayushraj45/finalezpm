package com.example.globalpm.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;

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