package com.cat201.librarysystem6;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class IssueBookController {

    // Book fields
    @FXML private TextField isbnField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField yearPublishedField;
    @FXML private TextField publisherField;

    // Member fields
    @FXML private TextField memberIdField;
    @FXML private TextField nameField;
    @FXML private TextField stateField;
    @FXML private TextField emailField;
    @FXML private TextField phoneNumField;

    // Buttons
    @FXML private Button searchBookButton;
    @FXML private Button searchMemberButton;
    @FXML private Button backButton;
    @FXML private Button issueButton;

    private Library library;
    private static final String FILE_PATH = "src/main/java/com/cat201/librarysystem6/Book.csv";

    @FXML
    private void initialize() throws IOException {
        library = new Library();
        library.loadBooks(FILE_PATH);
    }

    @FXML
    private void handleSearchBook(ActionEvent event) throws IOException {
        String isbn = isbnField.getText();
        Book book = library.byISBN(isbn);
        if (book != null) {
            titleField.setText(book.getTitle());
            authorField.setText(book.getAuthor());
            yearPublishedField.setText(String.valueOf(book.getYear()));
            publisherField.setText(book.getPublisher());
        } else {
            // Handle case where book is not found
            showBookNotFoundAlert(isbn);
            titleField.clear();
            authorField.clear();
            yearPublishedField.clear();
            publisherField.clear();
        }
    }

    @FXML
    private void handleSearchMember(ActionEvent event) throws IOException {
        String memberId = memberIdField.getText().trim();
        // Retrieve all members from the file
        ArrayList<Member> members = Member.getAllMembers("src/main/java/com/cat201/librarysystem6/Members.csv");
        // Search for the member with the entered member ID
        Member foundMember = null;
        for (Member m : members) {
            if (m.getMemberID().equals(memberId)) {
                foundMember = m;
                break;
            }
        }

        if(foundMember != null) {
            nameField.setText(foundMember.getName());
            stateField.setText(foundMember.getState());
            emailField.setText(foundMember.getEmail());
            phoneNumField.setText(foundMember.getPhoneNumber());
        }   else {
            showAlert(Alert.AlertType.ERROR, "Invalid Member ID", "Invalid Member ID");
            nameField.clear();
            stateField.clear();
            emailField.clear();
            phoneNumField.clear();
        }
    }

    @FXML
    private void handleIssueBook() {
        String isbn = isbnField.getText();
        Book book = library.byISBN(isbn);
        if (book != null && book.isAvailable()) {
            String borrowerName = nameField.getText();
            book.borrowBook(borrowerName);
            library.writeFile(FILE_PATH);
            showAlert(Alert.AlertType.INFORMATION, "Book Issued", "Book was successfully issued");
            clearAllFields();
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "Cannot Issue Book");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showBookNotFoundAlert(String isbn) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Book Not Found");
        alert.setContentText("The book '" + isbn + "' was not found in the library.");
        alert.showAndWait();
    }

    @FXML
    private void handleBack(javafx.event.ActionEvent event) {
        try{
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("home_admin.fxml"));
            javafx.scene.Parent homeScreen = loader.load();
            javafx.scene.Scene homeScene = new javafx.scene.Scene(homeScreen);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setResizable(false);
            stage.setScene(homeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to go back: " + e.getMessage());
        }
    }

    // Clear All Fields
    public void clearAllFields() {
        isbnField.clear();
        titleField.clear();
        authorField.clear();
        yearPublishedField.clear();
        publisherField.clear();
        memberIdField.clear();
        nameField.clear();
        stateField.clear();
        emailField.clear();
        phoneNumField.clear();
    }
}
