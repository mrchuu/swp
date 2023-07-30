/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.AdminFeature;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import controller.LogginValidate;
import dao.MessageDAO;
import dao.UserDAO;
import entity.Message;
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
 * @author FPT
 */
@WebServlet(name = "AdminUserList", urlPatterns = {"/AdminUserList"})
public class AdminUserList extends HttpServlet {

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
            out.println("<title>Servlet AdminUserList</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminUserList at " + request.getContextPath() + "</h1>");
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
        Cookie[] cookies = request.getCookies();
        LogginValidate loggin = new LogginValidate();
        int logged = loggin.checkLoggedIn(cookies);
        UserDAO ud = new UserDAO();
        MessageDAO md = new MessageDAO();
        if (logged == 0) {
            response.sendRedirect("login");
        } else {
            User currUser = ud.getUserById(logged);
            if (currUser.getRoleId() == 1) {
                Vector<User> userList = ud.GetAllStudentSortedByScore();
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String json = gson.toJson(userList);
                request.setAttribute("userJson", json);
                request.getRequestDispatcher("AdminUserList.jsp").forward(request, response);
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
        Cookie[] cookies = request.getCookies();
        LogginValidate loggin = new LogginValidate();
        int logged = loggin.checkLoggedIn(cookies);
        UserDAO ud = new UserDAO();
        MessageDAO md = new MessageDAO();
        if (logged == 0) {
            response.sendRedirect("login");
        } else {
            User currUser = ud.getUserById(logged);
            if (currUser.getRoleId() == 1) {
                int user_id = Integer.parseInt(request.getParameter("user_id"));
                int statusChange = Integer.parseInt(request.getParameter("status"));
                int a = ud.BanUnbanUser(statusChange, user_id);
                response.getWriter().print(a);
            } else {
                response.sendRedirect("UnauthorizedAccess.jsp");
            }
        }
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
