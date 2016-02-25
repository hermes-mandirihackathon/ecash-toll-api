package com.github.yafithekid.mandiri_ecash_api.requests;

public class MEABalanceInquiryRequest {
    String token;
    String msisdn;

    public static class Builder {
        com.github.yafithekid.mandiri_ecash_api.requests.MEABalanceInquiryRequest request = new com.github.yafithekid.mandiri_ecash_api.requests.MEABalanceInquiryRequest();

        public Builder msisdn(String msisdn){
            request.setMsisdn(msisdn); return this;
        }

        public Builder token(String token){
            request.setToken(token); return this;
        }

        public com.github.yafithekid.mandiri_ecash_api.requests.MEABalanceInquiryRequest build(){ return request; }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }
}
