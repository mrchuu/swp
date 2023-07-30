/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import dao.GenderDAO;
import dao.ProvinceDAO;
import dao.UserDAO;
import dto.UserEditProfileDto;
import entity.Gender;
import entity.Province;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author ACER
 */
@WebServlet(name = "editProfile", urlPatterns = {"/editProfile"})
public class editProfile extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();
    private GenderDAO gd = new GenderDAO();
    private ProvinceDAO pd = new ProvinceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        int user_id = 0;
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

        int uid = Integer.parseInt(request.getParameter("uid"));
        if (user_id == 0) {
            response.sendRedirect("login");
        } else {
            if (uid != user_id) {
                response.sendRedirect("UnauthorizedAccess.jsp");
            } else {
                User user = userDAO.getUserById(uid);
                Vector<Province> provinceList = pd.getAllProvince();
                Vector<Gender> genderList = gd.getAllGender();

                request.setAttribute("genderList", genderList);
                request.setAttribute("provinceList", provinceList);
                request.setAttribute("currUser", user);
                request.getRequestDispatcher("EditProfile.jsp").forward(request, response);

            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        Pattern phoneRegex = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
        String phoneErr = "";

        int uid = Integer.parseInt(request.getParameter("uid"));
        String fname = request.getParameter("fullname");
        int gender = Integer.parseInt(request.getParameter("gender"));
        Date dob = Date.valueOf(request.getParameter("dob"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        if (!phoneRegex.matcher(phone).find()) {
            phoneErr = "Invalid phone number!";
            session.setAttribute("phoneErr", phoneErr);
            response.sendRedirect("editProfile?uid=" + uid);
            return;
        }

        UserEditProfileDto user = new UserEditProfileDto(uid, fname, gender, dob, phone, address);

        boolean f = userDAO.editProfile(user);

        if (f) {
            session.setAttribute("msgSuccess", "Update Thanh Cong!");
            response.sendRedirect("editProfile?uid=" + uid);
            return;
        }

        out.print("Edit Fail");

    }
}
