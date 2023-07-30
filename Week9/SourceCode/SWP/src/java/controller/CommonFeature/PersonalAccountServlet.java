/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import dao.CourseDAO;
import dao.GenderDAO;
import dao.RoleDAO;
import dao.SubscriptionDAO;
import dao.UserDAO;
import entity.Gender;
import entity.Role;
import entity.User;
import entity.ManageCourse;
import entity.Subscription;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class PersonalAccountServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int user_id = Integer.parseInt(request.getParameter("viewerId"));
        int profileId = Integer.parseInt(request.getParameter("ProfileId"));
        
        GenderDAO gd = new GenderDAO();
        RoleDAO rd = new RoleDAO();
        CourseDAO cd = new CourseDAO();
        if (user_id == 0) {
            response.sendRedirect("login.jsp");
        } else {
            UserDAO userDAO = new UserDAO();
            User profile = userDAO.getUserById(profileId);
            SubscriptionDAO sd = new SubscriptionDAO();
            Subscription currSubscription = sd.GetCurrentSubscription(user_id);
            Gender g = gd.getGenderById(profile.getGenderId());
            Role r = rd.getRoleById(profile.getRoleId());
            
            Vector<ManageCourse> profileCourses = cd.getmyCourseList(profile.getUserId(), null, null, null);
            if(user_id == profileId){
                request.setAttribute("viewOwn", true);
            }else{
                request.setAttribute("viewOwn", false);
            }
            request.setAttribute("currSubscription", currSubscription);
            request.setAttribute("profileCourses", profileCourses);
            request.setAttribute("profile", profile);
            request.setAttribute("gender", g);
            request.setAttribute("role", r);
            request.getRequestDispatcher("PersonalAccount.jsp").forward(request, response);
         
        }

    }

}
