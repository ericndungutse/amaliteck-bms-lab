package org.example.demo.model;

import java.util.LinkedList;

public interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    Account getAccount(int accountNumber);
    void addTransaction(Transaction transaction);
    LinkedList<String> getLastNTransactions();
    Object getAccountNumber();
    void setBalance(double v);
}
