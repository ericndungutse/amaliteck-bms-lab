package org.example.demo.repositories;



public class AccountType {
    private int Id;
    private AccountTypeEnum accountName;
    private double interestRate;
    private boolean minBalanceRequired;
    private double minBalance;
    private boolean overdraftAllowed;
    private double overdraftLimit;
    // Applies only on fixed account
    private double principal;
    private String description;

    public AccountType(
            AccountTypeEnum accountName,
            double interestRate,
            boolean minBalanceRequired,
            double minBalance,
            boolean overdraftAllowed,
            double overdraftLimit,
            double principal,
            String description) {

        this.accountName = accountName;
        this.interestRate = interestRate;
        this.minBalanceRequired = minBalanceRequired;
        this.minBalance = minBalance;
        this.overdraftAllowed = overdraftAllowed;
        this.overdraftLimit = overdraftLimit;
        this.principal = principal;
        this.description = description;
    }

    public boolean isOverdraftAllowed() {
        return overdraftAllowed;
    }

    public void setOverdraftAllowed(boolean overdraftAllowed) {
        this.overdraftAllowed = overdraftAllowed;
    }

    public double getMinBalance() {
        return minBalance;
    }

    public void setMinBalance(double minBalance) {
        this.minBalance = minBalance;
    }

    public boolean isMinBalanceRequired() {
        return minBalanceRequired;
    }

    public void setMinBalanceRequired(boolean minBalanceRequired) {
        this.minBalanceRequired = minBalanceRequired;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public AccountTypeEnum getAccountName() {
        return accountName;
    }

    public void setAccountName(AccountTypeEnum accountName) {
        this.accountName = accountName;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getPrincipal() {
        return principal;
    }

    public void setPrincipal(double principal) {
        this.principal = principal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "Id=" + Id +
                ", accountName=" + accountName +
                ", interestRate=" + interestRate +
                ", minBalanceRequired=" + minBalanceRequired +
                ", minBalance=" + minBalance +
                ", overdraftAllowed=" + overdraftAllowed +
                ", overdraftLimit=" + overdraftLimit +
                ", principal=" + principal +
                ", description='" + description + '\'' +
                '}';
    }
}
