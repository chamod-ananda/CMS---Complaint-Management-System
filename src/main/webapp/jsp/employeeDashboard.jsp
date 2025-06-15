<%--
  Created by IntelliJ IDEA.
  User: Chamod Ananda
  Date: 6/14/2025
  Time: 1:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="lk.ijse.cms.dto.User" %>
<%@ page import="lk.ijse.cms.dto.Complaint" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User loggedUser = (User) session.getAttribute("user");
    if (loggedUser == null || !"EMPLOYEE".equalsIgnoreCase(loggedUser.getRole())) {
        response.sendRedirect("index.jsp?error=unauthorized");
        return;
    }

    List<Complaint> complaints = (List<Complaint>) request.getAttribute("complaints");
    String msg = request.getParameter("msg");
    String error = request.getParameter("error");
%>

<html>
    <head>
        <title>Employee Dashboard</title>
<%--        <link rel="stylesheet" href="../css/employeeDashboard.css">--%>
        <link rel="stylesheet" href="<%= request.getContextPath() %>/css/employeeDashboard.css">

    </head>
    <body>
        <h2>Welcome, <%= loggedUser.getUsername() %> (Employee)</h2>

        <% if (msg != null) { %>
        <div class="msg"><%= msg %></div>
        <% } else if (error != null) { %>
        <div class="error"><%= error %></div>
        <% } %>

        <form method="post" action="<%= request.getContextPath() %>/ComplaintServlet">
            <input type="hidden" name="action" value="submit">
            <label for="title">Title:</label><br>
            <input type="text" id="title" name="title" required maxlength="100" placeholder="Complaint Title"><br>

            <label for="description">Description:</label><br>
            <textarea id="description" name="description" rows="4" required placeholder="Complaint Description"></textarea><br>

            <button type="submit">Submit</button>
        </form>

        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Description</th>
                <th>Status</th>
                <th>Remarks</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (complaints != null && !complaints.isEmpty()) {
                    for (Complaint c : complaints) {
                        String status = c.getStatus();
                        String cssClass = "other";
                        if ("PENDING".equalsIgnoreCase(status)) cssClass = "pending";
                        else if ("RESOLVED".equalsIgnoreCase(status)) cssClass = "resolved";
            %>
            <tr>
                <td><%= c.getId() %></td>
                <td><%= c.getTitle() %></td>
                <td><%= c.getDescription() %></td>
                <td><span class="status <%= cssClass %>"><%= status %></span></td>
                <td><%= (c.getRemarks() != null && !c.getRemarks().isEmpty()) ? c.getRemarks() : "-" %></td>
                <td>
                    <% if ("PENDING".equalsIgnoreCase(status)) { %>
                    <a href="<%= request.getContextPath() %>/ComplaintServlet?action=editForm&id=<%= c.getId() %>" class="action-btn edit-btn">Edit</a>
                    <a href="<%= request.getContextPath() %>/ComplaintServlet?action=delete&id=<%= c.getId() %>" class="action-btn delete-btn"
                       onclick="return confirm('Are you sure you want to delete this complaint?');">Delete</a>
                    <% } else { %>
                    <em>Not editable</em>
                    <% } %>
                </td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="6" class="no-data">You haven't submitted any complaints yet.</td>
            </tr>
            <% } %>
            </tbody>
        </table>

        <a href="<%= request.getContextPath() %>/LogoutServlet" class="logout-link">Logout</a>
    </body>
</html>
