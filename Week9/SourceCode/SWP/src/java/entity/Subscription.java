/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author FPT
 */
public class Subscription {
    int reg_id; 
    Date reg_time;
    int user_id;
    Price_Package currentPackage;
    Date expireDate;
    boolean Status;

    public Subscription(int reg_id, Date reg_time, int user_id, Price_Package currentPackage, Date expireDate, boolean Status) {
        this.reg_id = reg_id;
        this.reg_time = reg_time;
        this.user_id = user_id;
        this.currentPackage = currentPackage;
        this.expireDate = expireDate;
        this.Status = Status;
    }

    
    public Subscription() {
    }

    public int getReg_id() {
        return reg_id;
    }

    public void setReg_id(int reg_id) {
        this.reg_id = reg_id;
    }

    public Date getReg_time() {
        return reg_time;
    }

    public void setReg_time(Date reg_time) {
        this.reg_time = reg_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Price_Package getCurrentPackage() {
        return currentPackage;
    }

    public void setCurrentPackage(Price_Package currentPackage) {
        this.currentPackage = currentPackage;
    }

    

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }
    
}
