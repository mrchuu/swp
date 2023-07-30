/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.QuesResult;
import java.sql.SQLException;
import java.util.Vector;

/**
 *
 * @author Uslaptop
 */
public class QuesResultDAO extends MyDAO {

    public Boolean insertQuesResult(int ques_id, int user_id,
            boolean ques_status, boolean ques_flag, String ques_answer, int quiz_result_id) {

        xSql = "INSERT INTO Ques_Result(ques_id, user_id,\n"
                + "ques_status, ques_flag, ques_answer, quiz_result_id)\n"
                + "VALUES ( ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, ques_id);
            ps.setInt(2, user_id);
            ps.setBoolean(3, ques_status);
            ps.setBoolean(4, ques_flag);
            ps.setString(5, ques_answer);
            ps.setInt(6, quiz_result_id);
            
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    
    // Get all ques result by quiz_result_id
    public Vector<QuesResult> getQuesResultBy(int filter_quiz_result_id){
        Vector<QuesResult> resultList = new Vector<>();
        xSql = "select*from ques_result where quiz_result_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, filter_quiz_result_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ques_result_id = rs.getInt("ques_result_id");
                int ques_id = rs.getInt("ques_id");
                int user_id = rs.getInt("user_id");
                Boolean ques_status = rs.getBoolean("ques_status");
                Boolean ques_flag = rs.getBoolean("ques_flag");
                String ques_answer = rs.getString("ques_answer");
                int quiz_result_id = rs.getInt("quiz_result_id");
                QuesResult quesResult = new QuesResult(ques_result_id, ques_id, user_id, ques_answer, quiz_result_id, ques_status, ques_flag);
                resultList.add(quesResult);
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return resultList;
    }
    

    public static void main(String[] args) {
        QuesResultDAO pd = new QuesResultDAO();
        System.out.println("Test insertQuesResult");
        Boolean inserted = pd.insertQuesResult(1, 2, true,false, "123", 32);
        System.out.println(inserted);
        
        System.out.println("Test getQuesResultBy");
        Vector<QuesResult> quesResultList = pd.getQuesResultBy(1);
        for(QuesResult quesResult : quesResultList){
            System.out.println(quesResult);
        }
    }
}
