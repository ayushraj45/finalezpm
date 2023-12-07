package com.example.globalpm.entities;

import jakarta.persistence.Entity;

@Entity
@SuppressWarnings({"unused", "java:S107"})
public class Owner extends User{

    String access;

    public Owner(String name, Location location, String access) {
        super(name, location);
        this.access = access;
    }

     public Owner(){

     }

}
