package com.mandiriecash.etollapi.mea;

public class MEALoginResponse {
    public static final String LOGIN_FAILED = "LOGIN_FAILED";
    private String status;
    private String token;
    private String msisdn;

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public String getMsisdn() {
        return msisdn;
    }
}
