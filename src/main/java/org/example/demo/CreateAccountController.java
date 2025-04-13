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

                }

            }
        });

    }

    // This method restricts input to only valid doubles
    // TODO Delete if not necessary
    private void restrictToDouble(TextField textField) {
        TextFormatter<Double> formatter = new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if (newText.isEmpty()) return change; // Allow empty field
            try {
                Double.parseDouble(newText);
                return change; // Valid double input
            } catch (NumberFormatException e) {
                return null; // Invalid input (block it)
            }
        });

        textField.setTextFormatter(formatter);
    }



    @FXML
    private void handleCreateAccount(ActionEvent event) throws IOException {
        AccountType selected = accountTypeComboBox.getValue();

        // if user selected current -> call service method that creates for current
        if(selected.getName().equals("current")){
           Account newAcc =  accountService.createAccount(holderNameField.getText());
            System.out.println(accountService.getAccountByAccNumber(newAcc.getAccountNumber()));
        // if user selected saving -> call service method that creates for saving
        } else if (selected.getName().equals("saving")) {
            // Get the text from the initial deposit field
            String initialDepositText = initialDepositField.getText();

            // Convert it to a double, handling potential invalid input
            double initialDeposit = 0.0;
            initialDeposit = Double.parseDouble(initialDepositText);
            Account acc = accountService.createAccount(holderNameField.getText(), initialDeposit);
            System.out.println(acc);
        // if user selected fixed -> call service method that creates for fixed
        } else if (selected.getName().equals("fixed")) {
            // Get the text from the initial deposit field
            String initialDepositText = initialDepositField.getText();

            // Convert it to a double, handling potential invalid input
            double initialDeposit = 0.0;
            initialDeposit = Double.parseDouble(initialDepositText);
            Account acc = accountService.createAccount(initialDeposit, holderNameField.getText(), maturityDatePicker.getValue());
            System.out.println(acc);
        }

    }
//        System.out.println(accountService.getAccountByAccNumber(1));




}
