package com.mandiriecash.etollapi.models;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Ichwan Haryo Sembodo on 01/02/2016.
 */
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name = "time")
    private Timestamp time;
    @Column(name = "vehicle_id")
    private int vehicle_id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="source_toll_id")
    private Toll source_toll;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dest_toll_id")
    private Toll dest_toll;
    @Column(name = "price")
    private int price;
    @Column(name = "user_id")
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }

    public Toll getSource_toll() {
        return source_toll;
    }

    public void setSource_toll(Toll source_toll) {
        this.source_toll = source_toll;
    }

    public Toll getDest_toll() {
        return dest_toll;
    }

    public void setDest_toll(Toll dest_toll) {
        this.dest_toll = dest_toll;
    }

    //    public int getSource_toll_id() {
//        return source_toll_id;
//    }
//
//    public void setSource_toll_id(int source_toll_id) {
//        this.source_toll_id = source_toll_id;
//    }

//    public int getDest_toll_id() {
//        return dest_toll_id;
//    }
//
//    public void setDest_toll_id(int dest_toll_id) {
//        this.dest_toll_id = dest_toll_id;
//    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
