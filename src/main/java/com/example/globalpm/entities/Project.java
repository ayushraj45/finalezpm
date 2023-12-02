package com.example.globalpm.entities;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Project {

    @Id
    @GeneratedValue
    Long id;

    private String name;

    @OneToMany (mappedBy = "project")
    List<Task> tasks;

    public Project(String name) {
        this.name = name;
    }

    Project(){}

    public void addTask(Task task) {
        if (tasks == null) {
            tasks = new ArrayList<>();
        }

    }
}