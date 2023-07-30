/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Choice;
import entity.Lesson;
import entity.Question;
import entity.Quiz;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Uslaptop
 */
public class QuizDAO extends MyDAO {

    public Vector<Quiz> getAll() {
        Vector<Quiz> vector = new Vector<Quiz>();
        xSql = "select*from Quiz";
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int quiz_id = rs.getInt("quiz_id");
                String quiz_name = rs.getString("quiz_name");
                String quiz_desc = rs.getString("quiz_desc");
                int section_id = rs.getInt("section_id");
                boolean quiz_status = rs.getBoolean("quiz_status");
                // Them question list
                //Quiz quiz = new Quiz(quiz_id, quiz_name, quiz_desc, section_id, quiz_status);
                //vector.add(quiz);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }

    public Quiz getQuizById(int id) {
        Quiz quiz = null;
        xSql = "Select * from Quiz where quiz_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                int quiz_id = rs.getInt("quiz_id");
                String quiz_name = rs.getString("quiz_name");
                String quiz_desc = rs.getString("quiz_desc");
                int section_id = rs.getInt("section_id");
                boolean quiz_status = rs.getBoolean("quiz_status");
                quiz = new Quiz(quiz_id, quiz_name, quiz_desc, section_id, quiz_status);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return quiz;
    }

    public Vector<Choice> getChoicebyQuestionId(int search_ques_id) {
        Vector<Choice> vector = new Vector<Choice>();

        xSql = "select*from choices where ques_id= ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, search_ques_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int choice_id = rs.getInt("choice_id");
                String choice_content = rs.getString("choice_content");
                boolean is_true = rs.getBoolean("is_true");
                int ques_id = rs.getInt("ques_id");
                Choice ch = new Choice(choice_id, choice_content, is_true, ques_id);
                vector.add(ch);
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return vector;
    }

    public Vector<Question> getQuestionByQuizId(int search_ques_id) {
        Vector<Question> vector = new Vector<Question>();

        xSql = "select*from Question where quiz_id= ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, search_ques_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int ques_id = rs.getInt("ques_id");
                String ques_content = rs.getString("ques_content");
                String ques_note = rs.getString("ques_note");
                int quiz_id = rs.getInt("quiz_id");
                Question ques = new Question(ques_id, ques_content, ques_note, quiz_id);
                vector.add(ques);
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }
        return vector;
    }

    public Vector<String> getAllCorrectAnswer(int quiz_id) {
        Vector<String> correctAnswers = new Vector<>();
        xSql = "SELECT c.choice_content\n"
                + "FROM quiz q\n"
                + "JOIN question qu ON qu.quiz_id = q.quiz_id\n"
                + "JOIN choices c ON c.ques_id = qu.ques_id\n"
                + "WHERE q.quiz_id = ? AND c.is_true = 'TRUE'"
                + "order by qu.ques_id asc";

        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, quiz_id);
            rs = ps.executeQuery();
            while (rs.next()) {
                String correct_choice = rs.getString("choice_content");
                correctAnswers.add(correct_choice);
            }
        } catch (Exception e) {
            System.out.println("checkCourse: " + e.getMessage());
        }

        return correctAnswers;
    }

    //Son
    public Vector<Quiz> getQuizListBySectionId(int SectionId) {
        Vector<Quiz> vector = new Vector<Quiz>();
        xSql = "select q.* from Quiz q, Section S\n"
                + "where q.section_id = s.section_id\n"
                + "and s.section_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, SectionId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int quiz_id = rs.getInt("quiz_id");
                String quiz_name = rs.getString("quiz_name");
                String quiz_desc = rs.getString("quiz_desc");
                boolean quiz_status = rs.getBoolean("quiz_status");
                //Them question list
                Quiz quiz = new Quiz(quiz_id, quiz_name, quiz_desc, SectionId, quiz_status);
                vector.add(quiz);
            }
        } catch (SQLException ex) {
            Logger.getLogger(QuizDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vector;
    }
    //son

    public void SetQuizStatus(int UpdateStatus, int quizId) {
        xSql = "update Quiz set quiz_status = ? where quiz_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, UpdateStatus);
            ps.setInt(2, quizId);
            ps.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //son 
    public void UpdateChoices(int chocieId, String ChoiceContent, Boolean UpdatedIsTrue) {
        xSql = "update choices\n"
                + "set choice_content = ?, \n"
                + "is_true = ?\n"
                + "where choice_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ChoiceContent);
//            if (UpdatedIsTrue != null) {
//                xSql += "is_true = ?\n"
//                        + "where choice_id = ?";
//                ps.setBoolean(2, UpdatedIsTrue);
//                ps.setInt(3, chocieId);
//            } else {
//                xSql += " where choice_id = ?";
//                ps.setInt(2, chocieId);
//            }
            ps.setBoolean(2, UpdatedIsTrue);
            ps.setInt(3, chocieId);
            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void UpdateChoicesContentOnly(int chocieId, String ChoiceContent) {
        xSql = "update choices\n"
                + "set choice_content = ?\n"
                + "where choice_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, ChoiceContent);
            ps.setInt(2, chocieId);
            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //remove Choices by id
    public void RemoveChoices(int chocieId) {
        xSql = "delete from choices where choice_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, chocieId);
            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //insert a new default choice 
    public void insertChoice(int quesId, String choiceContent, boolean status) {
        xSql = "insert into choices (choice_content, is_true, ques_id) values (?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, choiceContent);
            ps.setBoolean(2, status);
            ps.setInt(3, quesId);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    //retrieve the max choice id(the choice that was added last to the database);
    public int retrieveMaxChoiceId() {
        xSql = "select MAX(choice_id) as maxid from choices";
        int maxChoiceId = 0;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxChoiceId = rs.getInt("maxid");
            }
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return maxChoiceId;

    }

    //update ten
    public void updateQuestionContent(int quesId, String updatedQuizContent) {
        xSql = "update Question \n"
                + "set ques_content = ?\n"
                + "where ques_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, updatedQuizContent);
            ps.setInt(2, quesId);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public int insertQuestion(int QuizId, String quesContent, String quesNote) {
        xSql = "insert into Question (ques_content, ques_note, quiz_id) output inserted.ques_id values (?, ?, ?)";
        int maxId = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, quesContent);
            ps.setString(2, quesNote);
            ps.setInt(3, QuizId);
            rs = ps.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("ques_id");
            }
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return maxId;
    }

    public void deleteQuestion(int quesId) {
        xSql = "delete from Question where ques_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, quesId);
            ps.executeQuery();
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }
//    public void deleteAllChoicesOfOneQuestion(int quesId){
//        xSql = "delete from choices where ques_id = ?";
//        try {
//            ps = con.prepareStatement(xSql);
//            ps.setInt(1, quesId);
//            ps.executeQuery();
//        } catch (Exception e) {
//            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
//        }
//    }

    public void QuizInitialization(int section_id) {
        xSql = "insert into Quiz (quiz_name,quiz_desc, section_id, quiz_status) \n"
                + "values \n"
                + "(N'Bài quiz mới được tạo ra, hãy chỉnh sửa và enable', '', ?, 0)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, section_id);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public void UpdateQuiz(int quizId, String quizName) {
        xSql = "update Quiz \n"
                + "set quiz_name = ?\n"
                + "where quiz_id = ?";
        try {
            ps = con.prepareStatement(xSql);
            ps.setString(1, quizName);
            ps.setInt(2, quizId);
            ps.executeUpdate();
        } catch (Exception e) {
            Logger.getLogger(LessonDAO.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public int quizDone(int courseId, int user_id) {
        xSql = "select count(distinct qr.quiz_id) as passed from Quiz_Result qr, Quiz q, Section s, Course c\n"
                + "where qr.user_id = ?\n"
                + "and qr.quiz_id = q.quiz_id\n"
                + "and q.section_id = s.section_id\n"
                + "and s.course_id = c.course_id\n"
                + "and c.course_id = ?\n"
                + "and q.quiz_status = 1\n"
                + "and qr.quiz_status = 1";
        int count = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, user_id);
            ps.setInt(2, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("passed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public int quizSum(int courseId) {
        xSql = "select count(q.quiz_id) as quizCount from Quiz q, Section s, Course c\n"
                + "where q.section_id = s.section_id \n"
                + "and s.course_id = c.course_id\n"
                + "and c.course_id = ?\n"
                + "and q.quiz_status = 1";
        int count = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("quizCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public static void main(String[] args) {
        QuizDAO pd = new QuizDAO();
//        System.out.println("Test getAllQuestionCorrectAnswer");
//        Vector<String> correctAnswers = pd.getAllCorrectAnswer(2);
//        for (String a : correctAnswers) {
//            System.out.println(a);
//        }
        System.out.println("Test getQuizById: ");
        Quiz quiz = pd.getQuizById(1);
        System.out.println(quiz.getQuiz_name());
    }
}
