package com.banking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankingSystem {
    private static List<Customer> customers = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample flow to create accounts
        System.out.println("Welcome to the Banking System");

        while (true) {
            System.out.println("\nChoose an option: \n1. Register Customer \n2. Create Account \n3. Deposit \n4. Withdraw \n5. Check Balance \n6. Apply Interest \n7. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    registerCustomer();
                    break;
                case 2:
                    createAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    applyInterest();
                    break;
                case 7:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void registerCustomer() {
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();

        System.out.println("Enter customer ID:");
        String id = scanner.nextLine();

        Customer customer = new Customer(name, id);
        customers.add(customer);
        System.out.println("Customer registered successfully.");
    }

    private static void createAccount() {
        System.out.println("Enter customer ID:");
        String customerId = scanner.nextLine();
        Customer customer = findCustomerById(customerId);

        if (customer == null) {
            System.out.println("Customer not found.");
            return;
        }

        System.out.println("Choose account type: \n1. Savings \n2. Checking");
        int type = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Enter initial deposit amount:");
        double initialDeposit = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        Account account;
        if (type == 1) {
            System.out.println("Enter interest rate (%):");
            double rate = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account = new SavingsAccount(generateAccountNumber(), rate);
        } else {
            account = new CheckingAccount(generateAccountNumber());
        }

        account.deposit(initialDeposit);
        customer.addAccount(account);
        System.out.println("Account created successfully. Account Number: " + account.getAccountNumber());
    }

    private static void deposit() {
        System.out.println("Enter account number:");
        String accNum = scanner.nextLine();
        Account account = findAccountByNumber(accNum);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Enter deposit amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        account.deposit(amount);
        System.out.println("Deposit successful. New Balance: " + account.getBalance());
    }

    private static void withdraw() {
        System.out.println("Enter account number:");
        String accNum = scanner.nextLine();
        Account account = findAccountByNumber(accNum);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Enter withdrawal amount:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New Balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private static void checkBalance() {
        System.out.println("Enter account number:");
        String accNum = scanner.nextLine();
        Account account = findAccountByNumber(accNum);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.println("Current balance: " + account.getBalance());
    }

    private static void applyInterest() {
        System.out.println("Enter Savings Account number:");
        String accNum = scanner.nextLine();
        Account account = findAccountByNumber(accNum);

        if (account instanceof SavingsAccount) {
            ((SavingsAccount) account).applyInterest();
            System.out.println("Interest applied. New Balance: " + account.getBalance());
        } else {
            System.out.println("Invalid account type.");
        }
    }

    private static Customer findCustomerById(String id) {
        for (Customer c : customers) {
            if (c.getId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    private static Account findAccountByNumber(String accNum) {
        for (Customer c : customers) {
            for (Account a : c.getAccounts()) {
                if (a.getAccountNumber().equals(accNum)) {
                    return a;
                }
            }
        }
        return null;
    }

    private static String generateAccountNumber() {
        // Simple account number generator
        return "ACC" + (1000 + (int)(Math.random() * 9000));
    }
}
