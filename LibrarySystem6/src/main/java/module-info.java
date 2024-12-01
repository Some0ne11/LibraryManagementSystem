module com.cat201.librarysystem6 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.cat201.librarysystem6 to javafx.fxml;
    exports com.cat201.librarysystem6;
}