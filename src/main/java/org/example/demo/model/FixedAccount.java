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
        this.transactions.add(new Transaction(TransactionTypeEnum.INITIAL_TRANSACTION, principal));
    }

    @Override
    public void withdraw(double amount) {

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

        return this.principal + (this.principal * this.interestRate); // Or this.balance + interest if you're calculating it elsewhere
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
