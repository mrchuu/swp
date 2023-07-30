/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TestFeature;

import dao.CourseDAO;
import dao.QuizDAO;
import dao.SubscriptionDAO;
import entity.Course;
import entity.ManageCourse;
import entity.Question;
import entity.Quiz;
import entity.Subscription;
import jakarta.servlet.RequestDispatcher;
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
 * @author ACER
 */
public class QuizHandle extends HttpServlet {

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
            out.println("<title>Servlet QuizHandle</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizHandle at " + request.getContextPath() + "</h1>");
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

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                }
            }
        }

        if (user_id != 0) {
            String quizIdString = request.getParameter("quiz_id");
            int quiz_id = Integer.parseInt(quizIdString);
            // Truyen danh sach cau hoi
            CourseDAO cd = new CourseDAO();
            int cid = cd.getCourseidFromQuiz(quiz_id);
            ManageCourse checkRegisterdCourse = cd.checkCourseRegistered(cid, user_id);
            if (checkRegisterdCourse == null) {
                response.sendRedirect("courseDetails?course_id=" + cid);
            } else {
                SubscriptionDAO subScriptionDAO = new SubscriptionDAO();
                Subscription currentSubscription = subScriptionDAO.GetCurrentSubscription(user_id);
                QuizDAO quizDAO = new QuizDAO();
                Quiz requestedQuiz = quizDAO.getQuizById(quiz_id);
                if (requestedQuiz.isQuiz_status() == false) {
                    response.sendRedirect("UnactiveLesson.jsp");
                    return;
                }
                Course course = cd.searchById(cid);
                Vector<Question> quesList = quizDAO.getQuestionByQuizId(quiz_id);
                request.setAttribute("currentSubscription", currentSubscription);
                request.setAttribute("Course", course);
                request.setAttribute("requestedQuiz", requestedQuiz);
                request.setAttribute("quesList", quesList);
                request.setAttribute("quiz_id", quiz_id);
                RequestDispatcher rd = request.getRequestDispatcher("QuizHandle.jsp");
                rd.forward(request, response);
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
