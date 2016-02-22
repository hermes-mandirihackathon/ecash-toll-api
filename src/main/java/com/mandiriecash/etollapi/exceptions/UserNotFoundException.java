package com.mandiriecash.etollapi.exceptions;

/**
 * Created by yafi on 22-Feb-16.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String msisdn) {
        super("User with msisdn = "+msisdn+" not found");
    }

    public UserNotFoundException(int id){
        super("User with id = "+id+" not found");
    }
}
