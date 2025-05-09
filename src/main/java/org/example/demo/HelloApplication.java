package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo.constants.AppConstants;
import org.example.demo.model.Account;
import org.example.demo.model.AccountType;
import org.example.demo.repositories.AccountTypeRepository;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

import java.io.IOException;
import java.time.LocalDate;

public class HelloApplication extends Application {
    private static final AccountService accountService = ServiceLocator.getAccountService();
    private static final AccountTypeService accountTypeService = ServiceLocator.getAccountTypeService();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
       AccountType currentAccType =  accountTypeService.createAccountType( AppConstants.CURRENT,
                0,
                false,
                0,
                true,
                500.0,
                "Earn a lot");

       AccountType savingAccType =  accountTypeService.createAccountType( AppConstants.SAVING,
                0,
                true,
                300,
                false,
                0,
                "Earn a lot");

       AccountType fixedAccType =  accountTypeService.createAccountType( AppConstants.FIXED,
                0.12,
                false,
                0,
                false,
                0,
                "Earn a lot");

       // Testing Data
       // Current Account
       accountService.createAccount("Eric");
       accountService.deposit(1, 500);
       accountService.deposit(1, 500);
       accountService.deposit(1, 640);
       accountService.deposit(1, 560);
       accountService.deposit(1, 500);
       accountService.deposit(1, 200);
       accountService.deposit(1, 100);
       accountService.deposit(1, 4500);
       accountService.deposit(1, 800);

       // FIxed
       accountService.createAccount(500, "Ndungutse", LocalDate.parse("2024-12-12"));
       // Saving
       accountService.createAccount("tuyizere", 1000);
        accountService.deposit(3, 500);
        accountService.deposit(3, 500);
        accountService.deposit(3, 640);
        accountService.deposit(3, 560);
        accountService.deposit(3, 500);
        accountService.deposit(3, 200);
        accountService.deposit(3, 300);
        accountService.deposit(3, 4500);
        accountService.deposit(3, 800);
        launch();
    }
}