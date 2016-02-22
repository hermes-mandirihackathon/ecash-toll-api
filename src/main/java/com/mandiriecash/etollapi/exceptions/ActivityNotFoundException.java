package com.mandiriecash.etollapi.exceptions;



public class ActivityNotFoundException extends Exception {
    public ActivityNotFoundException(int id){
        super("Activity with id = "+id+" not found");
    }
}
