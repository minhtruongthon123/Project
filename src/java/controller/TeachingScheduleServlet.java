/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import Model.Schedule;
import Model.Student;
import Model.Teacher;
import Model.User;
import dal.ScheduleDAO;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author minhn
 */
public class TeachingScheduleServlet extends HttpServlet {

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
            out.println("<title>Servlet TeachingScheduleServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TeachingScheduleServlet at " + request.getContextPath() + "</h1>");
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
        TeacherDAO teacherDAO=new TeacherDAO();
        if (u != null && u.getRole() == 2) {
            ScheduleDAO scheduleDAO = new ScheduleDAO();
            List<Schedule> listSchedule = new ArrayList<>();
            List<List<Schedule>> listbyWeek = new ArrayList<>();
            List<Schedule> list = new ArrayList<>();
            Teacher tc = null;            
            String PageNumber = request.getParameter("page");
            int page = 0;
            if (PageNumber != null) {
                try {
                    page = Integer.parseInt(PageNumber) - 1;
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            try {
                tc = teacherDAO.TeacherInfo(u.getUsername());
                list = scheduleDAO.getAllScheduleforTeacher(tc.getId());
                listbyWeek = scheduleDAO.getSchedulebyWeek(list);
                listSchedule = listbyWeek.get(page);
            } catch (SQLException ex) {
                Logger.getLogger(TeachingScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TeachingScheduleServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            int NumberofPage = listbyWeek.size();
            Collections.sort(listSchedule, new Comparator<Schedule>() {
                @Override
                public int compare(Schedule fs1, Schedule fs2) {
                    // Đầu tiên so sánh ngày
                    int dateComparison = fs1.getDate().compareTo(fs2.getDate());
                    if (dateComparison != 0) {
                        return dateComparison;
                    }
                    // Nếu ngày giống nhau, thì so sánh thời gian bắt đầu
                    return fs2.getTimeStart().compareTo(fs1.getTimeStart());
                }
            });
            request.setAttribute("page", page + 1);
            request.setAttribute("numpage", NumberofPage);
            request.setAttribute("list", listSchedule);
            request.getRequestDispatcher("teachingschedule.jsp").forward(request, response);
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
