/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Gender;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class GenderDAO extends MyDAO {

    public Vector<Gender> getAllGender() {
        xSql = "select * from Gender";
        Vector<Gender> gv = new Vector<>();
        int xgenderId;
        String xgenderName;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                xgenderId = rs.getInt("gender_id");
                xgenderName = rs.getString("gender_name");
                gv.add(new Gender(xgenderId, xgenderName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gv;
    }

    public Gender getGenderById(int gId) {
        xSql = "select * from Gender where gender_id = ?";
        Gender g = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, gId);
            rs = ps.executeQuery();
            if (rs.next()) {
                g = new Gender(
                rs.getInt("gender_id"),
                rs.getString("gender_name")
                );
            
         
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }
}
