/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Vector;

/**
 *
 * @author FPT
 */
public class Section {
    int Section_id;
    int course_id;
    String section_name;
    int section_status;
    Vector<Lesson> lessonList = new Vector<>();
    Vector<Quiz> QuizList = new Vector<>();

    public Section(int Section_id, int course_id, String section_name, int section_status) {
        this.Section_id = Section_id;
        this.course_id = course_id;
        this.section_name = section_name;
        this.section_status = section_status;
    }

    

    public int getSection_id() {
        return Section_id;
    }

    public void setSection_id(int Section_id) {
        this.Section_id = Section_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public int getSection_status() {
        return section_status;
    }

    public void setSection_status(int section_status) {
        this.section_status = section_status;
    }

    public Vector<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(Vector<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public Vector<Quiz> getQuizList() {
        return QuizList;
    }

    public void setQuizList(Vector<Quiz> QuizList) {
        this.QuizList = QuizList;
    }
    
}
