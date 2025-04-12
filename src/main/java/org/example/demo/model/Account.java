package org.example.demo.model;

import java.util.LinkedList;

public interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    LinkedList<String> getLastNTransactions();
    int getAccountNumber();
    void setBalance(double amount);

    AccountType getType();
}
