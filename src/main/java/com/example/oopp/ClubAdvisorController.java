package com.example.oopp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

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
    private Button clubAttendenceButton;

    @FXML
    private Button clubJoinRequestButton;

    @FXML
    private AnchorPane createClubAnchorPane;

    @FXML
    private AnchorPane eventSchedulingPane;
    @FXML
    private AnchorPane eventSchedulingSecondPane;
    @FXML
    private TextField eventSchedulingEnterField;
    @FXML
    private ChoiceBox<Club> eventSchedulingChoiceBox;

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
    EventSheduleDatabaseConnection dbConnection = new EventSheduleDatabaseConnection();
    @FXML
    public void enterAdvisorIdClick(ActionEvent event) {
        String advisorId = eventSchedulingEnterField.getText();

        // Check if advisor ID exists
        if (dbConnection.isAdvisorIdExists(advisorId)) {
            // If advisor ID exists, set the second pane visible
            eventSchedulingSecondPane.setVisible(true);

            // Retrieve and load all clubs to the ChoiceBox
            List<Club> clubs = dbConnection.getClubsByAdvisorId(advisorId);

            // Clear existing items in the ChoiceBox
            eventSchedulingChoiceBox.getItems().clear();


            for (Club club : clubs) {
                if (club != null) {
                    eventSchedulingChoiceBox.getItems().add(club);
                }
            }

            // Set a custom cell factory for the ChoiceBox to display club names
            eventSchedulingChoiceBox.setConverter(new StringConverter<Club>() {
                @Override
                public String toString(Club club) {
                    return club == null ? "" : club.getClubName();
                }

                @Override
                public Club fromString(String string) {
                    return null;
                }
            });
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
