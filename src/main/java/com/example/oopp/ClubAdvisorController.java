package com.example.oopp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

//import static com.example.oopp.Database.getDBConnection;
//import static com.example.oopp.HelloController.showAlertSuccess;

public class ClubAdvisorController {
    @FXML
    private AnchorPane AttendencePane;

    @FXML
    private AnchorPane JoinRequestsPane;

    @FXML
    private Button ManageClubButton;

    @FXML
    private Button SheduleEventsButton;

    @FXML
    private Button clubAttendenceButton;

    @FXML
    private Button clubJoinRequestButton;

    @FXML
    private AnchorPane createClubAnchorPane;

    @FXML
    private AnchorPane eventSchedulingPane;

    @FXML
    public void mainClick(ActionEvent event){
        if (event.getSource()==ManageClubButton){
            createClubAnchorPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventSchedulingPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
        }
        if (event.getSource()==SheduleEventsButton){
            eventSchedulingPane.setVisible(true);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(false);

        }
        if (event.getSource()==clubJoinRequestButton) {
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(true);
            AttendencePane.setVisible(false);
        }

        if (event.getSource()==clubAttendenceButton){
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(true);
        }


    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

//---------------------------------save club advisor data to database----------------------------------------------------
    public static void insertTeacher(ClubAdvisor clubAdvisor) {
        // Check if the teacher already exists in the database
        if (isTeacherExists(clubAdvisor.getTeacherId())) {
            showAlertSuccess("Teacher with ID " + clubAdvisor.getTeacherId() + " already exists.");
            return;
        }

        String insertQuery = "INSERT INTO clubAdvisor (teacherId, teacherName, userPassword) VALUES (?, ?, ?)";

        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setString(1, clubAdvisor.getTeacherId());
            preparedStatement.setString(2, clubAdvisor.getTeacherName());
            preparedStatement.setString(3, clubAdvisor.getTeacherPassword());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                showAlertSuccess("Teacher registered successfully!");

                // Load student FXML after successful registration
                FXMLLoaderUtil.loadFXML("clubadvisor.fxml", "Club Advisor");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlertSuccess("Error occurred while registering the teacher.");
        }
    }

    // Check if the teacher already exists in the database
    private static boolean isTeacherExists(String teacherId) {
        String query = "SELECT COUNT(*) FROM clubAdvisor WHERE teacherId = ?";
        try (Connection connection = getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, teacherId);
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

//-------------------------------get teacher data from the database-----------------------------------------------------

    private static final String GET_TEACHER_QUERY = "SELECT teacherId, teacherName, userPassword FROM clubAdvisor WHERE teacherId = ?";

    static ClubAdvisor getTeacherFromDatabase(String teacherId) {
        try (Connection connection = Database.getDBConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_TEACHER_QUERY)) {

            preparedStatement.setString(1, teacherId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String retrievedTeacherId = resultSet.getString("teacherId");
                    String retrievedTeacherName = resultSet.getString("teacherName");
                    String retrievedTeacherPassword = resultSet.getString("userPassword");

                    return new ClubAdvisor(retrievedTeacherId, retrievedTeacherName, retrievedTeacherPassword);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlertSuccess("Error retrieving teacher data from the database.");
        }

        return null;
    }


}
