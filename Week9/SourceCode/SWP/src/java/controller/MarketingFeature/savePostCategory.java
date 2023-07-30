/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.MarketingFeature;

import dao.PostCategoryDAO;
import entity.PostCategory;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Vector;

/**
 *
 * @author ACER
 */
@WebServlet(name = "savePostCategory", urlPatterns = {"/savePostCategory"})
public class savePostCategory extends HttpServlet {

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
            out.println("<title>Servlet savePostCategory</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet savePostCategory at " + request.getContextPath() + "</h1>");
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
        // Get the updated data from the request
        PrintWriter out = response.getWriter();

        Enumeration<String> paramNames = request.getParameterNames();
        Vector<String> name = new Vector<>();
        Vector<String> note = new Vector<>();
        
        

        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            if (paramName.startsWith("name")) {
                String n = request.getParameter(paramName);
                name.add(n);
            }
            if (paramName.startsWith("note")) {
                String n = request.getParameter(paramName);
                note.add(n);
            }
        }
        
        PostCategoryDAO blogDAO = new PostCategoryDAO();
        Vector<PostCategory> blogList = blogDAO.getAll();
        for (int i = 0; i < blogList.size(); i++) {
            blogDAO.Update(blogList.get(i).getBlog_id(), name.get(i), note.get(i));
        }
        response.sendRedirect("postCategoryEdit");
    }

    /**
     * Returns a short note of the servlet.
     *
     * @return a String containing servlet note
     */
    @Override
    public String getServletInfo() {
        return "Short note";
    }// </editor-fold>

}
