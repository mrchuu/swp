/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.TestFeature;

import controller.AES;
import dao.QuizDAO;
import dao.UserDAO;
import entity.Quiz;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;
import java.util.regex.Pattern;

/**
 *
 * @author FPT
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ImportQuestion extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Part filePart = null;
        int quiz_id = Integer.parseInt(request.getParameter("quiz_id"));
        String quiz_name = request.getParameter("quiz_name");
        filePart = request.getPart("QuizImport");
        String saveDirectory = request.getServletContext().getRealPath("") + "/QuizUpload/";
        String fileName = "";
        if (filePart != null && filePart.getSize() > 0) {
            fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString() + System.currentTimeMillis();
        } 
        String filePath = saveDirectory + fileName;
        InputStream fileContent = filePart.getInputStream();
        Files.copy(fileContent, Paths.get(filePath));
        File Quizinput = new File(filePath);
        BufferedReader br = new BufferedReader(new FileReader(Quizinput));
        String st;
        Vector<String> QuestionAndAns = new Vector<>();
        Vector<String> QuestionAndAnsSplitted = new Vector<>();
        String[] aa;
        Vector<String> QuestionOnly = new Vector<>();
        Vector<String> AnswerOnly = new Vector<>();
        Vector<String> trueOnly = new Vector<>();
        QuizDAO qd = new QuizDAO();
        int maxQuesId = 0;
        while ((st = br.readLine()) != null) // Print the string
        {
            QuestionAndAns.add(st);
        }
        for (String QuestionAndAn : QuestionAndAns) {
            aa = QuestionAndAn.split("#");
            for (int i = 0; i < aa.length; i++) {
//                out.print(aa[i]+"PPPPPPPPPPPPPPPPPPPP");
                if (aa[i].length() >= 13) {
                    if (aa[i].substring(2, 10).equals("Question")) {
//                        out.print(aa[i].substring(12) + "////////////");
                        QuestionOnly.add(aa[i].substring(12));
                        maxQuesId = qd.insertQuestion(quiz_id, aa[i].substring(12), "");
                    }
                }
                if (aa[i].substring(2, 5).equals("ans")) {
                    if (aa[i].length() >= 12) {
                        if (aa[i].substring(2, 9).equals("anstrue")) {
                            trueOnly.add(aa[i].substring(11));
                            qd.insertChoice(maxQuesId, aa[i].substring(11), true);

                        } else {
                            AnswerOnly.add(aa[i].substring(7));
                            qd.insertChoice(maxQuesId, aa[i].substring(7), false);
                        }

                    } else {
                        AnswerOnly.add(aa[i].substring(7));
                        qd.insertChoice(maxQuesId, aa[i].substring(7), false);
                    }
                }
            }

        }

        String encodedQuizName = URLEncoder.encode(quiz_name, "UTF-8");
        out.println(quiz_name);
        response.sendRedirect("EditQuizContent?quiz_id=" + quiz_id + "&quiz_name=" + encodedQuizName);
    }

}
