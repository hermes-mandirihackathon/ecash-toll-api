package com.mandiriecash.etollapi.mea.exceptions;

/**
 * Created by yafi on 09-Feb-16.
 */
public class MEAUnknownErrorException extends Exception{
    public MEAUnknownErrorException(){
        super("Unknown error from Mandiri API");
    }
}
