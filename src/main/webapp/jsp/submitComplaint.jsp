<%--
  Created by IntelliJ IDEA.
  User: Chamod Ananda
  Date: 6/14/2025
  Time: 3:45 PM
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
        <title>Submit Complaint</title>
        <link rel="stylesheet" href="../css/subComplaint.css">
    </head>
    <body>
        <div class="form-container">
            <h2>Submit Complaint</h2>
            <form action="../ComplaintServlet" method="post">
                <input type="hidden" name="action" value="submit">
                <input type="text" name="title" placeholder="Complaint Title" required>
                <textarea name="description" placeholder="Description" rows="5" required></textarea>
                <button type="submit">Submit</button>
            </form>
        </div>
    </body>
</html>
