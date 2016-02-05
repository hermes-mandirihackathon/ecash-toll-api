package com.mandiriecash.etollapi.mea.requests;

public class MEAOnStorePurchaseRequest {
    String msisdn;
    String token;
    int amount;
    String credentials;

    public static class Builder {
        MEAOnStorePurchaseRequest request = new MEAOnStorePurchaseRequest();

        public Builder msisdn(String msisdn){ request.setMsisdn(msisdn); return this; }

        public Builder token(String token){ request.setToken(token); return this; }

        public Builder amount(int amount){ request.setAmount(amount); return this; }

        public Builder credentials(String credentials){ request.setCredentials(credentials); return this; }

        public MEAOnStorePurchaseRequest build(){ return request; }
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }
}
