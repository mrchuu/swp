/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TestFeature;

import dao.CourseDAO;
import dao.LessonDAO;
import dao.QuesResultDAO;
import dao.QuizDAO;
import dao.QuizResultDAO;
import entity.Question;
import entity.QuizResult;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Vector;
import java.sql.Timestamp;

/**
 *
 * @author ACER
 */
public class SubmitQuiz extends HttpServlet {

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
            out.println("<title>Servlet SubmitQuiz</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubmitQuiz at " + request.getContextPath() + "</h1>");
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
        QuesResultDAO quesResultDAO = new QuesResultDAO();
        QuizResultDAO quizResultDAO = new QuizResultDAO();
        response.setContentType("text/html;charset=UTF-8");
        QuizDAO quizDAO = new QuizDAO();

        // Get list of question id
        Vector<Integer> quesList = new Vector<>();

        Enumeration<String> paramNames = request.getParameterNames();
        // Get list of answers
        Vector<String> answers = new Vector<>();
        // Get list of flags
        Vector<Boolean> flags = new Vector<>();

        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (paramName.startsWith("answer")) {
                String answer = request.getParameter(paramName);
                answers.add(answer);
            }
            if (paramName.startsWith("flag")) {
                Boolean flag = Boolean.parseBoolean(request.getParameter(paramName));
                flags.add(flag);
            }
            if (paramName.startsWith("ques")) {
                int ques_id = Integer.parseInt(request.getParameter(paramName));
                quesList.add(ques_id);
            }
        }

        // Get user_id
        int user_id = 0;
        Cookie[] cookies = request.getCookies();

        // Get quiz_id
        int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                }
            }
        }

        // Get start and finish time
        String start_time_string = request.getParameter("start_time");
        Timestamp start_time = Timestamp.valueOf(start_time_string);
        Timestamp end_time = new Timestamp(System.currentTimeMillis());

        // Get attempt number
        int maxAttempt = quizResultDAO.getMaxAttempByUserIdAndQuizId(user_id, quiz_id);
        // Caculate grade
        // Get all question correct answer
        Vector<String> correctAnswers = quizDAO.getAllCorrectAnswer(quiz_id);
        
        int totalQues = correctAnswers.size();
        int correctQues = 0;
        for (int i = 0; i < answers.size(); i++) {
            // Caculate grade
            response.getWriter().print(correctAnswers.get(i));
            if (answers.get(i).equals(correctAnswers.get(i))) {
                correctQues += 1;
            }
        }
        double num = (double) correctQues / (double) totalQues * 10;
        double grade = (double) Math.round(num * 100) / 100.0;
        PrintWriter out = response.getWriter();
        // Save quiz result to database
        int quizResultId = quizResultDAO.insertQuizResult(quiz_id, user_id, grade > 5, (float) grade, start_time, end_time, maxAttempt + 1);
        if (grade > 5) {
            LessonDAO ld = new LessonDAO();
            ld.AddScore(user_id, 10);
        }
        for (int i = 0; i < answers.size(); i++) {
            quesResultDAO.insertQuesResult(quesList.get(i), user_id, answers.get(i).equals(correctAnswers.get(i)), flags.get(i), answers.get(i), quizResultId);
        }
        CourseDAO cd = new CourseDAO();
        int courseId = cd.getCourseidFromQuiz(quiz_id);
        int passed = quizDAO.quizDone(courseId, user_id);
        int sum = quizDAO.quizSum(courseId);
        LessonDAO ld = new LessonDAO();
        int lessonCount = ld.LessonCount(courseId);
        int lessonDone = ld.LessonDone(user_id, courseId);
        if(passed >= sum && lessonDone >= lessonCount){
            cd.DoneCourse(user_id, courseId);
        }
        
        request.setAttribute("quiz_result_id", quizResultId);
        request.getRequestDispatcher("QuizReview").forward(request, response);
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
