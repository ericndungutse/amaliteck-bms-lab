package org.example.demo.repositories;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public abstract class AbstractAccount {
    private static int counter = 1;
    protected int accountNumber;
    protected String holderName;
    protected String type;
    protected double balance;
    protected List<Transaction> transactions = new LinkedList<>();

    public AbstractAccount(String accountNumber, String holderName, String type) {
        this.accountNumber =  counter++;
        this.holderName = holderName;
        this.type = type;
        this.balance = 0.0;
    }

    // For Current accounts
    public void createAccount(String name, String holderName) {
        // Implementation for CURRENT accounts
    }

    // For Fixed and Savings accounts
    public void createAccount(String name, double principal, String holderName) {
        // Implementation for FIXED and SAVINGS accounts
    }

    public void deposit(double amount) {
        this.balance += amount;
        transactions.add(new Transaction(TransactionTypeEnum.DEPOSIT, amount, new Date()));
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
            transactions.add(new Transaction(TransactionTypeEnum.WITHDRAWAL, amount, new Date()));
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public List<Transaction> getLastNTransactions(int n) {
        int start = Math.max(transactions.size() - n, 0);
        return transactions.subList(start, transactions.size());
    }

    public AbstractAccount getAccount(String accountNumber) {
        // Logic to fetch account by account number
        return null; // Placeholder
    }
}
