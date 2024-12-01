package com.cat201.librarysystem6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MemberHomeController {

    @FXML private Button searchBookButton;
    @FXML private Button logoutButton;
    @FXML private Button submitFeedbackButton;
    @FXML private TextArea feedbackTextArea;

    // Event handler for "Search Book" button
    @FXML
    private void onSearchBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search_book.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setTitle("Search Book");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    private void onSubmitFeedback(ActionEvent event) throws IOException {
        String feedback = feedbackTextArea.getText().trim();

        if (feedback.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Feedback Error", "Feedback cannot be empty!");
        }
        else {
            showAlert(Alert.AlertType.INFORMATION, "Feedback Submitted", "Thank you for your feedback!");
            feedbackTextArea.clear();
        }
    }

    // Event handler for "Logout" button
    @FXML
    private void onLogout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("start_page.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setTitle("Start Page");
        stage.setScene(new Scene(root));
        stage.show();

        showAlert(Alert.AlertType.INFORMATION, "Logout succesful", "System logout successfully.");
    }

    // Utility method to show alerts
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}