/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Subject;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uslaptop
 */
public class SubjectDAO extends MyDAO {

    public Vector<Subject> getAll() {
        Vector<Subject> vector = new Vector<Subject>();
        xSql = "select* from Subject";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int sub_id = rs.getInt("sub_id");
                String sub_name = rs.getString("sub_name");
                String sub_desc = rs.getString("sub_desc");

                // create object
                Subject sub = new Subject(sub_id, sub_name, sub_desc);
                vector.add(sub);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    
    public boolean Add(String sub_name, String sub_desc){
        xSql = "insert into Subject (sub_name, sub_desc) values (?, ?)";
        try{
            ps = con.prepareStatement(xSql);
            ps.setString(1, sub_name);
            ps.setString(2, sub_desc);
            int rs = ps.executeUpdate();
            if(rs > 0){
                return true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
    public boolean Delete(int id){
        xSql = "delete Subject where sub_id = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            int rs = ps.executeUpdate();
            if(rs > 0){
                return true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
    public boolean Update(int id, String sub_name, String sub_desc){
        xSql = "update Subject set sub_name = ?, sub_desc = ? where sub_id = ?";
        try{
            ps = con.prepareStatement(xSql);
            ps.setString(1, sub_name);
            ps.setString(2, sub_desc);
            ps.setInt(3, id);
            int rs = ps.executeUpdate();
            if(rs > 0){
                return true;
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(SubjectDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return false;
    }
    
    public static void main(String[] args) {
        SubjectDAO pc = new SubjectDAO();
        
//        Vector<Subject> list = pc.getAll();
//        System.out.println("Test getAll()");
//        for (Subject c : list) {
//            System.out.println(c);
//        }
        
//        System.out.println("Test Add()");
//        boolean added = pc.Add("test", "test");
//        System.out.println(added);
        
//        System.out.println("Test Delete()");
//        boolean deleted = pc.Delete(2);
//        System.out.println(deleted);

        System.out.println("Test Update()");
        boolean updated = pc.Update(11, "default", "default");
        System.out.println(updated);
    }
}
