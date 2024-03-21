/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.Attendance;
import Model.Group;
import Model.Student;
import Model.Teacher;
import Model.User;
import dal.AttendanceDAO;
import dal.GroupDAO;
import dal.StudentDAO;
import dal.TeacherDAO;
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
public class ReportAttendance extends HttpServlet {

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
            out.println("<title>Servlet ReportAttendance</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportAttendance at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("u");
        if (u != null && u.getRole() == 2) {
            String group = request.getParameter("group");
            AttendanceDAO AttendanceDAO = new AttendanceDAO();
            List<Attendance> listAttendance = new ArrayList<>();
            GroupDAO groupDAO = new GroupDAO();
            List<Group> listGroup = new ArrayList<>();
            StudentDAO studentDAO = new StudentDAO();
            TeacherDAO teacherDAO = new TeacherDAO();
            Teacher tc = null;
            List<String> listStudent = new ArrayList<>();
            List<String> listID = new ArrayList<>();
            List<Integer> list = new ArrayList<>();
            List<List<Integer>> listPresent = new ArrayList<>();
            try {
                tc=teacherDAO.TeacherInfo(u.getUsername());
                listGroup = groupDAO.getAllGroupbyTeacherID(tc.getId());
            } catch (SQLException ex) {
                Logger.getLogger(TeacherAttandeceServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TeacherAttandeceServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (group == null) {
                request.setAttribute("listGroup", listGroup);
                request.getRequestDispatcher("ReportAttendance.jsp").forward(request, response);
            } else {
                try {
                    tc=teacherDAO.TeacherInfo(u.getUsername());
                    listAttendance = AttendanceDAO.getAttendancebyTeacher(tc.getId(), Integer.parseInt(group));
                    listStudent = studentDAO.getAllStudentinClass(Integer.parseInt(group));
                    listID = studentDAO.getAllStudentID(Integer.parseInt(group));
                    for (String string : listID) {
                        list = AttendanceDAO.getAttendancebyStudentID(string, Integer.parseInt(group));
                        listPresent.add(list);
                    }
                    request.setAttribute("listPresent", listPresent);
                    request.setAttribute("listAttendance", listAttendance);
                    request.setAttribute("listGroup", listGroup);
                    request.setAttribute("listStudent", listStudent);
                    request.setAttribute("listID", listID);
                    request.getRequestDispatcher("ReportAttendance.jsp").forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ReportAttendance.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else if (u != null && u.getRole() == 2) {
            request.getRequestDispatcher("teacherhome.jsp").forward(request, response);
        } else if (u != null && u.getRole() == 3) {
            request.getRequestDispatcher("Adminhome.jsp").forward(request, response);
        } else if(u==null) {
            request.getRequestDispatcher("/login").forward(request, response);
        }
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
        processRequest(request, response);
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
