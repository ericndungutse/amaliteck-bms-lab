package org.example.demo;

import org.example.demo.repositories.AccountRepository;
import org.example.demo.repositories.AccountTypeRepository;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

public class ServiceLocator {
    private static final AccountRepository accountRepository = new AccountRepository();
    private static final AccountTypeRepository accountTypeRepo = new AccountTypeRepository();
    private static final AccountTypeService accountTypeService = new AccountTypeService(accountTypeRepo);
    private static final AccountService accountService = new AccountService(accountTypeService, accountRepository);

    public static AccountTypeService getAccountTypeService() {
        return accountTypeService;
    }

    public static AccountService getAccountService() {
        return accountService;
    }
}
