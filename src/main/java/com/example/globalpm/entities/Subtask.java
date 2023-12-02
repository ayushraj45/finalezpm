package com.example.globalpm.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class Subtask {

    @Id
    @GeneratedValue
    Long id;

    String name;

    String description;

    @ManyToOne
    private Task task;

    @Autowired
    public Subtask(String name, String description, Task task) {
        this.name = name;
        this.task = task;
        this.description = description;
    }

    public Subtask(){}
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}