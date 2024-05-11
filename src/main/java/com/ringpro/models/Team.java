package com.ringpro.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "tbl_teams")
public class Team {
    
    @Id
    @Column(name = "teamID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "wrestlerID")
    private int wrestlerID;
    @Column(name = "matchID")
    private int matchID;
    @Column(name = "team")
    private int team;

    
    public Team(int id, int wrestlerID, int matchID, int team) {
        this.id = id;
        this.wrestlerID = wrestlerID;
        this.matchID = matchID;
        this.team = team;
    }
    
    public Team(int wrestlerID, int matchID, int team) {
        this.wrestlerID = wrestlerID;
        this.matchID = matchID;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWrestlerID() {
        return wrestlerID;
    }

    public void setWrestlerID(int wrestlerID) {
        this.wrestlerID = wrestlerID;
    }

    public int getMatchID() {
        return matchID;
    }

    public void setMatchID(int matchID) {
        this.matchID = matchID;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "WrestlerMatch [id=" + id + ", wrestlerID=" + wrestlerID + ", matchID=" + matchID + ", team=" + team
                + "]";
    }

    

}
