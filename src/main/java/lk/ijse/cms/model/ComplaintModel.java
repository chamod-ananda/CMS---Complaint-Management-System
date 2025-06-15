package lk.ijse.cms.model;

import lk.ijse.cms.db.DBConnectionPool;
import lk.ijse.cms.dto.Complaint;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComplaintModel {
    public boolean addComplaint(Complaint complaint) {

        String sql = "INSERT INTO complaints (title, description, status, user_id) VALUES (?, ?, 'PENDING', ?)";

        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, complaint.getTitle());
            statement.setString(2, complaint.getDescription());
            statement.setInt(3, complaint.getUserId());

            int rows = statement.executeUpdate();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Complaint> getComplaintsByUserId(int userId) {
        List<Complaint> complaints = new ArrayList<>();
        String sql = "SELECT * FROM complaints WHERE user_id=?";

        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, userId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(rs.getInt("id"));
                complaint.setTitle(rs.getString("title"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getString("status"));
                complaint.setRemarks(rs.getString("remarks"));
                complaint.setUserId(rs.getInt("user_id"));
                complaints.add(complaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return complaints;
    }

    public boolean updateComplaint(Complaint complaint) {
        String sql = "UPDATE complaints SET title = ?, description = ? WHERE id = ? AND status = 'PENDING'";
        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, complaint.getTitle());
            statement.setString(2, complaint.getDescription());
            statement.setInt(3, complaint.getId());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteComplaint(int complaintId) {
        String sql = "DELETE FROM complaints WHERE id = ? AND status = 'PENDING'";
        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, complaintId);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Complaint getComplaintById(int id) {
        String sql = "SELECT * FROM complaints WHERE id = ?";
        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(rs.getInt("id"));
                complaint.setTitle(rs.getString("title"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getString("status"));
                complaint.setRemarks(rs.getString("remarks"));
                complaint.setUserId(rs.getInt("user_id"));
                return complaint;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Complaint> getAllComplaints() {
        List<Complaint> list = new ArrayList<>();
        String sql = "SELECT * FROM complaints";

        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Complaint complaint = new Complaint();
                complaint.setId(rs.getInt("id"));
                complaint.setTitle(rs.getString("title"));
                complaint.setDescription(rs.getString("description"));
                complaint.setStatus(rs.getString("status"));
                complaint.setRemarks(rs.getString("remarks"));
                complaint.setUserId(rs.getInt("user_id"));
                list.add(complaint);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean updateComplaintByAdmin(int id, String status, String remarks) {
        String sql = "UPDATE complaints SET status = ?, remarks = ? WHERE id = ?";
        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, status);
            statement.setString(2, remarks);
            statement.setInt(3, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteComplaintByAdmin(int id) {
        String sql = "DELETE FROM complaints WHERE id = ?";
        try (Connection connection = DBConnectionPool.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
