/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Post;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author Uslaptop
 */
public class PostDAO extends MyDAO {

    public Vector<Post> getAll() {
        Vector<Post> vector = new Vector<Post>();
        xSql = "select * from Post";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String post_img = rs.getString("post_img");
                String post_title = rs.getString("post_title");
                String post_desc = rs.getString("post_desc");
                Date post_date = rs.getDate("post_date");
                Boolean post_status = rs.getBoolean("post_status");
                int blog_id = rs.getInt("blog_id");

                // create object
                Post post = new Post(post_id, post_img, post_title, post_desc, post_date, post_status, blog_id);
                vector.add(post);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Post> searchByName(String search_title) {
        Vector<Post> vector = new Vector<Post>();
        xSql = "select * from Post where post_title like ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + search_title + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String post_img = rs.getString("post_img");
                String post_title = rs.getString("post_title");
                String post_desc = rs.getString("post_desc");
                Date post_date = rs.getDate("post_date");
                Boolean post_status = rs.getBoolean("post_status");
                int blog_id = rs.getInt("blog_id");

                // create object
                Post post = new Post(post_id, post_img, post_title, post_desc, post_date, post_status, blog_id);
                vector.add(post);
            }
        } catch (Exception e) {
            System.out.println("checkPost: " + e.getMessage());
        }
        return vector;
    }

    public Post searchById(int search_id) {
        Post post = null;
        xSql = "select * from Post where post_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, search_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int post_id = rs.getInt("post_id");
                String post_img = rs.getString("post_img");
                String post_title = rs.getString("post_title");
                String post_desc = rs.getString("post_desc");
                Date post_date = rs.getDate("post_date");
                Boolean post_status = rs.getBoolean("post_status");
                int blog_id = rs.getInt("blog_id");

                // create object
                post = new Post(post_id, post_img, post_title, post_desc, post_date, post_status, blog_id);
            }
        } catch (Exception e) {
            System.out.println("checkPost: " + e.getMessage());
        }
        return post;
    }
    
    // Manh
    public Boolean addPost(String post_img, String post_title, String post_desc, Date post_date, Boolean post_status, int blog_id){
        xSql = "INSERT INTO post (post_img, post_title, post_desc, post_date, post_status, blog_id) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            ps = con.prepareStatement(xSql);
            ps.setString(1, post_img);
            ps.setString(2, post_title);
            ps.setString(3, post_desc);
            ps.setDate(4, post_date);
            ps.setBoolean(5, post_status);
            ps.setInt(6, blog_id);
            int row = ps.executeUpdate();
            if(row > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException ex) {
            return false;
        }
    }
    
    public Boolean updatePost(int post_id, String post_img, String post_title, String post_desc, Date post_date, Boolean post_status, int blog_id){
        xSql = "UPDATE Post\n" +
               "SET post_img = ?, post_title = ?, post_desc = ?, post_date = ?, post_status = ?, blog_id = ?\n" +
               "WHERE post_id = ?;";
        try{
            ps = con.prepareStatement(xSql);
            ps.setString(1, post_img);
            ps.setString(2, post_title);
            ps.setString(3, post_desc);
            ps.setDate(4, post_date);
            ps.setBoolean(5, post_status);
            ps.setInt(6, blog_id);
            ps.setInt(7, post_id);
            int row = ps.executeUpdate();
            if(row > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException ex) {
            return false;
        }
    }
    
    public Boolean deleteById(int id) {
        xSql = "delete from Post where post_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            int row = ps.executeUpdate();
            if(row > 0){
                return true;
            }
            else{
                return false;
            }
        }catch (SQLException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        PostDAO pd = new PostDAO();
        
//        System.out.println("Test searchById");
//        Post post = pd.searchById(1);
//        System.out.println(post);
//        
//        System.out.println("Test searchByName");
//        Vector<Post> postSearchByName = pd.searchByName("a");
//        for(Post p : postSearchByName){
//            System.out.println(p);
//        }
//        
//        System.out.println("Test addPost");
//        Boolean inserted = pd.addPost("tempAvatar.jpg", "Người đầu tiên ở Việt Nam đạt 9.0 cả bốn kỹ năng IELTS", 
//                "test", Date.valueOf("2022-06-30"), true, 1);
//        System.out.println(inserted);
//        
//        System.out.println("Test updatePost");
//        Boolean updated = pd.updatePost(1001, "tempAvatar.jpg", "test", 
//                "test", Date.valueOf("2022-06-07"), true, 1);
//        System.out.println(inserted);
    }
}
