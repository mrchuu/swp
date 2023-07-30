/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import dao.QuizDAO;
import java.util.Vector;

/**
 *
 * @author ACER
 */
public class Question {

    int ques_id;
    String ques_content;
    String ques_note;
    int quiz_id;
    Vector<Choice> choices;

    public Question() {
    }

    public Question(int ques_id, String ques_content, String ques_note, int quiz_id) {
        this.ques_id = ques_id;
        this.ques_content = ques_content;
        this.ques_note = ques_note;
        this.quiz_id = quiz_id;
        QuizDAO quizDAO = new QuizDAO();
        choices = quizDAO.getChoicebyQuestionId(ques_id);

    }

    public Question(int ques_id, String ques_content, String ques_note, int quiz_id, Vector<Choice> choices) {
        this.ques_id = ques_id;
        this.ques_content = ques_content;
        this.ques_note = ques_note;
        this.quiz_id = quiz_id;
        this.choices = choices;
    }
    
    public int getQues_id() {
        return ques_id;
    }

    public void setQues_id(int ques_id) {
        this.ques_id = ques_id;
    }

    public String getQues_content() {
        return ques_content;
    }

    public void setQues_content(String ques_content) {
        this.ques_content = ques_content;
    }

    public String getQues_note() {
        return ques_note;
    }

    public void setQues_note(String ques_note) {
        this.ques_note = ques_note;
    }

    public int getQuiz_id() {
        return quiz_id;
    }

    public void setQuiz_id(int quiz_id) {
        this.quiz_id = quiz_id;
    }

    public Vector<Choice> getChoices() {
        return choices;
    }

    public void setChoices(Vector<Choice> choices) {
        this.choices = choices;
    }
}
