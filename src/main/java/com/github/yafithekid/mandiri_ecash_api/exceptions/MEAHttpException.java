package com.github.yafithekid.mandiri_ecash_api.exceptions;

public class MEAHttpException extends Exception{
    int code;
    String messsage;

    public MEAHttpException(int code,String message){
        super(code+": "+message);
    }

    public int getCode() {
        return code;
    }

    public String getMesssage() {
        return messsage;
    }
}
