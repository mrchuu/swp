/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Uslaptop
 */
public class Choice {

    int choice_id;
    String choice_content;
    boolean is_true;
    int ques_id;

    public Choice() {
    }

    public Choice(int choice_id, String choice_content, boolean is_true, int ques_id) {
        this.choice_id = choice_id;
        this.choice_content = choice_content;
        this.is_true = is_true;
        this.ques_id = ques_id;
    }

    public int getChoice_id() {
        return choice_id;
    }

    public void setChoice_id(int choice_id) {
        this.choice_id = choice_id;
    }

    public String getChoice_content() {
        return choice_content;
    }

    public void setChoice_content(String choice_content) {
        this.choice_content = choice_content;
    }

    public boolean isIs_true() {
        return is_true;
    }

    public void setIs_true(boolean is_true) {
        this.is_true = is_true;
    }

    public int getQues_id() {
        return ques_id;
    }

    public void setQues_id(int ques_id) {
        this.ques_id = ques_id;
    }

}
