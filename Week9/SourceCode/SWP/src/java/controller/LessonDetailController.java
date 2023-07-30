/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CourseDAO;
import dao.LessonDAO;
import dao.UserDAO;
import dto.LessonDto;
import entity.Course;
import entity.Lesson;
import entity.ManageCourse;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author admin
 */
public class LessonDetailController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LessonDAO lessonDAO = new LessonDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        int user_id = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                }
            }
        }
        if (user_id == 0) {
            response.sendRedirect("login.jsp");
            return;
        } else {
            UserDAO ud = new UserDAO();
            CourseDAO cd = new CourseDAO();
            User currUser = ud.getUserById(user_id);
            int lId = Integer.parseInt(request.getParameter("lId"));
            int cid = cd.getCourseidFromLeson(lId);
            ManageCourse checkRegisterdCourse = cd.checkCourseRegistered(cid, user_id);
            if (checkRegisterdCourse == null) {
                response.sendRedirect("courseDetails?course_id=" + cid);
            } else {
                Course course = cd.searchById(cid);
                String courseName = course.getCourse_name();
                Lesson lesson = lessonDAO.getLessonDetails(lId);
                if(lesson.isLesson_status()==false && currUser.getRoleId() == 2){
                    response.sendRedirect("UnactiveLesson.jsp");
                    return;
                }
                boolean done = lessonDAO.checkLessonDone(user_id, lId);
                request.setAttribute("currUser", currUser);
                request.setAttribute("courseName", courseName);
                request.setAttribute("done", done);
                request.setAttribute("lesson", lesson);
                request.getRequestDispatcher("lessonDetail.jsp").forward(request, response);
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

    }

}
