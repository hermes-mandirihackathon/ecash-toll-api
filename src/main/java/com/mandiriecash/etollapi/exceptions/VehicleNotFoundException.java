package com.mandiriecash.etollapi.exceptions;

/**
 * Created by yafi on 22-Feb-16.
 */
public class VehicleNotFoundException extends Exception{
    public VehicleNotFoundException(String plateNo){
        super("Vehicle with plate_no = "+plateNo+" not found");
    }
}
