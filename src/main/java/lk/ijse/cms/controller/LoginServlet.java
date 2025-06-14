package lk.ijse.cms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.cms.dto.User;
import lk.ijse.cms.model.UserModel;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        UserModel userModel = new UserModel();
        User user = userModel.ValidateUser(username, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            if ("EMPLOYEE".equals(user.getRole())) {
                resp.sendRedirect("jsp/employeeDashboard.jsp");
            } else if("ADMIN".equals(user.getRole())){
                resp.sendRedirect("jsp/adminDashboard.jsp");
            } else {
                resp.sendRedirect("jsp/index.jsp?error=role");
            }
        } else {
            resp.sendRedirect("jsp/index.jsp?error=1");
        }
    }
}
