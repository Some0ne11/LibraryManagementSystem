package com.cat201.librarysystem6;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;

public class SearchBookController {

    @FXML private TextField searchField;
    @FXML private Button searchButton;
    @FXML private GridPane resultGrid;
    @FXML private ImageView bookImage;
    @FXML private TextField isbnField;
    @FXML private TextField titleField;
    @FXML private TextField authorField;
    @FXML private TextField yearField;
    @FXML private TextField publisherField;
    @FXML private TextField availabilityField;

    private Library library;

    public void initialize() throws IOException{
        library = new Library();
        String FILE_PATH = "src/main/java/com/cat201/librarysystem6/Book.csv";
        library.loadBooks(FILE_PATH);
    }

    @FXML
    private void handleSearch() {
        String searchQuery = searchField.getText().trim();
        if (searchQuery.isEmpty()) {
            clearResults();
            showAlert(AlertType.WARNING, "Search Error", "Please enter a search query.");
            return;
        }

        // First, try searching by ISBN
        Book book = library.byISBN(searchQuery);
        if (book != null) {
            displayBookDetails(book);
            showAlert(AlertType.INFORMATION, "Book Found", "The book has been found in the library by ISBN!");
            return;
        }

        // Then, search by title
        ArrayList<Book> titleResults = library.byTitle(searchQuery);
        if (!titleResults.isEmpty()) {
            displayBookDetails(titleResults.getFirst()); // Display the first match
            showAlert(AlertType.INFORMATION, "Book Found", "The book has been found in the library by Title!");
            return;
        }

        // Lastly, search by author
        ArrayList<Book> authorResults = library.byAuthor(searchQuery);
        if (!authorResults.isEmpty()) {
            displayBookDetails(authorResults.getFirst()); // Display the first match
            showAlert(AlertType.INFORMATION, "Book Found", "The book has been found in the library by Author!");
            return;
        }

        // If no matches are found
        clearResults();
        showAlert(AlertType.ERROR, "Book Not Found", "No book matches your search query.");
    }

    private void displayBookDetails(Book book) {
        isbnField.setText(book.getISBN());
        titleField.setText(book.getTitle());
        authorField.setText(book.getAuthor());
        yearField.setText(String.valueOf(book.getYear()));
        publisherField.setText(book.getPublisher());
        availabilityField.setText(book.isAvailable() ? "Available" : "Unavailable");
    }

    private void clearResults() {
        isbnField.clear();
        titleField.clear();
        authorField.clear();
        yearField.clear();
        publisherField.clear();
        availabilityField.clear();
    }

    private void showAlert(AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}