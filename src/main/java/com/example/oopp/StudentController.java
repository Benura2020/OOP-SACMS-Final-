package com.example.oopp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class StudentController {
    @FXML
    private Button studentClubButton;

    @FXML
    private Button studentEventButton;

    @FXML
    private Button studentExitButton;

    @FXML
    private Button studentHomeButton;

    @FXML
    private AnchorPane student_club;

    @FXML
    private AnchorPane student_event;

    @FXML
    private AnchorPane student_home;


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

    


}
