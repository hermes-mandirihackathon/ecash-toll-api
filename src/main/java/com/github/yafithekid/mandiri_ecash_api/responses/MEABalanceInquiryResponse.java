package com.github.yafithekid.mandiri_ecash_api.responses;

public class MEABalanceInquiryResponse {
    public static final String SUCCESS = "SUCCESS";
    private String status;
    private String creditLimit;
    private String accountBalance;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(String creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }
}
