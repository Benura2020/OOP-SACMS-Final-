package com.example.oopp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

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

}
