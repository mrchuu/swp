/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Province;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class ProvinceDAO extends MyDAO {

    public Vector<Province> getAllProvince() {
        xSql = "select * from province";
        Vector<Province> pVector = new Vector<>();
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int pid = rs.getInt("province_id");
                String pname = rs.getString("name");
                pVector.add(new Province(pid, pname));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pVector;
    }
}
