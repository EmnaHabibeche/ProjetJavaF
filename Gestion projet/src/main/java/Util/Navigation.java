package Util;

import Entity.User;
import com.system.projectmanagementsystem.HomeController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class Navigation {
    public static <T> T navigateTo(ActionEvent event, String fxmlPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            URL fxmlUrl = Navigation.class.getResource(fxmlPath);
            if (fxmlUrl == null) {
                throw new FileNotFoundException("FXML file not found: " + fxmlPath);
            }
            loader.setLocation(fxmlUrl);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    private static User currentUser; // Store the current logged-in user

    // Main method for launching the JavaFX application


    // Method to get the current logged-in user
    public static User getCurrentUser() {
        return currentUser;
    }

    // Method to set the current logged-in user
    public static void setCurrentUser(User user) {
        currentUser = user;
    }




}



// Set the new scene in the current stage
