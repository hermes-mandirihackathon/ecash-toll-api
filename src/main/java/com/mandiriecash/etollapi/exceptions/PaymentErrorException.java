package com.mandiriecash.etollapi.exceptions;

import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAHttpException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;

public class PaymentErrorException extends Exception{
    public PaymentErrorException(String message){
        super(message);
    }

    public PaymentErrorException(MEAIOException meaIOException){
        super(meaIOException);
    }

    public PaymentErrorException(MEAHttpException meaHttpException){
        super(meaHttpException);
    }

    public PaymentErrorException(MEATokenExpiredException meaTokenExpired){
        super(meaTokenExpired);
    }
}
