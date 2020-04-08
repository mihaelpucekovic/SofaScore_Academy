package com.sofascoreacademy.model;

import java.io.Serializable;

public class Event implements Serializable {

    private int id;
    private Team homeTeam;
    private Team awayTeam;

    public Event (int id, Team homeTeam, Team awayTeam) {
        this.id = id;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public int getId() {
        return id;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }
}

