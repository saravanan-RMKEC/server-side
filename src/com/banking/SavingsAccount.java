package com.banking;

public class SavingsAccount extends Account {
    private double interestRate; // Annual interest rate

    public SavingsAccount(String accountNumber, double interestRate) {
        super(accountNumber);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    public void applyInterest() {
        balance += (balance * interestRate) / 100;
    }

    public double getInterestRate() {
        return interestRate;
    }
}
