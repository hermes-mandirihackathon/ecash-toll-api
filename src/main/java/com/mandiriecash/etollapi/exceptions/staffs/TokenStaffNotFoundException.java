package com.mandiriecash.etollapi.exceptions.staffs;


public class TokenStaffNotFoundException extends Exception{
    public TokenStaffNotFoundException(String token){
        super("No token found: "+token);
    }
}
