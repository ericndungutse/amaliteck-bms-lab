package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.model.Transaction;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

import java.io.IOException;
import java.util.List;

public class AccountOperationsController {
    @FXML
    private TextField accountNumberField;

    @FXML
    private TextArea output;

    @FXML
    private TextField amountField;

    @FXML
    private TextField lastNField;

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
        String lastNText = lastNField.getText().trim();
        String accountNumberText = accountNumberField.getText();


        if (accountNumberText.isEmpty() || lastNText.isEmpty()) {
            showError("Please fill in both Account number and number of transactions.");
            return;
        }

        int n, accountNumber;

        try {
            n = Integer.parseInt(lastNText);
            accountNumber = Integer.parseInt(accountNumberText);

            if (n <= 0) throw new NumberFormatException();
        } catch (NumberFormatException e) {
            showError("Invalid number entered. Please enter a positive integer for account number and number of transactions.");
            return;
        }

        try {
            List<Transaction> transactions = accountService.getLastNTransactions(accountNumber, n);

            if (transactions.isEmpty()) {
                output.setText("No transactions found.");
                return;
            }

            StringBuilder sb = new StringBuilder("The "+ n + " Recent Transactions:\n");
            for (Transaction tx : transactions) {
                sb.append(String.format(
                        "Type: %s Amount: %.2f Date: %s\n",
                        tx.getType(),
                        tx.getAmount(),
                        tx.getDate()
                ));
            }

            output.setText(sb.toString());

        } catch (RuntimeException e) {
            output.setText("Error: " + e.getMessage());
        }
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

    public void handleGoToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
