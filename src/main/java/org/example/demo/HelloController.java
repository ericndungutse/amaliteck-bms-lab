package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.demo.repositories.AccountRepository;
import org.example.demo.repositories.AccountTypeRepository;
import org.example.demo.services.AccountTypeService;

import java.io.IOException;


public class HelloController {
    private Label welcomeText;

    @FXML
    private void handleNavigateToCreateAccount(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("create-account-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleNavigateToAccountOperations(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("account-operations-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void handleListAccountTypes(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("account-types-list-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}