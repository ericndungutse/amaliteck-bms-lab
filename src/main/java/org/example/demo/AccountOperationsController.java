package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

public class AccountOperationsController {
    @FXML
    private TextField accountNumberField;

    @FXML
    private TextArea output;

    @FXML
    private TextField amountField;

    private final AccountTypeService accountTypeService = ServiceLocator.getAccountTypeService();
    private final AccountService accountService = ServiceLocator.getAccountService();

    // Check Balance
    public void handleCheckBalance(ActionEvent actionEvent) {
        String accountNumberText = accountNumberField.getText();
        if (accountNumberText == null ||accountNumberText.isBlank()) {
            showError("Please enter a valid account ID.");
            return;
        }

        try {
            int accountNumber = Integer.parseInt(accountNumberText);
            double balance = accountService.getBalance(accountNumber);
            showInfo("Balance","Current Balance: " + balance);
        } catch (NumberFormatException e) {
           showError("Invalid account ID format.");
        } catch (Exception e) {
           showError("Error: " + e.getMessage());
        }
    }

    // Deposit
    public void handleDeposit(ActionEvent actionEvent) {
        String accountNumberText = accountNumberField.getText();
        String amountText = amountField.getText();

        if (accountNumberText.isEmpty() || amountText.isEmpty()) {
            showError("Please fill in both Account number and Amount.");
            return;
        }

        try {
            int accountNumber = Integer.parseInt(accountNumberText);
            double amount = Double.parseDouble(amountText);

            accountService.deposit(accountNumber, amount);
            showInfo("Deposit successful","Deposit successful! New balance is " + accountService.getBalance(accountNumber));

        } catch (NumberFormatException e) {
            showError("Invalid input. Please enter numeric values.");
        } catch (RuntimeException e) {
            showError("Error: " + e.getMessage());
        }
    }

    // Withdraw
    public void handleWithdraw(ActionEvent actionEvent) {
        // Get the withdrawal amount from the input field;
        String withdrawalAmountText = amountField.getText();
        // Get Account number
        String accountNumberText = accountNumberField.getText();

        // Check if the input is not empty and is a valid number
        if (accountNumberText.isEmpty() || withdrawalAmountText.isEmpty()) {
            showError("Please fill in both Account Number and Amount.");
            return;
        }

        double withdrawalAmount;
        int accountNumber;
        try {
            withdrawalAmount = Double.parseDouble(withdrawalAmountText);
            accountNumber = Integer.parseInt(accountNumberText);
        } catch (NumberFormatException e) {
            showError("Invalid withdrawal amount or account number. Please enter a valid number.");
            return;
        }

        try {
            // Call the account service to withdraw the amount
            accountService.withdraw(accountNumber, withdrawalAmount);

            // Show the updated balance to the user
            showInfo("Withdrawal successful", "New balance: " + accountService.getBalance(accountNumber));

        } catch (RuntimeException e) {
            // Handle any exceptions thrown by the accountService
            showError(e.getMessage());
        }
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


    // Show info message in an alert
    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
