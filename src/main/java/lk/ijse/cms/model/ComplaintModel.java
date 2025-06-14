package lk.ijse.cms.model;

import lk.ijse.cms.db.DBConnectionPool;
import lk.ijse.cms.dto.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ComplaintModel {
    public boolean addComplaint(Complaint complaint) {

        String sql = "INSERT INTO complaints (title, description, status, user_id) VALUES (?,?, 'PENDING',?)";

        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,complaint.getTitle());
            statement.setString(2, complaint.getDescription());
            statement.setInt(3, complaint.getUserId());
            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
