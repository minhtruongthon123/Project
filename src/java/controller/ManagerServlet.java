/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.Group;
import Model.Manager;
import Model.Student;
import Model.Teacher;
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
public class ManagerServlet extends HttpServlet {

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
            out.println("<title>Servlet ManagerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManagerServlet at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        Student student = (Student) session.getAttribute("student");
        Teacher tc = (Teacher) session.getAttribute("teacher");
        if (tc == null && student != null) {
            request.getRequestDispatcher("studenthome.jsp").forward(request, response);
        }
        if (student == null && tc == null) {
            request.getRequestDispatcher("/login").forward(request, response);

        }
        String search = request.getParameter("search");
        List<Student> listStudent = new ArrayList<>();
        StudentDAO studentDAO = new StudentDAO();
        GroupDAO groupDAO = new GroupDAO();
        List<Group> listGroup = new ArrayList<>();
        List<Manager> listManager = new ArrayList<>();
        if (search != null) {
            try {
                listStudent = studentDAO.searchStudent(search);
                for (Student i : listStudent) {
                    listGroup = groupDAO.getAllGroupbyStudentID(i.getId());
                    listManager.add(new Manager(i, listGroup));
                }
                request.setAttribute("listManager", listManager);
                request.getRequestDispatcher("StudentManager.jsp").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                listStudent = studentDAO.searchStudent("");
                for (Student i : listStudent) {
                    listGroup = groupDAO.getAllGroupbyStudentID(i.getId());
                    listManager.add(new Manager(i, listGroup));
                }
                request.setAttribute("listManager", listManager);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ManagerServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("listStudent", listStudent);
            request.getRequestDispatcher("StudentManager.jsp").forward(request, response);
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
