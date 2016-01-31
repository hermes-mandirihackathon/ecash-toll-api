package com.mandiriecash.etollapi.controllers;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import java.util.ArrayList;
import java.util.List;

import com.mandiriecash.etollapi.models.User;
import com.mandiriecash.etollapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
    public @ResponseBody
    UserResponse getUserById(@PathVariable int id){
        return new UserResponse("OK", "", userService.getUserById(id), "", 0);
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public @ResponseBody UserResponse createUser(@RequestBody User user){
        userService.createUser(user);
        return new UserResponse("OK", "", new ArrayList<User>(), "", 0);
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public @ResponseBody UserResponse loginUser(@RequestBody User user){
        String token = userService.loginUser(user);
        if(token != null) {
            return new UserResponse("OK", "", new ArrayList<User>(), token, 0);
        }
        return new UserResponse("FAIL", "INVALID USER", new ArrayList<User>(), "", 0);
    }

    @RequestMapping(value="/balance", method = RequestMethod.POST)
    public @ResponseBody UserResponse balanceUser(@RequestBody User user, @RequestBody String token){
        return new UserResponse("OK", "", new ArrayList<User>(), "", userService.balanceInquiry(user, token));
    }
}

class UserResponse{
    public String status;
    public String message;
    public List<User> users;
    public String token;
    public long balance;

    public UserResponse(String status, String message, List<User> users, String token, long balance){
        this.status = status;
        this.message = message;
        this.users = users;
        this.token = token;
        this.balance = balance;
    }
}