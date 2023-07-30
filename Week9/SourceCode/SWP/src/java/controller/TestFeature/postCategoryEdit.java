/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TestFeature;

import controller.LogginValidate;
import dao.PostCategoryDAO;
import dao.PricePackageDAO;
import dao.UserDAO;
import entity.PostCategory;
import entity.Price_Package;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author ACER
 */
@WebServlet(name = "postCategoryEdit", urlPatterns = {"/postCategoryEdit"})
public class postCategoryEdit extends HttpServlet {

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
            out.println("<title>Servlet postCategoryEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet postCategoryEdit at " + request.getContextPath() + "</h1>");
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

        LogginValidate logginVal = new LogginValidate();
        Cookie[] cookies = request.getCookies();
        User currUser = null;
        UserDAO ud = new UserDAO();
        int logged = logginVal.checkLoggedIn(cookies);
        if (logged == 0) {
            response.sendRedirect("login");
        } else {
            currUser = ud.getUserById(logged);
            if (currUser.getRoleId() == 1 || currUser.getRoleId() == 4) {
                PostCategoryDAO blogDAO = new PostCategoryDAO();
                Vector<PostCategory> categoryList = blogDAO.getAll();
                request.setAttribute("categoryList", categoryList);
                request.getRequestDispatcher("PostCategoryEdit.jsp").forward(request, response);
            } else {
                response.sendRedirect("UnauthorizedAccess.jsp");
            }
        }

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
        processRequest(request, response);
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
