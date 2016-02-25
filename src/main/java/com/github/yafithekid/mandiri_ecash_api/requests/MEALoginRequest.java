package com.github.yafithekid.mandiri_ecash_api.requests;

public class MEALoginRequest {
    private String uid;
    private String credentials;
    private String msisdn;

    public static class Builder {
        com.github.yafithekid.mandiri_ecash_api.requests.MEALoginRequest request = new com.github.yafithekid.mandiri_ecash_api.requests.MEALoginRequest();

        public Builder uid(String uid){
            request.setUid(uid); return this;
        }

        public Builder credentials(String credentials){
            request.setCredentials(credentials); return this;
        }

        public Builder msisdn(String msisdn){
            request.setMsisdn(msisdn); return this;
        }

        public com.github.yafithekid.mandiri_ecash_api.requests.MEALoginRequest build(){
            return request;
        }
    }

    public String getUid() {
        return uid;
    }

    public String getCredentials() {
        return credentials;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

}
