/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import controller.AES;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author admin
 */
public class LoginController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("login.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        final String secretKey = "a/f/gr'fw=q-=d-";
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        UserDAO ud = new UserDAO();
        String decryptedPassword = AES.encrypt(password, secretKey);
        User userLogin = ud.login(email, decryptedPassword);

        if (userLogin != null) {
            // Set user da login vao cookie
            Cookie userCookie = new Cookie("currUserId", String.valueOf(userLogin.getUserId()));
            // Thoi gian cookie ton tai
            int maxAge = 60 * 60 * 3; // 24 hours in seconds
            userCookie.setMaxAge(maxAge);
            response.addCookie(userCookie);
            response.sendRedirect("homepage");
        } else {
            request.setAttribute("err", "Sai email hoặc mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }
    }

}
