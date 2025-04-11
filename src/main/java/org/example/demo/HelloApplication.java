package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo.model.Account;
import org.example.demo.accountTypes.AccountType;
import org.example.demo.accountTypes.AccountTypeEnum;
import org.example.demo.model.SavingAccount;
import org.example.demo.repositories.AccountRepository;
import org.example.demo.repositories.AccountTypeRepository;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

import java.io.IOException;

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

       AccountType newAccType =  accTypeSrv.createAccountType( "current",
                0,
                0.4,
                false,
                0,
                true,
                500.0,
                "Earn a lot");

        accSrv.createAccount("Eric");
        System.out.println(accSrv.getAccountByAccNumber(    1));



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