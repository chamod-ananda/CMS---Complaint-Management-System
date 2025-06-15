package lk.ijse.cms.controller;

import jakarta.servlet.RequestDispatcher;
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
import java.util.List;

@WebServlet("/ComplaintServlet")
public class ComplaintServlet extends HttpServlet {

    private ComplaintModel complaintModel = new ComplaintModel();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession(false);
        /*User user = (User) session.getAttribute("user");
        ComplaintModel complaintModel = new ComplaintModel();*/
        User loggedUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (loggedUser == null) {
            resp.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        if ("dashboard".equalsIgnoreCase(action)) {
            List<Complaint> complaints = complaintModel.getComplaintsByUserId(loggedUser.getId());
            req.setAttribute("complaints", complaints);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/employeeDashboard.jsp");
            dispatcher.forward(req, resp);

        } else if ("editForm".equalsIgnoreCase(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Complaint complaint = complaintModel.getComplaintById(id);
                if (complaint != null && complaint.getUserId() == loggedUser.getId()) {
                    req.setAttribute("complaintToEEdit", complaint);
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/editComplaint.jsp");
                    dispatcher.forward(req, resp);
                    return;
                }
            }
            resp.sendRedirect("ComplaintServlet?action=dashboard&error=Invalid complaint");
        } else if ("delete".equalsIgnoreCase(action)) {
            String idStr = req.getParameter("id");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                boolean deleted = complaintModel.deleteComplaint(id);
                if (deleted) {
                    resp.sendRedirect("ComplaintServlet?action=dashboard&msg=Complaint deleted");
                } else {
                    resp.sendRedirect("ComplaintServlet?action=dashboard&error=Complaint can't deleted");
                }
            } else {
                resp.sendRedirect("ComplaintServlet?action=dashboard&error=Invalid complaint ID");
            }
        } else {
            resp.sendRedirect("ComplaintServlet?action=dashboard");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        User loggedUser = (session != null) ? (User) session.getAttribute("user") : null;

        if (loggedUser == null) {
            resp.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        String action = req.getParameter("action");

        if ("submit".equalsIgnoreCase(action)) {
            String title = req.getParameter("title");
            String description = req.getParameter("description");

            Complaint complaint = new Complaint();
            complaint.setTitle(title);
            complaint.setDescription(description);
            complaint.setUserId(loggedUser.getId());

            boolean added = complaintModel.addComplaint(complaint);
            if (added) {
                resp.sendRedirect("ComplaintServlet?action=dashboard&&msg=Complaint added successfully");
            } else {
                resp.sendRedirect("ComplaintServlet?action=dashboard&&error=Complaint failed add");
            }
        } else if ("update".equalsIgnoreCase(action)) {
            String idStr = req.getParameter("id");
            String title = req.getParameter("title");
            String description = req.getParameter("description");

            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                Complaint complaint = complaintModel.getComplaintById(id);

                if (complaint != null && complaint.getUserId() == loggedUser.getId() && "PENDING".equalsIgnoreCase(complaint.getStatus())) {
                    complaint.setTitle(title);
                    complaint.setDescription(description);
                    boolean updated = complaintModel.updateComplaint(complaint);
                    if (updated) {
                        resp.sendRedirect("ComplaintServlet?action=dashboard&msg=Complaint updated successfully");
                    } else {
                        resp.sendRedirect("ComplaintServlet?action=editForm&id=" + id + "&error=Complaint failed to update");
                    }
                } else {
                    resp.sendRedirect("ComplaintServlet?action=dashboard&error=Invalid complaint or not editable");
                }
            } else {
                resp.sendRedirect("ComplaintServlet?action=dashboard&error=Invalid complaint ID");
            }
        } else {
            resp.sendRedirect("ComplaintServlet?action=dashboard");
        }
    }
}
