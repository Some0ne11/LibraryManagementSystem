package com.cat201.librarysystem6;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class NewBookController {

    @FXML private TextField isbnField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField yearPublishedField;
    @FXML private TextField publisherField;

    private Library library;
    private final String FILE_PATH = "src/main/java/com/cat201/librarysystem6/Book.csv";
    @FXML
    private void initialize() throws IOException {
        // Initialize the Library object and load book data
        library = new Library();
        library.loadBooks(FILE_PATH);
    }

    @FXML
    private void handleAddBook() {
        try {
            // Get input values from the text fields
            String isbn = isbnField.getText().trim();
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            String yearText = yearPublishedField.getText().trim();
            String publisher = publisherField.getText().trim();

            // Validate that all fields are filled
            if (isbn.isEmpty() || title.isEmpty() || author.isEmpty() || yearText.isEmpty() || publisher.isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "All fields must be filled!");
                return;
            }

            // Validate the year input (ensure it is a valid integer)
            int year;
            try {
                year = Integer.parseInt(yearText);
                if (year < 1000 || year > 9999) {
                    throw new NumberFormatException("Invalid year range");
                }
            } catch (NumberFormatException e) {
                showAlert(Alert.AlertType.ERROR, "Input Error", "Year must be a valid 4-digit number!");
                return;
            }

            // Check if a book with the same ISBN already exists
            if (library.isBookExists(isbn)) {
                showAlert(Alert.AlertType.ERROR, "Duplicate Error", "A book with the same ISBN already exists!");
                return;
            }

            Book newBook = new Book(isbn, title, author, year, publisher, true, "null");
            // Add the book to the library and save it to the file
            library.addBook(newBook, FILE_PATH);
            showAlert(Alert.AlertType.INFORMATION, "Success", "Book added successfully!");
            clearFields(); // Clear the text fields for new input

        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack(javafx.event.ActionEvent event) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("home_admin.fxml"));
            javafx.scene.Parent homeScreen = loader.load();
            javafx.scene.Scene homeScene = new javafx.scene.Scene(homeScreen);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(homeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to go back: " + e.getMessage());
        }
    }

    // Utility method to clear input fields
    private void clearFields() {
        isbnField.clear();
        titleField.clear();
        authorField.clear();
        yearPublishedField.clear();
        publisherField.clear();
    }

    // Utility method to show alert messages
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
