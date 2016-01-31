package com.mandiriecash.etollapi.models;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Created by Ichwan Haryo Sembodo on 27/01/2016.
 */


@Entity
@Table(name="users")
public class User implements Serializable{
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;

    @Column(name="phone_no")
    private String phone_no;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPhone_no() {
        return phone_no;
    }
    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", phone_no=" + phone_no + "]";
    }

}

