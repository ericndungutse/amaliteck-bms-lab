package org.example.demo.services;

import org.example.demo.accountTypes.AccountType;
import org.example.demo.model.Account;
import org.example.demo.model.CurrentAccount;
import org.example.demo.model.SavingAccount;
import org.example.demo.repositories.AccountRepository;

public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountTypeService accountTypeService;

    public AccountService(AccountTypeService accountTypeService) {
        this.accountRepository = new AccountRepository();
        this.accountTypeService = accountTypeService;
    }

    // Create Current Account
    public Account createAccount(String holderName) {
        // Get AccountType
        AccountType accountType = accountTypeService.getAccountTypeByName("current");

        // Instantiate Current Account
        Account newAccount = new CurrentAccount(accountType, holderName);

        // Save / persist it to the repository
        accountRepository.createAccount(newAccount);

        return newAccount;
    }


    // Create Saving Account
    public Account createAccount(String holderName, double initialDeposit) {
        // Get AccountType
        AccountType accountType = accountTypeService.getAccountTypeByName("saving");

        // Ensure that initial balance is equal or greater than account type minBalance
        if(initialDeposit < accountType.getMinBalance()){
            throw new RuntimeException("Operation failed! " + "To create a saving account, you need initial deposit of at least " + accountType.getMinBalance());
        }

        // Instantiate Saving Account
        Account newAccount = new SavingAccount(holderName, accountType, initialDeposit);

        // Save / persist it to the repository
        accountRepository.createAccount(newAccount);

        return newAccount;
    }


//    public void deposit(String accountNumber, double amount) {
//        accountRepository.deposit(accountNumber, amount);
//    }
//
//    public void withdraw(String accountNumber, double amount) {
//        accountRepository.withdraw(accountNumber, amount);
//    }
//
//    public double getBalance(String accountNumber) {
//        return accountRepository.getBalance(accountNumber);
//    }
//
//    public List<Transaction> getLastNTransactions(String accountNumber, int n) {
//        return accountRepository.getLastNTransactions(accountNumber, n);
//    }
//
    public Account getAccountByAccNumber(int accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }
}



