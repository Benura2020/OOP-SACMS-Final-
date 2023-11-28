package com.example.oopp;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;

import com.example.oopp.Club;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;

import java.util.List;
import java.util.ResourceBundle;

import java.util.ArrayList;
import java.util.List;


import static com.example.oopp.Database.getDBConnection;
import static com.example.oopp.HelloController.showAlertSuccess;


public class StudentController implements Initializable {


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
    private TableColumn<Club, String> studentClubDescriptionDisplay;

    @FXML
    private TableView<Club> studentClubDisplayTable;

    @FXML
    private TableColumn<Club, String> studentClubNameDisplay;

    @FXML
    private TableView<Club> student_joined_club_table;

    @FXML
    private TableView<Club> student_send_club_request_table;

    @FXML
    private TableColumn<Club, Integer> clubIdColumn;

    @FXML
    private TableColumn<Club, String> clubNameColumn;

    @FXML
    private TableColumn<Club, String> clubDescriptionColumn;

    @FXML
    private TableColumn<Club, String> teacherIdColumn;




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

    public void studentEventButtonOnAction(){
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


    EventSheduleDatabaseConnection dbConnection = new EventSheduleDatabaseConnection();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        List<Club> allClubs = dbConnection.getAllClubs();
//
//        // Set up the club name column
//        studentClubNameDisplay.setCellValueFactory(new PropertyValueFactory<>("clubName"));
//
//        // Set up the club description column (replace "getDescription" with your actual method)
//        studentClubDescriptionDisplay.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getClubDescription()));
//
//        // Add all clubs to the table
//        studentClubDisplayTable.getItems().addAll(allClubs);



    }

//    public void handleJoinButtonClick(ActionEvent event) {
//        // Get the selected club
//        Club selectedClub = studentClubDisplayTable.getSelectionModel().getSelectedItem();
//
//        if (selectedClub != null) {
//            // Get student ID from your controller (replace this with your actual method)
//            String studentId = "S001";
//
//            // Get the club ID from the database
//            int clubId = dbConnection.getClubIdByClubName(selectedClub.getClubName());
//
//            // Insert the membership request
//            dbConnection.insertMembershipRequest(studentId, clubId);
//
//
//        } else {
//            // Handle the case when no club is selected
//            showAlert("Error","Please select a club before clicking Join.");
//        }
//    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


// ------------------------------------------table loading--------------------------------------------------------------


    public static List<Club> fetchClubsNotJoined(String studentId) {
        List<Club> clubsNotJoined = new ArrayList<>();
        String query = "SELECT * FROM club WHERE clubId NOT IN (SELECT clubId FROM student_club WHERE studentId = ?)";

        try (Connection connection = Database.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Populate Club objects from the result set
                    Club club = new Club();
                    club.setClubId(resultSet.getString("clubId"));
                    club.setClubName(resultSet.getString("clubName"));
                    club.setClubDescription(resultSet.getString("clubDescription"));
                    club.setTeacherId(resultSet.getString("teacherId"));

                    clubsNotJoined.add(club);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return clubsNotJoined;
    }

    public static List<Club> fetchJoinedClubs(String studentId) {
        List<Club> joinedClubs = new ArrayList<>();
        String query = "SELECT club.* FROM club " +
                "JOIN student_club ON club.clubId = student_club.clubId " +
                "WHERE student_club.studentId = ?";

        try (Connection connection = Database.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, studentId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Populate Club objects from the result set
                    Club club = new Club();
                    club.setClubId(resultSet.getString("clubId"));
                    club.setClubName(resultSet.getString("clubName"));
                    club.setClubDescription(resultSet.getString("clubDescription"));
                    club.setTeacherId(resultSet.getString("teacherId"));

                    joinedClubs.add(club);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception as needed
        }

        return joinedClubs;
    }



}
