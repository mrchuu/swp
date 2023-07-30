/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TestFeature;

import dao.CourseDAO;
import dao.QuesResultDAO;
import dao.QuizDAO;
import dao.QuizResultDAO;
import entity.Course;
import entity.QuesResult;
import entity.Quiz;
import entity.QuizResult;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author ACER
 */
public class QuizReview extends HttpServlet {

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
        String quiz_result_id_string = request.getParameter("quiz_result_id");
        int quiz_result_id;
        if(quiz_result_id_string == null){
            quiz_result_id = (int)request.getAttribute("quiz_result_id");
        }
        else{
            quiz_result_id = Integer.parseInt(quiz_result_id_string);
        }

        QuesResultDAO quesResultDAO = new QuesResultDAO();
        QuizResultDAO quizResultDAO = new QuizResultDAO();
        QuizDAO quizDAO = new QuizDAO();
        // Get question result list
        Vector<QuesResult> quesResultList = quesResultDAO.getQuesResultBy(quiz_result_id);
        // Get quiz result list
        QuizResult quizResult = quizResultDAO.getQuizResultByQuizResultId(quiz_result_id);

        //lay thong tin cho navigation
        int quizId = quizResult.getQuiz_id();
        CourseDAO courseDAO = new CourseDAO();
        int courseId = courseDAO.getCourseidFromQuiz(quizId);
        Course course = courseDAO.searchById(courseId);
        Quiz requestedQuiz = quizDAO.getQuizById(quizId);
        // Get correct answers
        Vector<String> correctAnswers = quizDAO.getAllCorrectAnswer(quizResult.getQuiz_id());
        request.setAttribute("Course", course);
        request.setAttribute("requestedQuiz", requestedQuiz);
        request.setAttribute("quizResult", quizResult);
        request.setAttribute("quesResultList", quesResultList);
        request.setAttribute("correctAnswers", correctAnswers);
        request.getRequestDispatcher("QuizReview.jsp").forward(request, response);
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
        processRequest(request, response);
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
