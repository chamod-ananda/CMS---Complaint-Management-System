<%--
  Created by IntelliJ IDEA.
  User: Chamod Ananda
  Date: 6/15/2025
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="lk.ijse.cms.dto.Complaint" %>
<%
  List<Complaint> allComplaints = (List<Complaint>) request.getAttribute("allComplaints");
%>
<html>
  <head>
      <title>Admin - All Complaints</title>
  </head>
  <body>
    <h2>All Complaints</h2>
    <a id="backBtn" href="<%= request.getContextPath() %>/jsp/adminDashboard.jsp" ...>Back to Dashboard</a>

    <%
      String msg = request.getParameter("msg");
      String error = request.getParameter("error");
      if ("updated".equals(msg)) {
    %>
    <div class="msg">Complaint updated successfully.</div>
    <%
    } else if ("deleted".equals(msg)) {
    %>
    <div class="msg">Complaint deleted successfully.</div>
    <%
    } else if ("Update failed".equals(error)) {
    %>
    <div class="error-msg">Failed to update complaint.</div>
    <%
    } else if ("Delete failed".equals(error)) {
    %>
    <div class="error-msg">Failed to delete complaint.</div>
    <%
      }
    %>

    <table>
      <thead>
      <tr>
        <th>ID</th>
        <th>User ID</th>
        <th>Title</th>
        <th>Description</th>
        <th>Status & Remarks</th>
        <th>Actions</th>
      </tr>
      </thead>
      <tbody>
      <%
        if (allComplaints != null && !allComplaints.isEmpty()) {
          for (Complaint c : allComplaints) {
      %>
      <tr>
        <td><%= c.getId() %></td>
        <td><%= c.getUserId() %></td>
        <td><%= c.getTitle() %></td>
        <td><%= c.getDescription() %></td>
        <td>
          <form action="AdminServlet" method="post" class="update-form" style="margin:0;">
            <input type="hidden" name="action" value="adminUpdate">
            <input type="hidden" name="id" value="<%= c.getId() %>">

            <select name="status" required>
              <option value="PENDING" <%= "PENDING".equals(c.getStatus()) ? "selected" : "" %>>PENDING</option>
              <option value="REJECT" <%= "REJECT".equals(c.getStatus()) ? "selected" : "" %>>REJECT</option>
              <option value="RESOLVED" <%= "RESOLVED".equals(c.getStatus()) ? "selected" : "" %>>RESOLVED</option>
            </select>

            <input type="text" name="remarks" value="<%= c.getRemarks() != null ? c.getRemarks() : "" %>" placeholder="Add remarks">

            <button type="submit">Update</button>
          </form>
        </td>
        <td>
          <form action="AdminServlet" method="post" style="margin:0;">
            <input type="hidden" name="action" value="adminDelete">
            <input type="hidden" name="id" value="<%= c.getId() %>">
            <button type="submit" class="delete-btn" onclick="return confirm('Are you sure you want to delete this complaint?');">Delete</button>
          </form>
        </td>
      </tr>
      <%
        }
      } else {
      %>
      <tr>
        <td colspan="6">No complaints found.</td>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
  </body>
</html>
