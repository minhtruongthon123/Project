/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.Schedule;
import Model.Student;
import Model.Subject;
import Model.Teacher;
import Model.User;
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
public class SubjectTimeTable extends HttpServlet {

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
            out.println("<title>Servlet SubjectTimeTable</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectTimeTable at " + request.getContextPath() + "</h1>");
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
        String[] subject = request.getParameterValues("subjectSelected");
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("u");
        if (u != null && u.getRole() == 1) {
            Student st = new Student();
            List<Schedule> list = new ArrayList<>();
            st = (Student) session.getAttribute("student");
            List<Subject> listsubject = new ArrayList<>();
            SubjectDAO sb = new SubjectDAO();
            ScheduleDAO scheduleDAO = new ScheduleDAO();
            if (subject != null) {
                List<String> search = new ArrayList<>();
                for (int i = 0; i < subject.length; i++) {
                    search.add(subject[i]);
                }
                try {
                    list = scheduleDAO.getScheduleforStudentbySubject(search, st.getId());
                    listsubject = sb.getSubjectbyStudentID(st.getId());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SubjectTimeTable.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectTimeTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    list = scheduleDAO.getAllScheduleforStudent(st.getId());
                    listsubject = sb.getSubjectbyStudentID(st.getId());
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectTimeTable.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SubjectTimeTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            boolean[] checked = new boolean[listsubject.size()];
            for (int i = 0; i < checked.length; i++) {
                if (isCheck(listsubject.get(i).getName(), subject)) {
                    checked[i] = true;
                } else {
                    checked[i] = false;
                }
            }
            request.setAttribute("check", checked);
            request.setAttribute("listsubject", listsubject);
            request.setAttribute("list", list);
            request.getRequestDispatcher("subjecttimetable.jsp").forward(request, response);
        } else if (u != null && u.getRole() == 2) {
            request.getRequestDispatcher("teacherhome.jsp").forward(request, response);
        } else if (u != null && u.getRole() == 3) {
            request.getRequestDispatcher("Adminhome.jsp").forward(request, response);
        } else if(u==null) {
            request.getRequestDispatcher("/login").forward(request, response);
        }
    }

    private boolean isCheck(String subjectName, String[] subject) {
        if (subject == null) {
            return false;
        } else {
            for (int i = 0; i < subject.length; i++) {
                if (subject[i].equalsIgnoreCase(subjectName)) {
                    return true;
                }
            }
            return false;
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
