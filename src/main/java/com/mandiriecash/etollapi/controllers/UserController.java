package com.mandiriecash.etollapi.controllers;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mandiriecash.etollapi.models.User;
import com.mandiriecash.etollapi.mea.exceptions.MEALoginFailedException;
import com.mandiriecash.etollapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    static final String OK = "ok";
    static final String ERROR = "error";

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

    @RequestMapping(value="/login", method = RequestMethod.GET)
    //TODO change to POST
    /**
     * Example call: http://localhost:8080/ecashtollapi/login?msisdn=085712345678&credentials=xxxxxx
     */
    public @ResponseBody UserResponse loginUser(
            @RequestParam(name = "id",required = false) String uid,
            @RequestParam(name = "msisdn",required = true) String msisdn,
            @RequestParam(name = "credentials",required = true) String credentials){
        UserResponse userResponse;
        try {
            //TODO uid buat apa???
            String token = userService.loginUser(uid,msisdn,credentials);
            userResponse = new UserResponse(OK,"",null,token,0);
        } catch (IOException e) {
            userResponse = new UserResponse(ERROR,"Error while contacting Mandiri API");
            e.printStackTrace();
        } catch (MEALoginFailedException e) {
            userResponse = new UserResponse(ERROR,e.getMessage());
        }
        return userResponse;
    }

    @RequestMapping(value="/balance", method = RequestMethod.POST)
    public @ResponseBody UserResponse balanceUser(@RequestBody User user, @RequestBody String token){
        return new UserResponse("OK", "", new ArrayList<User>(), "", userService.balanceInquiry(user, token));
    }
}

class LoginRequest{
    private String uid;
    private String msisdn;
    private String credentials;

    public String getUid() {
        return uid;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public String getCredentials() {
        return credentials;
    }
}

class UserResponse{
    public final String status;
    public final String message;
    //TODO ini apaan
    public final List<User> users;
    public final String token;
    public final long balance;

    public UserResponse(String status,String message){
        this.status = status;
        this.message = message;
        this.users = null;
        this.token = null;
        this.balance = 0;
    }

    public UserResponse(String status, String message, List<User> users, String token, long balance){
        this.status = status;
        this.message = message;
        this.users = users;
        this.token = token;
        this.balance = balance;
    }
}