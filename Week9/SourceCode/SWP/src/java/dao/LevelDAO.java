/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Level;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Logger;

/**
 *
 * @author ACER
 */
public class LevelDAO extends MyDAO {

    public Vector<Level> getAll() {
        Vector<Level> levelList = new Vector<>();
        xSql = "select * from level";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while(rs.next()){
                int level_id = rs.getInt(1);
                String level_name = rs.getString(2);
                Level level = new Level(level_id, level_name);
                levelList.add(level);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        return levelList;
    }
    
}
