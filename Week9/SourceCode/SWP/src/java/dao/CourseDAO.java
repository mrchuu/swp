/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Course;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.ManageCourse;
import entity.User;
import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ACER
 */
public class CourseDAO extends MyDAO {

    // Manh
    public int addCourse(String course_name, String course_img, float course_price, String course_desc, Date last_update, int sub_id, int level_id, Boolean course_status, int duration, String course_title) {
        int n = 0;
        xSql = "INSERT INTO [dbo].[Course](course_name, \n"
                + "course_img , \n"
                + "course_price, \n"
                + "course_desc, \n"
                + "last_update, \n"
                + "sub_id, \n"
                + "level_id, \n"
                + "course_status, \n"
                + "durationDAY, \n"
                + "course_Title) OUTPUT INSERTED.course_id VALUES (?,?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, course_name);
            ps.setString(2, course_img);
            ps.setFloat(3, course_price);
            ps.setString(4, course_desc);
            ps.setDate(5, last_update);
            ps.setInt(6, sub_id);
            ps.setInt(7, level_id);
            ps.setBoolean(8, course_status);
            ps.setInt(9, duration);
            ps.setString(10, course_title);
            rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt("course_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    // Son
    public Vector<ManageCourse> getmyCourseList(int user_Id, String sub_idRaw, String searchName, String sortType) {
        Vector<ManageCourse> vector = new Vector<ManageCourse>();
        xSql = "select c.*,mc.course_Start, mc.course_end, mc.done from Course c, Manage_Course mc\n"
                + "where c.course_id = mc.course_id\n"
                + "and mc.user_id = ?";
        if (sub_idRaw != null) {
            xSql += " and sub_id = '" + Integer.parseInt(sub_idRaw) + "'";
        }
        if (searchName != null) {
            xSql += " and course_name like '%" + searchName + "%'";
        }
        if (sortType != null) {
            if (sortType.equals("recent")) {
                xSql += " order by c.last_update asc";
            } else {
                xSql += " order by course_name asc";
            }
        }
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, user_Id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                Date course_Start = rs.getDate("course_Start");
                Date course_End = rs.getDate("course_end");
                boolean done = rs.getBoolean("done");
                vector.add(new ManageCourse(course_Start, course_End, course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle, done));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    // Linh
    public Vector<Course> getAll() {
        Vector<Course> vector = new Vector<Course>();
        xSql = "select c.* from Course c";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                vector.add(new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    // Linh
    public Vector<Course> getCourseBySubId(int filter_sub_id, int offset, int fetch, String sorttype) {
        Vector<Course> vector = new Vector<Course>();
        xSql = "select * from \"Course\" c where sub_id = ?\n";
        if (sorttype.equalsIgnoreCase("name")) {
            xSql += " order by course_name asc";
        }
        if (sorttype.equalsIgnoreCase("recent")) {
            xSql += " order by last_update desc";
        }

        xSql += " offset ? row\n"
                + "fetch next ? rows only";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, filter_sub_id);
            ps.setInt(2, offset);
            ps.setInt(3, fetch);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                vector.add(new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    // Manh
    public Vector<Course> Get4HottestBySubId(int search_sub_id) {
        Vector<Course> vector = new Vector<Course>();

        xSql = "SELECT top 4 c.*, COALESCE(u.user_count, 0) AS user_count\n"
                + "                FROM course c\n"
                + "                LEFT JOIN (\n"
                + "                  SELECT course_id, COUNT(user_id) AS user_count\n"
                + "                  FROM manage_course\n"
                + "                  GROUP BY course_id\n"
                + "                ) u ON c.course_id = u.course_id\n"
                + "                WHERE c.sub_id = ?\n"
                + "                ORDER BY user_count DESC";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, search_sub_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                Course c = new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle);
                vector.add(c);
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return vector;
    }

    // Linh
    public Vector<Course> getHottestCourse() {
        Vector<Course> vector = new Vector<Course>();
        xSql = "SELECT course.course_id, course.course_name, course.course_img, course.course_price,\n"
                + "       course.course_desc, course.last_update, course.sub_id, course.level_id,\n"
                + "       course.course_status, course.durationDAY, course.course_Title\n"
                + "FROM course\n"
                + "INNER JOIN (\n"
                + "    SELECT top 1000 course_id, COUNT(*) as enroll_count\n"
                + "    FROM Manage_Course\n"
                + "    GROUP BY course_id\n"
                + "    ORDER BY enroll_count DESC\n"
                + ") AS popular_courses ON course.course_id = popular_courses.course_id";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                vector.add(new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    // Linh
    public Vector<Course> searchByName(String search_name, String sorttype, int offset) {
        Vector<Course> vector = new Vector<Course>();
        xSql = "select * from Course where course_name like ?";
        if (sorttype.equalsIgnoreCase("name")) {
            xSql += " order by course_name asc";
        }
        if (sorttype.equalsIgnoreCase("recent")) {
            xSql += " order by last_update desc";
        }

        try {
            xSql += " offset ? row\n"
                    + "fetch next 9 rows only";
            ps = con.prepareStatement(xSql);

            ps.setNString(1, "%" + search_name + "%");
            ps.setInt(2, offset);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                vector.add(new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle));
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return vector;
    }

    // Linh
    public Course searchById(int search_id) {
        Course course = null;
        xSql = "select*from Course where course_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, search_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                course = new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle);
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return course;
    }

    // Linh
    public Vector<Course> searchNameSortByHottest(String search_name) {
        Vector<Course> vector = new Vector<Course>();

        xSql = "SELECT c.*, COALESCE(u.user_count, 0) AS user_count\n"
                + "                FROM course c\n"
                + "                LEFT JOIN (\n"
                + "                  SELECT course_id, COUNT(user_id) AS user_count\n"
                + "                  FROM manage_course\n"
                + "                  GROUP BY course_id\n"
                + "                ) u ON c.course_id = u.course_id\n"
                + "                WHERE c.course_name like ?\n"
                + "                ORDER BY user_count DESC";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, "%" + search_name + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                Course c = new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle);
                vector.add(c);
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return vector;
    }

    // Linh
    public Vector<Course> searchSubIdSortByHottest(int search_sub_id) {
        Vector<Course> vector = new Vector<Course>();

        xSql = "SELECT c.*, COALESCE(u.user_count, 0) AS user_count\n"
                + "                FROM course c\n"
                + "                LEFT JOIN (\n"
                + "                  SELECT course_id, COUNT(user_id) AS user_count\n"
                + "                  FROM manage_course\n"
                + "                  GROUP BY course_id\n"
                + "                ) u ON c.course_id = u.course_id\n"
                + "                WHERE c.sub_id = ?\n"
                + "                ORDER BY user_count DESC";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, search_sub_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                Course c = new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle);
                vector.add(c);
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return vector;
    }

    // Son
    public Map<String, Integer> getDashBoardDataPar(String sortType) {
        Map<String, Integer> map = new LinkedHashMap<>();
        xSql = "select distinct top 5 c.course_name, count(mc.user_id) as participants\n"
                + "from Manage_Course mc, Course c, \"User\" u\n"
                + "where mc.course_id = c.course_id \n"
                + "and u.user_id = mc.user_id\n"
                + "group by c.course_name, mc.course_id";
        if (sortType.equalsIgnoreCase("most")) {
            xSql += " order by participants desc";
        } else {
            xSql += " order by participants asc";
        }
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String courseName = rs.getNString("course_name");
                int participants = rs.getInt("participants");
                map.put(courseName, participants);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;

    }

    // Linh
    public Vector<Course> getByNameAndSubId(String search_name, int filter_sub_id) {
        Vector<Course> vector = new Vector<Course>();
        xSql = "select * from Course where course_name like ? and sub_id =?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setNString(1, "%" + search_name + "%");
            ps.setInt(2, filter_sub_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                vector.add(new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle));
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return vector;
    }

    // Linh
    public Vector<Course> searchByNameAndSubIdSortByHottest(String search_name, int search_sub_id) {
        Vector<Course> vector = new Vector<Course>();
        xSql = "SELECT c.*, COALESCE(u.user_count, 0) AS user_count\n"
                + "FROM course c\n"
                + "LEFT JOIN (\n"
                + "  SELECT course_id, COUNT(user_id) AS user_count\n"
                + "  FROM manage_course\n"
                + "  GROUP BY course_id\n"
                + ") u ON c.course_id = u.course_id\n"
                + "WHERE c.sub_id = ? and c.course_name like ?\n"
                + "ORDER BY user_count DESC;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(2, "%" + search_name + "%");
            ps.setInt(1, search_sub_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                vector.add(new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle));
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return vector;
    }

    //Manh
    public Boolean addCourseToUser(int course_id, int user_id, Date course_start, Date course_end) {
        xSql = "INSERT INTO Manage_Course (course_id, user_id, course_Start, course_end) VALUES (?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, course_id);
            ps.setInt(2, user_id);
            ps.setDate(3, course_start);
            ps.setDate(4, course_end);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println("checkCourse: " + ex.getMessage());
            return false;
        }
    }

    public int getTotalNumber(int sub_id, String search) {
        xSql = "select count(course_id) as cc from Course";
        int num = 0;
        if (sub_id != 0) {
            xSql += " where sub_id = '" + sub_id + "'";
        }
        if (search != null) {
            xSql += " where course_name like '%" + search + "%'";
        }
        try {
            ps = con.prepareStatement(xSql);

            rs = ps.executeQuery();
            if (rs.next()) {
                num = rs.getInt("cc");
            }

        } catch (Exception e) {
        }
        return num;
    }

    //son
    public Vector<Course> getAllCoursewithPagination(int offset, int fetch, String sorttype) {
        xSql = "select * from \"Course\" c\n";
        if (sorttype.equalsIgnoreCase("name")) {
            xSql += " order by course_name asc";
        }
        if (sorttype.equalsIgnoreCase("recent")) {
            xSql += " order by last_update desc";
        }

        Vector<Course> vector = new Vector<>();
        try {
            xSql += " offset ? row\n"
                    + "fetch next ? rows only";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, offset);
            ps.setInt(2, fetch);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int sub_id = rs.getInt("sub_id");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                vector.add(new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Course> SortCoursesByParRate(int offset, int fetch, int sub_id, String search) {
        xSql = "select distinct c.*, COUNT(mc.user_id) as parRate from Course c left join Manage_Course mc\n"
                + "on mc.course_id = c.course_id";

        Vector<Course> vector = new Vector<>();
        try {
            if (sub_id != 0) {
                xSql += " and c.sub_id = '%" + sub_id + "%'";
            }
            if (search != null) {
                xSql += " and c.course_name like '%" + search + "%'";
            }
            xSql += " group by mc.course_id, c.course_id, c.course_name, c.course_desc, c.course_img, c.course_price, c.course_status,\n"
                    + "c.course_Title, c.durationDAY, c.last_update, c.level_id, c.sub_id\n"
                    + "order by parRate desc\n"
                    + "offset ? row\n"
                    + "fetch next ? rows only";
            ps = con.prepareStatement(xSql);
            ps.setInt(1, offset);
            ps.setInt(2, fetch);
            rs = ps.executeQuery();
            while (rs.next()) {
                int course_id = rs.getInt("course_id");
                String course_name = rs.getString("course_name");
                String course_img = rs.getString("course_img");
                float course_price = rs.getFloat("course_price");
                String course_desc = rs.getString("course_desc");
                Date last_update = rs.getDate("last_update");
                int level_id = rs.getInt("level_id");
                Boolean course_status = rs.getBoolean("course_status");
                int duration = rs.getInt("durationDAY");
                String courseTitle = rs.getString("course_Title");
                vector.add(new Course(course_id, course_name, course_img, course_price, course_desc, last_update.toString(), sub_id, level_id, course_status, duration, courseTitle));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    // Manh
    public Boolean deleteCourse(int course_id) {
        xSql = "delete from course where course_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, course_id);
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
        }
    }

    // Manh
    public int updateCourse(int course_id, String course_name, String course_img, float course_price, String course_desc, Date last_update, int sub_id, int level_id, Boolean course_status, int duration, String course_title) {
        int n = 0;
        xSql = "UPDATE [dbo].[Course]\n"
                + "SET course_name = ?, \n"
                + "    course_img = ?, \n"
                + "    course_price = ?, \n"
                + "    course_desc = ?, \n"
                + "    last_update = ?, \n"
                + "    sub_id = ?, \n"
                + "    level_id = ?, \n"
                + "    course_status = ?, \n"
                + "    durationDAY = ?, \n"
                + "    course_Title = ?\n"
                + "WHERE course_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, course_name);
            ps.setString(2, course_img);
            ps.setFloat(3, course_price);
            ps.setString(4, course_desc);
            ps.setDate(5, last_update);
            ps.setInt(6, sub_id);
            ps.setInt(7, level_id);
            ps.setBoolean(8, course_status);
            ps.setInt(9, duration);
            ps.setString(10, course_title);
            ps.setInt(11, course_id);
            n = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return n;
    }

    //checkCourseRegistered
    public ManageCourse checkCourseRegistered(int courseId, int userId) {
        xSql = "select mc.* from Manage_Course mc\n"
                + "where mc.user_id = ?\n"
                + "and course_id = ?";
        ManageCourse registered = null;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Date course_Start = rs.getDate("course_Start");
                Date course_End = rs.getDate("course_end");
                boolean done = rs.getBoolean("done");
                registered = new ManageCourse(course_Start, course_End, done);
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return registered;
    }

    public int getCoursePublisher(int courseId) {
        xSql = "select u.user_id from Manage_Course mc, \"User\" u\n"
                + "where course_id = ?\n"
                + "and mc.user_id = u.user_id\n"
                + "and u.role_id = 3";
        int publisher_id = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                publisher_id = rs.getInt("user_id");
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return publisher_id;

    }

    public int getCourseidFromQuiz(int quizId) {
        xSql = "select distinct mc.course_id from Course mc, quiz q, Section s\n"
                + "where q.section_id = s.section_id\n"
                + "and s.course_id = mc.course_id\n"
                + "and q.quiz_id = ?";
        int courseId = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, quizId);
            rs = ps.executeQuery();
            if (rs.next()) {
                courseId = rs.getInt("course_id");
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return courseId;
    }

    public int getCourseidFromLeson(int lesonId) {
        xSql = "select distinct l.*, mc.course_id from Course mc, Lesson l, Section s\n"
                + "where l.section_id = s.section_id\n"
                + "and s.course_id = mc.course_id\n"
                + "and l.lesson_id = ?";
        int courseId = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, lesonId);
            rs = ps.executeQuery();
            if (rs.next()) {
                courseId = rs.getInt("course_id");
            }
        } catch (Exception e) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return courseId;
    }

    public void DoneCourse(int userId, int courseId) {
        xSql = "update Manage_Course\n"
                + "set done = 1\n"
                + "where user_id = ?\n"
                + "and course_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public int CountPublishedCourse(){
        int count = 0;
        xSql = "select count(course_id) from Course";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if(rs.next()){
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    
    public static void main(String[] args) {
        CourseDAO pd = new CourseDAO();

//        System.out.println("Test addCourse");
//        int n = pd.addCourse("Test", "Test", 75000, "Test", Date.valueOf("2022-03-04"), 2, 1, true, 30, "test");
//        System.out.println(n);
        int a = pd.getCourseidFromQuiz(50);
        System.out.println(a);

//        System.out.println("Test getHottestCourse");
//        Vector<Course> cv = pd.getHottestCourse();
//        for (Course c : cv) {
//            System.out.println(c);
//
//        }
//        System.out.println("Test getByNameAndSubId(G, 1)");
//        Vector<Course> cv1 = pd.getByNameAndSubId("G", 1);
//        for (Course c : cv1) {
//            System.out.println(c);
//        }
//        System.out.println("Test getCourseBySubId");
//        Vector<Course> cv2 = pd.getCourseBySubId(2);
//        for (Course c : cv2) {
//            System.out.println(c);
//        }
//        System.out.println("Test searchNameSortByHottest");
//        Vector<Course> cv3 = pd.searchNameSortByHottest("G");
//        for (Course c : cv3) {
//            System.out.println(c);
//        }
//        System.out.println("Test searchSubIdSortByHottest");
//        Vector<Course> cv4 = pd.searchSubIdSortByHottest(1);
//        for (Course c : cv4) {
//            System.out.println(c);
//        }
//        System.out.println("Test searchByNameAndSubIdSortByHottest");
//        Vector<Course> cv5 = pd.searchByNameAndSubIdSortByHottest("N", 2);
//        for (Course c : cv5) {
//            System.out.println(c);
//        }
//
//        System.out.println("Test Get4HottestBySubId");
//        Vector<Course> cv6 = pd.Get4HottestBySubId(2);
//        for (Course c : cv6) {
//            System.out.println(c);
//        }
//        System.out.println("Test addCourseToUser");
//        if(pd.addCourseToUser(1, 3, Date.valueOf("2023-05-26"), Date.valueOf("2023-06-25"))){
//            System.out.println("Success");
//        }
//        else{
//            System.out.println("Fail");
//        }
    }

}
