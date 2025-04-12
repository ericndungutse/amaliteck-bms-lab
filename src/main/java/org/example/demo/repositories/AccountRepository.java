package org.example.demo.repositories;
import org.example.demo.model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    // Create Account
    public void createAccount(Account account) {
        // save it in accounts
        accounts.add(account);
    }

    // Get account by account number
    public Account getAccountByAccountNumber(int accountNumber) {
        return accounts.stream().filter(acc -> acc.getAccountNumber() == accountNumber).findFirst().orElseThrow(()->  new RuntimeException("Account not found!"));
    }

    // Update Account
    public void updateAccount(Account account) {
        for (int i = 0; i < accounts.size(); i++) {
            Account existingAccount = accounts.get(i);
            if (existingAccount.getAccountNumber() == account.getAccountNumber()) {
                accounts.set(i, account);
                return;
            }
        }
    }
}



