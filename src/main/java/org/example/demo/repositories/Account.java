package org.example.demo.repositories;

import java.util.LinkedList;

public interface Account {
    void createAccount(AccountType accountType, String holderName);
    void createAccount(AccountType accountType, double initialDeposit, String holderName);
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance(boolean fixed);
    Account getAccount(int accountNumber);
    LinkedList<String> getLastNTransactions();
}
