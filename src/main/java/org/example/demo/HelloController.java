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
    @FXML private TextField accountTypeField;
    @FXML private TextField interestRateField;
    @FXML private CheckBox minBalanceRequiredCheck;
    @FXML private TextField minBalanceField;
    @FXML private CheckBox overdraftAllowedCheck;
    @FXML private TextField overdraftLimitField;
    @FXML private TextArea descriptionArea;
    @FXML

    private Label welcomeText;
    private final AccountTypeService accountTypeService = ServiceLocator.getAccountTypeService();

    @FXML
    private void handleCreateCurrentAccount() {
        // TODO: Implement logic to create a CurrentAccount
        System.out.println("Creating a new Current Account...");
    }
    @FXML
    private void handleSaveAccountType() {
        try {
            String name = accountTypeField.getText();
            double interestRate = Double.parseDouble(interestRateField.getText());
            boolean minBalanceRequired = minBalanceRequiredCheck.isSelected();
            double minBalance = minBalanceRequired ? Double.parseDouble(minBalanceField.getText()) : 0.0;
            boolean overdraftAllowed = overdraftAllowedCheck.isSelected();
            double overdraftLimit = overdraftAllowed ? Double.parseDouble(overdraftLimitField.getText()) : 0.0;
            String description = descriptionArea.getText();



            accountTypeService.createAccountType(name, interestRate, minBalanceRequired, minBalance, overdraftAllowed, overdraftLimit, description);

            System.out.println(accountTypeService.getAllAccountTypes());
            // Clear fields after saving
//            accountTypeField.clear();
//            interestRateField.clear();
//            minBalanceRequiredCheck.setSelected(false);
//            minBalanceField.clear();
//            overdraftAllowedCheck.setSelected(false);
//            overdraftLimitField.clear();
//            descriptionArea.clear();

        } catch (NumberFormatException e) {
            System.err.println("Invalid number input.");
        }

    }

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
}