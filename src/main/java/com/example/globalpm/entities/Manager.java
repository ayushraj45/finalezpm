package com.example.globalpm.entities;

import jakarta.persistence.Entity;

@Entity
public class Manager extends User{

    String access;

    public Manager(String name, Location location, String access) {
        super(name, location);
        this.access = access;
    }

    public Manager(String access) {

    }
}
