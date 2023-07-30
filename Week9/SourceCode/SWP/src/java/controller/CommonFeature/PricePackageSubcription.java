/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import dao.PricePackageDAO;
import dao.SubscriptionDAO;
import dao.UserDAO;
import entity.Price_Package;
import entity.Subscription;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

import java.util.Calendar;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class PricePackageSubcription extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Cookie[] cookies = request.getCookies();
        int user_id = 0;
        User currUser = null;
        UserDAO ud = new UserDAO();
        boolean inSession = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                    request.setAttribute("currUser", currUser);
                    PricePackageDAO ppd = new PricePackageDAO();
                    SubscriptionDAO sd = new SubscriptionDAO();
                    Subscription currentSubscription = sd.GetCurrentSubscription(user_id);
                    if (currentSubscription != null && currentSubscription.isStatus() == true) {
                        request.setAttribute("currentSubscription", currentSubscription);
                    }
                    Vector<Price_Package> packageList = ppd.getAll();
                    request.setAttribute("packageList", packageList);
                    inSession = true;
                    break;

                }
            }
            if (inSession) {
                request.getRequestDispatcher("PackageSubcribe.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int packageId = Integer.parseInt(request.getParameter("packageId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int packagePrice = Integer.parseInt(request.getParameter("price"));
        int packageDuration = Integer.parseInt(request.getParameter("duration"));
        LocalDate ld = LocalDate.now();
        Date reg_date = Date.valueOf(ld);
        Date expireDate;
        if(packageDuration > 6){
            expireDate = Date.valueOf(ld.plusYears(10));
        }else{
            expireDate = Date.valueOf(ld.plusMonths(packageDuration));
        }
        
        SubscriptionDAO sd = new SubscriptionDAO();
        int a = sd.addSubcription(userId, packageId, reg_date, expireDate);
        UserDAO ud = new UserDAO();
        ud.handleTransaction(userId, packagePrice);
        out.println(a);
    }

}
