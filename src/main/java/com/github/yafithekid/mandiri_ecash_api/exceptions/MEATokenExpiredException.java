package com.github.yafithekid.mandiri_ecash_api.exceptions;

public class MEATokenExpiredException extends Exception {
    public MEATokenExpiredException(){
        super("Token expired");
    }
}
