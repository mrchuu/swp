/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CourseContent;

import controller.LecturerValidator;
import dao.CourseDAO;
import dao.LessonDAO;
import dao.SectionDAO;
import dao.UserDAO;
import entity.Lesson;
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
public class EditLessonContent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int user_id = 0;
        UserDAO ud = new UserDAO();
        CourseDAO cd = new CourseDAO();
        Cookie[] cookies = request.getCookies();
        User currUser = null;
        int lesson_id = Integer.parseInt(request.getParameter("lesson_id"));
        String lessonName = request.getParameter("lesson_Name").trim();
        String lessonVideo = request.getParameter("lesson_Video").trim();
        String lessonDes = request.getParameter("Lesson_desc").trim();

        LecturerValidator lv = new LecturerValidator();
        int val = lv.val(cookies, lesson_id, -1);
        if (val == 0) {
            response.sendRedirect("login");
        } else if (val == 1) {
            response.sendRedirect("UnauthorizedAccess.jsp");
        } else {

            request.setAttribute("lesson_id", lesson_id);
            request.setAttribute("lesson_Name", lessonName);
            request.setAttribute("lesson_Video", lessonVideo);
            request.setAttribute("Lesson_desc", lessonDes);
            request.getRequestDispatcher("EditLesson.jsp").forward(request, response);
        }
        

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int lesson_id = Integer.parseInt(request.getParameter("lesson_id"));
        String lessonName = request.getParameter("lesson_Name").trim();
        String lessonVideo = request.getParameter("lesson_Video").trim();
        String lessonDes = request.getParameter("Lesson_desc").trim();
        LessonDAO ld = new LessonDAO();
        ld.editLessonDetail(lessonName, lessonVideo, lessonDes, lesson_id);
        response.sendRedirect("LessonDetail?lId=" + lesson_id);
    }

}
