package com.ringpro.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// This is the model class for the wrestler object.  An instance of the Wrestler class represents a wrestler object.
// wrestler.java defines the wrestler model class and all the things you can do with the wrestler class
// What can we do with the Wrestler class?  Not a ton... create an instance of the wrestler class with constructors,
// getters and setters for the elements within the class, and @override that I don't really know what it does



// @Entity is used in spring apps to declare 'hey man, this is like a record in a database we should treat it that way'
// @Table points us to the SQL table containing records that are for the wrestler model class
@Entity
@Table(name = "tbl_wrestlers")    
public class Wrestler {


    // @Id is declaring 'hey the next variable of the Wrestler model class is the primary key within the associated database table'
    // @column is used to let the wrestler model class know for each class variable which table column the variable correlates to
    // example.... the java object of wrestler has wrestler.name which correlates to [tbl_wrestler].[wrestler_name]
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
        return "wrestler [id=" + id + ", name=" + name + ", moniker=" + moniker + ", location=" + location
                + ", weight=" + weight + ", notes=" + notes + "]";
    }


    
}
