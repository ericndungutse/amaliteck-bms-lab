package org.example.demo;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.example.demo.model.Account;
import org.example.demo.model.AccountType;
import org.example.demo.repositories.AccountTypeRepository;
import org.example.demo.services.AccountService;
import org.example.demo.services.AccountTypeService;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    @FXML
    private ComboBox<AccountType> accountTypeComboBox;
    @FXML
    private TextField holderNameField;

    @FXML
    private Label initialDepositLabel;

    @FXML
    private TextField initialDepositField;

    @FXML
    private Label maturityDateLabel;

    @FXML
    private DatePicker maturityDatePicker;

    @FXML
    private Label maturityDateInfoLabel;

    private final AccountTypeService accountTypeService = ServiceLocator.getAccountTypeService();
    private final AccountService accountService = ServiceLocator.getAccountService();

    private void restrictMaturityDatePicker() {
        LocalDate now = LocalDate.now();
        LocalDate minDate = now.plusMonths(6);
        LocalDate maxDate = now.plusMonths(12);

        maturityDatePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        // Disable dates not in the 6–12 month range
                        if (item.isBefore(minDate) || item.isAfter(maxDate)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Optional: red tint for disabled
                        }
                    }
                };
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Hide initial deposit fields by default
        initialDepositLabel.setVisible(false);
        initialDepositField.setVisible(false);
        maturityDateInfoLabel.setVisible(false);
        maturityDatePicker.setVisible(false);
        maturityDateLabel.setVisible(false);


        List<AccountType> types = accountTypeService.getAllAccountTypes();
        accountTypeComboBox.setItems(FXCollections.observableArrayList(types));

        // Set listener for account type selection
        accountTypeComboBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                String typeName = newVal.getName().toLowerCase();

                boolean isFixed = typeName.equals("fixed");
                boolean isSaving = typeName.equals("saving");
                boolean isCurrent = typeName.equals("current");

                if(isFixed){

                initialDepositLabel.setVisible(true);
                initialDepositField.setVisible(true);
                maturityDatePicker.setVisible(true);
                maturityDateInfoLabel.setVisible(true);
                maturityDateLabel.setVisible(true);


                    restrictMaturityDatePicker();

                } else if (isSaving) {
                    initialDepositLabel.setVisible(true);
                    initialDepositField.setVisible(true);
                    maturityDateLabel.setVisible(false);
                    maturityDatePicker.setVisible(false);
                    maturityDateInfoLabel.setVisible(false);
                } else if (isCurrent) {
                    initialDepositLabel.setVisible(false);
                    initialDepositField.setVisible(false);
                    maturityDatePicker.setVisible(false);
                    maturityDateInfoLabel.setVisible(false);
                    maturityDateLabel.setVisible(false);
                }

            }
        });

    }

    @FXML
    private void handleCreateAccount(ActionEvent event) throws IOException {
        AccountType selected = accountTypeComboBox.getValue();


        // Ensure user selects an account
        if (selected == null) {
            showError("Please select an account type.");
            return;
        }

        // Ensure user enters his name
        if(holderNameField.getText().isEmpty()){
            showError("Please enter your name in holder name field.");
            return;
        }

        // if user selected current -> call service method that creates for current
        if(selected.getName().equals("current")){
           Account newAcc =  accountService.createAccount(holderNameField.getText());
           showInfo("Account Created", "Your account has bee created successfully. Your account number is " + newAcc.getAccountNumber());
        // if user selected saving -> call service method that creates for saving
        } else if (selected.getName().equals("saving")) {
            // Get the text from the initial deposit field
            String initialDepositText = initialDepositField.getText();

            // Validate initial deposit
            if(initialDepositText.isEmpty()){
                showError("Please enter initial deposit.");
                return;
            }

            double initialDeposit;
            // Validate number
            try {
            // Convert it to a double, handling potential invalid input
            initialDeposit = Double.parseDouble(initialDepositText);
            } catch (NumberFormatException e) {
                showError("Please enter a valid number for initial deposit.");
                return;
            }

            // Ensure initial deposit is greater or equal to min balance
            double minBalance = accountTypeService.getAccountTypeByName("saving").getMinBalance();
            if(initialDeposit < minBalance){
                showError("Minimum initial deposit is " + minBalance);
            }

            // Create the account
            Account acc = accountService.createAccount(holderNameField.getText(), initialDeposit);
            showInfo("Account Created", "Your account has bee created successfully. Your account number is " + acc.getAccountNumber());
        // if user selected fixed -> call service method that creates for fixed
        } else if (selected.getName().equals("fixed")) {
            // Get the text from the initial deposit field
            String initialDepositText = initialDepositField.getText();

            // Validate initial deposit
            if(initialDepositText.isEmpty()){
                showError("Please enter initial deposit.");
                return;
            }

            double initialDeposit = 0.0;
            try {
                // Convert it to a double, handling potential invalid input
                initialDeposit = Double.parseDouble(initialDepositText);
            } catch (NumberFormatException e) {
                showError("Please enter a valid number for initial deposit.");
                return;
            }

            Account acc = accountService.createAccount(initialDeposit, holderNameField.getText(), maturityDatePicker.getValue());
            showInfo("Account Created", "Your account has bee created successfully. Your account number is " + acc.getAccountNumber());
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

    @FXML
    public void handleGoToHome(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
