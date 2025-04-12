package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo.model.Account;
import org.example.demo.model.AccountType;
import org.example.demo.repositories.AccountTypeRepository;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        AccountTypeRepository accountTypeRepo = new AccountTypeRepository();
        AccountTypeService accTypeSrv = new AccountTypeService(accountTypeRepo);
        AccountService accSrv = new AccountService(accTypeSrv);

       AccountType currentAccType =  accTypeSrv.createAccountType( "current",
                0,
                false,
                0,
                true,
                500.0,
                "Earn a lot");

       AccountType savingAccType =  accTypeSrv.createAccountType( "saving",
                0,
                true,
                300,
                false,
                0,
                "Earn a lot");

       AccountType fixedAccType =  accTypeSrv.createAccountType( "fixed",
                1.02,
                false,
                0,
                false,
                0,
                "Earn a lot");

        try {
            // Current Account
//            Account currAccount = accSrv.createAccount("Eric");
//
//            accSrv.deposit(1, 300);
//            System.out.println(accSrv.getAccountByAccNumber(1));
//            accSrv.deposit(1,200);
//            System.out.println(accSrv.getAccountByAccNumber(1));
//
//            System.out.println("Afyer withdraw");
//            accSrv.withdraw(1, 1000);
//            System.out.println(accSrv.getAccountByAccNumber(1));


//            System.out.println(currAccount);
//            accSrv.withdraw(currAccount.getAccountNumber(), 100);
//            System.out.println(currAccount);

            // Saving Account
//            Account savingAcc = accSrv.createAccount("Tuyizere", 500);
//            accSrv.deposit(savingAcc.getAccountNumber(), 500);
//            accSrv.withdraw(1,200);
//
//            System.out.println(accSrv.getAccountByAccNumber(1));

//            // Fixed Account
            Account fixedAcc = accSrv.createAccount(5000, "Ndungute", LocalDate.of(2024,12,12));
            accSrv.withdraw(fixedAcc.getAccountNumber(), 400);
            accSrv.withdraw(fixedAcc.getAccountNumber(), 450);
//            System.out.println(fixedAcc);
//            accSrv.withdraw(fixedAcc.getAccountNumber(), 400);
//            System.out.println(fixedAcc);



        } catch (RuntimeException e) {
            // Code to handle the exception
            System.out.println("👎" + e.getMessage());
        }



        // Savings Account Type Setup
//        AccountType savingsAccount = new AccountType(
//                AccountTypeEnum.SAVINGS,          // Account type
//                0.04,                             // Interest rate (e.g., 4%)
//                0.0,
//                true,                             // Minimum balance required
//                1000.0,                           // Minimum balance
//                false,                            // Overdraft allowed
//                0.0,                              // Overdraft limit
//                "Savings account with interest accumulation" // Description
//        );
//
//        Account savingAcc1 = new SavingAccount("Eric", savingsAccount, 3000);
//
//
//        System.out.println(savingAcc1.getBalance());

//        launch();
        System.out.println("Hello From App");
//
//        AccountType currentAccount = new AccountType(
//                AccountTypeEnum.CURRENT, // Account type
//                0,                     // Interest rate
//                false,                    // Minimum balance required
//                0,                  // Minimum balance
//                true,                    // Overdraft allowed
//                500.0,                   // Overdraft limit
//                0.0,                 // Principal
//                "Current account with overdraft facility" // Description
//        );
//
//        Account currAcc = new CurrentAccount(currentAccount, "Eric");
////        Account currAcc2 = new CurrentAccount(currentAccount, "James");
//
//        System.out.println(currentAccount);
//        System.out.println(currAcc.getBalance());
////        System.out.println(currAcc2);
//
//        currAcc.withdraw(3000);
//        System.out.println(currAcc.getBalance());
    }



}