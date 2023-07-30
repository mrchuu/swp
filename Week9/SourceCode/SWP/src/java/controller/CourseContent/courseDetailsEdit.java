/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CourseContent;

import dao.CourseDAO;
import dao.LevelDAO;
import dao.SubjectDAO;
import entity.Course;
import entity.Level;
import entity.Subject;
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
 * @author ACER
 */
@WebServlet(name = "courseDetailsEdit", urlPatterns = {"/courseDetailsEdit"})
public class courseDetailsEdit extends HttpServlet {

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
            out.println("<title>Servlet courseDetailsEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet courseDetailsEdit at " + request.getContextPath() + "</h1>");
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
        String courseIdString = request.getParameter("course_id");
        String pageType = request.getParameter("type");
        
        // Get subject list
        SubjectDAO subjectDAO = new SubjectDAO();
        Vector<Subject> subList = subjectDAO.getAll();
        request.setAttribute("subList", subList);
        
        // Get level list
        LevelDAO levelDAO = new LevelDAO();
        Vector<Level> levelList = levelDAO.getAll();
        request.setAttribute("levelList", levelList);
        
        if (pageType.equals("edit")) {
            int course_id = Integer.parseInt(courseIdString);
            CourseDAO courseDAO = new CourseDAO();
            Course course = courseDAO.searchById(course_id);
            request.setAttribute("course", course);
            request.setAttribute("update", true);
            request.getRequestDispatcher("CourseDetailEdit.jsp").forward(request, response);
        }
        else if(pageType.equals("add")){
            request.setAttribute("update", false);
            request.getRequestDispatcher("CourseDetailEdit.jsp").forward(request, response);
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
