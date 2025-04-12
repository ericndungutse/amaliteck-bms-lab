package org.example.demo.model;

import java.time.LocalDate;

public class FixedAccount extends AbstractAccount{
    private double principal;
    private double interestRate;
    private LocalDate maturityDate;

    public FixedAccount(String holderName, AccountType accountType, double principal, LocalDate maturityDate) {
        super(accountType, holderName);
        this.interestRate = accountType.getFixedAccInterestRate();
        this.principal = principal;
        this.maturityDate = maturityDate;
        this.balance = principal;
        this.transactions.add(new Transaction(TransactionTypeEnum.INITIAL_TRANSACTION, principal));
    }

    public void withdraw(double amount) {
        LocalDate today = LocalDate.now();

        if (today.isBefore(this.maturityDate)) {
            throw new RuntimeException("Withdrawal denied: Account has not matured.");
        } else if (amount > this.getBalance()) {
            throw new RuntimeException("Withdrawal denied: Insufficient balance.");
        } else {
            this.setBalance(this.getBalance() - amount);
        }
    }

    public LocalDate getMaturityDate() {
        return maturityDate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public double getBalance(){
        LocalDate today = LocalDate.now();
        if (today.isBefore(this.maturityDate)) {
            return -1;
        }
        return this.principal + (this.principal * this.interestRate);
    }

    @Override
    public String toString() {
        return "FixedAccount{" +
                "principal=" + principal +
                ", interestRate=" + interestRate +
                ", holderName='" + holderName + '\'' +
                ", accountNumber=" + accountNumber +
                ", type=" + type +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}
