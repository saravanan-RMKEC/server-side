package com.banking;

public class CheckingAccount extends Account {

    public CheckingAccount(String accountNumber) {
        super(accountNumber);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        // Assuming no overdraft allowed
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
