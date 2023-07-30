/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.PostCategory;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uslaptop
 */
public class PostCategoryDAO extends MyDAO {

    public Vector<PostCategory> getAll() {
        Vector<PostCategory> vector = new Vector<PostCategory>();
        xSql = "select * from Post_Category";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int blog_id = rs.getInt("blog_id");
                String blog_name = rs.getString("blog_name");
                String note = rs.getString("note");

                // create object
                PostCategory pc = new PostCategory(blog_id, blog_name, note);
                vector.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public boolean Add(String blog_name, String note) {
        xSql = "insert into Post_Category(blog_name, note) values (?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, blog_name);
            ps.setString(2, note);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean Update(int blog_id, String blog_name, String note) {
        xSql = "update Post_Category set blog_name = ?, note = ? where blog_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, blog_name);
            ps.setString(2, note);
            ps.setInt(3, blog_id);
            int rs = ps.executeUpdate();
            if (rs > 0) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public boolean Delete(int blog_id){
        xSql = "delete from Post_Category where blog_id = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, blog_id);
            int rs = ps.executeUpdate();
            if(rs > 0){
                return true;
            }
        }catch (SQLException ex) {
            Logger.getLogger(PostCategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static void main(String[] args) {
        PostCategoryDAO pc = new PostCategoryDAO();
        Vector<PostCategory> list = pc.getAll();
        System.out.println("Test getAll()");
        for (PostCategory c : list) {
            System.out.println(c.getBlog_name());
        }
        
        System.out.println("Test Add()");
        boolean added = pc.Add("test", "test");
        System.out.println(added);
        
        System.out.println("Test Update()");
        boolean updated = pc.Update(11, "default", "default");
        System.out.println(updated);
        
        System.out.println("Test Delete");
        boolean deleted = pc.Delete(11);
        System.out.println(deleted);
    }

}
