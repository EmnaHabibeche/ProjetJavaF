package DAO;

import Entity.User;
import Util.Navigation;
import com.system.projectmanagementsystem.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAoImpl implements UserDAO {


    private Connection connection;

    public UserDAoImpl() {
        try {
            connection = DB.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle connection error
        }
    }

    @Override
    public User getUserByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("username");
                // You can retrieve other user details similarly
                user = new User(resultSet.getString("username"),resultSet.getInt("id")); // Assuming you have a constructor that takes username
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database query error
        }
        return user;
    }

    @Override

        public  User login(String username, String password) {
            try (Connection connection = DB.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
                statement.setString(1, username);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int userId = resultSet.getInt("id");
                        System.out.println(userId);
                        String userEmail = resultSet.getString("email");
//                        return new User(resultSet.getString("username"));


                        return new User(userId, username, userEmail);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public boolean signUp(String username, String password) {
            try (Connection connection = DB.getConnection();
                 PreparedStatement statement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {
                statement.setString(1, username);
                statement.setString(2, password);
                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }


    }



