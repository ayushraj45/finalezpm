package com.example.globalpm.entities;

import jakarta.persistence.Entity;

//@Entity
public class Location {

    String country;

    public Location(String country) {
        this.country = country;
    }
}
