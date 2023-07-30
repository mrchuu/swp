/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CourseContent;

import dao.LessonDAO;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author FPT
 */
@WebServlet(name = "AddnewLessonTosection", urlPatterns = {"/addnewLessonTosection"})
public class AddnewLessonTosection extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int user_id = 0;
        UserDAO ud = new UserDAO();
        Cookie[] cookies = request.getCookies();
        User currUser = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                }
            }
        }
        if (user_id == 0) {
            response.sendRedirect("login");
        } else {
            if (currUser.getRoleId() == 3) {
                int Section_id = Integer.parseInt(request.getParameter("section_id"));
                int Course_id = Integer.parseInt(request.getParameter("course_Id"));
                request.setAttribute("Section_id", Section_id);
                request.setAttribute("Course_id", Course_id);
                request.getRequestDispatcher("AddLesson.jsp").forward(request, response);
            }else{
                response.sendRedirect("UnauthorizedAccess.jsp");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int Course_id = Integer.parseInt(request.getParameter("Course_id"));
        int Section_id = Integer.parseInt(request.getParameter("section_id"));
        String lessonName = request.getParameter("lesson_Name").trim();
        String lessonVideo = request.getParameter("lesson_Video").trim();
        String lessonDes = request.getParameter("Lesson_desc").trim();
        LessonDAO ld = new LessonDAO();
        ld.AddnewLessonToSection(Section_id, lessonName, lessonVideo, lessonDes);
        response.sendRedirect("LessonListController?Course_id=" + Course_id);
    }

}
