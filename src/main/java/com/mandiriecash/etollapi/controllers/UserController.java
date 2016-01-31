package com.mandiriecash.etollapi.controllers;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import java.util.List;

import com.mandiriecash.etollapi.models.User;
import com.mandiriecash.etollapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public @ResponseBody
    String getUserById(@PathVariable("id") int id){
        return this.userService.getUserById(id).toString();
    }
}

class UserResponse{
    String messages;
    String status;
    List<User> users;

    public UserResponse(String messages, String status, List<User> users){
        this.messages = messages;
        this.status = status;
        this.users = users;
    }
}