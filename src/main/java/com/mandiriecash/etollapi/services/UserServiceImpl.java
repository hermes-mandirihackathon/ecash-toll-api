package com.mandiriecash.etollapi.services;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import com.mandiriecash.etollapi.dao.UserDAO;
import com.mandiriecash.etollapi.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDAO userDAO;

    public List<User> getUserById(int id) {
        // TODO Auto-generated method stub
        List users = new ArrayList<User>();
        User user = userDAO.getUserById(id);
        System.out.println(user.toString());
        users.add(user);
        return users;
    }

    public void createUser(User user) {
        userDAO.createUser(user);
    }

    public String loginUser(User user) {
        //tembak ke server mandiri hackathon
        //return token
        return null;
    }

    public long balanceInquiry(User user, String token) {
        //tembak ke server mandiri hackathon
        return 0;
    }
}