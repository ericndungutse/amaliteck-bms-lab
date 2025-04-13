package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

public class AccountOperationsController {
    @FXML
    private TextField accountIdField;

    @FXML
    private Button getBalanceButton;

    @FXML
    private Label balanceLabel;

    private final AccountTypeService accountTypeService = ServiceLocator.getAccountTypeService();
    private final AccountService accountService = ServiceLocator.getAccountService();

    public void handleCheckBalance(ActionEvent actionEvent) {
        String accountIdText = accountIdField.getText();
        if (accountIdText == null || accountIdText.isBlank()) {
            balanceLabel.setText("Please enter a valid account ID.");
            return;
        }

        try {
            int accountId = Integer.parseInt(accountIdText);
            double balance = accountService.getBalance(accountId);
            System.out.println(balance);
            balanceLabel.setText("Current Balance: " + balance);
        } catch (NumberFormatException e) {
            balanceLabel.setText("Invalid account ID format.");
        } catch (Exception e) {
            balanceLabel.setText("Error: " + e.getMessage());
        }
    }

    public void handleDeposit(ActionEvent actionEvent) {
    }

    public void handleWithdraw(ActionEvent actionEvent) {
    }

    public void handleGetLastNTransactions(ActionEvent actionEvent) {
    }
}
