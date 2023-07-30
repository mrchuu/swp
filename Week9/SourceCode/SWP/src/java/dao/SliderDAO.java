/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Slider;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uslaptop
 */
public class SliderDAO extends MyDAO {

    public Vector<Slider> getAll() {
        Vector<Slider> vector = new Vector<Slider>();
        xSql = "select*from Slider";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int slider_id = rs.getInt("slider_id");
                String slider_title = rs.getString("slider_title");
                String slider_img = rs.getString("slider_img");
                String slider_link = rs.getString("slider_link");
                boolean slider_status = rs.getBoolean("slider_status");
                String slider_note = rs.getString("slider_note");
                Slider sl = new Slider(slider_id, slider_title, slider_img, slider_link, slider_status, slider_note);
                vector.add(sl);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SliderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;

    }

    public Slider getSliderById(int id) {
        Slider slider = null;
        xSql = "select * from slider where slider_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int slider_id = rs.getInt("slider_id");
                String slider_title = rs.getString("slider_title");
                String slider_img = rs.getString("slider_img");
                String slider_link = rs.getString("slider_link");
                boolean slider_status = rs.getBoolean("slider_status");
                String slider_note = rs.getString("slider_note");
                slider = new Slider(slider_id, slider_title, slider_img, slider_link, slider_status, slider_note);
            }
        } catch (Exception e) {
            System.out.println("checkPost: " + e.getMessage());
        }
        return slider;
    }

    public Boolean addSlider(String slider_title, String slider_img, String slider_link, boolean slider_status, String slider_note) {
        xSql = "INSERT INTO slider (slider_title, slider_img, slider_link, slider_status, slider_note) VALUES (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, slider_title);
            ps.setString(2, slider_img);
            ps.setString(3, slider_link);
            ps.setBoolean(4, slider_status);
            ps.setString(5, slider_note);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public Boolean updateSlider(int slider_id, String slider_title, String slider_img, String slider_link, boolean slider_status, String slider_note) {
        xSql = "UPDATE Slider\n"
                + "SET slider_title = ?, slider_img = ?, slider_link = ?, slider_status = ?, slider_note = ?\n"
                + "WHERE slider_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, slider_title);
            ps.setString(2, slider_img);
            ps.setString(3, slider_link);
            ps.setBoolean(4, slider_status);
            ps.setString(5, slider_note);
            ps.setInt(6, slider_id);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    public Boolean deleteSliderById(int slider_id){
        xSql = "DELETE FROM slider WHERE slider_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, slider_id);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }

    public static void main(String[] args) {
        SliderDAO pc = new SliderDAO();

//        System.out.println("Test getAll");
//        Vector<Slider> list = pc.getAll();
//        for (Slider c : list) {
//            System.out.println(c.getSlider_link());
//        }

        System.out.println("Test addSlider");
        Boolean inserted = pc.addSlider("Nơi mưa không cần trú, nơi nắng không cần che",
                "img/tempAvatar.jpg",
                "courseList?subject=4",
                true,
                "Trong một khoảnh khắc ta không nhớ mình là ai");
        System.out.println(inserted);

//        System.out.println("Test updateSlider");
//        Boolean updated = pc.updateSlider(6, "Mình cùng chia nhau mảnh không gian này",
//                "img/tempAvatar.jpg",
//                "courseList?subject=4",
//                true,
//                "Anh là thằng nhóc chân anh không mang giày");
//        System.out.println(updated);

//        System.out.println("Test deleteSliderById");
//        Boolean deleted = pc.deleteSliderById(6);
//        System.out.println(deleted);
    }

}
