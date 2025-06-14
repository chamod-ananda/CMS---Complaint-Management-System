<%--
  Created by IntelliJ IDEA.
  User: Chamod Ananda
  Date: 6/14/2025
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lk.ijse.cms.dto.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    User loggedUser = (User) session.getAttribute("user");
    if (loggedUser == null || !"EMPLOYEE".equals(loggedUser.getRole())) {
        response.sendRedirect("login.jsp?error=unauthorized");
        return;
    }
%>
<html>
    <head>
        <title>Employee Dashboard</title>
    </head>
    <body>
        <div class="dashboard">
            <h2>Welcome, <%= loggedUser.getUsername() %> (Employee)</h2>

            <a href="submitComplaint.jsp">New Complaint</a>
            <a href="ComplaintServlet?action=viewMy">My Complaints</a>
            <a href="ComplaintServlet?action=deletePending">Delete Complaint</a>
            <a href="logout.jsp">Logout</a>
        </div>
    </body>
</html>
