/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CourseDAO;
import dao.LessonDAO;
import dao.UserDAO;
import entity.User;
import jakarta.servlet.http.Cookie;

/**
 *
 * @author FPT
 */
public class LecturerValidator {

    public int val(Cookie[] cookies, int lessonId, int course_id) {
        int user_id = 0;
        UserDAO ud = new UserDAO();
        CourseDAO cd = new CourseDAO();
        User currUser = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                }
            }
        }
        if (user_id == 0) {
            return 0;
        } else {
            //validate if the user is a teacher
            if (currUser.getRoleId() == 3) {
                //validate if the user is the publisher
                if (lessonId != -1) {
                    int courseId = cd.getCourseidFromLeson(lessonId);
                    int author = cd.getCoursePublisher(courseId);
                    if (author != currUser.getUserId()) {
                        return 1;
                    } else {
                        return 2;
                    }
                } else {
                    if (course_id != -1) {
                        int author = cd.getCoursePublisher(course_id);
                        if (author != currUser.getUserId()) {
                            return 1;
                        } else {

                            return 2;
                        }
                    }else{
                        return 2;
                    }

                }

            } else {
                return 1;
            }
        }
    }

    public int val1(Cookie[] cookies, int quizId) {
        int user_id = 0;
        UserDAO ud = new UserDAO();
        CourseDAO cd = new CourseDAO();
        User currUser = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("currUserId")) {
                    user_id = Integer.parseInt(cookie.getValue());
                    currUser = ud.getUserById(user_id);
                }
            }
        }
        if (user_id == 0) {
            return 0;
        } else {
            if (currUser.getRoleId() == 3) {

                int courseId = cd.getCourseidFromQuiz(quizId);
                int author = cd.getCoursePublisher(courseId);
                if (author != currUser.getUserId()) {
                    return 1;
                } else {
                    return 2;
                }

            } else {
                return 1;
            }
        }
    }
}
