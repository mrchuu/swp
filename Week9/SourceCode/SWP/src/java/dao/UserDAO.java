/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.UserEditProfileDto;
import entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class UserDAO extends MyDAO {

    public void addNewUser(User nu) {
        xSql = "INSERT INTO [dbo].[User]"
                + "([user_email],"
                + "[password],"
                + "[full_name],"
                + "[user_img],"
                + "[gender_id],"
                + "[user_dob],"
                + "[user_phone],"
                + "[user_address],"
                + "[user_wallet],"
                + "[role_id],"
                + "[user_time],"
                + "[user_status],"
                + "[Score]) \n"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?, ?)";
        String dobraw = nu.getDob().toString();
        String userTime = nu.getUserTime().toString();
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, nu.getUserEmail());
            ps.setString(2, nu.getPassword());
            ps.setString(3, nu.getFullName());
            ps.setString(4, nu.getUserImg());
            ps.setInt(5, nu.getGenderId());
            ps.setDate(6, Date.valueOf(dobraw));
            ps.setString(7, nu.getUserPhone());
            ps.setString(8, nu.getUserAddress());
            ps.setString(9, nu.getUserWallet());
            ps.setInt(10, nu.getRoleId());
            ps.setDate(11, Date.valueOf(userTime));
            ps.setBoolean(12, nu.isUserStatus());
            ps.setInt(13, nu.getScore());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User getUserById(int id) {
        xSql = "select * from [dbo].[User] WHERE [user_id] = ?";
        User u = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                u = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_email"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("user_img"),
                        rs.getInt("gender_id"),
                        rs.getDate("user_dob"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_wallet"),
                        rs.getInt("role_id"),
                        rs.getDate("user_time"),
                        rs.getBoolean("user_status"),
                        rs.getInt("Score")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }

    public User getUserByEmail(String email) {
        xSql = "select * from [dbo].[User] WHERE [user_email] = ?";
        User uByEmail = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                uByEmail = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_email"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("user_img"),
                        rs.getInt("gender_id"),
                        rs.getDate("user_dob"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_wallet"),
                        rs.getInt("role_id"),
                        rs.getDate("user_time"),
                        rs.getBoolean("user_status"),
                        rs.getInt("Score")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uByEmail;
    }

    public User login(String email, String password) {

        xSql = "SELECT * FROM [dbo].[User] WHERE [user_email] = ? AND [password] = ?";

        User userLogin = null;

        try {

            ps = con.prepareStatement(xSql);
            ps.setString(1, email);
            ps.setString(2, password);

            rs = ps.executeQuery();

            if (rs.next()) {
                userLogin = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_email"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("user_img"),
                        rs.getInt("gender_id"),
                        rs.getDate("user_dob"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_wallet"),
                        rs.getInt("role_id"),
                        rs.getDate("user_time"),
                        rs.getBoolean("user_status"),
                        rs.getInt("Score")
                );
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userLogin;
    }

    public void updateProfile(String fullName, int genderId, String dobString, String Address, String phoneNumber) {
        xSql = "update [dbo].[User]\n"
                + "set full_name = ?, gender_id = ?,\n"
                + "user_dob = ?, user_address = ?, \n"
                + "user_phone = ?\n"
                + "where user_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + fullName + "%");
            ps.setInt(2, genderId);
            ps.setDate(3, Date.valueOf(dobString));
            ps.setString(4, "%" + Address + "%");
            ps.setString(5, "%" + phoneNumber + "%");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean changePass(String email, String newPassword) {

        xSql = "update [dbo].[User] set [password] = '" + newPassword + "'"
                + "where [user_email] = '" + email + "'";
        boolean f = false;
        try {

            ps = con.prepareStatement(xSql);
            ps.executeUpdate();
            f = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return f;
    }

    public Map<String, Integer> getDashBoardDataPop(String sortType) {
        Map<String, Integer> map = new LinkedHashMap<>();
        xSql = "select top 5 u.user_address, COUNT(u.user_id) as uNumber\n"
                + "from \"User\" u, province p\n"
                + "where u.user_address = p.name\n"
                + "and u.role_id = 2\n"
                + "group by (u.user_address)\n";
        if (sortType.equalsIgnoreCase("most")) {
            xSql += "order by uNumber desc";
        } else {
            xSql += "order by uNumber asc";
        }
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String provinceName = rs.getNString("user_address");
                int uNumber = rs.getInt("uNumber");
                map.put(provinceName, uNumber);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;

    }

    public boolean editProfile(UserEditProfileDto user) {

        xSql = "update [User] set full_name = ?,"
                + " gender_id = ?, "
                + "user_dob = ?, "
                + "user_address = ?, "
                + "user_phone = ? where user_id = ?";

        boolean f = false;

        try {

            ps = con.prepareStatement(xSql);

            ps.setString(1, user.getFullName());
            ps.setInt(2, user.getGenderId());
            ps.setDate(3, user.getDob());
            ps.setString(4, user.getUserAddress());
            ps.setString(5, user.getUserPhone());
            ps.setInt(6, user.getUserId());

            int rs = ps.executeUpdate();

            if (rs == 1) {
                f = true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return f;
    }

    public void Deposit(int userId, int amount) {
        xSql = "update \"User\"\n"
                + "set user_wallet = user_wallet + ?\n"
                + "where user_id = ?";
        try {

            ps = con.prepareStatement(xSql);
            ps.setInt(1, amount);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public HashMap<Integer, User> Top3UserLeaderBoard() {
        HashMap<Integer, User> map = new HashMap<>();
        User u = null;
        xSql = "select top 3 * from \"User\" u\n"
                + "where u.role_id = 2\n"
                + "order by u.Score desc";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_email"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("user_img"),
                        rs.getInt("gender_id"),
                        rs.getDate("user_dob"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_wallet"),
                        rs.getInt("role_id"),
                        rs.getDate("user_time"),
                        rs.getBoolean("user_status"),
                        rs.getInt("Score")
                );
                map.put(rs.getInt("Score"), u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return map;
    }

    public Vector<User> GetAllStudentSortedByScore() {
        Vector<User> StudentList = new Vector<>();
        xSql = "select * from \"User\" u\n"
                + "where u.role_id = 2\n"
                + "order by u.Score desc\n";
        User u = null;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_email"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("user_img"),
                        rs.getInt("gender_id"),
                        rs.getDate("user_dob"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_wallet"),
                        rs.getInt("role_id"),
                        rs.getDate("user_time"),
                        rs.getBoolean("user_status"),
                        rs.getInt("Score")
                );
                StudentList.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return StudentList;
    }

    public Vector<User> GetAllUser() {
        Vector<User> StudentList = new Vector<>();
        xSql = "select * from \"User\" u";

        User u = null;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_email"),
                        rs.getString("password"),
                        rs.getString("full_name"),
                        rs.getString("user_img"),
                        rs.getInt("gender_id"),
                        rs.getDate("user_dob"),
                        rs.getString("user_phone"),
                        rs.getString("user_address"),
                        rs.getString("user_wallet"),
                        rs.getInt("role_id"),
                        rs.getDate("user_time"),
                        rs.getBoolean("user_status"),
                        rs.getInt("Score")
                );
                StudentList.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return StudentList;
    }

    public void handleTransaction(int userId, int amount) {
        xSql = "update \"User\"\n"
                + "set user_wallet = user_wallet - ?\n"
                + "where user_id = ?";
        try {

            ps = con.prepareStatement(xSql);
            ps.setInt(1, amount);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int StudentCount(int mode) {
        xSql = "select COUNT(user_id) from \"User\"\n"
                + "where role_id = ?";
        int count = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, mode);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int BanUnbanUser(int statusChange, int user_id) {
        xSql = "update \"User\"\n"
                + "set user_status = ?\n"
                + "where user_id = ?";
        int count = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, statusChange);
            ps.setInt(2, user_id);
            count = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

}
