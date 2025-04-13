package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

public class AccountOperationsController {
    @FXML
    private TextField accountIdField;

    @FXML
    private TextArea output;

    @FXML
    private Label balanceLabel;

    @FXML
    private TextField amountField;

    private final AccountTypeService accountTypeService = ServiceLocator.getAccountTypeService();
    private final AccountService accountService = ServiceLocator.getAccountService();

    // Check Balance
    public void handleCheckBalance(ActionEvent actionEvent) {
        String accountIdText = accountIdField.getText();
        if (accountIdText == null || accountIdText.isBlank()) {
            showError("Please enter a valid account ID.");
            return;
        }

        try {
            int accountId = Integer.parseInt(accountIdText);
            double balance = accountService.getBalance(accountId);
            System.out.println(balance);
            balanceLabel.setText("Current Balance: " + balance);
        } catch (NumberFormatException e) {
           showError("Invalid account ID format.");
        } catch (Exception e) {
           showError("Error: " + e.getMessage());
        }
    }

    // Deposit
    public void handleDeposit(ActionEvent actionEvent) {
        String idText = accountIdField.getText();
        String amountText = amountField.getText();

        if (idText.isEmpty() || amountText.isEmpty()) {
            showError("Please fill in both Account ID and Amount.");
            return;
        }

        try {
            int accountId = Integer.parseInt(idText);
            double amount = Double.parseDouble(amountText);

            accountService.deposit(accountId, amount);
            output.setText("Deposit successful!");

        } catch (NumberFormatException e) {
            showError("Invalid input. Please enter numeric values.");
        } catch (RuntimeException e) {
            showError("Error: " + e.getMessage());
        }
    }

    // Withdraw
    public void handleWithdraw(ActionEvent actionEvent) {
    }

    public void handleGetLastNTransactions(ActionEvent actionEvent) {
    }

    // Show error message in an alert
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
