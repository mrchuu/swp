package controller.CommonFeature;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import controller.AES;
import dao.CourseDAO;
import dao.UserDAO;
import entity.Course;
import entity.ManageCourse;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class ChangePasswordController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("changePassword.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        final String secretKey = "a/f/gr'fw=q-=d-";
        String email = request.getParameter("email");
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        UserDAO ud = new UserDAO();

        int user_id = 0;
        Cookie[] cookies = request.getCookies();
        User currUser = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                }
            }
        }

        // Get course by user id
        CourseDAO courseDAO = new CourseDAO();

        if (user_id == 0) {
            response.sendRedirect("login");
        } else {
            if (!currUser.getUserEmail().equals(email)) {
                request.setAttribute("err", "Sai email!");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                return;

            } else {

                User checkEmail = ud.getUserByEmail(email);
                User checkEmailPassword = ud.login(email, AES.encrypt(oldPassword, secretKey));

                if (checkEmail == null) {
                    out.print("aaaaaa");
                    request.setAttribute("err", "Email does not exist!");
                    request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    return;
                }

                if (checkEmailPassword == null) {
                    request.setAttribute("err", "Wrong old password!");
                    request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    return;
                }
                String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
                String PasswordPatternErr = "Mật khẩu cần phải bao gồm cả chữ cái và số và có độ dài ít nhất là 6 kí tự";
                if (!newPassword.matches(passwordPattern)) {
                    request.setAttribute("err", PasswordPatternErr);
                    out.print(newPassword);
                    out.print("aaaaaa");
                    request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    return;
                }
                boolean f = ud.changePass(checkEmail.getUserEmail(), AES.encrypt(newPassword, secretKey));

                if (!f) {
                    request.setAttribute("err", "Wrong when Change password!");
                    request.getRequestDispatcher("changePassword.jsp").forward(request, response);
                    return;
                }

                request.setAttribute("success", "Sucess");
                request.getRequestDispatcher("changePassword.jsp").forward(request, response);
            }
        }

    }

}
