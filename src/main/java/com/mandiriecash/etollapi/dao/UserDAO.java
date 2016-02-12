package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.User;

/**
 * Created by Ichwan Haryo Sembodo on 27/01/2016.
 */
public interface UserDAO {
    public User getUserById(int id);
    public void createUser(User user);
    public User getUserByMsisdn(String msisdn);
}
