/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.MarketingFeature;

import dao.CourseDAO;
import dao.QuizResultDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author FPT
 */
@WebServlet(name = "savePostCategory", urlPatterns = {"/dashboardQuizResult"})
public class DashBoardQuizResult extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String sortTypePop = request.getParameter("sortTypePop");
        int quizId = Integer.parseInt(request.getParameter("quizId"));
        if(request.getParameter("quizId") == null) {
            quizId = 1;
        }
        if (sortTypePop == null) {
            sortTypePop = "most";
        }
        HttpSession session = request.getSession();
        session.setAttribute("sort_type", sortTypePop);
        session.setAttribute("quizId", quizId);
        QuizResultDAO qr = new QuizResultDAO();
        Map<String, Integer> quizResultMap = qr.getDashBoardDataPop(sortTypePop, quizId);
        
        String[] key = new String[quizResultMap.size()];
        Integer[] values = new Integer[quizResultMap.size()];
        int i = 0;
        for (Map.Entry<String, Integer> entry : quizResultMap.entrySet()) {
            key[i] = entry.getKey();
            values[i] = entry.getValue();
            i++;
        }
        
        request.setAttribute("key", key);
        request.setAttribute("values", values);
        request.getRequestDispatcher("DashBoardQuizResult.jsp").forward(request, response);
        
    }
    
}
