<%--
  Created by IntelliJ IDEA.
  User: Chamod Ananda
  Date: 6/13/2025
  Time: 9:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Account</title>
    <link rel="stylesheet" href="../css/register.css">
</head>
<body>
<div class="form-container">
    <h2>Create New Account</h2>
    <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
        <input type="text" name="username" placeholder="Enter Username" required>
        <input type="password" name="password" placeholder="Enter Your Password" required>
        <input type="password" name="confirmPassword" placeholder="Confirm Your Password" required>
        <select name="role" required>
            <option value="ADMIN">ADMIN</option>
            <option value="EMPLOYEE">Employee</option>
        </select>
        <button type="submit">Sign Up</button>
    </form>

    <%
        String error = request.getParameter("error");
    %>
    <div class="error">
        <% if ("password".equals(error)) { %>
         Passwords do not match!
        <% } else if ("exists" .equals(error)) { %>
        Username already exists!
        <% } else if ("db".equals(error)) { %>
        Something went wrong! Please try again.
        <% } %>
    </div>
    <div class="signin-link">
        Already have an Account? <a href="../index.jsp">Login</a>
    </div>
</div>

</body>
</html>
