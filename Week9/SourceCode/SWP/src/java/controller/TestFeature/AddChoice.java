/* 
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TestFeature;

import com.google.gson.JsonObject;
import controller.LecturerValidator;
import dao.CourseDAO;
import dao.QuizDAO;
import dao.UserDAO;
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
@WebServlet(name = "AddChoice", urlPatterns = {"/AddChoice"})
public class AddChoice extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int user_id = 0;
        UserDAO ud = new UserDAO();
        CourseDAO cd = new CourseDAO();
        Cookie[] cookies = request.getCookies();
        int QuizId = Integer.parseInt(request.getParameter("Quizid"));
        Integer quesId = Integer.parseInt(request.getParameter("quesId"));
        int courseId = cd.getCourseidFromQuiz(QuizId);
        LecturerValidator lv = new LecturerValidator();
        int val = lv.val1(cookies, QuizId);
        if (val == 0) {
            response.sendRedirect("login");
        } else if (val == 1) {
            response.sendRedirect("UnauthorizedAccess.jsp");
        } else {
            QuizDAO qd = new QuizDAO();
            qd.insertChoice(quesId, "nhập nội dung câu trả lời", false);
            int maxChoiceId = qd.retrieveMaxChoiceId();
            JsonObject responseJson = new JsonObject();
            responseJson.addProperty("maxChoiceId", maxChoiceId);
            responseJson.addProperty("defaultChoiceContent", "Nhập nội dung tại đây");
            response.setContentType("application/json");

// Write the JSON object as the response
            response.getWriter().write(responseJson.toString());
        }

    }

}
