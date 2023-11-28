package com.example.oopp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.oopp.Database.getDBConnection;
import static com.example.oopp.HelloController.showAlertSuccess;

public class StudentController {
    // Variable to store the signed-in student ID
    public static String signedInStudentId;

    @FXML
    private Button studentClubButton;

    @FXML
    private Button studentEventButton;

    @FXML
    private Button studentExitButton;

    @FXML
    private Button studentHomeButton;

    @FXML
    private Button studentJoinClubButton;

    @FXML
    private Button studentLeaveClubButton;

    @FXML
    private AnchorPane student_club;

    @FXML
    private AnchorPane student_event;

    @FXML
    private AnchorPane student_home;

    @FXML
    private TableView<?> student_joined_club_table;

    @FXML
    private TableView<?> student_send_club_request_table;




    public void studentHomeButtonOnAction(ActionEvent event){
        HelloController.toggleVisibility(student_home, true); //method for showing and hiding anchor panes in HelloController
        HelloController.toggleVisibility(student_club, false);
        HelloController.toggleVisibility(student_event, false);
    }

    public void studentClubButtonOnAction(ActionEvent event){
        HelloController.toggleVisibility(student_home, false);
        HelloController.toggleVisibility(student_club, true);
        HelloController.toggleVisibility(student_event, false);
    }

    public void studentEventButtonOnAction(ActionEvent event){
        HelloController.toggleVisibility(student_home, false);
        HelloController.toggleVisibility(student_club, false);
        HelloController.toggleVisibility(student_event, true);
    }

    public void studentExitButtonOnAction(ActionEvent event){
        Stage stage = (Stage) studentExitButton.getScene().getWindow();
        stage.close();
    }

    // -----------------------------------------------------------------------------------------------------------------

 //----------------------------save student data to database------------------------------------------------------------

    public static void insertStudent(Student student) {

        // Check if the student already exists in the database
        if (isStudentExists(student.getStudentId())) {
            showAlertSuccess("Student with ID " + student.getStudentId() + " already exists.");
            return;
        }
        String insertQuery = "INSERT INTO student (studentId, studentName, userPassword) VALUES (?, ?, ?)";
        // ? is parameterized placeholder for the values to be inserted . It helps to prevent SQL injection attacks.

        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getStudentName());
            preparedStatement.setString(3, student.getStudentPassword());

            int affectedRows = preparedStatement.executeUpdate(); //If affectedRows > 0 , it means student was successfully registered
            if (affectedRows > 0) {
                showAlertSuccess("Student registered successfully!");


                // Load student FXML after successful registration
                FXMLLoaderUtil.loadFXML("student.fxml", "Student");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlertSuccess("Error occurred while registering the student.");
        }
    }

    // Check if the student already exists in the database
    private static boolean isStudentExists(String studentId) {
        String query = "SELECT COUNT(*) FROM student WHERE studentId = ?";
        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//-------------------------------get student data from the database-----------------------------------------------------

    private static final String GET_STUDENT_QUERY = "SELECT studentId, studentName, userPassword FROM student WHERE studentId = ?";

    static Student getStudentFromDatabase(String studentId){
        try (Connection connection = Database.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENT_QUERY)) {

            preparedStatement.setString(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String retrievedStudentId = resultSet.getString("studentId");
                    String retrievedStudentName = resultSet.getString("studentName");
                    String retrievedStudentPassword = resultSet.getString("userPassword");

                    return new Student(retrievedStudentId, retrievedStudentName, retrievedStudentPassword);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlertSuccess("Error retrieving student data from the database.");
        }

        return null;
    }

// ------------------------------------------table loading--------------------------------------------------------------


}
