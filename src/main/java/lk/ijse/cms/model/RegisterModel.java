package lk.ijse.cms.model;

import lk.ijse.cms.db.DBConnectionPool;
import lk.ijse.cms.dto.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterModel {

    public boolean userExists(String username) {
        String sql = "SELECT username FROM users WHERE username = ?";

        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO users(username, password, role) VALUES (?,?,?)";

        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getRole());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
