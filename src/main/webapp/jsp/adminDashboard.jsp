<%--
  Created by IntelliJ IDEA.
  User: Chamod Ananda
  Date: 6/14/2025
  Time: 12:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="lk.ijse.cms.dto.User" %>
<%
    User loggedUser = (User) session.getAttribute("user");
    if (loggedUser == null || !"ADMIN".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized");
        return;
    }
%>
<html>
    <head>
        <title>Admin Dashboard</title>
        <link rel="stylesheet" href="../css/adminDashboard.css">
    </head>
    <body>
        <div class="dashboard">
            <h2>Welcome, <%= loggedUser.getUsername() %> (Admin)</h2>
            <ul class = "nav-list">
                <li><a href="view_complaints.jsp">All Complaints</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>
        </div>
    </body>
</html>
