/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.CommonFeature;

import dao.CourseDAO;
import dao.SubjectDAO;
import dao.UserDAO;
import entity.Course;
import entity.ManageCourse;
import entity.Subject;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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
 * @author FPT
 */
public class MyCourseListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        int user_id = 0;
        Cookie[] cookies = request.getCookies();
        User currUser = null;
        UserDAO ud = new UserDAO();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                    request.setAttribute("currUser", currUser);
                }
            }
        }

        if (user_id == 0) {
            response.sendRedirect("login.jsp");
            return;
        } else {
            if (currUser.getRoleId() == 2 || currUser.getRoleId() == 3) {
                CourseDAO cd = new CourseDAO();
                SubjectDAO sd = new SubjectDAO();

                String subIdRaw = request.getParameter("sub_id");
                // Search name
                String search = request.getParameter("search");

                // Sort type
                HttpSession session = request.getSession();
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

                List<Subject> subjectList = sd.getAll();
                request.setAttribute("subjectList", subjectList);

                Vector<ManageCourse> myCourses = cd.getmyCourseList(user_id, subIdRaw, search, sort_type);
                out.print("catch");
                request.setAttribute("myCourses", myCourses);

               
                out.print(myCourses.size());

                request.getRequestDispatcher("MyCourseList.jsp").forward(request, response);
            }else{
                response.sendRedirect("UnauthorizedAccess.jsp");
            }
        }

    }

}
