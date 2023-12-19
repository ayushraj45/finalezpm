package com.example.globalpm.entities;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @ManyToMany(mappedBy = "assignedProjects")
    List<User> users = new ArrayList<>();


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

    @JsonManagedReference
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        users.add(user);
        setUsers(users);
    }
}