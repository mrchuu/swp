/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CourseContent;

import controller.LecturerValidator;
import dao.CourseDAO;
import dao.LessonDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author ACER
 */
@WebServlet(name = "addOrUpdateCourse", urlPatterns = {"/addOrUpdateCourse"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class addOrUpdateCourse extends HttpServlet {

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
            out.println("<title>Servlet updateCourse</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet updateCourse at " + request.getContextPath() + "</h1>");
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
        CourseDAO cd = new CourseDAO();
        Cookie[] cookies = request.getCookies();
        String name = request.getParameter("course_name");
        String title = request.getParameter("course_title");
        String description = request.getParameter("course_des");
        int sub_id = Integer.parseInt(request.getParameter("sub_id"));
        int level_id = Integer.parseInt(request.getParameter("level_id"));
        int price = Integer.parseInt(request.getParameter("price"));
        boolean active = Boolean.parseBoolean(request.getParameter("active"));
        int duration = Integer.parseInt(request.getParameter("duration"));
        boolean update = Boolean.parseBoolean(request.getParameter("update"));

        // Get post image
        Part filePart = null;
        filePart = request.getPart("post_image");
        String saveDirectory = request.getServletContext().getRealPath("") + "/img/";
        String fileName;
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString() + System.currentTimeMillis();
        } else {
            fileName = "tempAvatar.jpg";
        }
        String filePath = saveDirectory + fileName;

        String sqlFilePath = "img/" + fileName;
        if (filePart != null && filePart.getSize() > 0) {
            InputStream fileContent = filePart.getInputStream();
            Files.copy(fileContent, Paths.get(filePath));
        }

        // Get current date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date(calendar.getTime().getTime());

        CourseDAO courseDAO = new CourseDAO();

        if (update) {
            int id = Integer.parseInt(request.getParameter("course_id"));
            LecturerValidator lv = new LecturerValidator();
            int val = lv.val(cookies, -1, id);
            if (val == 0) {
                response.sendRedirect("login");
            } else if (val == 1) {
                response.sendRedirect("UnauthorizedAccess.jsp");
            } else {
                courseDAO.updateCourse(id, name, sqlFilePath, price, description, currentDate, sub_id, level_id, active, duration, title);
            }
            // updateCourse

        } else {
            // addCourse
            LecturerValidator lv = new LecturerValidator();
            int val = lv.val(cookies, -1, -1);
            if (val == 0) {
                response.sendRedirect("login");
            } else if (val == 1) {
                response.sendRedirect("UnauthorizedAccess.jsp");
            } else {
                int course_id = courseDAO.addCourse(name, sqlFilePath, price, description, currentDate, sub_id, level_id, true, duration, title);
                int user_id = 0;

                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("currUserId")) {
                            user_id = Integer.parseInt(cookie.getValue());
                        }
                    }
                }
                courseDAO.addCourseToUser(course_id, user_id, currentDate, currentDate);
            }

        }
        response.sendRedirect("courseListEdit");

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
