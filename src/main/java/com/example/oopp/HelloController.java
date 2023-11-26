package com.example.oopp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class HelloController {

    @FXML
    private AnchorPane advisor_signin_form;

    @FXML
    private AnchorPane advisor_signup_form;

    @FXML
    private Button mainAdvisorButton;

    @FXML
    private Button mainStudentButton;

    @FXML
    private AnchorPane selection_MainMenu;

    @FXML
    private TextField signinStudentIdTextField;

    @FXML
    private PasswordField signinStudentPasswordField;

    @FXML
    private TextField signinTeacherIdTextField;

    @FXML
    private PasswordField signinTeacherPasswordField;

    @FXML
    private PasswordField signupStudentConfirmPasswordField;

    @FXML
    private TextField signupStudentIdTextField;

    @FXML
    private TextField signupStudentNameTextField;

    @FXML
    private PasswordField signupStudentPasswordField;

    @FXML
    private PasswordField signupTeacherConfirmPasswordField;

    @FXML
    private TextField signupTeacherIdTextField;

    @FXML
    private TextField signupTeacherNameTextField;

    @FXML
    private PasswordField signupTeacherPasswordField;

    @FXML
    private Button studentSigninButton;

    @FXML
    private Hyperlink studentSigninLink;

    @FXML
    private Button studentSignupButton;

    @FXML
    private Hyperlink studentSignupLink;

    @FXML
    private AnchorPane student_signin_form;

    @FXML
    private AnchorPane student_signup_form;

    @FXML
    private Button teacherSigninButton;

    @FXML
    private Hyperlink teacherSigninLink;

    @FXML
    private Button teacherSignupButton;

    @FXML
    private Hyperlink teacherSignupLink;

    @FXML
    private Label studentSigninMessageLabel;

    @FXML
    private Label teacherSigninMessageLabel;

    @FXML
    private Button studentSigninBackButton;

    @FXML
    private Button teacherSigninBackButton;

    @FXML
    private Button studentSignupBackButton;

    @FXML
    private Button teacherSignupBackButton;


    // method for showing and hiding anchor panes . also used in other classes
    public static void toggleVisibility(AnchorPane pane, boolean isVisible) {
        pane.setVisible(isVisible);
    }

    //show a desired default pane when launch the application
    public void showDefaultPane() {
        // Show default AnchorPane
        toggleVisibility(selection_MainMenu, true);
        // Hide other AnchorPanes
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    //start menu student section

    public void mainStudentButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, true);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    //start menu club advisor section
    public void mainAdvisorButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, true);
    }

    public void studentSigninBackButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, true);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void teacherSigninBackButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, true);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void studentSignupBackButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, true);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void teacherSignupBackButtonOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, true);
    }

    public void studentSigninLinkOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, true);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void studentSignupLinkOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, true);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, false);
    }

    public void teacherSigninLinkOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, false);
        toggleVisibility(advisor_signin_form, true);
    }

    public void teacherSignupLinkOnAction(ActionEvent event){
        toggleVisibility(selection_MainMenu, false);
        toggleVisibility(student_signup_form, false);
        toggleVisibility(student_signin_form, false);
        toggleVisibility(advisor_signup_form, true);
        toggleVisibility(advisor_signin_form, false);
    }

    // student signin
    public void studentSigninButtonOnAction(ActionEvent event){
        if(signinStudentIdTextField.getText().isBlank() == false && signinStudentPasswordField.getText().isBlank() == false){

        }else{
            studentSigninMessageLabel.setText("Please enter StudentId and password");
        }
    }

    //validate student signin from database
    public void validateStudentLogin(){

    }

    // teacher signin
    public void teacherSigninButtonOnAction(ActionEvent event){
        if(signinStudentIdTextField.getText().isBlank() == false && signinStudentPasswordField.getText().isBlank() == false){
            validateTeacherLogin();
        }else{
            teacherSigninMessageLabel.setText("Please enter TeacherId and password");
        }
    }

    //validate teacher signin from database
    public void validateTeacherLogin(){

    }
}