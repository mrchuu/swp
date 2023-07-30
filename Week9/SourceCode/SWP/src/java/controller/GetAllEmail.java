/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Vector;

/**
 *
 * @author FPT
 */
@WebServlet(name = "GetAllEmail", urlPatterns = {"/GetAllEmail"})
public class GetAllEmail extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO ud = new UserDAO();
        Vector<User> allU = ud.GetAllUser();
        String[] allEmail = new String[allU.size()];
        int i = 0;
        for (User u : allU) {
            allEmail[i] = u.getUserEmail();
            i++;
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String emailList = String.join(",", allEmail);
        response.getWriter().write(emailList);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO ud = new UserDAO();
        Vector<User> allU = ud.GetAllUser();
        String[] allEmail = new String[allU.size()];
        int i = 0;
        for (User u : allU) {
            allEmail[i] = u.getUserEmail();
            i++;
        }
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        String emailList = String.join(",", allEmail);
        response.getWriter().write(emailList);
    }

}
