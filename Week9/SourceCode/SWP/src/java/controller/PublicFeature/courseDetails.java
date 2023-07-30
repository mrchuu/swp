/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.PublicFeature;

import dao.CourseDAO;
import dao.PricePackageDAO;
import dao.UserDAO;
import entity.Course;
import entity.ManageCourse;
import entity.Price_Package;
import entity.User;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author ACER
 */
public class courseDetails extends HttpServlet {

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
        String courseIdString = request.getParameter("course_id");
        int course_id = Integer.parseInt(courseIdString);
        CourseDAO courseDAO = new CourseDAO();
        Course course = courseDAO.searchById(course_id);
        request.setAttribute("course", course);
        //view only
        int user_id = -1;
        UserDAO ud = new UserDAO();
        int publisherId = courseDAO.getCoursePublisher(course_id);
        User Publisher = ud.getUserById(publisherId);
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    
                }
            }
        }
        
        if(user_id != 0){
            
            CourseDAO cd = new CourseDAO();
            User currUser = ud.getUserById(user_id);
            request.setAttribute("currUser", currUser);
            ManageCourse registerd = cd.checkCourseRegistered(course_id, user_id);
            if(registerd != null){
                request.setAttribute("registerd", true);
            }
        }
        request.setAttribute("viewerId", user_id);
        request.setAttribute("publisher", Publisher);
        RequestDispatcher rd = request.getRequestDispatcher("CourseDetails.jsp");
        rd.forward(request, response);
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

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
