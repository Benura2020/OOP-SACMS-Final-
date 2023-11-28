package com.example.oopp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


import java.sql.*;


import static com.example.oopp.ClubAdvisorController.getTeacherFromDatabase;



public class HelloController {
    @FXML
    private Button admin_signup;

    @FXML
    private TextField advisor_confirm_pw;

    @FXML
    private AnchorPane advisor_form;

    @FXML
    private TextField advisor_mail;

    @FXML
    private TextField advisor_password;

    @FXML
    private Hyperlink advisor_signin_link;

    @FXML
    private Button advisor_signup;

    @FXML
    private TextField advisor_username;

    @FXML
    private Hyperlink create_account_link;

    @FXML
    private Button signin_button;

    @FXML
    private AnchorPane signin_form;

    @FXML
    private TextField signin_password;

    @FXML
    private TextField signin_username;

    @FXML
    private AnchorPane std_create_account_form;

    @FXML
    private TextField student_confirm_pw;

    @FXML
    private TextField student_email;

    @FXML
    private TextField student_password;

    @FXML
    private Hyperlink student_signin_link;

    @FXML
    private Button student_signup;

    @FXML
    private TextField student_username;

    private int currentState = 1; // You can use integers to represent different states


    /*@FXML
    private void initialize() {
        // Set the initial state (e.g., showing the sign-in form)
        showSignInForm();
    }


    @FXML
    private void handleHyperlinkClick(ActionEvent event) {
        if (event.getSource() == student_signin_link) {
            // Switch to the student sign-in form
            showSignInForm();
        } else if (event.getSource() == create_account_link) {
            // Switch to the student create account form
            showStudentCreateAccountForm();
        } else if (event.getSource() == advisor_signin_link) {
            // Switch to the advisor sign-in form
            showSignInForm();
        }
    }

    private void showSignInForm() {
        signin_form.setVisible(true);
        std_create_account_form.setVisible(false);
        advisor_form.setVisible(false);
        currentState = 1; // Set the current state to sign-in

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

    //-------------------------------------pane switching---------------------------------------------------------------

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

    // ---------------------------------------user signin and registration ---------------------------------------------


    // alert dialog for student and club advisor signup

    private void showAlertError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    static void showAlertSuccess(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // -----------------------------------------------------------------------------------------------------------------

    // student signup
    StudentController studentController = new StudentController();

    public void studentSignupButtonOnAction(){
        //get user inputs from text fields and password fields

        String studentId = signupStudentIdTextField.getText();
        String studentName = signupStudentNameTextField.getText();
        String studentPassword = signupStudentPasswordField.getText();
        String studentConfirmPassword = signupStudentConfirmPasswordField.getText();

        // Validate student ID
        if (!InputValidations.validateId(studentId)) {
            showAlertError("Invalid student ID. Enter a valid one.");

            // Clear all text and password fields
            signupStudentIdTextField.clear();
            signupStudentNameTextField.clear();
            signupStudentPasswordField.clear();
            signupStudentConfirmPasswordField.clear();

            //Set focus back to student ID text field
            signupStudentIdTextField.requestFocus();
            return;
        }

        // Validate password and confirmPassword
        if (!InputValidations.arePasswordsEqual(studentPassword, studentConfirmPassword)) {
            showAlertError("Passwords do not match.Please enter same password");

            // Clear both password fields
            signupStudentPasswordField.clear();
            signupStudentConfirmPasswordField.clear();

            //set focus back to password field
            signupStudentPasswordField.requestFocus();
            return;
        }

        //get registered student ID
        studentController.signedInStudentId = studentId;
        // creating the object of the student
        Student student = new Student(studentId, studentName, studentPassword);

        //Insert user data into database
        StudentController.insertStudent(student);
    }

    // club advisor signup

    public void teacherSignupButtonOnAction(){
        //get user inputs from text fields and password fields

        String teacherId = signupTeacherIdTextField.getText();
        String teacherName = signupTeacherNameTextField.getText();
        String teacherPassword = signupTeacherPasswordField.getText();
        String teacherConfirmPassword = signupTeacherConfirmPasswordField.getText();

        // Validate teacher ID
        if (!InputValidations.validateId(teacherId)) {
            showAlertError("Invalid teacher ID. Enter a valid one.");

            // Clear all text and password fields
            signupTeacherIdTextField.clear();
            signupTeacherNameTextField.clear();
            signupTeacherPasswordField.clear();
            signupTeacherConfirmPasswordField.clear();

            //Set focus back to teacher ID text field
            signupTeacherIdTextField.requestFocus();
            return;
        }

        // Validate password and confirmPassword
        if (!InputValidations.arePasswordsEqual(teacherPassword, teacherConfirmPassword)) {
            showAlertError("Passwords do not match.Please enter same password");

            // Clear both password fields
            signupTeacherPasswordField.clear();
            signupTeacherConfirmPasswordField.clear();

            //set focus back to password field
            signupTeacherPasswordField.requestFocus();
            return;
        }

        //creating the object of the club advisor
        ClubAdvisor clubAdvisor = new ClubAdvisor(teacherId, teacherName, teacherPassword);

        //Insert user data into database
        ClubAdvisorController.insertTeacher(clubAdvisor);

    }

    private void showStudentCreateAccountForm() {
        signin_form.setVisible(false);
        std_create_account_form.setVisible(true);
        advisor_form.setVisible(false);
        currentState = 2; // Set the current state to student create account
    }*/

//    @FXML
//    private void initialize() {
//        // Set the initial state (e.g., showing the sign-in form)
//        showSignInForm();
//    }


//    @FXML
//    private void handleHyperlinkClick(ActionEvent event) {
//        if (event.getSource() == student_signin_link) {
//            // Switch to the student sign-in form
//            showSignInForm();
//        } else if (event.getSource() == create_account_link) {
//            // Switch to the student create account form
//            showStudentCreateAccountForm();
//        } else if (event.getSource() == advisor_signin_link) {
//            // Switch to the advisor sign-in form
//            showSignInForm();
//        }
//    }
//
//    private void showSignInForm() {
//        signin_form.setVisible(true);
//        std_create_account_form.setVisible(false);
//        advisor_form.setVisible(false);
//        currentState = 1; // Set the current state to sign-in
//    }
//
//    private void showStudentCreateAccountForm() {
//        signin_form.setVisible(false);
//        std_create_account_form.setVisible(true);
//        advisor_form.setVisible(false);
//        currentState = 2; // Set the current state to student create account
//    }


//    private int currentState = 1;
//        switch (currentState) {
//        case 1:
//            std_create_account_form.setVisible(true);
//            advisor_form.setVisible(false);
//            signin_form.setVisible(false);
//            break;
//        case 2:
//            pane1.setVisible(false);
//            pane2.setVisible(true);
//            pane3.setVisible(false);
//            break;
//        case 3:
//            pane1.setVisible(false);
//            pane2.setVisible(false);
//            pane3.setVisible(true);
//            break;
//    }
////    create_account_link.setOnAction(a -> {
////        std_create_account_form.setVisible(true);
////        advisor_form.setVisible(false);
////        signin_form.setVisible(false);
////        });
////    student_signin_link.setOnAction(a -> {
////        std_create_account_form.setVisible(false);
////        advisor_form.setVisible(false);
////        signin_form.setVisible(true);
////        });
////    admin_signup.setOnAction(a -> {
////        std_create_account_form.setVisible(false);
////        advisor_form.setVisible(true);
////        signin_form.setVisible(false);
////        });
////    advisor_signin_link.setOnAction(a -> {
////        std_create_account_form.setVisible(false);
////        advisor_form.setVisible(false);
////        signin_form.setVisible(true);
////        });
////    }
//    @FXML
//    private void initialize() {
//        // Set the initial state (e.g., pane1 is visible, and pane2 and pane3 are hidden)
//        formSwitch();
//    }

    public void studentSigninButtonOnAction(ActionEvent event){
        // Get student inputs from text fields and password field
        String signinStudentId = signinStudentIdTextField.getText();
        String signinPassword = signinStudentPasswordField.getText();

        // Validate student ID
        if (!InputValidations.validateId(signinStudentId)) {
            showAlertError("Invalid student ID. Enter a valid one.");
            signinStudentIdTextField.clear();
            signinStudentIdTextField.requestFocus();
            return;
        }

        // Retrieve data from the database using student ID
        Student student = studentController.getStudentFromDatabase(signinStudentId);

        // Check if the student is registered
        if (student == null) {
            showAlertError("Student with ID " + signinStudentId + " is not registered.");
            return;
        }

        // Validate the entered password
        if (!student.getStudentPassword().equals(signinPassword)) {
            showAlertError("Incorrect password. Please try again.");
            signinStudentPasswordField.clear();
            signinStudentPasswordField.requestFocus();
            return;
        }

        // Successful login alert
        showAlertSuccess("Student logged in successfully!");

        // Load student FXML after successful registration
        FXMLLoaderUtil.loadFXML("student.fxml", "Student");

    }

    public String getStudentSignInId(){
        return signinStudentIdTextField.getText();
    }

    // teacher signin

    public void teacherSigninButtonOnAction(ActionEvent event) {
        // Get teacher inputs from text fields and password field
        String signinTeacherId = signinTeacherIdTextField.getText();
        String signinPassword = signinTeacherPasswordField.getText();

        // Validate teacher ID
        if (!InputValidations.validateId(signinTeacherId)) {
            showAlertError("Invalid teacher ID. Enter a valid one.");
            signinTeacherIdTextField.clear();
            signinTeacherIdTextField.requestFocus();
            return;
        }

        // Retrieve data from the database using teacher ID
        ClubAdvisor teacher = getTeacherFromDatabase(signinTeacherId);

        // Check if the teacher is registered
        if (teacher == null) {
            showAlertError("Teacher with ID " + signinTeacherId + " is not registered.");
            return;
        }

        // Validate the entered password
        if (!teacher.getTeacherPassword().equals(signinPassword)) {
            showAlertError("Incorrect password. Please try again.");
            signinTeacherPasswordField.clear();
            signinTeacherPasswordField.requestFocus();
            return;
        }

        // Successful login alert
        showAlertSuccess("Club advisor logged in successfully!");

        // Load student FXML after successful registration
        FXMLLoaderUtil.loadFXML("clubadvisor.fxml", "Club Advisor");

    }




}