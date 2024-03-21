/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.Student;
import Model.Teacher;
import Model.User;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Validation;
import dal.LoginDAO;

/**
 *
 * @author minhn
 */
public class ChangeServlet extends HttpServlet {

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
            out.println("<title>Servlet ChangeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangeServlet at " + request.getContextPath() + "</h1>");
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
        String type = request.getParameter("type");
        request.setAttribute("type", type);
        request.getRequestDispatcher("ChangeProfile.jsp").forward(request, response);
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
        Validation val = new Validation();
        HttpSession session = request.getSession();
        String type = request.getParameter("type");
        User u = (User) session.getAttribute("u");
        PrintWriter out = response.getWriter();
        Student student = null;
        Teacher tc = null;
        if (u==null) {
            request.getRequestDispatcher("/login").forward(request, response);
            return; // Thêm lệnh này để ngăn chương trình tiếp tục thực thi nếu không có người dùng nào đăng nhập
        }

        if (type.equalsIgnoreCase("password")) {
            String newPassword = request.getParameter("newPassword");
            String oldPassword = request.getParameter("currentPassword");
            if (!u.getPassword().equals(oldPassword)) {
                request.setAttribute("error", "Invalid OldPassword Input");
                request.getRequestDispatcher("ChangeProfile.jsp").forward(request, response);
            }else if(!val.isValidPassword(newPassword) ){
                request.setAttribute("error", "Invalid Password Input");
                request.getRequestDispatcher("ChangeProfile.jsp").forward(request, response);
            }
            LoginDAO login=new LoginDAO();
            try {
                login.changePassword(newPassword, u.getUsername());
                request.getRequestDispatcher("/logout").forward(request, response);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(ChangeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (type.equalsIgnoreCase("phone")) {
            String newPhoneNumber = request.getParameter("phone");
            if (!val.isValidPhoneNumber(newPhoneNumber)) {
                request.setAttribute("error", "Invalid Phone Number Input");
                request.getRequestDispatcher("ChangeProfile.jsp").forward(request, response);

            }

            // Kiểm tra và thực hiện thay đổi số điện thoại tùy thuộc vào loại người dùng (student hoặc teacher)
            if (u.getRole()==1) {
                StudentDAO stu = new StudentDAO();
                try {
                    student =stu.StudentInfo(u.getUsername());
                    stu.changePhone(newPhoneNumber, student);
                    student =stu.StudentInfo(u.getUsername());
                    session.setAttribute("student", student);
                    request.getRequestDispatcher("studentabout.jsp").forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ChangeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(u.getRole()==2) {
                TeacherDAO teacherDAO = new TeacherDAO();
                try {
                    tc =teacherDAO.TeacherInfo(u.getUsername());
                    teacherDAO.changePhone(newPhoneNumber, tc);
                    tc =teacherDAO.TeacherInfo(u.getUsername());
                    session.setAttribute("teacher", tc);
                    request.getRequestDispatcher("teacherabout.jsp").forward(request, response);
                } catch (SQLException | ClassNotFoundException ex) {
                    Logger.getLogger(ChangeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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
