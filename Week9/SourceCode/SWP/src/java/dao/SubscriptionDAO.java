/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Price_Package;
import entity.Subscription;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FPT
 */
public class SubscriptionDAO extends MyDAO {

    public Subscription GetCurrentSubscription(int userId) {
            xSql = "select p.*, s.reg_time, s.user_id, s.expireDate, s.reg_id, s.status from Subscription s, Price_Package p where\n"
                    + "p.package_id = s.package_id\n"
                    + "and s.user_id = ?\n"
                    + "and s.status = 1";
        Subscription currentSubscription = null;
        int a = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                int package_id = rs.getInt("package_id");
                String package_name = rs.getString("package_name");
                int duration = rs.getInt("duration");
                boolean pack_status = rs.getBoolean("pack_status");
                float price = rs.getFloat("price");
                String description = rs.getString("description");
                Price_Package pc = new Price_Package(package_id, package_name, duration, pack_status, price, description);
                Date reg_time = rs.getDate("reg_time");
                Date expireDate = rs.getDate("expireDate");
                int reg_id = rs.getInt("reg_id");
                boolean SubscriptionStatus = rs.getBoolean("status");
                currentSubscription = new Subscription(reg_id, Date.valueOf("2023-07-09"), userId, pc, Date.valueOf("2023-07-09"),SubscriptionStatus);

            }
        } catch (Exception e) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return currentSubscription;
    }

    public int addSubcription(int userId, int packageId, Date reg_time, Date expireDate) {
        xSql = "insert into Subscription (user_id, package_id, reg_time, expireDate, status) values (?, ?, ?, ?, ?)";
        int a = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setInt(2, packageId);
            ps.setDate(3, reg_time);
            ps.setDate(4, expireDate);
            ps.setInt(5, 1);
            a = ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return a;
    }

    public HashMap<Integer, Integer> GetSubScriptionDashBoardData(String SortTypeRevenue, int year, int monthF) {
        HashMap<Integer, Integer> map = new HashMap<>();
        LocalDate ld = LocalDate.now();
        xSql = "select MONTH(s.reg_time) as month, (p.price*COUNT(s.user_id)) as Revenue from Subscription s, Price_Package p\n"
                + "where s.package_id = p.package_id\n"
                + "and Month(s.reg_time) <= ?\n"
                + "and Year(s.reg_time) = ?\n"
                + "group by MONTH(s.reg_time), p.price";
        int month = 0, Revenue = 0;
        for (int i = 1; i <= monthF; i++) {
            map.put(i, 0);
        }
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, monthF);
            ps.setInt(2, year);
            rs = ps.executeQuery();
            while (rs.next()) {
                Revenue = rs.getInt("Revenue");
                month = rs.getInt("month");
                for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                    if (entry.getKey()==month) {
                        map.replace(month, Revenue);
                    }
                }

            }
        } catch (Exception e) {
            Logger.getLogger(PricePackageDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return map;

    }

    public static void main(String[] args) {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        System.out.println("Current Date: " + currentDate);

        // Add 7 days to the current date to calculate the end date
        LocalDate endDate = currentDate.plusDays(7);
        System.out.println("End Date: " + endDate);
    }
}
