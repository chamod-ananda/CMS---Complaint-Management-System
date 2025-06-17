package lk.ijse.cms.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.cms.dto.Complaint;
import lk.ijse.cms.dto.User;
import lk.ijse.cms.model.ComplaintModel;

import java.io.IOException;
import java.util.List;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private ComplaintModel complaintModel = new ComplaintModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) req.getSession().getAttribute("user");

        if (loggedUser == null || !"ADMIN".equals(loggedUser.getRole())) {
            resp.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        String action = req.getParameter("action");

        if ("adminView".equals(action)) {
            List<Complaint> allComplaints = complaintModel.getAllComplaints();
            req.setAttribute("allComplaints", allComplaints);
            req.getRequestDispatcher("/jsp/AdminViewComplaints.jsp").forward(req, resp);
        } else {
            resp.sendRedirect("adminDashboard.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedUser = (User) req.getSession().getAttribute("user");

        if (loggedUser == null || !"ADMIN".equals(loggedUser.getRole())) {
            resp.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        String action = req.getParameter("action");

        if ("adminUpdate".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String status = req.getParameter("status");
            String remarks = req.getParameter("remarks");

            boolean updated = complaintModel.updateComplaintByAdmin(id, status, remarks);
            if (updated) {
                resp.sendRedirect("AdminServlet?action=adminView&msg=updated");
            } else {
                resp.sendRedirect("AdminServlet?action=adminView&error=Update failed");
            }
        } else if ("adminDelete".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            boolean deleted = complaintModel.deleteComplaintByAdmin(id);
            if (deleted) {
                resp.sendRedirect("AdminServlet?action=adminView&msg=deleted");
            } else {
                resp.sendRedirect("AdminServlet?action=adminView&error=Delete failed");
            }
        } else {
            resp.sendRedirect("index.jsp?error=unauthorized");
        }
    }
}
