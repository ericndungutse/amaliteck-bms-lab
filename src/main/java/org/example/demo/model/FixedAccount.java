package org.example.demo.model;

import org.example.demo.accountTypes.AccountType;

public class FixedAccount extends AbstractAccount{
    private double principal;
    public FixedAccount(AccountType accountType, String holderName) {
        super(accountType, holderName);
        // TODO initialDeposit transaction
    }

    @Override
    public void withdraw(double amount) {

    }

    @Override
    public double getBalance(){
        return  this.balance + 1;
    }


}
