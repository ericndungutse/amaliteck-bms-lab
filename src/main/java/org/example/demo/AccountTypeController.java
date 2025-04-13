package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.model.AccountType;
import org.example.demo.services.AccountTypeService;

import java.io.IOException;
import java.util.List;

public class AccountTypeController {
    @FXML private TextField accountTypeField;
    @FXML private TextField interestRateField;
    @FXML private CheckBox minBalanceRequiredCheck;
    @FXML private TextField minBalanceField;
    @FXML private CheckBox overdraftAllowedCheck;
    @FXML private TextField overdraftLimitField;
    @FXML private TextArea descriptionArea;
    @FXML private ListView accountTypeListView;
    private final AccountTypeService accountTypeService = ServiceLocator.getAccountTypeService();

    @FXML
    public void initialize() {
        if (accountTypeListView != null) {
            List<AccountType> accountTypes = accountTypeService.getAllAccountTypes();
            accountTypeListView.getItems().addAll(accountTypes);


            accountTypeListView.setCellFactory(listView -> new ListCell<AccountType>() {
                @Override
                protected void updateItem(AccountType type, boolean empty) {
                    super.updateItem(type, empty);
                    if (empty || type == null) {
                        setText(null);
                        setGraphic(null); // Very important!
                    } else {
                        setText(String.format("""
                                        📘 Name: %s
                                        📈 Interest Rate: %.2f%%
                                        🛑 Min Balance Required: %s
                                        💰 Min Balance: %.2f
                                        🔄 Overdraft Allowed: %s
                                        💸 Overdraft Limit: %.2f
                                        📝 Description: %s
                                        """,
                                type.getName(),
                                type.getFixedAccInterestRate(),
                                type.isMinBalanceRequired() ? "Yes" : "No",
                                type.getMinBalance(),
                                type.isOverdraftAllowed() ? "Yes" : "No",
                                type.getOverdraftLimit(),
                                type.getDescription()
                        ));
                        setStyle("-fx-padding: 10; -fx-border-color: lightgray; -fx-background-color: #f9f9f9;");
                    }
                }
            });
        }
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
            accountTypeField.clear();
            interestRateField.clear();
            minBalanceRequiredCheck.setSelected(false);
            minBalanceField.clear();
            overdraftAllowedCheck.setSelected(false);
            overdraftLimitField.clear();
            descriptionArea.clear();

        } catch (NumberFormatException e) {
            System.err.println("Invalid number input.");
        }
    }

    @FXML
    public void handleGoToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @FXML
    public void handleNavigateToCreateAccountType(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("create-account-types-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
