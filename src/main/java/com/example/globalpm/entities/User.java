package com.example.globalpm.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class User {

    //Variables
    @Id
    @GeneratedValue
    UUID id;
    String name;

    Location location;

    @ManyToMany(mappedBy = "users")
    List<Project> projects;
    @ManyToMany(mappedBy = "users")
    List<Goal> goals;
    @ManyToMany(mappedBy = "users")
    List<Task> tasks;

    //Constructors


    public User(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public User(){

    }
}
