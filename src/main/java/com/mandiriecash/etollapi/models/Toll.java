package com.mandiriecash.etollapi.models;

import javax.persistence.*;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
@Entity
@Table(name="tolls")
public class Toll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "name")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
