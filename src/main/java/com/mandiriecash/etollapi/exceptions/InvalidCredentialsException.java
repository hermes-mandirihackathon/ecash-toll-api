package com.mandiriecash.etollapi.exceptions;

public class InvalidCredentialsException extends Exception{
    public static final int INVALID_EMAIL = 1;
    public static final int INVALID_PASSWORD = 2;
    public static final int INVALID_EMAIL_OR_PASSWORD = 3;

    public InvalidCredentialsException(String s) {
        super(s);
    }

    public static InvalidCredentialsException create(int cause){
        if (cause == INVALID_EMAIL){
            return new InvalidCredentialsException("Invalid email");
        } else if (cause == INVALID_PASSWORD){
            return new InvalidCredentialsException("Invalid password");
        } else {
            return new InvalidCredentialsException("Invalid email/password");
        }
    }
}
