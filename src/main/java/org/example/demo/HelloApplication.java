package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.demo.repositories.AccountType;
import org.example.demo.repositories.AccountTypeEnum;

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
                3.5,                     // Interest rate
                false,                    // Minimum balance required
                0,                  // Minimum balance
                true,                    // Overdraft allowed
                500.0,                   // Overdraft limit
                0.0,                 // Principal
                "Current account with overdraft facility" // Description
        );


        System.out.println(currentAccount);
    }
}