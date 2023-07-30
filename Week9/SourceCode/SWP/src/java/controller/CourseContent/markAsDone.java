/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.CourseContent;

import dao.CourseDAO;
import dao.LessonDAO;
import dao.QuizDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author FPT
 */
@WebServlet(name="markAsDone", urlPatterns={"/markAsDone"})
public class markAsDone extends HttpServlet {
   
    
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int userId = Integer.parseInt(request.getParameter("userId")); 
        int lessonId  = Integer.parseInt(request.getParameter("lessonId"));
        
        LessonDAO ld = new LessonDAO();
        ld.AddScore(userId, 5);
        ld.markasDone(userId, lessonId);
        CourseDAO cd = new CourseDAO();
        QuizDAO quizDAO = new QuizDAO();
        int courseId = cd.getCourseidFromLeson(lessonId);
        int passed = quizDAO.quizDone(courseId, userId);
        int sum = quizDAO.quizSum(courseId);
        int lessonCount = ld.LessonCount(courseId);
        int lessonDone = ld.LessonDone(userId, courseId);
        if(passed >= sum && lessonDone >= lessonCount){
            cd.DoneCourse(userId, courseId);
        }
        
    }

   

}
