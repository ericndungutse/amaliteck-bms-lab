package org.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class AddAccTypeController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onBackButtonClick() {
        try {
            // Load the new FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/demo/hello-view.fxml"));
            AnchorPane newPane = loader.load();

            // Create a new scene with the loaded FXML content
            Scene newScene = new Scene(newPane);

            // Get the current stage (window) and set the new scene
            Stage currentStage = (Stage) welcomeText.getScene().getWindow();
            currentStage.setScene(newScene);

            // Optionally, you can also set a title for the new stage
            currentStage.setTitle("Create Account Type");

            // Show the new scene
            currentStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
