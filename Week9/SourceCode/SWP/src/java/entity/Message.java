/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author FPT
 */
public class Message {
    int MessageId;
    int SenderId;
    int ReceiverId;
    String content;
    boolean marked;
    boolean reported;
    User sender;
    User receiver;

    public Message(int MessageId, int SenderId, int ReceiverId, String content, boolean marked, boolean reported) {
        this.MessageId = MessageId;
        this.SenderId = SenderId;
        this.ReceiverId = ReceiverId;
        this.content = content;
        this.marked = marked;
        this.reported = reported;
    }
     
    

    public Message() {
    }

    public int getMessageId() {
        return MessageId;
    }

    public void setMessageId(int MessageId) {
        this.MessageId = MessageId;
    }

    public int getSenderId() {
        return SenderId;
    }

    public void setSenderId(int SenderId) {
        this.SenderId = SenderId;
    }

    public int getReceiverId() {
        return ReceiverId;
    }

    public void setReceiverId(int ReceiverId) {
        this.ReceiverId = ReceiverId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }
    
}
