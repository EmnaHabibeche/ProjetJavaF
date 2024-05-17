package DAO;

import Entity.Task;
import com.system.projectmanagementsystem.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoClass implements TaskDAO {

    private Connection connection;

    public TaskDaoClass() {
        try {
            connection = DB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection error
        }
    }

    @Override
    public void addTask(Task task) {
        String sql = "INSERT INTO tasks (taskId, description, status, startDate, endDate, projectId) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, task.getTaskId());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getStatus());
            pstmt.setDate(4, java.sql.Date.valueOf(task.getStartDate()));
            pstmt.setDate(5, java.sql.Date.valueOf(task.getEndDate()));
            pstmt.setString(6, task.getProjectId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Task> getTasksByProjectId(String projectId) {
        List<Task> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks WHERE projectId = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, projectId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Task task = new Task(
                        rs.getString("taskId"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getDate("startDate").toLocalDate(),
                        rs.getDate("endDate").toLocalDate(),
                        rs.getString("projectId")

                );
                tasks.add(task);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
        }
        return tasks;
    }
}