/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.MarketingFeature;

import dao.PostDAO;
import entity.Post;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
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
@WebServlet(name = "addOrUpdatePost", urlPatterns = {"/addOrUpdatePost"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class addOrUpdatePost extends HttpServlet {

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
        String title = request.getParameter("post_title");
        String description = request.getParameter("post_des");
        int blog_id = Integer.parseInt(request.getParameter("blog_id"));
        Boolean update = Boolean.parseBoolean(request.getParameter("update"));
        // Get post image
        Part filePart = null;
        filePart = request.getPart("post_image");
        String saveDirectory = request.getServletContext().getRealPath("") + "/img/";
        String fileName = "";
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
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
        PostDAO postDAO = new PostDAO();
        if (update) {
            int id = Integer.parseInt(request.getParameter("post_id"));
            // update post
            if (fileName.equals("tempAvatar.jpg")) {
                Post currPost = postDAO.searchById(id);
                postDAO.updatePost(id, currPost.getPost_img(), title, description, currentDate, true, blog_id);
            } else {
                postDAO.updatePost(id, sqlFilePath, title, description, currentDate, true, blog_id);
            }

        } else {
            // addPost
            postDAO.addPost(sqlFilePath, title, description, currentDate, true, blog_id);
        }

        response.sendRedirect("postListEdit");
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
