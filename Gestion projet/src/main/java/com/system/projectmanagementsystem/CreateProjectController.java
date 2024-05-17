package com.system.projectmanagementsystem;

import DAO.*;
import Entity.Project;
import Entity.Task;
import Entity.User;
import Util.Navigation;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import DAO.UserDAoImpl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateProjectController {



    @FXML
    private TextField userEmailTextField;

    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker endDate;

    @FXML
    private Button addUserButton;

    @FXML
    private Button home_btn;
    @FXML
    private Button add_project;
    @FXML
    private TableView<Project> projectsTableView;

    @FXML
    private TextField projectNameTextField;


    @FXML
    private TableColumn<Project, String> nameColumn;





    @FXML
    private TableColumn<Project, LocalDate> startDateColumn;

    @FXML
    private TableColumn<Project, LocalDate> endDateColumn;

    @FXML
    private TableColumn<Project, String> ownerColumn;
    @FXML
    private Button add_task;
    @FXML
    private TextField taskNameTextField;

    @FXML
    private TextField descriptionTextArea;

    @FXML
    private DatePicker startDatePicker;

    @FXML
    private DatePicker endDatePicker;

    @FXML
    private Button addTaskButton;

    @FXML
    private TaskDAO taskDAO;


    private ProjectDAO projectDAO = new ProjectDaoClass(); // Your ProjectDAO implementation

    private UserDAO userDAO;

    // Setter methods for injecting ProjectDAO and UserDAO
    public void setProjectDAO(ProjectDAO projectDAO) {
        this.projectDAO = projectDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }




    private User currentUser;
    @FXML
    private Label usernameLabel;
    public void setCurrentUser(User user) {
        currentUser = user;
        usernameLabel.setText("Welcome, " + user.getUsername());
    }
    @FXML
    private void goToHomePage() {
        try {
            // Load the FXML file of the target page
            FXMLLoader loader = new FXMLLoader(getClass().getResource("home.fxml"));
            Parent root = loader.load();

            // Get the controller for the new scene if needed


            // Get the current stage
            Stage currentStage = (Stage) home_btn.getScene().getWindow();

            // Set the new scene in the current stage
            currentStage.setScene(new Scene(root));
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void showProjectCreationDialog() {
        try {
            // Load the FXML file for the project creation dialog
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateProject.fxml"));
            DialogPane dialogRoot = loader.load();

            // Create a new stage for the dialog
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Create Project");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(add_project.getScene().getWindow());
            Scene scene = new Scene(dialogRoot);
            dialogStage.setScene(scene);

            // Show the dialog
            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddProject() {
        String projectName = projectNameTextField.getText();
        Date P_startDate = Date.valueOf(startDate.getValue());
        Date P_endDate = Date.valueOf(endDate.getValue());
        System.out.println(userEmailTextField.getText());
        String userEmail = userEmailTextField.getText();


        UserDAO userDAO = new UserDAoImpl();


//        // Retrieve the user by email using UserDAOImpl
   User user = userDAO.getUserByEmail(userEmail);
//
//        if (user == null) {
//            showAlert(Alert.AlertType.ERROR, "Error", "User with email '" + userEmail + "' not found.");
//
//            return;
//        }

        // Create a new project object with the input values
        Project project = new Project();
        project.setName(projectName);
        project.setStartDate(P_startDate);
        project.setEndDate(P_endDate);
        project.setUser_id(user.getId());
        ProjectDAO projectDAO = new ProjectDaoClass();
        // Call the addProject method in your ProjectDAO
        projectDAO.addProject(project,user);
        System.out.println(user.getId());

//        showAlert(Alert.AlertType.INFORMATION, "Success", "Project added successfully.");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));  nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startDateColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        endDateColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleAddTask() {
        // Retrieve task details from the input fields
        String taskId = generateTaskId(); // You need to implement a method to generate unique task IDs
        String taskName = taskNameTextField.getText();
        String description = descriptionTextArea.getText();
        LocalDate startDate = startDatePicker.getValue();
        LocalDate endDate = endDatePicker.getValue();

        // Validate task details (ensure they are not empty, etc.)
        if (taskName.isEmpty() || description.isEmpty() || startDate == null || endDate == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields.");
            return;
        }

        // Create a new Task object with the retrieved details
        Object projectId = " ";
// Assuming taskId, taskName, description, status, startDate, endDate, and projectId are all appropriately defined
        Task task = new Task(taskId, description, "New", startDate, endDate, (String) projectId);

        // Save the task to the database using the TaskDAO
        taskDAO.addTask(task);

        // Close the dialog window
        Stage stage = (Stage) taskNameTextField.getScene().getWindow();
        stage.close();
    }

    // Method to generate a unique task ID (You need to implement this)
    private String generateTaskId() {
        // Implement your logic to generate a unique task ID
        return "TASK_ID"; // Replace this with your actual implementation
    }

    // Method to show an alert dialog


    @FXML
    public void showAddTaskDialog() {
        try {
            // Load the FXML file for the project creation dialog
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddTask.fxml"));
            DialogPane dialogRoot = loader.load();

            // Create a new stage for the dialog
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Add Task");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(add_task.getScene().getWindow());
            Scene scene = new Scene(dialogRoot);
            dialogStage.setScene(scene);

            // Show the dialog
            dialogStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    }