package org.example.demo.repositories;
import org.example.demo.model.Account;
import org.example.demo.model.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    // Create Account
    public void createAccount(Account account) {
        // save it in accounts
        accounts.add(account);
    }

    // Get account by account number
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        Optional<Account> account = accounts.stream().filter(acc -> acc.getAccountNumber().equals(accountNumber)).findFirst();

        if(account.isPresent()){
            return  account;
        } else{
            throw new RuntimeException("Account not Found");
        }
    }

    // Update Account
    public void updateAccount(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            Account existingAccount = accounts.get(i);
            if (existingAccount.getAccountNumber().equals(account.getAccountNumber())) {
                accounts.set(i, account);
                return;
            }
        }
    }
}



