package com.cat201.librarysystem6;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppLaunch extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AppLaunch.class.getResource("start_page.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 703, 486);

        // Disable the maximize functionality
        stage.setResizable(false);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}