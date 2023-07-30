/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import dao.CourseDAO;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author ACER
 */
public class CourseRegister extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CourseRegister</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CourseRegister at " + request.getContextPath() + "</h1>");
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

        int user_id = 0;
        Cookie[] cookies = request.getCookies();
        UserDAO ud = new UserDAO();
        User currUser = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                }
            }
        }

        String courseIdString = request.getParameter("course_id");
        int course_id = Integer.parseInt(courseIdString);

        // Get current date
        Calendar calendar = Calendar.getInstance();
        java.util.Date currentDate = calendar.getTime();
        // Convert java.util.Date to java.sql.Date
        Date sqlCurrentDate = new Date(currentDate.getTime());
        // Get end date
        calendar.add(Calendar.DATE, 10);
        java.util.Date endDate = calendar.getTime();
        // Convert java.util.Date to java.sql.Date
        Date sqlEndDate = new Date(endDate.getTime());

        CourseDAO courseDAO = new CourseDAO();
        if (user_id != 0) {
            if (currUser.getRoleId() == 2) {
                courseDAO.addCourseToUser(course_id, user_id, sqlCurrentDate, sqlEndDate);
                response.sendRedirect("mycourselistservlet");
            } else {
                response.sendRedirect("UnauthorizedAccess.jsp");
            }
        } else {
            response.sendRedirect("login.jsp");
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
