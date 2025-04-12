package org.example.demo.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.LinkedList;

public interface Account {
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    LinkedList<Transaction> getLastNTransactions(int n);
    int getAccountNumber();
    void setBalance(double amount);
    AccountType getType();
    void addTransaction(Transaction transaction);
}
