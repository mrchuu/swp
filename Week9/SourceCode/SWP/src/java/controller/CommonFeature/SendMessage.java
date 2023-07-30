/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import dao.MessageDAO;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author FPT
 */
@WebServlet(name = "SendMessage", urlPatterns = {"/SendMessage"})
public class SendMessage extends HttpServlet {

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
        if (user_id == 0) {
            response.sendRedirect("login");
        } else {
            int senderId = currUser.getUserId();
            User sender = ud.getUserById(senderId);
            User receiver = ud.getUserByEmail(request.getParameter("receiver"));
            if (sender.isUserStatus() == false && receiver.getRoleId() != 1) {
                out.print("0");
            } else {
                String content = request.getParameter("content");
                MessageDAO md = new MessageDAO();
                md.SendMessage(senderId, receiver.getUserId(), content);
                out.print("1");
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
        if (user_id == 0) {
            response.sendRedirect("login");
        } else {
            int senderId = currUser.getUserId();
            User sender = ud.getUserById(senderId);
            User receiver = ud.getUserByEmail(request.getParameter("receiver"));
            if (sender.isUserStatus() == false && receiver.getRoleId() != 1) {
                response.getWriter().print("0");
            } else {
                String content = request.getParameter("content");
                MessageDAO md = new MessageDAO();
                md.SendMessage(senderId, receiver.getUserId(), content);
                response.getWriter().print("1");
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
