/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Price_Package;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uslaptop
 */
public class PricePackageDAO extends MyDAO {

    public Vector<Price_Package> getAll() {
        Vector<Price_Package> vector = new Vector<Price_Package>();
        xSql = "select* from Price_Package";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int package_id = rs.getInt("package_id");
                String package_name = rs.getString("package_name");
                int duration = rs.getInt("duration");
                boolean pack_status = rs.getBoolean("pack_status");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                Price_Package pc = new Price_Package(package_id, package_name, duration, pack_status, price, description);
                vector.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public boolean addPricePackage(Price_Package pricePackage) {
        xSql = "INSERT INTO [dbo].[Price_Package](package_name, \n"
                + "duration , \n"
                + "pack_status, \n"
                + "price, \n"
                + "description) VALUES (?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, pricePackage.getPackage_name());
            ps.setInt(2, pricePackage.getDuration());
            ps.setBoolean(3, pricePackage.isPack_status());
            ps.setFloat(4, pricePackage.getPrice());
            ps.setString(5, pricePackage.getDescription());

            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePricePackage(int id, String name, int duration, Boolean status, float price, String description) {
        String xSql = "UPDATE [dbo].[Price_Package] "
                + "SET package_name=?, "
                + "duration=?, "
                + "pack_status=?, "
                + "price=?, "
                + "description=? "
                + "WHERE package_id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, name);
            ps.setInt(2, duration);
            ps.setBoolean(3, status);
            ps.setFloat(4, price);
            ps.setString(5, description);
            ps.setInt(6, id); // specify which row to update based on ID

            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteById(int id) {
        xSql = "delete from Price_package where package_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);

            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Price_Package getUserSubcription(int userid) {
        xSql = "select p.* from Subscription s, Price_Package p where\n"
                + "s.user_id = ?\n"
                + "and p.package_id = s.package_id";
        Price_Package pc = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userid);
            rs = ps.executeQuery();
            if (rs.next()) {
                int package_id = rs.getInt("package_id");
                String package_name = rs.getString("package_name");
                int duration = rs.getInt("duration");
                boolean pack_status = rs.getBoolean("pack_status");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                pc = new Price_Package(package_id, package_name, duration, pack_status, price, description);
            }
        } catch (Exception e) {
        }
        return pc;
    }

    public static void main(String[] args) {
        PricePackageDAO pc = new PricePackageDAO();

//        System.out.println("Test updatePricePackage");
//        boolean updated = pc.updatePricePackage(5, "Test2", 0, true, 0, "description");
//        System.out.println(updated);
//        System.out.println("Test getAll");
//        Vector<Price_Package> list = pc.getAll();
//        for (Price_Package c : list) {
//            System.out.println(c.getDescription());
//        }
//        System.out.println("Test addPricePackage");
//        Price_Package pricePackage = new Price_Package(4, "Test", 90, true, 100, "Test");
//        boolean added = pc.addPricePackage(pricePackage);
//        System.out.println(added);
        System.out.println("Test deleteById");
        boolean deleted = pc.deleteById(4);
        System.out.println(deleted);
    }

}
