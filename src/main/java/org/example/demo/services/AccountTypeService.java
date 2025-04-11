package org.example.demo.services;

import org.example.demo.accountTypes.AccountType;
import org.example.demo.repositories.AccountTypeRepository;

import java.util.List;
import java.util.Optional;


public class AccountTypeService {
    private final AccountTypeRepository accountTypeRepository;

    public AccountTypeService(AccountTypeRepository accountTypeRepository) {
        this.accountTypeRepository = accountTypeRepository;
    }

    public AccountType createAccountType(
            String name,
            double fixedAccInterestRate,
            double savingAccInterestRate,
            boolean minBalanceRequired,
            double minBalance,
            boolean overdraftAllowed,
            double overdraftLimit,
            String description
    ) {
        AccountType newAccountType = new AccountType(
                name,
                fixedAccInterestRate,
                savingAccInterestRate,
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
            double savingAccInterestRate,
            boolean minBalanceRequired,
            double minBalance,
            boolean overdraftAllowed,
            double overdraftLimit,
            String description
    ) {
        AccountType optionalAccountType = accountTypeRepository.getAccountTypeByAccountName(name);

        if (optionalAccountType != null ) {
            optionalAccountType.setFixedAccInterestRate(fixedAccInterestRate);
            optionalAccountType.setSavingAccInterestRate(savingAccInterestRate);
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


    // TODO HANDLE EXCEPTION
    public AccountType getAccountTypeByName(String name) {
        return accountTypeRepository.getAccountTypeByAccountName(name);
    }


    // GEt ALL ACCOUNT TYPES
    public List<AccountType> getAllAccountTypes() {
        return accountTypeRepository.getAllAccountTypes();
    }
}



