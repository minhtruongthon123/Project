/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.Attendance;
import Model.Group;
import Model.Student;
import Model.Teacher;
import dal.AttendanceDAO;
import dal.GroupDAO;
import dal.StudentDAO;
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
public class TeacherAttandeceServlet extends HttpServlet {

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
            out.println("<title>Servlet TeacherAttandeceServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeacherAttandeceServlet at " + request.getContextPath() + "</h1>");
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
        Student student = (Student) session.getAttribute("student");
        Teacher tc = (Teacher) session.getAttribute("teacher");
        if (tc == null && student != null) {
            request.getRequestDispatcher("studenthome.jsp").forward(request, response);
        }
        if (student == null && tc == null) {
            request.getRequestDispatcher("/login").forward(request, response);

        }
        String group = request.getParameter("group");
        String scheduleID = request.getParameter("scheduleID");
        AttendanceDAO AttendanceDAO = new AttendanceDAO();
        List<Attendance> listAttendance = new ArrayList<>();
        GroupDAO groupDAO = new GroupDAO();
        List<Group> listGroup = new ArrayList<>();
        StudentDAO studentDAO = new StudentDAO();
        List<String> listStudent = new ArrayList<>();
        List<String> listID = new ArrayList<>();
        if (group != null) {
            if (scheduleID == null) {
                try {
                    listAttendance = AttendanceDAO.getAttendancebyTeacher(tc.getId(), Integer.parseInt(group));
                    listGroup = groupDAO.getAllGroupbyTeacherID(tc.getId());
                    request.setAttribute("listAttendance", listAttendance);
                    request.setAttribute("listGroup", listGroup);
                    request.setAttribute("group", group);
                    request.getRequestDispatcher("teacherattendance.jsp").forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(TeacherAttandeceServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    listAttendance = AttendanceDAO.getAttendancebyTeacher(tc.getId(), Integer.parseInt(group));
                    listGroup = groupDAO.getAllGroupbyTeacherID(tc.getId());
                    listStudent = studentDAO.getAllStudentinClass(Integer.parseInt(group));
                    listID = studentDAO.getAllStudentID(Integer.parseInt(group));
                    request.setAttribute("listAttendance", listAttendance);
                    request.setAttribute("listGroup", listGroup);
                    request.setAttribute("listStudent", listStudent);
                    request.setAttribute("listID", listID);
                    request.setAttribute("group", group);
                    request.setAttribute("scheduleID", scheduleID);
                    request.getRequestDispatcher("teacherattendance.jsp").forward(request, response);
                } catch (SQLException ex) {
                    Logger.getLogger(TeacherAttandeceServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TeacherAttandeceServlet.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            try {
                listGroup = groupDAO.getAllGroupbyTeacherID(tc.getId());
                request.setAttribute("listGroup", listGroup);
                request.getRequestDispatcher("teacherattendance.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(TeacherAttandeceServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        String scheduleID = request.getParameter("scheduleID");
        int size = Integer.parseInt(request.getParameter("size"));
        int ID = 0;
        // Khai báo một mảng để lưu trữ giá trị của listID
        String[] listID = new String[size];
        String[] listPresent = new String[size];
        String[] listComment = new String[size];
        int[] present = new int[size];
        AttendanceDAO attendanceDAO = new AttendanceDAO();
        // Lặp qua các chỉ số để lấy giá trị của từng trường listID[index]
        for (int i = 0; i < size; i++) {
            listID[i] = request.getParameter("listID" + i);
            listPresent[i] = request.getParameter("present" + i);
            listComment[i] = request.getParameter("comment" + i);
        }
        try{
            for (int i = 0; i < size; i++) {
                present[i]=Integer.parseInt(listPresent[i]);
            }
            ID=Integer.parseInt(scheduleID);
            
            response.sendRedirect(request.getContextPath() + "/teacherattendace");
        }catch(NumberFormatException e){
            
        }
        for (int i = 0; i < size; i++) {
            try {
                attendanceDAO.takeAttendance(ID, listID[i], present[i], listComment[i]);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(TeacherAttandeceServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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
