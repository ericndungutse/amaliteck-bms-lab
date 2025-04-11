package org.example.demo.accounts;

import java.util.LinkedList;

public interface Account {
//    void createAccount(AccountType accountType, String holderName);
//    void createAccount(AccountType accountType, double initialDeposit, String holderName);
    void deposit(double amount);
    void withdraw(double amount);
    double getBalance();
    Account getAccount(int accountNumber);
    void addTransaction(Transaction transaction);
    LinkedList<String> getLastNTransactions();
}
