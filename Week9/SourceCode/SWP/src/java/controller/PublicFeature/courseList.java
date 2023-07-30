/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.PublicFeature;

import dao.CourseDAO;
import dao.SubjectDAO;
import entity.Course;
import entity.Subject;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 *
 * @author ACER
 */
public class courseList extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Search name
        String search = request.getParameter("search");

        // Sort type
        String sort_type = request.getParameter("sort_type");
        System.out.println(sort_type);
        if (sort_type == null) {
            if (session.getAttribute("sort_type") != null) {
                sort_type = (String) session.getAttribute("sort_type");
            } else {
                sort_type = "recent";
            }
        }
        session.setAttribute("sort_type", sort_type);

        // Sub_id to display
        String subIdString = request.getParameter("sub_id");
        int sub_id;
        if (subIdString != null) {
            sub_id = Integer.parseInt(subIdString);
        } else {
            if (session.getAttribute("sub_id") != null) {
                sub_id = (int) session.getAttribute("sub_id");
            } else {
                sub_id = 0;
            }
        }

        session.setAttribute("sub_id", sub_id);

        // Current page display
        String currentPageString = request.getParameter("currentPage");
        int currentPage;
        if (currentPageString == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(currentPageString);
        }

        int recordsPerPage = 9;

        CourseDAO courseDAO = new CourseDAO();
        List<Course> courseToDisplay = new ArrayList<>();

        SubjectDAO subjectDAO = new SubjectDAO();
        List<Subject> subjectList = subjectDAO.getAll();
        PrintWriter out = response.getWriter();

        request.setAttribute("subjectList", subjectList);

        List<Course> courseList;

        int totalRecords = 0;
        int totalPages;
        // Get courseList by search and sub_id
        if (search == null) {
            if ((int) session.getAttribute("sub_id") == 0) {
                courseToDisplay = courseDAO.getAllCoursewithPagination((currentPage - 1) * 9, 9, sort_type);
                totalRecords = courseDAO.getTotalNumber(0, search);
            } else {
                courseToDisplay = courseDAO.getCourseBySubId(sub_id, (currentPage - 1) * 9, 9, sort_type);
                totalRecords = courseDAO.getTotalNumber(sub_id, search);
            }

        } else {
            courseToDisplay = courseDAO.searchByName(search, sort_type, (currentPage - 1) * 9);
            totalRecords = courseDAO.getTotalNumber(0, search);
        }
        if (sort_type.equalsIgnoreCase("Mostparticipant")) {
            courseToDisplay = courseDAO.SortCoursesByParRate((currentPage - 1) * 9, 9, (int) session.getAttribute("sub_id"), search);
            totalRecords = courseDAO.getTotalNumber(0, null);


        }
        totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);
        request.setAttribute("courseToDisplay", courseToDisplay);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        RequestDispatcher rd = request.getRequestDispatcher("CourseList.jsp");
        rd.forward(request, response);
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
