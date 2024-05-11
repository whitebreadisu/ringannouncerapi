package com.ringpro.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_matches")
public class Match {
    
    @Id
    @Column(name = "matchID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "matchtype")
    private String matchtype;
    @Column(name = "timelimit")
    private String timelimit;
    @Column(name = "fallrule")
    private String fallrule;
    
    public Match() {
    }

    public Match(int id, String matchtype, String timelimit, String fallrule) {
        this.id = id;
        this.matchtype = matchtype;
        this.timelimit = timelimit;
        this.fallrule = fallrule;
    }

    public Match(String matchtype, String timelimit, String fallrule) {
        this.matchtype = matchtype;
        this.timelimit = timelimit;
        this.fallrule = fallrule;
    }

    public int getId() {
        return id;
    }

    public String getMatchtype() {
        return matchtype;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public String getFallrule() {
        return fallrule;
    }

    public void setMatchtype(String matchtype) {
        this.matchtype = matchtype;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }

    public void setFallrule(String fallrule) {
        this.fallrule = fallrule;
    }

    @Override
    public String toString() {
        return "Match [id=" + id + ", matchtype=" + matchtype + ", timelimit=" + timelimit + ", fallrule=" + fallrule + "]";
    }



}
