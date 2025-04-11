package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo.repositories.Account;
import org.example.demo.repositories.AccountType;
import org.example.demo.repositories.AccountTypeEnum;
import org.example.demo.repositories.CurrentAccount;

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
//        launch();
        System.out.println("Hello From App");

        AccountType currentAccount = new AccountType(
                AccountTypeEnum.CURRENT, // Account type
                0,                     // Interest rate
                false,                    // Minimum balance required
                0,                  // Minimum balance
                true,                    // Overdraft allowed
                500.0,                   // Overdraft limit
                0.0,                 // Principal
                "Current account with overdraft facility" // Description
        );

        Account currAcc = new CurrentAccount(currentAccount, "Eric");
//        Account currAcc2 = new CurrentAccount(currentAccount, "James");

        System.out.println(currentAccount);
        System.out.println(currAcc.getBalance());
//        System.out.println(currAcc2);

        currAcc.withdraw(3000);
        System.out.println(currAcc.getBalance());
    }
}