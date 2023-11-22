package com.example.oopp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

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
}