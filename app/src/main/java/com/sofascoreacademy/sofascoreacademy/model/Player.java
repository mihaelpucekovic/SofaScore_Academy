package com.sofascoreacademy.sofascoreacademy.model;

import com.sofascoreacademy.model.Team;

import java.io.Serializable;

public class Player implements Serializable {

    private int id;
    private String name;
    private Team team;

    public Player (int id, String name, Team team) {
        this.id = id;
        this.name = name;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Team getTeam() {
        return team;
    }
}
