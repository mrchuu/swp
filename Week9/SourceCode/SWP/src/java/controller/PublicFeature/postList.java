/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.PublicFeature;

import dao.PostCategoryDAO;
import dao.PostDAO;
import entity.Post;
import entity.PostCategory;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 *
 * @author ACER
 */
public class postList extends HttpServlet {

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

        // Blog_id to display
        String blogIdString = request.getParameter("blog_id");
        int blog_id;
        if (blogIdString != null) {
            blog_id = Integer.parseInt(blogIdString);
        } else {
            if (session.getAttribute("blog_id") != null) {
                blog_id = (int) session.getAttribute("blog_id");
            } else {
                blog_id = 1;
            }
        }

        session.setAttribute("blog_id", blog_id);

        // Current page display
        String currentPageString = request.getParameter("currentPage");
        int currentPage;
        if (currentPageString == null) {
            currentPage = 1;
        } else {
            currentPage = Integer.parseInt(currentPageString);
        }
        
        int recordsPerPage = 9;
        
        PostDAO postDAO = new PostDAO();
        
        Vector<Post> postToDisplay = new Vector<>();

        PostCategoryDAO blogDAO = new PostCategoryDAO();
        List<PostCategory> blogList = blogDAO.getAll();

        request.setAttribute("blogList", blogList);

        List<Post> postList;

        // Get postList by search and blog_id
        if (search == null) {
            postList = postDAO.getAll().stream().filter(s -> s.getBlog_id() == blog_id).collect(Collectors.toList());
        } else {
            postList = postDAO.searchByName(search).stream().filter(s -> s.getBlog_id() == blog_id).collect(Collectors.toList());
        }

        // Sort postList by name
        if (sort_type.compareTo("name") == 0) {
            Collections.sort(postList, new Comparator<Post>() {
                @Override
                public int compare(Post c1, Post c2) {
                    return c1.getPost_title().compareTo(c2.getPost_title());
                }
            });
        }
        // Sort postList by recent
        else if(sort_type.compareTo("recent") == 0){
            Collections.sort(postList, new Comparator<Post>() {
                @Override
                public int compare(Post c1, Post c2) {
                    return c1.getPost_date().compareTo(c2.getPost_date());
                }
            });
            Collections.reverse(postList);
        }

        for (int i = recordsPerPage * (currentPage - 1); i < recordsPerPage * currentPage && i < postList.size(); i++) {
            postToDisplay.add(postList.get(i));
        }

        int totalRecords = postList.size();
        int totalPages = (int) Math.ceil((double) totalRecords / recordsPerPage);

        request.setAttribute("postToDisplay", postToDisplay);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("currentPage", currentPage);

        RequestDispatcher rd = request.getRequestDispatcher("PostList.jsp");
        rd.forward(request, response);
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
