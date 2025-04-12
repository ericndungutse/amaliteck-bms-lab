package org.example.demo.model;


public class AccountType {
    private String name;
    private double fixedAccInterestRate = 0.0;
    private boolean minBalanceRequired;
    private double minBalance;
    private boolean overdraftAllowed;
    private double overdraftLimit;
    private String description;

    public AccountType(
            String name,
            double fixedAccInterestRate,
            boolean minBalanceRequired,
            double minBalance,
            boolean overdraftAllowed,
            double overdraftLimit,
            String description) {

        this.name = name;
        this.minBalanceRequired = minBalanceRequired;
        this.minBalance = minBalance;
        this.fixedAccInterestRate = fixedAccInterestRate;
        this.overdraftAllowed = overdraftAllowed;
        this.overdraftLimit = overdraftLimit;
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

    public String getName() {
        return name;
    }

    public void setAccountName(String name) {
        this.name = name;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
    }

    public double getFixedAccInterestRate() {
        return fixedAccInterestRate;
    }

    public void setFixedAccInterestRate(double fixedAccInterestRate) {
        this.fixedAccInterestRate = fixedAccInterestRate;
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
                ", name=" + name +
                ", minBalanceRequired=" + minBalanceRequired +
                ", minBalance=" + minBalance +
                ", overdraftAllowed=" + overdraftAllowed +
                ", overdraftLimit=" + overdraftLimit +
                ", description='" + description + '\'' +
                '}';
    }
}
