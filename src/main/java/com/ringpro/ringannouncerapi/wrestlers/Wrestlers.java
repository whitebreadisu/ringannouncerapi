package com.ringpro.ringannouncerapi.wrestlers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_wrestlers")
public class Wrestlers {

    @Id
    @Column(name = "wrestlerID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    
    public Wrestlers() {
    }

    public Wrestlers(int id, String name, String moniker, String location, String weight, String notes) {
        this.id = id;
        this.name = name;
        this.moniker = moniker;
        this.location = location;
        this.weight = weight;
        this.notes = notes;
    }

    public Wrestlers(String name, String moniker, String location, String weight, String notes) {
        this.name = name;
        this.moniker = moniker;
        this.location = location;
        this.weight = weight;
        this.notes = notes;
    }

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
        return "wrestlers [id=" + id + ", name=" + name + ", moniker=" + moniker + ", location=" + location
                + ", weight=" + weight + ", notes=" + notes + "]";
    }


    
}
