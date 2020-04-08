package com.sofascoreacademy.model;

import java.io.Serializable;

public class Tournament implements Serializable {

    private int id;
    private String name;

    public Tournament (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


