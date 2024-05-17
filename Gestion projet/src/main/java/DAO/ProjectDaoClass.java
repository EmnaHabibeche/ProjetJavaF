package DAO;

import Entity.Project;
import Entity.User;
import Util.Navigation;
import com.system.projectmanagementsystem.DB;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoClass implements ProjectDAO  {


        private Connection connection;

        // Constructor
        public ProjectDaoClass() {
            try {
                // Obtain the database connection using the DB class
                connection = DB.getConnection();
                System.out.println("Connected to the database.");
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle exceptions
            }

        }

    @Override
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT name, start_date, end_date FROM projects");

            while (resultSet.next()) {
                Project project = new Project(
                        resultSet.getString("name"),
                        resultSet.getDate("start_date"),
                        resultSet.getDate("end_date")
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return projects;
    }





//    @Override
//    public void addProject(Project project) {
//        String sql = "INSERT INTO projects (name, start_date, end_date ,email) VALUES (?, ?, ?, ?)";
//        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, project.getName());
//            statement.setDate(2, project.getStartDate());
//            statement.setDate(3, project.getEndDate());
//            statement.setString(4, project.getOwner().getEmail());
//            int rowsInserted = statement.executeUpdate();
//
//            if (rowsInserted > 0) {
//                showAlert(Alert.AlertType.INFORMATION, "Success", "Project added successfully.");
//            } else {
//                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add project.");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
//        }
//    }

@Override
    public void addProject(Project project, User currentUser) {
        String sql = "INSERT INTO projects (name, start_date, end_date, owner_id) VALUES (?, ?, ?, ?)";


        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, project.getName());
            statement.setDate(2, project.getStartDate());
            statement.setDate(3, project.getEndDate());
            statement.setInt(4, currentUser.getId()); // Assuming getId() returns the ID of the current user
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                showAlert(Alert.AlertType.INFORMATION, "Success", "Project added successfully.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Error", "Failed to add project.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "An error occurred: " + e.getMessage());
        }
    }

    @Override
    public void addProject(Project project) {

    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
