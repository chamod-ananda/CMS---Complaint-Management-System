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
        <h2>Welcome, <%= loggedUser.getUsername() %> (Admin)</h2>

        <div class="card-container">
            <div class="card" onclick="location.href='<%= request.getContextPath() %>/AdminServlet?action=adminView'">
                <div class="card-icon">🧾</div>
                <div>All Complaints</div>
                <a href="<%= request.getContextPath() %>/AdminServlet?action=adminView">Go</a>
            </div>

            <div class="card" onclick="location.href='<%= request.getContextPath() %>/LogoutServlet'">
                <div class="card-icon">🚪</div>
                <div>Logout</div>
                <a href="<%= request.getContextPath() %>/LogoutServlet">Go</a>
            </div>
        </div>
    </body>
</html>
