package com.github.yafithekid.mandiri_ecash_api.responses;

import java.util.List;

public class MEAAccountHistoryResponse {
    String status;
    String creditLimit;
    String accountBalance;
    String onPage;
    String pageSize;
    String totalCount;
    List<AccountHistory> accountHistoryDetails;

    public static class AccountHistory {
        String transferType;
        String name;
        String username;
        String amount;
        String description;
        String transactionDate;
        String transactionNumber;
        String status;
        String parentTransactionNumber;
        String privateField;

        public String getTransferType() {
            return transferType;
        }

        public void setTransferType(String transferType) {
            this.transferType = transferType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getTransactionNumber() {
            return transactionNumber;
        }

        public void setTransactionNumber(String transactionNumber) {
            this.transactionNumber = transactionNumber;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getParentTransactionNumber() {
            return parentTransactionNumber;
        }

        public void setParentTransactionNumber(String parentTransactionNumber) {
            this.parentTransactionNumber = parentTransactionNumber;
        }

        public String getPrivateField() {
            return privateField;
        }

        public void setPrivateField(String privateField) {
            this.privateField = privateField;
        }
    }

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

    public String getOnPage() {
        return onPage;
    }

    public void setOnPage(String onPage) {
        this.onPage = onPage;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public List<AccountHistory> getAccountHistoryDetails() {
        return accountHistoryDetails;
    }

    public void setAccountHistoryDetails(List<AccountHistory> accountHistoryDetails) {
        this.accountHistoryDetails = accountHistoryDetails;
    }
}
