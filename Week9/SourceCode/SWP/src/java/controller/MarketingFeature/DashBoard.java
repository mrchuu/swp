/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.MarketingFeature;

import dao.CourseDAO;
import dao.SubscriptionDAO;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author FPT
 */
public class DashBoard extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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
            if (currUser.getRoleId() != 4 && currUser.getRoleId() != 1) {
                response.sendRedirect("UnauthorizedAccess.jsp");
            } else {
                String sortTypePop = request.getParameter("sortTypePop");
                String sortTypePar = request.getParameter("sortTypePar");
                String sortTypeRevenue = request.getParameter("sortTypeRevenue");
                String rawDate = request.getParameter("DateInput");
                String rawYear = null;
                String rawMonth = null;
                if (rawDate != null) {
                    rawYear = rawDate.substring(0, 4);
                    rawMonth = rawDate.substring(5, 7);
                }

                LocalDate ld = LocalDate.now();
                int year = 0;
                int month = 0;
                if (sortTypePop == null) {
                    sortTypePop = "most";
                }
                if (sortTypePar == null) {
                    sortTypePar = "most";
                }
                if (rawYear == null) {
                    year = ld.getYear();
                    month = ld.getMonthValue();
                } else {
                    year = Integer.valueOf(rawYear);
                    if (rawMonth == null) {
                        month = 12;
                    } else {
                        month = Integer.parseInt(rawMonth);
                    }
                }

                HttpSession session = request.getSession();
                session.setAttribute("sort_type", sortTypePop);
                session.setAttribute("sort_typePar", sortTypePar);
                session.setAttribute("DateInput", rawDate);

                CourseDAO cd = new CourseDAO();

                SubscriptionDAO sd = new SubscriptionDAO();
                Map<String, Integer> UserOnProvinceMap = ud.getDashBoardDataPop(sortTypePop);
                Map<String, Integer> CourseParticipantMap = cd.getDashBoardDataPar(sortTypePar);
                Map<Integer, Integer> RevenueByMonth = sd.GetSubScriptionDashBoardData(sortTypeRevenue, year, month);
                int i = 0;
                int j = 0;
                int k = 0;
                String key[] = new String[5];
                Integer values[] = new Integer[5];
                String key1[] = new String[5];
                Integer values1[] = new Integer[5];
                Integer key2[] = new Integer[month];
                Integer values2[] = new Integer[month];
                for (Map.Entry<String, Integer> entry : UserOnProvinceMap.entrySet()) {
                    key[i] = entry.getKey();
                    values[i] = entry.getValue();
                    i++;
                }
                for (Map.Entry<String, Integer> entry : CourseParticipantMap.entrySet()) {
                    key1[j] = entry.getKey();
                    values1[j] = entry.getValue();
                    j++;
                }
                for (Map.Entry<Integer, Integer> entry : RevenueByMonth.entrySet()) {
                    key2[k] = entry.getKey();
                    values2[k] = entry.getValue();
                    k++;
//             out.print(entry.getKey()+"  "+entry.getValue()+"  /");
                }
                for (int a = 0; a < key2.length; a++) {
                    out.print(key2[a] + "  " + values2[a] + "  /");
                }

                request.setAttribute("key1", key1);
                request.setAttribute("values1", values1);
                request.setAttribute("key", key);
                request.setAttribute("values", values);
                request.setAttribute("key2", key2);
                request.setAttribute("values2", values2);
                request.getRequestDispatcher("DashBoard.jsp").forward(request, response);

            }
        }
    }

}
