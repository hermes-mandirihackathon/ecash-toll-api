package com.mandiriecash.etollapi.controllers;

/**
 * Created by Ichwan Haryo Sembodo on 31/01/2016.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.responses.MEABalanceInquiryResponse;
import com.mandiriecash.etollapi.mea.exceptions.MEAUnknownErrorException;
import com.mandiriecash.etollapi.models.User;
import com.mandiriecash.etollapi.mea.exceptions.MEALoginFailedException;
import com.mandiriecash.etollapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DefaultController {
    static final String OK = "ok";
    static final String ERROR = "error";

    @Autowired
    private UserService userService;

//    TODO belum dipake
//    @RequestMapping(value="/user/{id}", method = RequestMethod.GET)
//    public @ResponseBody
//    LoginResponse getUserById(@PathVariable int id){
//        return new LoginResponse("OK", "", userService.getUserById(id), "", 0);
//    }

    @RequestMapping(value="/login", method = RequestMethod.GET)
    //TODO change to POST
    /**
     * Example call: http://localhost:8080/ecashtollapi/login?msisdn=085712345678&credentials=xxxxxx
     */
    public @ResponseBody
    LoginResponse loginUser(
            @RequestParam(name = "uid",required = false) String uid,
            @RequestParam(name = "msisdn",required = true) String msisdn,
            @RequestParam(name = "credentials",required = true) String credentials){
        LoginResponse loginResponse;
        try {
            //TODO uid buat apa???
            String token = userService.loginUser(uid,msisdn,credentials);
            loginResponse = new LoginResponse(OK,"",msisdn,token);
        } catch (IOException e) {
            if (e instanceof MEAIOException){
                loginResponse = new LoginResponse(ERROR,"Error while contacting Mandiri ECash API");
            } else {
                loginResponse = new LoginResponse(ERROR,"Error while contacting Etoll API");
            }
            e.printStackTrace();
        } catch (MEALoginFailedException e) {
            loginResponse = new LoginResponse(ERROR,e.getMessage());
        }
        return loginResponse;
    }

    @RequestMapping(value="/balance", method = RequestMethod.GET)
    //TODO change to GET
    public @ResponseBody BalanceResponse balanceUser(
            @RequestParam(name = "token",required = true) String token,
            @RequestParam(name = "msisdn",required = true) String msisdn){
        BalanceResponse balanceResponse;
        try {
            MEABalanceInquiryResponse apiResponse = userService.balanceInquiry(token, msisdn);
            balanceResponse = new BalanceResponse(OK,apiResponse.getAccountBalance(),apiResponse.getCreditLimit());
        } catch (MEAUnknownErrorException e) {
            e.printStackTrace();
            //TODO change error message
            balanceResponse = new BalanceResponse(ERROR,e.getMessage());
        } catch (MEAIOException e) {
            e.printStackTrace();
            //TODO change error message
            balanceResponse = new BalanceResponse(ERROR,e.getMessage());
        }
        return balanceResponse;
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

class LoginResponse {
    public final String status;
    public final String message;
    public final String msisdn;
    public final String token;

    public LoginResponse(String status, String message){
        this.status = status;
        this.message = message;
        this.token = null;
        this.msisdn = null;
    }

    public LoginResponse(String status, String message,String msisdn, String token){
        this.status = status;
        this.message = message;
        this.token = token;
        this.msisdn = msisdn;
    }
}

class BalanceResponse{
    public final String status;
    public final String message;
    public final String accountBalance;
    public final String creditLimit;

    public BalanceResponse(String status,String accountBalance,String creditLimit){
        this.status = status;
        this.message = null;
        this.accountBalance = accountBalance;
        this.creditLimit = creditLimit;
    }

    public BalanceResponse(String status,String message){
        this.status = status;
        this.message = message;
        this.accountBalance = null;
        this.creditLimit = null;
    }
}