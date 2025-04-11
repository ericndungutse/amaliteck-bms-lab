package org.example.demo.repositories;

import org.example.demo.accountTypes.AccountType;

import java.util.ArrayList;
import java.util.List;

public class AccountTypeRepository {
    private final List<AccountType> accountTypes = new ArrayList<>();

    // Create Account
    public void createAccountType(AccountType newAccountType) {
        // save it in accounts
        accountTypes.add(newAccountType);
    }

    // Get account by account number
    public AccountType getAccountTypeByAccountName(String accountTypeName) {
        return accountTypes.stream()
                .filter(accType -> accType.getName().equals(accountTypeName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Account Type not Found"));
    }

    // Get ALl Account Types
    public List<AccountType> getAllAccountTypes(){
        return accountTypes;
    }

    // Update Account
    public void updateAccountType(AccountType accountType) {
        for (int i = 0; i < accountTypes.size(); i++) {
            AccountType existingAccountType = accountTypes.get(i);
            if (existingAccountType.getName().equals(accountType.getName())) {
                accountTypes.set(i, accountType);
                return;
            }
        }
    }
}
