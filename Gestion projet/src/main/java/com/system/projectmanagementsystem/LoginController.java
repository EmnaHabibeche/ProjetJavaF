package com.system.projectmanagementsystem;

import DAO.ProjectDAO;
import DAO.ProjectDaoClass;
import DAO.UserDAO;
import DAO.UserDAoImpl;
import Entity.User;
import Util.Navigation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private Hyperlink singUpButton;

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;

    @FXML
    private Label loginMessageLabel;

    private UserDAO userDAO = new UserDAoImpl(); // Your ProjectDAO implementation




//    @FXML
//    public void loginButtonOnAction(ActionEvent event) {
//        String enteredUsername = userName.getText();
//        String enteredPassword = password.getText();
//        if (enteredUsername.isBlank() || enteredPassword.isBlank()) {
//            loginMessageLabel.setText("Please enter both username and password");
//            return; // Exit the method early if fields are empty
//        }
//
//        try (Connection connection = DB.getConnection();
//             PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
//            statement.setString(1, enteredUsername);
//            statement.setString(2, enteredPassword);
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    loginMessageLabel.setText("Login successful");
//                } else {
//                    loginMessageLabel.setText("Invalid username or password");
//                }
//            }
//        } catch (SQLException e) {
//            loginMessageLabel.setText("Database error: " + e.getMessage());
//            e.printStackTrace();
//
//        }
//    }


    @FXML
    public void loginButtonOnAction(ActionEvent event) {
        String enteredUsername = userName.getText();
        String enteredPassword = password.getText();
        if (enteredUsername.isBlank() || enteredPassword.isBlank()) {
            loginMessageLabel.setText("Please enter both username and password");
            return; // Exit the method early if fields are empty
        }

        UserDAO userDAO = new UserDAoImpl();
        User user = userDAO.login(enteredUsername, enteredPassword);

        if (user != null) {
            loginMessageLabel.setText("Login successful");

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
                Parent root = loader.load();
                HomeController homeController = loader.getController();
                homeController.setCurrentUser(user);

                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            loginMessageLabel.setText("Invalid username or password");
        }}


    @FXML
    public void goToAnotherPage() {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("signup.fxml"));
            Parent root = loader.load();

            // Get the controller for the new scene if needed
            SignUpController signUpController = loader.getController();

            // Get the current stage
            Stage currentStage = (Stage) singUpButton.getScene().getWindow();

            // Set the new scene in the current stage
            currentStage.setScene(new Scene(root, 600, 600));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}