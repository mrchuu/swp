/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Uslaptop
 */
public class Post {

    int post_id;
    String post_img;
    String post_title;
    String post_desc;
    Date post_date;
    Boolean post_status;
    int blog_id;

    public Post() {
    }

    public Post(int post_id, String post_img, String post_title, String post_desc, Date post_date, Boolean post_status, int blog_id) {
        this.post_id = post_id;
        this.post_img = post_img;
        this.post_title = post_title;
        this.post_desc = post_desc;
        this.post_date = post_date;
        this.post_status = post_status;
        this.blog_id = blog_id;
    }

    public int getPost_id() {
        return post_id;
    }

    public void setPost_id(int post_id) {
        this.post_id = post_id;
    }

    public String getPost_img() {
        return post_img;
    }

    public void setPost_img(String post_img) {
        this.post_img = post_img;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }

    public String getPost_desc() {
        return post_desc;
    }

    public void setPost_desc(String post_desc) {
        this.post_desc = post_desc;
    }

    public Date getPost_date() {
        return post_date;
    }
    
    public String getPost_dateFormated() {
        SimpleDateFormat s = new SimpleDateFormat("dd-MM-yyyy");
        String formatedDate = s.format(post_date);
        return formatedDate;
    }

    public void setPost_date(Date post_date) {
        this.post_date = post_date;
    }

    public Boolean getPost_status() {
        return post_status;
    }

    public void setPost_status(Boolean post_status) {
        this.post_status = post_status;
    }

    public int getBlog_id() {
        return blog_id;
    }

    public void setBlog_id(int blog_id) {
        this.blog_id = blog_id;
    }

}
