<%--
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Login - Complaint Management System</title>
    <link rel="stylesheet" href="css/signin.css">
  </head>
  <body>
    <div class="login-container">
      <h2>Login</h2>
      <form action="${pageContext.request.contextPath}/login" method="POST">
        <input type="text" name="username" placeholder="Enter Username" required>
        <input type="password" name="password" placeholder="Enter Password" required>
        <button type="submit">Login</button>
      </form>

      <%
        String error = request.getParameter("error");
        if (error != null) {
      %>
      <p class="error">Invalid username or password</p>
      <% } %>

      <div class="signup-link">
        Don't have an Account? <a href="jsp/register.jsp">Create New Account</a>
      </div>
    </div>
  </body>
</html>--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Complaint Management System</title>
  <link rel="stylesheet" href="css/index.css">
</head>
<body>
<div class="hero-container">
  <div class="hero-content">
    <h1>Welcome to the Complaint Management System</h1>
    <p>
      Our platform simplifies the process of submitting and managing complaints
      for both employees and administrators. Track issues, ensure accountability,
      and resolve problems efficiently with real-time updates and an easy-to-use interface.
    </p>
    <a href="jsp/login.jsp" class="login-btn">Login</a>
  </div>
</div>
</body>
</html>

