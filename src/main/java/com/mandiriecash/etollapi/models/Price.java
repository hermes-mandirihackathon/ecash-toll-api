package com.mandiriecash.etollapi.models;

import javax.persistence.*;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
@Entity
@Table(name = "prices")
public class Price {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @OneToOne
    @JoinColumn(name = "source_toll_id")
    private Toll source_toll_id;
    @OneToOne
    @JoinColumn(name = "dest_toll_id")
    private Toll dest_toll_id;
    @Column(name = "category")
    private int category;
    @Column(name = "price")
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Toll getSource_toll_id() {
        return source_toll_id;
    }

    public void setSource_toll_id(Toll source_toll_id) {
        this.source_toll_id = source_toll_id;
    }

    public Toll getDest_toll_id() {
        return dest_toll_id;
    }

    public void setDest_toll_id(Toll dest_toll_id) {
        this.dest_toll_id = dest_toll_id;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
