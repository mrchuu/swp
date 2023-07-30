/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author FPT
 */
public class ManageCourse {
    Date startDate; 
    Date endDate;
    Course userCourse;
    boolean done;
    public ManageCourse(Date startDate, Date endDate, int course_id, String course_name, String course_img, float course_price, String course_desc, String last_update, int sub_id, int level_id, Boolean course_status, int duration, String CourseTitle) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userCourse = new Course(course_id, course_name, course_img, course_price, course_desc, last_update, sub_id, level_id, course_status, duration, CourseTitle);
    }
    public ManageCourse(Date startDate, Date endDate, int course_id, String course_name, String course_img, float course_price, String course_desc, String last_update, int sub_id, int level_id, Boolean course_status, int duration, String CourseTitle, boolean done) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.userCourse = new Course(course_id, course_name, course_img, course_price, course_desc, last_update, sub_id, level_id, course_status, duration, CourseTitle);
        this.done = done;
    }

    public ManageCourse(Date startDate, Date endDate, boolean done) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.done = done;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    public String getStartDateFormated(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        return sdf.format(this.getStartDate());
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getEndDateFormated(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
        return sdf.format(this.getEndDate());
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Course getUserCourse() {
        return userCourse;
    }

    public void setUserCourse(Course userCourse) {
        this.userCourse = userCourse;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
}
