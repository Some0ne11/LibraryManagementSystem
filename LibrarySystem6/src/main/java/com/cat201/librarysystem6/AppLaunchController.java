package com.cat201.librarysystem6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AppLaunchController {

    // Fields for Admin Login
    @FXML TextField loginAdminID;
    @FXML TextField loginAdminPassword;

    private static final String adminID = "admin";
    private static final String adminPassword = "admin";

    @FXML
    private void handleAdminLogin(ActionEvent event) throws IOException {
        String enteredAdminID = loginAdminID.getText().trim();
        String enteredAdminPassword = loginAdminPassword.getText().trim();

        // Validate credentials
        boolean isAuthenticated = validateAdminLogin(enteredAdminID, enteredAdminPassword);

        if (isAuthenticated) {
            showAlert(Alert.AlertType.INFORMATION, "Login Success", "Welcome back Administrator!");
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("home_admin.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setTitle("Home-Admin");
            stage.setScene(new Scene(root));
            stage.show();

        } else {
            showAlert(Alert.AlertType.ERROR, "Login Error", "Invalid Admin ID or password.");
        }
    }

    private boolean validateAdminLogin(String enteredAdminID, String enteredAdminPassword) {
        // Check if the entered ID and password match the predefined ones
        return enteredAdminID.equals(adminID) && enteredAdminPassword.equals(adminPassword);
    }
    /* -----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

    // Handle the "Login as Member" button
    @FXML
    private void handleLoginAsMember(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setTitle("Login");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
