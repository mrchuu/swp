/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import entity.Course;

/**
 *
 * @author admin
 */
public class LessonDto {
    int lesson_id;
    String lesson_name;
    String lesson_video;
    int section_id;
    String lesson_desc;
    boolean lesson_status;
    Course course;

    public LessonDto() {
    }

    public LessonDto(int lesson_id, String lesson_name, String lesson_video, int section_id, String lesson_desc, boolean lesson_status, Course course) {
        this.lesson_id = lesson_id;
        this.lesson_name = lesson_name;
        this.lesson_video = lesson_video;
        this.section_id = section_id;
        this.lesson_desc = lesson_desc;
        this.lesson_status = lesson_status;
        this.course = course;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public String getLesson_name() {
        return lesson_name;
    }

    public void setLesson_name(String lesson_name) {
        this.lesson_name = lesson_name;
    }

    public String getLesson_video() {
        return lesson_video;
    }

    public void setLesson_video(String lesson_video) {
        this.lesson_video = lesson_video;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public String getLesson_desc() {
        return lesson_desc;
    }

    public void setLesson_desc(String lesson_desc) {
        this.lesson_desc = lesson_desc;
    }

    public boolean isLesson_status() {
        return lesson_status;
    }

    public void setLesson_status(boolean lesson_status) {
        this.lesson_status = lesson_status;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
}
