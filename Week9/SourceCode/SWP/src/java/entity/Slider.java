/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Uslaptop
 */
public class Slider {

    int slider_id;
    String slider_title;
    String slider_img;
    String slider_link;
    boolean slider_status;
    String slider_note;

    public Slider() {
    }

    public Slider(int slider_id, String slider_title, String slider_img, String slider_link, boolean slider_status, String slider_note) {
        this.slider_id = slider_id;
        this.slider_title = slider_title;
        this.slider_img = slider_img;
        this.slider_link = slider_link;
        this.slider_status = slider_status;
        this.slider_note = slider_note;
    }

    public int getSlider_id() {
        return slider_id;
    }

    public void setSlider_id(int slider_id) {
        this.slider_id = slider_id;
    }

    public String getSlider_title() {
        return slider_title;
    }

    public void setSlider_title(String slider_title) {
        this.slider_title = slider_title;
    }

    public String getSlider_img() {
        return slider_img;
    }

    public void setSlider_img(String slider_img) {
        this.slider_img = slider_img;
    }

    public String getSlider_link() {
        return slider_link;
    }

    public void setSlider_link(String slider_link) {
        this.slider_link = slider_link;
    }

    public boolean isSlider_status() {
        return slider_status;
    }

    public void setSlider_status(boolean slider_status) {
        this.slider_status = slider_status;
    }

    public String getSlider_note() {
        return slider_note;
    }

    public void setSlider_note(String slider_note) {
        this.slider_note = slider_note;
    }

}
