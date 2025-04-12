package org.example.demo.model;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractAccount implements Account{
    private static int counter = 1;
    protected int accountNumber;
    protected String holderName;
    protected AccountType type;
    protected double balance;
    protected List<Transaction> transactions = new LinkedList<>();

    // For instantiating current account
    public  AbstractAccount(AccountType accountType, String holderName) {
        this.accountNumber = counter++;
        this.type = accountType;
        this.holderName = holderName;
        this.balance  = 0.0;
    }

    public AbstractAccount(String holderName) {
        this.holderName = holderName;
    }

    public void deposit(double amount) {
            this.balance += amount;
    }
    public abstract void withdraw(double amount);

    public double getBalance(){
        return  this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountType getType() {
        return type;
    }

    public int getAccountNumber() {
        return accountNumber;
    }


    @Override
    public void addTransaction(Transaction transaction) {
    this.transactions.add(transaction);
    }


    public LinkedList<String> getLastNTransactions() {
        return null;
    }

}