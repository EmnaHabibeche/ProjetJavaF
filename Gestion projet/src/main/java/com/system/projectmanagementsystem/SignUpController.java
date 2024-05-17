package com.system.projectmanagementsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SignUpController {

    @FXML
    private TextField signUpUsernameField;

    @FXML
    private TextField signUpEmailField;

    @FXML
    private PasswordField signUpPasswordField;

    @FXML
    private Label signUpMessageLabel;

    @FXML
    private Hyperlink loginButton;

    @FXML
    public void signUpButtonOnAction(ActionEvent event) {
        String username = signUpUsernameField.getText();
        String password = signUpPasswordField.getText();
        String email =signUpEmailField.getText();

        if (username.isBlank() || password.isBlank()) {
            signUpMessageLabel.setText("Please enter both username and password");
            return;
        }

        try (Connection connection = DB.getConnection();
             PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password,email) VALUES (?, ?, ?)")) {
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3,email);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                signUpMessageLabel.setText("Sign up successful");
            } else {
                signUpMessageLabel.setText("Sign up failed");
            }
        } catch (SQLException e) {
            signUpMessageLabel.setText("Database error: " + e.getMessage());
            e.printStackTrace();
        }
    }



    @FXML
    public void goToLoginPage() {
        try {
            // Load the FXML file for the login page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root = loader.load();

            // Get the controller for the login page
            LoginController loginController = loader.getController();

            // Get the current stage
            Stage currentStage = (Stage) loginButton.getScene().getWindow();

            // Set the login page scene in the current stage
            currentStage.setScene(new Scene(root, 600, 600));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
