/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CourseDAO;
import dao.LessonDAO;
import dao.QuizDAO;
import dao.SectionDAO;
import dao.SubscriptionDAO;
import dao.UserDAO;
import entity.Lesson;
import entity.ManageCourse;
import entity.Section;
import entity.Subscription;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author admin
 */
public class LessonListController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private LessonDAO lessonDAO = new LessonDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        int user_id = 0;
        Cookie[] cookies = request.getCookies();

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
            User currUser = ud.getUserById(user_id);
            int courseId = Integer.parseInt(request.getParameter("Course_id"));
            CourseDAO cd = new CourseDAO();
            ManageCourse checkRegisterdCourse = cd.checkCourseRegistered(courseId, user_id);
            if (checkRegisterdCourse == null) {
                response.sendRedirect("courseDetails?course_id=" + courseId);
            } else {
                boolean courseStatus = cd.searchById(courseId).getCourse_status();
                if (courseStatus) {
                    String courseName = cd.searchById(courseId).getCourse_name();
                    SectionDAO secDAO = new SectionDAO();
                    LessonDAO lesDAO = new LessonDAO();
                    QuizDAO quizDao = new QuizDAO();
                    SubscriptionDAO subScriptionDAO = new SubscriptionDAO();
                    Subscription currentSubscription = subScriptionDAO.GetCurrentSubscription(user_id);
                    Vector<Section> vs = secDAO.getSectionListByCourseId(courseId);

                    for (Section v : vs) {
                        v.setLessonList(lesDAO.getLessonBySectionId(v.getSection_id()));
                        v.setQuizList(quizDao.getQuizListBySectionId(v.getSection_id()));
                    }
                    HttpSession session = request.getSession();

                    session.setAttribute("Course_id", courseId);
                    request.setAttribute("courseName", courseName);
                    request.setAttribute("currSubscription", currentSubscription);
                    request.setAttribute("currUser", currUser);
                    request.setAttribute("SectionList", vs);
                    request.getRequestDispatcher("lessonList.jsp").forward(request, response);
                }else{
                    response.sendRedirect("UnauthorizedAccess.jsp");
                }

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
