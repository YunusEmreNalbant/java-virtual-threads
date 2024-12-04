package com.yunusemrenalbant.virtualthreads.model;

import java.math.BigDecimal;

public class MoneyTransferRequest {
    private String senderAccount;
    private String receiverAccount;
    private BigDecimal amount;

    public MoneyTransferRequest() {}

    public MoneyTransferRequest(String senderAccount, String receiverAccount, BigDecimal amount) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.amount = amount;
    }

    public String getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(String senderAccount) {
        this.senderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        this.receiverAccount = receiverAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
