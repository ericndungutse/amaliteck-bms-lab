package org.example.demo.model;

import org.example.demo.accountTypes.AccountType;

public class FixedAccount extends AbstractAccount{
    private double principal;
    private double interestRate;

    public FixedAccount(String holderName, AccountType accountType, double principal) {
        super(accountType, holderName);
        this.interestRate = accountType.getFixedAccInterestRate();
        this.principal = principal;
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
        return  this.balance + 1;
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
