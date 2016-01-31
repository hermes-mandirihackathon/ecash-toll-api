package com.mandiriecash.etollapi.services;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import com.mandiriecash.etollapi.dao.UserDAO;
import com.mandiriecash.etollapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    public User getUserById(int id) {
        // TODO Auto-generated method stub
        return userDAO.getUserById(id);
    }
}