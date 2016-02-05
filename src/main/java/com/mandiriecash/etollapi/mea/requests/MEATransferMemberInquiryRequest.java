package com.mandiriecash.etollapi.mea.requests;

public class MEATransferMemberInquiryRequest {
    String msisdn;
    String to;
    int amount;
    String token;

    public static class Builder {
        MEATransferMemberInquiryRequest request = new MEATransferMemberInquiryRequest();

        public Builder msisdn(String msisdn){
            request.setMsisdn(msisdn); return this;
        }

        public Builder to(String to){
            request.setTo(to); return this;
        }

        public Builder amount(int amount){
            request.setAmount(amount); return this;
        }

        public Builder token(String token){
            request.setToken(token); return this;
        }

        public MEATransferMemberInquiryRequest build(){return request;}
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
