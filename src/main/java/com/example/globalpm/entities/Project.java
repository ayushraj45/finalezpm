package com.example.globalpm.entities;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    //variables
    @Id
    @GeneratedValue
    Long id;

    private String name;

    @OneToMany (mappedBy = "project")
    List<Task> tasks;

    //Setters and Getters


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

    public void addTask(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

    }
}