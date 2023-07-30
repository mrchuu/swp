/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Uslaptop
 */
public class QuesResult {

    int ques_result_id, ques_id, user_id, quiz_result_id;
    String ques_answer;
    boolean ques_status, ques_flag;

    public QuesResult() {
    }

    public QuesResult(int ques_result_id, int ques_id, int user_id, String ques_answer, int quiz_result_id, boolean ques_status, boolean ques_flag) {
        this.ques_result_id = ques_result_id;
        this.ques_id = ques_id;
        this.user_id = user_id;
        this.ques_answer = ques_answer;
        this.quiz_result_id = quiz_result_id;
        this.ques_status = ques_status;
        this.ques_flag = ques_flag;
    }

    public int getQues_result_id() {
        return ques_result_id;
    }

    public void setQues_result_id(int ques_result_id) {
        this.ques_result_id = ques_result_id;
    }

    public int getQues_id() {
        return ques_id;
    }

    public void setQues_id(int ques_id) {
        this.ques_id = ques_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getQues_answer() {
        return ques_answer;
    }

    public void setQues_answer(String ques_answer) {
        this.ques_answer = ques_answer;
    }

    public int getQuiz_result_id() {
        return quiz_result_id;
    }

    public void setQuiz_result_id(int quiz_result_id) {
        this.quiz_result_id = quiz_result_id;
    }

    public boolean isQues_status() {
        return ques_status;
    }

    public void setQues_status(boolean ques_status) {
        this.ques_status = ques_status;
    }

    public boolean isQues_flag() {
        return ques_flag;
    }

    public void setQues_flag(boolean ques_flag) {
        this.ques_flag = ques_flag;
    }

}
