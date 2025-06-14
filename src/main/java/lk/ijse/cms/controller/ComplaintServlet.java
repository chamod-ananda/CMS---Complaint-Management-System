package lk.ijse.cms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lk.ijse.cms.dto.Complaint;
import lk.ijse.cms.dto.User;
import lk.ijse.cms.model.ComplaintModel;

import java.io.IOException;

@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("submit".equals(action)) {
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");

            if (user == null || !"EMPLOYEE".equals(user.getRole())) {
                resp.sendRedirect("login.jsp?error=unauthorized");
                return;
            }

            String title = req.getParameter("title");
            String description = req.getParameter("description");

            Complaint complaint = new Complaint();
            complaint.setTitle(title);
            complaint.setDescription(description);
            complaint.setUserId(user.getId());
            System.out.println("User ID: " + user.getId());

            ComplaintModel complaintModel = new ComplaintModel();
            boolean success = complaintModel.addComplaint(complaint);

            if (success) {
                resp.sendRedirect("jsp/employeeDashBoard.jsp?msg=success");
            } else {
                resp.sendRedirect("jsp/submitComplaint.jsp?msg=failed");
            }
        }
    }
}
