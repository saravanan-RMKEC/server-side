package com.banking;

public class Transaction {
    private String transactionType;
    private double amount;
    
    public Transaction(String type, double amount) {
        this.transactionType = type;
        this.amount = amount;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }
}
