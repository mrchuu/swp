/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.PublicFeature;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author FPT
 */
@WebServlet(name = "LeaderBoard", urlPatterns = {"/LeaderBoard"})
public class LeaderBoard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        int user_id = -1;
        User currUser = null;
        UserDAO ud = new UserDAO();
        if (cookies != null) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                    
                }
            }
        }
        UserDAO userDao = new UserDAO();
        HashMap<Integer, User> top3 = userDao.Top3UserLeaderBoard();
        Vector<User> StudentList = userDao.GetAllStudentSortedByScore();
        Integer[] key = new Integer[3];
        User[] value = new User[3];
        int index = 0;
        for (Map.Entry<Integer, User> entry : top3.entrySet()) {
            key[index] = entry.getKey();
            value[index] = entry.getValue();
            index++;
        }
        request.setAttribute("currUserId", user_id);
        request.setAttribute("key", key);
        request.setAttribute("value", value);
        request.setAttribute("StudentList", StudentList);
        request.getRequestDispatcher("LeaderBoard.jsp").forward(request, response);
    }

}
