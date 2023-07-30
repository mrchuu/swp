/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Message;
import java.util.Vector;

/**
 *
 * @author FPT
 */
public class MessageDAO extends MyDAO {

    public void SendMessage(int sender, int receiver, String content) {
        xSql = "insert into contact(user1_id, user2_id, content, reported, marked) values (?, ?, ?, ?, ?)";
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, sender);
            ps.setInt(2, receiver);
            ps.setString(3, content);
            ps.setBoolean(4, false);
            ps.setBoolean(5, false);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Vector<Message> getAllMessage() {
        xSql = "select * from contact\n"
                + "order by contact_id desc";
        Vector<Message> messageList = new Vector<>();
        int a = 0;
        try {
            ps = con.prepareStatement(xSql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int contact_id = rs.getInt("contact_id");
                int senderId = rs.getInt("user1_id");
                int receiverId = rs.getInt("user2_id");
                String content = rs.getString("content");
                boolean reported = rs.getBoolean("reported");
                boolean marked = rs.getBoolean("marked");
                messageList.add(new Message(contact_id, senderId, receiverId, content, marked, reported));
                a++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messageList;
    }

    public Message getMessageById(int id) {
        xSql = "select * from contact\n"
                + "where contact_id = ?";
        Message m = new Message();
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int contact_id = rs.getInt("contact_id");
                int senderId = rs.getInt("user1_id");
                int receiverId = rs.getInt("user2_id");
                String content = rs.getString("content");
                boolean reported = rs.getBoolean("reported");
                boolean marked = rs.getBoolean("marked");
                m = new Message(contact_id, senderId, receiverId, content, marked, reported);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    public int ReportOrUnreport(int messageId, int changeTo) {
        xSql = "update contact\n"
                + "set reported = ?\n"
                + "where contact_id = ?";
        int a = 0;
        try {
            ps = con.prepareStatement(xSql);
            ps.setInt(1, changeTo);
            ps.setInt(2, messageId);
            a = ps.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return a;
    }

    public static void main(String[] args) {
        MessageDAO md = new MessageDAO();
        System.out.println(md.ReportOrUnreport(10, 1));
    }

}
