package org.example.demo.model;

public class CurrentAccount extends AbstractAccount {
    public CurrentAccount(AccountType accountType, String holderName) {
        super(accountType, holderName);
    }
    @Override
    public void withdraw(double amount) {
        // Calculate balance
        double balance = this.balance - amount;

        // Check if balance is greater than overdraftLimit and throw an error
        if( Math.abs(balance) > this.type.getOverdraftLimit()){
            throw new RuntimeException("Insufficient fund.");
        }
        // If No, update balance
        this.setBalance(balance);
    }



    @Override
    public String toString() {
        return "CurrentAccount{" +
                "accountNumber=" + accountNumber +
                ", holderName='" + holderName + '\'' +
                ", type=" + type +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }
}