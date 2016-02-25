package com.mandiriecash.etollapi.dao;

import com.mandiriecash.etollapi.models.User;

/**
 * Created by Ichwan Haryo Sembodo on 27/01/2016.
 */
public interface UserDAO {
    public User getUserById(int id);
    public void createUser(User user);

    /**
     * Update the user
     * @param user user
     */
    void updateUser(User user);
    /**
     * Return user. return null if doesn't exist.
     * @param msisdn
     * @return
     */
    public User getUserByMsisdn(String msisdn);
}
