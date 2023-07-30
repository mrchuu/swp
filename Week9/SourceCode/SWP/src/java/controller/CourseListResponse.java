/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import entity.Course;
import java.util.List;

/**
 *
 * @author FPT
 */
public class CourseListResponse {

    List<Course> courseToDisplay;
    int totalPages;
    int currentPage;

    public CourseListResponse(List<Course> courseToDisplay, int totalPages, int currentPage) {
        this.courseToDisplay = courseToDisplay;
        this.totalPages = totalPages;
        this.currentPage = currentPage;
    }
}
