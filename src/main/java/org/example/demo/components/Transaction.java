package org.example.demo.components;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    private TransactionTypeEnum type;
    private double amount;
    private LocalDate date;

    // Constructor
    public Transaction(TransactionTypeEnum type, double amount, LocalDate date) {
        this.type = type;
        this.amount = amount;
        this.date = date;
    }

    // Getters and Setters
    public TransactionTypeEnum getType() {
        return type;
    }

    public void setType(TransactionTypeEnum type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type=" + type +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
