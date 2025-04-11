package org.example.demo.components;

import java.time.LocalDate;

public class SavingAccount extends AbstractAccount {
    private final double initialDeposit;

    public SavingAccount(String holderName, AccountType type, double initialDeposit) {
       super(type, holderName);
       this.initialDeposit = initialDeposit;
       this.balance = initialDeposit;
       this.transactions.add(new Transaction(TransactionTypeEnum.INITIAL_TRANSACTION, initialDeposit, LocalDate.now()));
    }

    @Override
    public void withdraw(double amount) {
//        // Assuming minimum balance needs to be maintained
//        double minimumBalance = getType().getMinimumBalance();
//        if (getBalance() - amount < minimumBalance) {
//            throw new IllegalArgumentException("Insufficient funds. Minimum balance requirement not met.");
//        }
//        setBalance(getBalance() - amount);
//        addTransaction(new TransactionClass(getAccountNumber(), TransactionTypeEnum.withdraw, amount));
    }

    @Override
    public String toString() {
        return "SavingAccount{" +
                "initialDeposit=" + initialDeposit +
                ", accountNumber=" + accountNumber +
                ", holderName='" + holderName + '\'' +
                ", balance=" + balance +
                ", transactions=" + transactions +
                '}';
    }

    public double getBalance() {
        double rate = this.type.getSavingAccInterestRate();
        return this.balance + (this.balance * rate);
    }
}
