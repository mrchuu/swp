/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import controller.AES;
import controller.CommonFeature.MailSender;
import dao.UserDAO;
import entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Random;

/**
 *
 * @author admin
 */
public class ResetPasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("resetPassword.jsp").forward(request, response);

    }

    public String getRandomPassword() {
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789"
                + "abcdefghijklmnopqrstuvxyz";

        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(8);

        for (int i = 0; i < 8; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int) (AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of sb
            sb.append(AlphaNumericString
                    .charAt(index));
        }

        return sb.toString();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        final String secretKey = "a/f/gr'fw=q-=d-";
        String email = request.getParameter("email");

        UserDAO ud = new UserDAO();

        User checkEmail = ud.getUserByEmail(email);

        if (checkEmail == null) {
            request.setAttribute("err", "Email does not exist!");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }
        MailSender ms = new MailSender();
        String randPassword = getRandomPassword();

        boolean f = ud.changePass(checkEmail.getUserEmail(), AES.encrypt(randPassword, secretKey));

        if (!f) {
            request.setAttribute("err", "Wrong when Reset password!");
            request.getRequestDispatcher("resetPassword.jsp").forward(request, response);
            return;
        }
        ms.sendEmail(email, "Your password has been successfully reseted", "Your new password is "+randPassword+", login to your account with this password and change your password at edit profile");
        request.setAttribute("success", "Sucess");
        request.getRequestDispatcher("resetPassword.jsp").forward(request, response);

    }
}
