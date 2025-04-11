package org.example.demo.repositories;

import java.util.Date;

public class Transaction {
    private TransactionTypeEnum type;
    private double amount;
    private Date date;

    // Constructor
    public Transaction(TransactionTypeEnum type, double amount, Date date) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
