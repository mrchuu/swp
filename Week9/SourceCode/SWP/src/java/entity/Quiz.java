/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Vector;

/**
 *
 * @author Uslaptop
 */
public class Quiz {

    int quiz_id;
    String quiz_name;
    String quiz_desc;
    int section_id;
    boolean quiz_status;
    Vector<Question> quesList;

    public Quiz() {
    }
     public Quiz(int quiz_id, String quiz_name, String quiz_desc, int section_id, boolean quiz_status) {
        this.quiz_id = quiz_id;
        this.quiz_name = quiz_name;
        this.quiz_desc = quiz_desc;
        this.section_id = section_id;
        this.quiz_status = quiz_status;
    }
    public Quiz(int quiz_id, String quiz_name, String quiz_desc, int section_id, boolean quiz_status, Vector<Question> quesList) {
        this.quiz_id = quiz_id;
        this.quiz_name = quiz_name;
        this.quiz_desc = quiz_desc;
        this.section_id = section_id;
        this.quiz_status = quiz_status;
        this.quesList = quesList; 
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public String getQuiz_name() {
        return quiz_name;
    }

    public void setQuiz_name(String quiz_name) {
        this.quiz_name = quiz_name;
    }

    public String getQuiz_desc() {
        return quiz_desc;
    }

    public void setQuiz_desc(String quiz_desc) {
        this.quiz_desc = quiz_desc;
    }

    public int getSection_id() {
        return section_id;
    }

    public void setSection_id(int section_id) {
        this.section_id = section_id;
    }

    public boolean isQuiz_status() {
        return quiz_status;
    }

    public void setQuiz_status(boolean quiz_status) {
        this.quiz_status = quiz_status;
    }

}
