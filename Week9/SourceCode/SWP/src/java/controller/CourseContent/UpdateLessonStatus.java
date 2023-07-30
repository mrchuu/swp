/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CourseContent;

import controller.LecturerValidator;
import dao.CourseDAO;
import dao.LessonDAO;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author FPT
 */
public class UpdateLessonStatus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int user_id = 0;
        UserDAO ud = new UserDAO();
        CourseDAO cd = new CourseDAO();
        Cookie[] cookies = request.getCookies();

        int lessonId = Integer.parseInt(request.getParameter("lessonId"));
        int updateStatus = Integer.parseInt(request.getParameter("updateStatus"));
        int courseId = cd.getCourseidFromLeson(lessonId);
        LecturerValidator lv = new LecturerValidator();
        int val = lv.val(cookies, lessonId, -1);
        if (val == 0) {
            response.sendRedirect("login");
        } else if (val == 1) {
            response.sendRedirect("UnauthorizedAccess.jsp");
        } else {
            
            LessonDAO ld = new LessonDAO();
            ld.SetLessonStatus(updateStatus, lessonId);
            request.getRequestDispatcher("LessonListController?Course_id="+courseId).forward(request, response);
        }

    }

}
