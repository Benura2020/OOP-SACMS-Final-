package com.example.oopp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.util.List;

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
    private AnchorPane eventUpdatePane;
    @FXML
    private Button EventDeleteUpdateButton;

    @FXML
    private Button clubAttendenceButton;

    @FXML
    private Button clubJoinRequestButton;

    @FXML
    private AnchorPane createClubAnchorPane;

    @FXML
    private AnchorPane eventSchedulingPane;
    @FXML
    private TextField eventSchedulingEnterField;
    @FXML
    private ChoiceBox<String> eventSchedulingChoiceBox;
    @FXML
    private AnchorPane eventSchedulingSecondPane;

    @FXML
    public void mainClick(ActionEvent event){
        if (event.getSource()==ManageClubButton){
            createClubAnchorPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventSchedulingPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            eventUpdatePane.setVisible(false);
        }
        if (event.getSource()==SheduleEventsButton){
            eventSchedulingPane.setVisible(true);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(false);
            eventUpdatePane.setVisible(false);

        }
        if (event.getSource()==clubJoinRequestButton) {
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(true);
            AttendencePane.setVisible(false);
            eventUpdatePane.setVisible(false);
        }

        if (event.getSource()==clubAttendenceButton){
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(true);
            eventUpdatePane.setVisible(false);
        }
        if (event.getSource()==EventDeleteUpdateButton){
            eventUpdatePane.setVisible(true);
            eventSchedulingPane.setVisible(false);
            createClubAnchorPane.setVisible(false);
            JoinRequestsPane.setVisible(false);
            AttendencePane.setVisible(false);

        }
    }

    EventSheduleDatabaseConnection dbConnection = new EventSheduleDatabaseConnection();
    @FXML
    public void enterAdvisorIdClick(ActionEvent event){
        String advisorId = eventSchedulingEnterField.getText();

        if (dbConnection.isAdvisorIdExists(advisorId)) {
            // If advisor ID exists, set the second pane visible
            eventSchedulingSecondPane.setVisible(true);

            // Retrieve and load all club names to the ChoiceBox
            List<String> clubs = dbConnection.getClubsByAdvisorId(advisorId);
            eventSchedulingChoiceBox.getItems().addAll(clubs);
        } else {
            // If advisor ID doesn't exist, show an alert
            showAlert("Advisor ID Not Found", "The provided Advisor ID does not exist in the database.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }






}
