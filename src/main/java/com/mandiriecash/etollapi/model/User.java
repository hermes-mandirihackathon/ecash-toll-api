package com.mandiriecash.etollapi.model;

/**
 * Created by Ichwan Haryo Sembodo on 27/01/2016.
 */
public class User {
    int id;
    String phone_no;

    public User(int id, String phone_no) {
        this.id = id;
        this.phone_no = phone_no;
    }

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
}
