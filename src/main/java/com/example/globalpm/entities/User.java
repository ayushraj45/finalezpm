package com.example.globalpm.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue
    private UUID id;
    @Embedded
    Location userLocation;

    private String name;

    @ManyToMany
    @JoinTable(name = "user_project",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    List<Project> assignedProjects = new ArrayList<>();

    //Constructors
    public User() {}

    public User(String name){
        this.name=name;
    }

    //Getters and Setters
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Project> getAssignedProjects() {
        return assignedProjects;
    }

    public void setAssignedProjects(List<Project> assignedProjects) {
        this.assignedProjects = assignedProjects;
    }

    public void assignProject(Project project){
        assignedProjects.add(project);
        setAssignedProjects(assignedProjects);
    }

    public Location getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(Location userLocation) {
        this.userLocation = userLocation;
    }
}
