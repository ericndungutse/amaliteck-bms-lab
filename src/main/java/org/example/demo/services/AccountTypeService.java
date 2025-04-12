package org.example.demo.services;



import org.example.demo.model.AccountType;
import org.example.demo.repositories.AccountTypeRepository;

import java.util.List;


public class AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeService(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    public AccountType createAccountType(
            String name,
            double fixedAccInterestRate,
            boolean minBalanceRequired,
            double minBalance,
            boolean overdraftAllowed,
            double overdraftLimit,
            String description
    ) {
        AccountType newAccountType = new AccountType(
                name,
                fixedAccInterestRate,
                minBalanceRequired,
                minBalance,
                overdraftAllowed,
                overdraftLimit,
                description
        );

        accountTypeRepository.createAccountType(newAccountType);

        return newAccountType;
    }

    public void updateAccountType(
            String name,
            double fixedAccInterestRate,
            boolean minBalanceRequired,
            double minBalance,
            boolean overdraftAllowed,
            double overdraftLimit,
            String description
    ) {
        AccountType optionalAccountType = accountTypeRepository.getAccountTypeByAccountName(name);

        if (optionalAccountType != null ) {
            optionalAccountType.setFixedAccInterestRate(fixedAccInterestRate);
            optionalAccountType.setMinBalanceRequired(minBalanceRequired);
            optionalAccountType.setMinBalance(minBalance);
            optionalAccountType.setOverdraftAllowed(overdraftAllowed);
            optionalAccountType.setOverdraftLimit(overdraftLimit);
            optionalAccountType.setDescription(description);

            accountTypeRepository.updateAccountType(optionalAccountType);
        } else {
            throw new RuntimeException("Account Type with name '" + name + "' not found.");
        }
    }



    public AccountType getAccountTypeByName(String name) {
        return accountTypeRepository.getAccountTypeByAccountName(name);
    }


    // GEt ALL ACCOUNT TYPES
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.getAllAccountTypes();
    }
}



