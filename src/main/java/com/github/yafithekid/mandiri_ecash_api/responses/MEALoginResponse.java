package com.github.yafithekid.mandiri_ecash_api.responses;

public class MEALoginResponse {
    public static final String LOGIN_FAILED = "LOGIN_FAILED";
    public static final String BLOCKED = "BLOCKED";
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

    public void setStatus(String status) {
        this.status = status;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}
