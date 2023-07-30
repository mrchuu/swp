/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Section;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class SectionDAO extends MyDAO {

    public Vector<Section> getSectionListByCourseId(int courseId) {
        xSql = "select * from Section where course_id = ?";
        Vector<Section> vector = new Vector<>();
        int secId;
        String section_name;
        int status;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            while (rs.next()) {
                secId = rs.getInt("section_id");
                section_name = rs.getString("section_name");
                status = rs.getInt("section_status");
                vector.add(new Section(secId, courseId, section_name, status));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vector;
    }
    public void AddNewSection(int CourseId){
        xSql = "Insert into Section (course_id, section_name, section_status) values (?, 'Please edit this Section', 1)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, CourseId);
            ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
