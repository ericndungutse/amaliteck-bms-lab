package org.example.demo.model;

public class SavingAccount extends AbstractAccount {
    private final double initialDeposit;

    public SavingAccount(String holderName, AccountType type, double initialDeposit) {
       super(type, holderName);
       this.initialDeposit = initialDeposit;
       this.balance = initialDeposit;
       this.transactions.add(new Transaction(TransactionTypeEnum.INITIAL_TRANSACTION, initialDeposit));
    }

    @Override
    public void withdraw(double amount) {
        // Calculate Balance
        double balance = this.balance - amount;

        // Check if balance is less than minimum balance
        if (balance < this.type.getMinBalance()) {
            throw new IllegalArgumentException("Insufficient funds. Minimum balance requirement not met.");
        }

        // Update balance
        this.setBalance(balance);
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
}
