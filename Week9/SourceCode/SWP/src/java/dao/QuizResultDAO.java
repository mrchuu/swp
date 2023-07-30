/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.QuizResult;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uslaptop
 */
public class QuizResultDAO extends MyDAO {

    public int insertQuizResult(int quiz_id, int user_id, boolean quiz_status,
            float quiz_grade, Timestamp quiz_start, Timestamp quiz_end,
            int attempt) {
        int n = 0;
        xSql = "INSERT INTO Quiz_Result(quiz_id, user_id,\n"
                + "quiz_status, quiz_grade, quiz_start, quiz_end, attempt) OUTPUT INSERTED.quiz_result_id\n"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, quiz_id);
            ps.setInt(2, user_id);
            ps.setBoolean(3, quiz_status);
            ps.setFloat(4, quiz_grade);
            ps.setTimestamp(5, quiz_start);
            ps.setTimestamp(6, quiz_end);
            ps.setInt(7, attempt);
            rs = ps.executeQuery();
            if (rs.next()) {
                n = rs.getInt("quiz_result_id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return n;
    }

    public QuizResult getQuizResultByQuizResultId(int filter_quiz_result_id) {
        QuizResult quizResult = null;
        xSql = "select* from Quiz_Result where quiz_result_id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, filter_quiz_result_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int quiz_result_id = rs.getInt("quiz_result_id");
                int quiz_id = rs.getInt("quiz_id");
                int user_id = rs.getInt("user_id");
                boolean quiz_status = rs.getBoolean("quiz_status");
                float quiz_grade = rs.getFloat("quiz_grade");
                Timestamp quiz_start = rs.getTimestamp("quiz_start");
                Timestamp quiz_end = rs.getTimestamp("quiz_end");
                int attempt = rs.getInt("attempt");

                quizResult = new QuizResult(quiz_result_id, quiz_id, user_id, quiz_status, quiz_grade, quiz_start, quiz_end, attempt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quizResult;
    }

    public int getMaxAttempByUserIdAndQuizId(int user_id, int quiz_id) {
        int maxAttempt = 0;
        xSql = "SELECT COALESCE(MAX(attempt), 0) AS max_attempt\n"
                + "FROM quiz_result\n"
                + "WHERE user_id = ? AND quiz_id = ?;";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, quiz_id);
            ps.setInt(2, user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                maxAttempt = rs.getInt("max_attempt");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return maxAttempt;
    }

    public Vector<QuizResult> getQuizResultByUserIdAndQuizId(int filter_user_id, int filter_quiz_id) {
        Vector<QuizResult> vector = new Vector<QuizResult>();
        xSql = "select* from Quiz_Result where user_id=? and quiz_id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, filter_user_id);
            ps.setInt(2, filter_quiz_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int quiz_result_id = rs.getInt("quiz_result_id");
                int quiz_id = rs.getInt("quiz_id");
                int user_id = rs.getInt("user_id");
                boolean quiz_status = rs.getBoolean("quiz_status");
                float quiz_grade = rs.getFloat("quiz_grade");
                Timestamp quiz_start = rs.getTimestamp("quiz_start");
                Timestamp quiz_end = rs.getTimestamp("quiz_end");
                int attempt = rs.getInt("attempt");

                vector.add(new QuizResult(quiz_result_id, quiz_id, user_id, quiz_status, quiz_grade, quiz_start, quiz_end, attempt));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Vector<QuizResult> getQuizResultByUserId(int filter_user_id) {
        Vector<QuizResult> vector = new Vector<QuizResult>();
        xSql = "select* from Quiz_Result where user_id=?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, filter_user_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int quiz_result_id = rs.getInt("quiz_result_id");
                int quiz_id = rs.getInt("quiz_id");
                int user_id = rs.getInt("user_id");
                boolean quiz_status = rs.getBoolean("quiz_status");
                float quiz_grade = rs.getFloat("quiz_grade");
                Timestamp quiz_start = rs.getTimestamp("quiz_start");
                Timestamp quiz_end = rs.getTimestamp("quiz_end");
                int attempt = rs.getInt("attempt");

                vector.add(new QuizResult(quiz_result_id, quiz_id, user_id, quiz_status, quiz_grade, quiz_start, quiz_end, attempt));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CourseDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Map<String, Integer> getDashBoardDataPop(String sortType, int quizId) {
        Map<String, Integer> map = new LinkedHashMap<>();
        xSql = "SELECT scores.score AS qGrade, COUNT(subquery.qGrade) AS user_count\n"
                + "FROM (\n"
                + "  SELECT 1 AS score UNION ALL\n"
                + "  SELECT 2 AS score UNION ALL\n"
                + "  SELECT 3 AS score UNION ALL\n"
                + "  SELECT 4 AS score UNION ALL\n"
                + "  SELECT 5 AS score UNION ALL\n"
                + "  SELECT 6 AS score UNION ALL\n"
                + "  SELECT 7 AS score UNION ALL\n"
                + "  SELECT 8 AS score UNION ALL\n"
                + "  SELECT 9 AS score UNION ALL\n"
                + "  SELECT 10 AS score\n"
                + ") AS scores\n"
                + "LEFT JOIN (\n"
                + "  SELECT u.user_email, u.full_name, SUM(qr.quiz_grade) AS qGrade \n"
                + "  FROM Quiz_Result qr\n"
                + "  INNER JOIN [User] u ON qr.user_id = u.user_id\n"
                + "  WHERE qr.quiz_id = ?\n"
                + "  GROUP BY u.user_email, u.full_name\n"
                + ") AS subquery ON scores.score = subquery.qGrade\n"
                + "GROUP BY scores.score\n";
        if (sortType.equalsIgnoreCase("most")) {
            xSql += "ORDER BY scores.score DESC";
        } else {
            xSql += "ORDER BY scores.score ASC";
        }
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, quizId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int qGrade = rs.getInt("qGrade");
                int userCount = rs.getInt("user_count");
                map.put(String.valueOf(qGrade), userCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static void main(String[] args) {
        QuizResultDAO pd = new QuizResultDAO();
        System.out.println("Test insertQuizResult");
        int quizResultId = pd.insertQuizResult(2, 3, true, 5, Timestamp.valueOf("2023-06-09 09:32:53"), Timestamp.valueOf("2023-06-09 09:37:53"), 1);
        System.out.println(quizResultId);

//        System.out.println("Test getQuizResultByUserId");
//        Vector<QuizResult> cv = pd.getQuizResultByUserIdAndQuizId(1, 1);
//        for (QuizResult c : cv) {
//            System.out.println(c);
//
//        }
//
//        System.out.println("Test getMaxAttempByUserIdAndQuizId");
//        int maxAttempt = pd.getMaxAttempByUserIdAndQuizId(1, 1);
//        System.out.println(maxAttempt);
//          System.out.println("Test getQuizResultByQuizResultId");
//          QuizResult quizResult = pd.getQuizResultByQuizResultId(35);
//          System.out.println(quizResult.getQuiz_id());
    }
}
