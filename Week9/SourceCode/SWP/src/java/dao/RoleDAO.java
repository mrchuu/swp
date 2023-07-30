/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Role;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class RoleDAO extends MyDAO{
    public Vector<Role> getRegisterRoles(){
        xSql = "select * from role where role_name like 'Student' "
                + "or role_name like 'Lecturer'";
        Vector<Role> rv = new Vector<>();
        int xroleId;
        String xroleName;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while(rs.next()){
                xroleId = rs.getInt("role_id");
                xroleName = rs.getString("role_name");
                rv.add(new Role(xroleId, xroleName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rv;
    }
    public Role getRoleById(int roleId){
        xSql = "select * from role where role_id = ?";
        Role r = null;
        int xroleId;
        String xroleName;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, roleId);
            rs = ps.executeQuery();
            while(rs.next()){
                xroleId = rs.getInt("role_id");
                xroleName = rs.getString("role_name");
                r = new Role(roleId, xroleName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return r;
    }
}
