package org.example.demo.repositories;



public class AccountType {
    private int Id;
    public AccountTypeEnum accountName;
    public double interestRate;
    public boolean minBalanceRequired;
    public double minBalance;
    public boolean overdraftAllowed;
    public double overdraftLimit;
    // Applies only on fixed account
    public double principal;
    // Applies only on Saving Account
    public double initialDeposit;
    public String description;

    public AccountType(
            int id,
            AccountTypeEnum accountName,
            double interestRate,
            boolean minBalanceRequired,
            double minBalance,
            boolean overdraftAllowed,
            double overdraftLimit,
            double principal,
            double initialDeposit,
            String description) {
        Id = id;
        this.accountName = accountName;
        this.interestRate = interestRate;
        this.minBalanceRequired = minBalanceRequired;
        this.minBalance = minBalance;
        this.overdraftAllowed = overdraftAllowed;
        this.overdraftLimit = overdraftLimit;
        this.principal = principal;
        this.initialDeposit = initialDeposit;
        this.description = description;
    }
}
