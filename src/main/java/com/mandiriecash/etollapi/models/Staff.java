package com.mandiriecash.etollapi.models;

import javax.persistence.*;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */

@Entity
@Table(name="staffs")
public class Staff {
    @Id
    @GeneratedValue
    @Column(name="id")
    private int id;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;
    @Column(name="toll_id")
    private int toll_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getToll_id() {
        return toll_id;
    }

    public void setToll_id(int toll_id) {
        this.toll_id = toll_id;
    }
}
