package com.ringpro.models;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_wrestlers")    
public class Wrestler {

    @Id
    @Column(name = "wrestlerID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "wrestler_name")
    private String name;
    @Column(name = "wrestler_moniker")
    private String moniker;
    @Column(name = "wrestler_location")
    private String location;
    @Column(name = "wrestler_weight")
    private String weight;
    @Column(name = "wrestler_notes")
    private String notes;


    // constructors
    public Wrestler() {
    }

    public Wrestler(int id, String name, String moniker, String location, String weight, String notes) {
        this.id = id;
        this.name = name;
        this.moniker = moniker;
        this.location = location;
        this.weight = weight;
        this.notes = notes;
    }

    public Wrestler(String name, String moniker, String location, String weight, String notes) {
        this.name = name;
        this.moniker = moniker;
        this.location = location;
        this.weight = weight;
        this.notes = notes;
    }

    //getters and setters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMoniker() {
        return moniker;
    }

    public String getLocation() {
        return location;
    }

    public String getWeight() {
        return weight;
    }

    public String getNotes() {
        return notes;
    }

        public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoniker(String moniker) {
        this.moniker = moniker;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "Wrestler [id=" + id + ", name=" + name + ", moniker=" + moniker + ", location=" + location + ", weight="
                + weight + ", notes=" + notes + "]";
    }


   
}
