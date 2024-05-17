package com.system.projectmanagementsystem;

import DAO.ProjectDAO;
import DAO.ProjectDaoClass;
import DAO.UserDAO;
import Entity.Project;
import Entity.User;
import Util.Navigation;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController {


    @FXML
    private Button projectBtn;

    @FXML
    private ListView<Project> projectsListView;

    @FXML
    private TableView<Project> projectsTableView;

    @FXML
    private TableColumn<Project, String> nameColumn;

    @FXML
    private TableColumn<Project, Date> startDateColumn;

    @FXML
    private TableColumn<Project, Date> endDateColumn;

    @FXML
    private TableColumn<Project, String> ownerColumn;
    @FXML
    private Label usernameLabel;
    private ProjectDAO projectDAO = new ProjectDaoClass(); // Your ProjectDAO implementation


    // Setter methods for injecting ProjectDAO and UserDAO
    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }
    private User currentUser;




    @FXML
    public void initialize() {
        // Define columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

        // Retrieve projects using ProjectDAO
        projectsTableView.getItems().addAll(projectDAO.getAllProjects());
    }
    public void setCurrentUser(User user) {
        currentUser = user;
        usernameLabel.setText("Welcome, " + user.getUsername());
    }
    @FXML
    private void goToProjectPage() {
        try {
            // Load the FXML file of the target page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Project.fxml"));
            Parent root = loader.load();

            // Get the controller for the new scene if needed


            // Get the current stage
            Stage currentStage = (Stage) projectBtn.getScene().getWindow();

            // Set the new scene in the current stage
            currentStage.setScene(new Scene(root));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    @FXML
//    public void start() {
//        // Define columns
//
//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
//        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
//        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
//        List<Project> projects = projectDAO.getAllProjects();
//        projectsTableView.getItems().addAll(projects);
//        // Retrieve projects using ProjectDAO
//
//
//    }

}