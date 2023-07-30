/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CourseDAO;
import dao.UserDAO;
import entity.User;
import jakarta.servlet.http.Cookie;

/**
 *
 * @author FPT
 */
public class LogginValidate {
    public int checkLoggedIn(Cookie[] cookies){
        
        int user_id = 0;
        UserDAO ud = new UserDAO();
        User currUser = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                    
                }
            }
        }
        return user_id;
    }
}
