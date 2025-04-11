package org.example.demo.accountTypes;


public class AccountType {
    private int Id;
    private AccountTypeEnum accountName;
    private double fixedAccInterestRate = 0.0;
    private double savingAccInterestRate = 0.0;
    private boolean minBalanceRequired;
    private double minBalance;
    private boolean overdraftAllowed;
    private double overdraftLimit;
    private String description;

    public AccountType(
            AccountTypeEnum accountName,
            double fixedAccInterestRate,
            double savingAccInterestRate,
            boolean minBalanceRequired,
            double minBalance,
            boolean overdraftAllowed,
            double overdraftLimit,
            String description) {

        this.accountName = accountName;
        this.minBalanceRequired = minBalanceRequired;
        this.minBalance = minBalance;
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

    public double getFixedAccInterestRate() {
        return fixedAccInterestRate;
    }

    public void setFixedAccInterestRate(double fixedAccInterestRate) {
        this.fixedAccInterestRate = fixedAccInterestRate;
    }

    public double getSavingAccInterestRate() {
        return savingAccInterestRate;
    }

    public void setSavingAccInterestRate(double savingAccInterestRate) {
        this.savingAccInterestRate = savingAccInterestRate;
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
                ", minBalanceRequired=" + minBalanceRequired +
                ", minBalance=" + minBalance +
                ", overdraftAllowed=" + overdraftAllowed +
                ", overdraftLimit=" + overdraftLimit +
                ", description='" + description + '\'' +
                '}';
    }
}
