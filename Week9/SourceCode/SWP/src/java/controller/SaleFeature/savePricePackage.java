/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.SaleFeature;

import controller.LogginValidate;
import dao.PricePackageDAO;
import dao.UserDAO;
import entity.Price_Package;
import entity.User;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author ACER
 */
@WebServlet(name = "savePricePackage", urlPatterns = {"/savePricePackage"})
public class savePricePackage extends HttpServlet {

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
        // Get the updated data from the request
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
                PrintWriter out = response.getWriter();

                Enumeration<String> paramNames = request.getParameterNames();
                Vector<String> name = new Vector<>();
                Vector<Integer> duration = new Vector<>();
                String[] status = request.getParameterValues("status");
                Vector<Float> price = new Vector<>();
                Vector<String> description = new Vector<>();

                while (paramNames.hasMoreElements()) {
                    String paramName = paramNames.nextElement();
                    if (paramName.startsWith("name")) {
                        String n = request.getParameter(paramName);
                        name.add(n);
                    }
                    if (paramName.startsWith("duration")) {
                        int n = Integer.parseInt(request.getParameter(paramName));
                        duration.add(n);
                    }
                    if (paramName.startsWith("status")) {

                    }
                    if (paramName.startsWith("price")) {
                        float n = Float.parseFloat(request.getParameter(paramName));
                        price.add(n);
                    }
                    if (paramName.startsWith("description")) {
                        String n = request.getParameter(paramName);
                        description.add(n);
                    }
                }

                PricePackageDAO pricePackageDAO = new PricePackageDAO();
                Vector<Price_Package> pricePackageList = pricePackageDAO.getAll();
                for (int i = 0; i < pricePackageList.size(); i++) {
                    boolean active = false;
                    if (status != null) {
                        for (int j = 0; j < status.length; j++) {
                            if (String.valueOf(pricePackageList.get(i).getPackage_id()).equals(status[j])) {
                                active = true;
                                break;
                            }
                        }
                    }
                    pricePackageDAO.updatePricePackage(pricePackageList.get(i).getPackage_id(), name.get(i), duration.get(i), active, price.get(i), description.get(i));
                }
                response.sendRedirect("pricePackageEdit");
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
