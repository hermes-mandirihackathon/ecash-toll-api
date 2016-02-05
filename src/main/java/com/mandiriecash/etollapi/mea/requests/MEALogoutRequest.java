package com.mandiriecash.etollapi.mea.requests;

/**
 * Created by yafi on 05-Feb-16.
 */
public class MEALogoutRequest {
    String msisdn;
    String token;

    public static class Builder{
        MEALogoutRequest request = new MEALogoutRequest();

        public Builder msisdn(String msisdn){
            request.setMsisdn(msisdn); return this;
        }

        public Builder token(String token){
            request.setToken(token); return this;
        }

        public MEALogoutRequest build(){ return request; }
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
