package com.github.yafithekid.mandiri_ecash_api.requests;

public class MEATransferMemberPaymentRequest {
    String from;
    String to;
    int amount;
    String description;
    String credentials;
    String token;

    public static class Builder {
        com.github.yafithekid.mandiri_ecash_api.requests.MEATransferMemberPaymentRequest request = new com.github.yafithekid.mandiri_ecash_api.requests.MEATransferMemberPaymentRequest();

        public Builder from(String from){ request.setFrom(from); return this; }

        public Builder to(String to){ request.setTo(to); return this; }

        public Builder amount(int amount){ request.setAmount(amount); return this; }

        public Builder description(String description){request.setDescription(description); return this;}

        public Builder credentials(String credentials){request.setCredentials(credentials); return this;}

        public Builder token(String token){request.setToken(token); return this; }

        public com.github.yafithekid.mandiri_ecash_api.requests.MEATransferMemberPaymentRequest build(){ return request; }
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
