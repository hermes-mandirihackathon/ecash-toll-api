package com.mandiriecash.etollapi.exceptions;

import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;

public class PaymentErrorException extends Exception{
    public PaymentErrorException(String message){
        super(message);
    }

    public PaymentErrorException(MEAIOException meaIOException){
        super(meaIOException);
    }
}
