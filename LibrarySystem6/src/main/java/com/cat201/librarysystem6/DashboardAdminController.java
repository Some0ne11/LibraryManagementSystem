package com.cat201.librarysystem6;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class DashboardAdminController {

    // Book Table
    @FXML private TableView<Book> allBooksTable;
    @FXML private TableColumn<Book, String> isbnColumn;
    @FXML private TableColumn<Book, String> titleColumn;
    @FXML private TableColumn<Book, String> authorColumn;
    @FXML private TableColumn<Book, String> yearColumn;
    @FXML private TableColumn<Book, String> publisherColumn;
    @FXML private TableColumn<Book, String> availabilityColumn;
    @FXML private TableColumn<Book, String> borrowerName;

    // Member Table
    @FXML private TableView<Member> allMembersTable;
    @FXML private TableColumn<Member, String> nameColumn;
    @FXML private TableColumn<Member, String> idColumn;
    @FXML private TableColumn<Member, String> stateColumn;
    @FXML private TableColumn<Member, String> emailColumn;
    @FXML private TableColumn<Member, String> phoneNumberColumn;

    @FXML private TextField totalBooksField;
    @FXML private TextField totalMembersField;
    @FXML private TextField issuedBooksField;

    private final ObservableList<Book> bookList = FXCollections.observableArrayList();
    private final ObservableList<Member> memberList = FXCollections.observableArrayList();

    public void initialize() {
        // Book data binding
        isbnColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getISBN()));
        titleColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        authorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAuthor()));
        yearColumn.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getYear()).asObject().asString());
        publisherColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPublisher()));
        availabilityColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().isAvailable() ? "true" : "false"));
        borrowerName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBorrowerName()));

        // Member data binding
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getMemberID()));
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        stateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getState()));
        emailColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        phoneNumberColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));

        loadBooksData();
        loadMembersData();

        // Update total counts
        totalBooksField.setText(String.valueOf(bookList.size()));
        totalMembersField.setText(String.valueOf(memberList.size()));
        issuedBooksField.setText(String.valueOf(countIssuedBooks()));
    }

    private void loadBooksData() {
        bookList.clear();   // Clear current list to avoid duplication

        // Create or access the Library instance
        Library library = Library.getInstance();

        // Load books from file and update the table
        try {
            library.loadBooks("src/main/java/com/cat201/librarysystem6/Book.csv");
            bookList.addAll(library.getAllBooks());
        } catch (IOException e) {
            System.out.println("Error loading books data: " + e.getMessage());
        }

        // Bind updated bookList to the TableView
        allBooksTable.setItems(bookList);
    }

    private void loadMembersData() {
        // Create or access the Member instance
        ArrayList<Member> loadedMembers = Member.getAllMembers("src/main/java/com/cat201/librarysystem6/Members.csv");

        // Clear current list to avoid duplication
        memberList.clear();

        // Add all loaded members to the list
        memberList.addAll(loadedMembers);

        // Bind updated memberList to the TableView
        allMembersTable.setItems(memberList);
    }

    private int countIssuedBooks() {
        // Use stream to count books where availability is marked as "false"
        return (int) bookList.stream().filter(book -> !book.isAvailable()).count();
    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("home_admin.fxml"));
        Parent root = loader.load();

        // Get the stage from the event's source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Set the new scene
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

}