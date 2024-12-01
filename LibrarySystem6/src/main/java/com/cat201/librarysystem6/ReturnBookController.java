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

public class ReturnBookController {

    // Member Fields
    @FXML private TextField memberIdField;
    @FXML private TextField memberNameField;
    @FXML private TextField memberStateField;
    @FXML private TextField memberEmailField;
    @FXML private TextField memberPhoneField;

    // Book Fields
    @FXML private TextField isbnField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField yearField;
    @FXML private TextField publisherField;

    // Buttons
    @FXML private Button searchBookButton;
    @FXML private Button searchMemberButton;
    @FXML private Button returnBookButton;
    @FXML private Button backButton;

    private Library library;
    private static final String FILE_PATH = "src/main/java/com/cat201/librarysystem6/Book.csv";
    private final String FILE_PATH_MEMBER = "src/main/java/com/cat201/librarysystem6/Members.csv";

    public void initialize() throws IOException{
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
            yearField.setText(String.valueOf(book.getYear()));
            publisherField.setText(book.getPublisher());
        } else {
            // Handle case where book is not found
            showAlert(Alert.AlertType.ERROR, "Invalid ISBN", "Invalid ISBN");
            titleField.clear();
            authorField.clear();
            yearField.clear();
            publisherField.clear();
        }
    }

    @FXML
    private void handleSearchMember(ActionEvent event) throws IOException {
        String memberId = memberIdField.getText().trim();

        // Retrieve all members from the file
        ArrayList<Member> members = Member.getAllMembers(FILE_PATH_MEMBER);
        // Search for the member with the entered member ID
        Member foundMember = null;
        for (Member m : members) {
            if (m.getMemberID().equals(memberId)) {
                foundMember = m;
                break;
            }
        }

        if(foundMember != null) {
            memberNameField.setText(foundMember.getName());
            memberStateField.setText(foundMember.getState());
            memberEmailField.setText(foundMember.getEmail());
            memberPhoneField.setText(foundMember.getPhoneNumber());
        }   else {
            showAlert(Alert.AlertType.ERROR, "Invalid Member ID", "Invalid Member ID");
            memberNameField.clear();
            memberStateField.clear();
            memberEmailField.clear();
            memberPhoneField.clear();
        }
    }

    @FXML
    private void handleReturnBook(ActionEvent event) {
        String isbn = isbnField.getText().trim();
        String memberId = memberIdField.getText().trim();

        // Retrieve the book by ISBN
        Book book = library.byISBN(isbn);
        // Ensure the book exists
        if (book == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Book not found.");
            return;
        }
        // Retrieve all members from the file
        ArrayList<Member> members = Member.getAllMembers(FILE_PATH_MEMBER);

        // Search for the member with the entered member ID
        Member foundMember = null;
        for (Member m : members) {
            if (m.getMemberID().equals(memberId)) {
                foundMember = m;
                break;
            }
        }

        // Ensure the member exists
        if (foundMember == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Member not found.");
            return;
        }

        // Check if the book has a borrower and the member ID matches
        if (book.getBorrowerName() != null && book.getBorrowerName().equals(foundMember.getName())) {
            // Ensure that the member's ID matches the book's borrower
            if (book.getBorrowerName().equals(foundMember.getName())) {
                book.returnBook(); // Mark the book as returned
                library.updateBook(book); // Update the book in the library system
                showAlert(Alert.AlertType.INFORMATION, "Book Returned", "Book returned successfully.");
                clearBookDetails();
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "This member did not borrow the book.");
            }
        } else {
            showAlert(Alert.AlertType.ERROR, "Error", "The book is not currently borrowed.");
        }
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("home_admin.fxml"));
        javafx.scene.Parent homeScreen = loader.load();
        javafx.scene.Scene homeScene = new javafx.scene.Scene(homeScreen);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(homeScene);
        stage.show();
    }

    // Helper method to clear book details from the UI
    private void clearBookDetails() {
        titleField.clear();
        authorField.clear();
        yearField.clear();
        publisherField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
