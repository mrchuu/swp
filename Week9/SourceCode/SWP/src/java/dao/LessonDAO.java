
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import dto.LessonDto;
import entity.Course;
import entity.Lesson;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uslaptop
 */
public class LessonDAO extends MyDAO {

    public Vector<Lesson> getAll() {
        Vector<Lesson> vector = new Vector<Lesson>();
        xSql = "select*from Lesson";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int lesson_id = rs.getInt("lesson_id");
                String lesson_name = rs.getString("lesson_name");
                String lesson_video = rs.getString("lesson_video");
                int section_id = rs.getInt("section_id");
                String lesson_desc = rs.getString("lesson_desc");
                boolean lesson_status = rs.getBoolean("lesson_status");
                Lesson les = new Lesson(lesson_id, lesson_name, lesson_video, section_id, lesson_desc, lesson_status);
                vector.add(les);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<Lesson> getAllOrbSecId() {
        Vector<Lesson> vector = new Vector<Lesson>();
        xSql = "SELECT * FROM Lesson WHERE section_id IS NOT NULL ORDER BY section_id";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int lesson_id = rs.getInt("lesson_id");
                String lesson_name = rs.getString("lesson_name");
                String lesson_video = rs.getString("lesson_video");
                int section_id = rs.getInt("section_id");
                String lesson_desc = rs.getString("lesson_desc");
                boolean lesson_status = rs.getBoolean("lesson_status");
                Lesson les = new Lesson(lesson_id, lesson_name, lesson_video, section_id, lesson_desc, lesson_status);
                vector.add(les);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Lesson getLessonDetails(int lId) {
        Lesson lesson = null;
        xSql = "select * from Lesson l where l.lesson_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, lId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int lesson_id = rs.getInt("lesson_id");
                String lesson_name = rs.getString("lesson_name");
                String lesson_video = rs.getString("lesson_video");
                int section_id = rs.getInt("section_id");
                String lesson_desc = rs.getString("lesson_desc");
                boolean lesson_status = rs.getBoolean("lesson_status");
                lesson = new Lesson(lesson_id, lesson_name, lesson_video, section_id, lesson_desc, lesson_status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lesson;
    }

    //son
    public Vector<Lesson> getLessonBySectionId(int SectionId) {
        xSql = "select l.* from Lesson l, Section s\n"
                + "where l.section_id = s.section_id\n"
                + "and s.section_id = ?";
        Vector<Lesson> vector = new Vector<>();
        int lessonId;
        String lessonName;
        String lessonVideo;
        String lessonDesc;
        boolean lesson_Status;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, SectionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                lessonId = rs.getInt("lesson_id");
                lessonName = rs.getString("lesson_name");
                lessonVideo = rs.getString("lesson_video");
                lessonDesc = rs.getString("lesson_desc");
                lesson_Status = rs.getBoolean("lesson_status");
                Lesson les = new Lesson(lessonId, lessonName, lessonVideo, SectionId, lessonDesc, lesson_Status);
                vector.add(les);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    //son
    public void SetLessonStatus(int UpdateStatus, int lessonId) {
        xSql = "update Lesson set lesson_status = ? where lesson_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, UpdateStatus);
            ps.setInt(2, lessonId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //son
    public void editLessonDetail(String lessonName, String lessonVideo, String lessonDesc, int lessonId) {
        xSql = "update Lesson\n"
                + "set lesson_name = ?, \n"
                + "lesson_video = ?,\n"
                + "lesson_desc = ?\n"
                + "where lesson_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, lessonName);
            ps.setString(2, lessonVideo);
            ps.setString(3, lessonDesc);
            ps.setInt(4, lessonId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //son
    public void AddnewLessonToSection(int Section_id, String lesson_Name, String lesson_video, String lesson_desc) {
        xSql = "insert into Lesson (lesson_name, lesson_video, section_id, lesson_desc, lesson_status) values (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, lesson_Name);
            ps.setString(2, lesson_video);
            ps.setInt(3, Section_id);
            ps.setString(4, lesson_desc);
            ps.setInt(5, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    //son
    public boolean checkLessonDone(int userId, int lessonId) {
        xSql = "select * from Lesson_Result\n"
                + "where user_id = ?\n"
                + "and lesson_id = ?";
        boolean done = false;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            rs = ps.executeQuery();
            if (rs.next()) {
                done = true;
            }
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return done;
    }

    public void markasDone(int userId, int lessonId) {
        xSql = "insert into Lesson_Result (user_id, lesson_id, lesson_status) values (?, ?, 1)";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setInt(2, lessonId);
            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void AddScore(int userId, int point) {
        xSql = "update \"User\"\n"
                + "set Score = Score + ?\n"
                + "where user_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, point);
            ps.setInt(2, userId);
            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public int LessonCount(int courseId) {
        xSql = "select count(l.lesson_id) as lessonCount from Lesson l, Section s, Course c\n"
                + "where l.section_id = s.section_id\n"
                + "and s.course_id = c.course_id\n"
                + "and c.course_id = ?\n"
                + "and l.lesson_status = 1";
        int a = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                a = rs.getInt("lessonCount");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;

    }

    public int LessonDone(int userId, int courseId) {
        xSql = "select COUNT(lr.lesson_id) as lessonDone from Lesson_Result lr, Lesson l, Section s, Course c\n"
                + "where lr.user_id = ?\n"
                + "and lr.lesson_id = l.lesson_id\n"
                + "and l.section_id = s.section_id\n"
                + "and s.course_id = c.course_id\n"
                + "and c.course_id = ?\n"
                + "group by lr.user_id";
        int a = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, userId);
            ps.setInt(2, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                a = rs.getInt("lessonDone");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;

    }
    public static void main(String[] args) {
        LessonDAO ld = new LessonDAO();
        System.out.println(ld.LessonDone(32, 3));
        System.out.println(ld.LessonCount(3));
    }
}
