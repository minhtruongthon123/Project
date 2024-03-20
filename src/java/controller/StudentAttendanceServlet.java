/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.Attendance;
import Model.Student;
import Model.Subject;
import Model.Teacher;
import Model.User;
import dal.AttendanceDAO;
import dal.ScheduleDAO;
import dal.SubjectDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minhn
 */
public class StudentAttendanceServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AttendanceSertvlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AttendanceSertvlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesson = request.getSession();
        Student st = (Student) sesson.getAttribute("student");
        Teacher tc = (Teacher) sesson.getAttribute("teacher");
        if (st == null && tc != null) {
            request.getRequestDispatcher("teacherhome.jsp").forward(request, response);
        }
        if (st == null && tc == null) {
            request.getRequestDispatcher("/login").forward(request, response);

        }
        String subject = request.getParameter("subject");
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        SubjectDAO sb = new SubjectDAO();
        List<Subject> listsubject = new ArrayList<>();
        List<Attendance> list = new ArrayList<>();
        if (subject != null) {
            try {
                list = attendanceDAO.getAttendancebyStudentandSubject(st.getId(), subject);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(StudentAttendanceServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            listsubject = sb.getSubjectbyStudentID(st.getId());
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(StudentAttendanceServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        request.setAttribute("list", list);
        request.setAttribute("listsubject", listsubject);
        request.getRequestDispatcher("studentattended.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
