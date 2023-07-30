/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Uslaptop
 */
public class QuizResult {

    int quiz_result_id, quiz_id, user_id;
    boolean quiz_status;
    float quiz_grade;
    Timestamp quiz_start;
    Timestamp quiz_end;
    int attempt;

    public QuizResult() {
    }

    public QuizResult(int quiz_result_id, int quiz_id, int user_id, boolean quiz_status, float quiz_grade, Timestamp quiz_start, Timestamp quiz_end, int attempt) {
        this.quiz_result_id = quiz_result_id;
        this.quiz_id = quiz_id;
        this.user_id = user_id;
        this.quiz_status = quiz_status;
        this.quiz_grade = quiz_grade;
        this.quiz_start = quiz_start;
        this.quiz_end = quiz_end;
        this.attempt = attempt;
    }

    public int getQuiz_result_id() {
        return quiz_result_id;
    }

    public void setQuiz_result_id(int quiz_result_id) {
        this.quiz_result_id = quiz_result_id;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public boolean isQuiz_status() {
        return quiz_status;
    }

    public void setQuiz_status(boolean quiz_status) {
        this.quiz_status = quiz_status;
    }

    public float getQuiz_grade() {
        return quiz_grade;
    }

    public void setQuiz_grade(float quiz_grade) {
        this.quiz_grade = quiz_grade;
    }

    public Timestamp getQuiz_start() {
        return quiz_start;
    }

    public void setQuiz_start(Timestamp quiz_start) {
        this.quiz_start = quiz_start;
    }

    public Timestamp getQuiz_end() {
        return quiz_end;
    }

    public void setQuiz_end(Timestamp quiz_end) {
        this.quiz_end = quiz_end;
    }

    public int getAttempt() {
        return attempt;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

}
