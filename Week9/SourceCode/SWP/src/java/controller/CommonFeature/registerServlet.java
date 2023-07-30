/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import controller.AES;
import dao.UserDAO;
import entity.User;
import java.io.*;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.*;
import jakarta.servlet.http.Part;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;


/**
 *
 * @author FPT
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class registerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        final String secretKey = "a/f/gr'fw=q-=d-";
        String email = request.getParameter("email");
        Pattern emailRegex = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        String emailerr = "";
        if (!emailRegex.matcher(email).find()) {
            emailerr = "Invalid Email !";
            request.setAttribute("emailerr", emailerr);
        }
        out.print(email);
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$";
        String PasswordPatternErr = "";
        out.print(password1);
        if(!password1.matches(passwordPattern)){
            PasswordPatternErr = "Mật khẩu cần phải bao gồm cả chữ cái và số và có độ dài ít nhất là 6 kí tự";
            request.setAttribute("PasswordPatternErr", PasswordPatternErr);
        }
        String passworderr = "";
        if (!password1.equals(password2)) {
            passworderr = "The re-entered password does not match the first one !";
            request.setAttribute("passworderr", passworderr);
        }
        String encryptedPassword = AES.encrypt(password2, secretKey);
        String fullname = request.getParameter("fullname");
        Part filePart = null;
        filePart = request.getPart("userImg");
        String saveDirectory = request.getServletContext().getRealPath("") + "/img/";
        String fileName;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString() + System.currentTimeMillis();
        } else {
            fileName = "tempAvatar.jpg";
        }
        String filePath = saveDirectory + fileName;

        String sqlFilePath = "img/" + fileName;

        String gender = request.getParameter("gender");
        String dobRaw = request.getParameter("dob");
        Date dob = Date.valueOf(dobRaw);
        LocalDate ldDob = LocalDate.parse(dobRaw);
        LocalDate currDate = LocalDate.now();
        Period age = Period.between(ldDob, currDate);
        out.print(age.getYears());
        String ageErr = "";
        if(age.getYears() < 13){
            ageErr = "Người dùng ít nhất phải 13 tuổi";
            request.setAttribute("ageErr", ageErr);
        }
        Pattern phoneRegex = Pattern.compile("(84|0[3|5|7|8|9])+([0-9]{8})\\b");
        String phone = request.getParameter("phone");
        String phoneErr = "";
        if (!phoneRegex.matcher(phone).find()) {
            phoneErr = "Invalid phone number!";
            request.setAttribute("phoneErr", phoneErr);
        }
        String address = request.getParameter("address");
        String role = request.getParameter("role");
        LocalDate ld = java.time.LocalDate.now();
        UserDAO ud = new UserDAO();
        Date userTime = Date.valueOf(ld);
        if (!phoneErr.isEmpty() || !emailerr.isEmpty() || !passworderr.isEmpty() || !ageErr.isEmpty() || !PasswordPatternErr.isEmpty()) {
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        } else if (ud.getUserByEmail(email) != null) {
            request.setAttribute("duplicateEmailErr", "Register failed, Duplicated email!");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            out.print("bbbbbbbbbb");
        } else {
            if (filePart != null && filePart.getSize() > 0) {
                InputStream fileContent = filePart.getInputStream();
                Files.copy(fileContent, Paths.get(filePath));
            }

            User newUser = new User(0, email, encryptedPassword, fullname, sqlFilePath, Integer.parseInt(gender), dob, phone, address, "0", Integer.parseInt(role), userTime, true, 0);
            ud.addNewUser(newUser);
            out.print(filePath);
            response.sendRedirect("homepage");
        }
    }

}
