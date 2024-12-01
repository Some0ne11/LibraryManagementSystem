package com.cat201.librarysystem6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class HomeAdminController {

    // Dashboard Admin Fields
    @FXML private Button addNewBookButton;
    @FXML private Button dashboardButton;
    @FXML private Button logoutButton;
    @FXML private Button issueBookButton;
    @FXML private Button returnBookButton;
    @FXML private Button searchBookButton;

    // Constructor
    public HomeAdminController() {
        Library library = new Library(); // Initialise the library instance
    }

    // Method to handle the 'Add New Book' button click
    @FXML
    private void handleAddNewBook(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("new_book.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Method to handle the 'Dashboard' button click
    @FXML
    private void handleDashboard(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("dashboard_admin.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Method to handle the 'Issue Book' button click
    @FXML
    private void handleIssueBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("issue_book.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Method to handle the 'Return Book' button click
    @FXML
    private void handleReturnBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("return_book.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    // Method to handle the 'Search Book' button click
    @FXML
    private void handleSearchBook(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("search_book.fxml"));
        Parent root = loader.load();
        Stage newStage = new Stage();
        newStage.setResizable(false);
        newStage.setTitle("Search Book");
        newStage.setScene(new Scene(root));
        newStage.show();
    }

    @FXML
    private void handleLogout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("start_page.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
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
