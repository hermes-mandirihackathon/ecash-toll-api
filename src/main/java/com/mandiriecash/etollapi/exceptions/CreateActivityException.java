package com.mandiriecash.etollapi.exceptions;


public class CreateActivityException extends Exception {
    public CreateActivityException(PaymentErrorException e){
        super(e);
    }
}
