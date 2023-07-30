/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.District;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class DistrictDAO extends MyDAO {

    public Vector<District> getAllDistrictbyProvince(String provinceName) {
        xSql = "select * from province"
                + "where province.province_id = district.province_id\n"
                + "and province.name like ?";
        Vector<District> pVector = new Vector<>();
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%N"+provinceName+"%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int did = rs.getInt("district_id");
                int pid = rs.getInt("province_id");
                String pname = rs.getString("name");
                pVector.add(new District(did, pid, pname));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pVector;
    }
}
