package com.ringpro.ringannouncerapi.matches;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_matches")
public class Matches {
    
    @Id
    @Column(name = "matchID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "wrestlerID_1")
    private int wrestlerID_1;
    @Column(name = "wrestlerID_2")
    private int wrestlerID_2;
    @Column(name = "matchtype")
    private String matchtype;
    @Column(name = "timelimit")
    private String timelimit;
    @Column(name = "fallrule")
    private String fallrule;
    
    public Matches() {
    }

    public Matches(int wrestlerID_1, int wrestlerID_2, String matchtype, String timelimit, String fallrule) {
        this.wrestlerID_1 = wrestlerID_1;
        this.wrestlerID_2 = wrestlerID_2;
        this.matchtype = matchtype;
        this.timelimit = timelimit;
        this.fallrule = fallrule;
    }

    public int getId() {
        return id;
    }

    public int getWrestlerID_1() {
        return wrestlerID_1;
    }

    public int getWrestlerID_2() {
        return wrestlerID_2;
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

    public void setWrestlerID_1(int wrestlerID_1) {
        this.wrestlerID_1 = wrestlerID_1;
    }

    public void setWrestlerID_2(int wrestlerID_2) {
        this.wrestlerID_2 = wrestlerID_2;
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
        return "Matches [id=" + id + ", wrestlerID_1=" + wrestlerID_1 + ", wrestlerID_2=" + wrestlerID_2
                + ", matchtype=" + matchtype + ", timelimit=" + timelimit + ", fallrule=" + fallrule + "]";
    }



}
