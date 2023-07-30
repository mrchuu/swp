/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CourseContent;

import controller.LecturerValidator;
import dao.CourseDAO;
import dao.LessonDAO;
import dao.QuizDAO;
import dao.UserDAO;
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
public class UpdateQuizStatus extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int user_id = 0;
        UserDAO ud = new UserDAO();
        CourseDAO cd = new CourseDAO();
        Cookie[] cookies = request.getCookies();
        int QuizId = Integer.parseInt(request.getParameter("Quizid"));
        int updateStatus = Integer.parseInt(request.getParameter("updateStatus"));
        int courseId = cd.getCourseidFromQuiz(QuizId);
        LecturerValidator lv = new LecturerValidator();
        int val = lv.val1(cookies, QuizId);
        if (val == 0) {
            response.sendRedirect("login");
        } else if (val == 1) {
            response.sendRedirect("UnauthorizedAccess.jsp");
        } else {

            QuizDAO qd = new QuizDAO();
            qd.SetQuizStatus(updateStatus, QuizId);
            request.getRequestDispatcher("LessonListController?Course_id=" + courseId).forward(request, response);
        }
    }

}
