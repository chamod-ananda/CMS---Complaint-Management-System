<%--
  Created by IntelliJ IDEA.
  User: Chamod Ananda
  Date: 6/14/2025
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lk.ijse.cms.dto.User" %>
<%@ page import="lk.ijse.cms.dto.Complaint" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loggeduser = (User) session.getAttribute("user");
    if (loggeduser == null || !"EMPLOYEE".equalsIgnoreCase(loggeduser.getRole())) {
        response.sendRedirect("index.jsp?error=unauthorized");
        return;
    }

    Complaint complaint = (Complaint) request.getAttribute("complaintToEdit");
    if (complaint == null || complaint.getUserId() != loggeduser.getId() || !"PENDING".equalsIgnoreCase(complaint.getStatus())) {
        response.sendRedirect("ComplaintServlet?action=dashboard&error=Invalid complaint to edit");
        return;
    }
    String error = request.getParameter("error");
%>

<html>
<head>
    <title>Edit Complaint</title>
<%--    <link rel="stylesheet" href="../css/editComplaint.css">--%>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/editComplaint.css">

</head>
<body>
<h2>Edit Complaint</h2>
<% if (error != null) { %>
<div class="error"><%= error %></div>
<% } %>

<form method="post" action="<%= request.getContextPath() %>/ComplaintServlet">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="id" value="<%= complaint.getId() %>">

    <label for="title">Title:</label><br>
    <input type="text" id="title" name="title" required maxlength="100" value="<%= complaint.getTitle() %>"><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" rows="4" required><%= complaint.getDescription() %></textarea><br>

    <button type="submit">Update</button>
</form>

<a href="<%= request.getContextPath() %>/ComplaintServlet?action=dashboard" class="back-link">Back to Dashboard</a>

<script src="${pageContext.request.contextPath}/js/edit-complaint.js"></script>
</body>
</html>

