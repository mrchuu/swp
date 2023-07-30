/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import controller.LogginValidate;
import dao.MessageDAO;
import dao.PricePackageDAO;
import dao.UserDAO;
import entity.Message;
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
 * @author FPT
 */
@WebServlet(name = "MailBox", urlPatterns = {"/MailBox"})
public class MailBox extends HttpServlet {

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
        User currUser = null;
        UserDAO ud = new UserDAO();
        LogginValidate logginVal = new LogginValidate();
        Cookie[] cookies = request.getCookies();
        MessageDAO md = new MessageDAO();
        int logged = logginVal.checkLoggedIn(cookies);
        Vector<Message> allMessages = md.getAllMessage();
        Vector<Message> displayList = new Vector<>();
        String mode = request.getParameter("mode");
        if (logged == 0) {
            response.sendRedirect("login");
        } else {
            currUser = ud.getUserById(logged);

            if (mode.equalsIgnoreCase("inbox")) {
                for (Message allMessage : allMessages) {
                    if (allMessage.getReceiverId() == logged) {
                        displayList.add(allMessage);
                        allMessage.setSender(ud.getUserById(allMessage.getSenderId()));
                        allMessage.setReceiver(ud.getUserById(allMessage.getReceiverId()));
                    }
                }

            } else if (mode.equalsIgnoreCase("sent")) {
                for (Message allMessage : allMessages) {
                    if (allMessage.getSenderId() == logged) {
                        displayList.add(allMessage);
                        allMessage.setSender(ud.getUserById(allMessage.getSenderId()));
                        allMessage.setReceiver(ud.getUserById(allMessage.getReceiverId()));
                    }
                }
            } else if (mode.equalsIgnoreCase("reported")) {
                for (Message allMessage : allMessages) {
                    if (allMessage.getReceiverId() == logged && allMessage.isReported()) {
                        displayList.add(allMessage);
                        allMessage.setSender(ud.getUserById(allMessage.getSenderId()));
                        allMessage.setReceiver(ud.getUserById(allMessage.getReceiverId()));
                    }
                }
            } else if (mode.equalsIgnoreCase("marked")) {
                for (Message allMessage : allMessages) {
                    if (allMessage.getReceiverId() == logged && allMessage.isMarked()) {
                        displayList.add(allMessage);
                        allMessage.setSender(ud.getUserById(allMessage.getSenderId()));
                        allMessage.setReceiver(ud.getUserById(allMessage.getReceiverId()));
                    }
                }
            }
            request.setAttribute("mode", mode);
            request.setAttribute("displayList", displayList);
            request.getRequestDispatcher("MailBox.jsp").forward(request, response);

        }

    }
}
