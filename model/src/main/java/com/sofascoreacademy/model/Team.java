package com.sofascoreacademy.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Team implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;

    public Team (int id, String name) {
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


