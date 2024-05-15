package com.ringpro.models;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_teams")
public class Team {

    @Id
    @Column(name = "teamID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private int id;

    // @Column(name = "wrestlerID")
    // private int wrestlerID;
    // @Column(name = "matchID")
    // private int matchID;
    @Column(name = "team")
    private int team;

    //----------------------------------------------------
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="matchID", referencedColumnName = "matchID")
    private Match match;

    public Match getMatch() {
        return match;
    }

    //----------------------------------------------------

    public Team() {
    }

    public Team(int id, int wrestlerID, int matchID, int team) {
        this.id = id;
        // this.wrestlerID = wrestlerID;
        // this.matchID = matchID;
        this.team = team;
    }

    public Team(int wrestlerID, int matchID, int team) {
        // this.wrestlerID = wrestlerID;
        // this.matchID = matchID;
        this.team = team;
    }

    public Team(int id, int team) {
        this.id = id;
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public int getWrestlerID() {
//        return wrestlerID;
//    }

//    public void setWrestlerID(int wrestlerID) {
//        this.wrestlerID = wrestlerID;
//    }

//    public int getMatchID() {
//        return matchID;
//    }

//    public void setMatchID(int matchID) {
//        this.matchID = matchID;
//    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "Team [id=" + id + ", team=" + team + "]";
    }


//    @Override
//    public String toString() {
//        return "Team [id=" + id + ", team=" + team + ", wrestlers=" + wrestlers + "]";
//    }

//    @Override
//    public String toString() {
//        return "Team [id=" + id + ", wrestlerID=" + wrestlerID + ", matchID=" + matchID + ", team=" + team + "]";
//    }



}
