/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TestFeature;

import controller.LecturerValidator;
import dao.CourseDAO;
import dao.QuizDAO;
import dao.UserDAO;
import entity.Choice;
import entity.Question;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class EditQuizContent extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));

        String quiz_name = request.getParameter("quiz_name");
        QuizDAO qd = new QuizDAO();
        int user_id = 0;
        UserDAO ud = new UserDAO();
        CourseDAO cd = new CourseDAO();
        Cookie[] cookies = request.getCookies();
        int courseId = cd.getCourseidFromQuiz(quiz_id);
        LecturerValidator lv = new LecturerValidator();
        int val = lv.val1(cookies, quiz_id);
        if (val == 0) {
            response.sendRedirect("login");
        } else if (val == 1) {
            response.sendRedirect("UnauthorizedAccess.jsp");
        } else {
            Vector<Question> questionList = qd.getQuestionByQuizId(quiz_id);
            for (Question question : questionList) {
                Vector<Choice> cv = qd.getChoicebyQuestionId(question.getQues_id());
                question.setChoices(cv);
            }
            request.setAttribute("quiz_name", quiz_name);
            request.setAttribute("quiz_id", quiz_id);
            request.setAttribute("questionList", questionList);
            request.getRequestDispatcher("QuestionListEdit.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        QuizDAO qd = new QuizDAO();
        PrintWriter out = response.getWriter();
        String[] editedQuesContent = request.getParameterValues("quesContent");
        String quiz_name = request.getParameter("quiz_name");
        int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));
        String[] Choicedeletions = request.getParameter("deletionchoice").trim().split(",");
        String[] Questiondeletions = request.getParameter("deletionquestion").trim().split(",");
        Vector<Integer> deletedChoicesId = new Vector<>();
        Vector<Integer> deletedQuestionId = new Vector<>();
        qd.UpdateQuiz(quiz_id, quiz_name);
        for (int i = 0; i < Choicedeletions.length; i++) {
            if (Choicedeletions[i] == " " || Choicedeletions[i] == "") {
                continue;
            } else {
                deletedChoicesId.add(Integer.parseInt(Choicedeletions[i]));
            }
        }
        Vector<Choice> ChoicesIdByQuesIdforDelete = new Vector<>();
        for (int i = 0; i < Questiondeletions.length; i++) {
            if (Questiondeletions[i] == " " || Questiondeletions[i] == "") {
                continue;
            } else {
                deletedQuestionId.add(Integer.parseInt(Questiondeletions[i]));
                ChoicesIdByQuesIdforDelete = qd.getChoicebyQuestionId(Integer.parseInt(Questiondeletions[i]));
                for (Choice choice : ChoicesIdByQuesIdforDelete) {
                    deletedChoicesId.add(choice.getChoice_id());
                }
            }
        }

        Vector<Question> questionList = qd.getQuestionByQuizId(quiz_id);
        Vector<Integer> rightChoicesId = new Vector<>();
        int choiceId = 0;
        for (Question question : questionList) {
            if (request.getParameter("rightChoiceFor" + question.getQues_id()) != null) {
                choiceId = Integer.parseInt(request.getParameter("rightChoiceFor" + question.getQues_id()));
                rightChoicesId.add(choiceId);
            }

        }
        Vector<Integer> ChoicesIdByQuesId = new Vector<>();
        Vector<Choice> ChoicesByQuesId = new Vector<>();
        for (int i = 0; i < questionList.size(); i++) {
            ChoicesByQuesId = (qd.getChoicebyQuestionId(questionList.get(i).getQues_id()));
            qd.updateQuestionContent(questionList.get(i).getQues_id(), editedQuesContent[i]);
//            out.print("//"+question.getQues_id());
            for (Choice choice : ChoicesByQuesId) {
                ChoicesIdByQuesId.add(choice.getChoice_id());
//                out.print("  "+choice.getChoice_id());
            }

        }
        String[] editedChocies = request.getParameterValues("EditedChoiceContent");
        if (editedChocies != null) {
            for (int i = 0; i < editedChocies.length; i++) {
                for (Integer rci : rightChoicesId) {
//                out.print("(" + ChoicesIdByQuesId.get(i) + "--" + rci + ")");
                    if (ChoicesIdByQuesId.get(i).equals(rci)) {
                        qd.UpdateChoices(ChoicesIdByQuesId.get(i), editedChocies[i], true);
                        break;
                    } else {
                        qd.UpdateChoices(ChoicesIdByQuesId.get(i), editedChocies[i], false);
                    }

                }

                for (Integer deletedChoiceId : deletedChoicesId) {
                    if (ChoicesIdByQuesId.get(i).equals(deletedChoiceId)) {
                        qd.RemoveChoices(ChoicesIdByQuesId.get(i));
                    }
                }

            }
            for (Question question : questionList) {
                for (int i = 0; i < deletedQuestionId.size(); i++) {
                    if (deletedQuestionId.get(i).equals(question.getQues_id())) {
                        qd.deleteQuestion(deletedQuestionId.get(i));
                    }
                }
            }
        }
        String encodedQuizName = URLEncoder.encode(quiz_name, "UTF-8");
        out.println(quiz_name);
        response.sendRedirect("EditQuizContent?quiz_id=" + quiz_id + "&quiz_name=" + encodedQuizName);

    }

}
