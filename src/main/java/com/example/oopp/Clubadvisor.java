package com.example.oopp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Clubadvisor extends Application {
    EventSheduleDatabaseConnection dbConnector = new EventSheduleDatabaseConnection();

    // Use the connection
    Connection connection = dbConnector.getConnection();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("clubadvisor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Student Club Activity Management System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
