package org.example.demo.services;

import org.example.demo.constants.AppConstants;
import org.example.demo.model.*;
import org.example.demo.repositories.AccountRepository;

import java.time.LocalDate;
import java.util.LinkedList;

public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountTypeService accountTypeService;

    public AccountService(AccountTypeService accountTypeService, AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
        this.accountTypeService = accountTypeService;
    }

    // Create Current Account
    public Account createAccount(String holderName) {
        // Get AccountType
        AccountType accountType = accountTypeService.getAccountTypeByName(AppConstants.CURRENT);

        // Instantiate Current Account
        Account newAccount = new CurrentAccount(accountType, holderName);

        // Save / persist it to the repository
        accountRepository.createAccount(newAccount);

        return newAccount;
    }

    // Create Saving Account
    public Account createAccount(String holderName, double initialDeposit) {
        // Get AccountType
        AccountType accountType = accountTypeService.getAccountTypeByName(AppConstants.SAVING);

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

    // Create Fixed Account
    public Account createAccount(double principal, String holderName, LocalDate maturityDate) {
        // Get AccountType
        AccountType accountType = accountTypeService.getAccountTypeByName(AppConstants.FIXED);

        // Instantiate Saving Account
        Account newAccount = new FixedAccount(holderName, accountType, principal, maturityDate);

        // Save / persist it to the repository
        accountRepository.createAccount(newAccount);

        return newAccount;
    }

    // Get Balance
    public double getBalance(int accountNumber) {
        // Get account from repository
        Account acc = accountRepository.getAccountByAccountNumber(accountNumber);

        // Return its balance
        return acc.getBalance();
    }

    public void deposit(int accountNumber, double amount) {
        // Get the account
        Account account = accountRepository.getAccountByAccountNumber(accountNumber);

        // Check if account is fixed and reject
       if(account.getType().getName().equals(AppConstants.FIXED)){
           throw new RuntimeException("Deposit not allowed on fixed account");
       }

        // Update Balance
        account.setBalance(account.getBalance() + amount);
        account.addTransaction(new Transaction(TransactionTypeEnum.DEPOSIT, amount));

       // Persist changes
        accountRepository.updateAccount(account);
    }

    // Withdraw
    public void withdraw(int accountNumber, double amount) {
        // Get the account
        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        account.withdraw(amount);
        account.addTransaction(new Transaction(TransactionTypeEnum.WITHDRAWAL, amount));
        accountRepository.updateAccount(account);
        System.out.println("Withdrawal of " + amount + " successful. New balance: " + accountRepository.getAccountByAccountNumber(accountNumber).getBalance());
    }

    public LinkedList<Transaction> getLastNTransactions(int accountNumber, int n) {
        Account acc = accountRepository.getAccountByAccountNumber(accountNumber);
        return acc.getLastNTransactions(n);
    }

    public Account getAccountByAccNumber(int accountNumber) {
        return accountRepository.getAccountByAccountNumber(accountNumber);
    }
}



