package com.smartMess.pojos;
// Generated Apr 24, 2016 3:13:47 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Message generated by hbm2java
 */
public class Message  implements java.io.Serializable {


     private Integer messageId;
     private Member member;
     private String text;
     private String toField;
     private Date date;
     private String status;
     private String remarkMess1;
     private String remarkMess2;

    public Message() {
    }

	
    public Message(Member member, String text, String toField, Date date) {
        this.member = member;
        this.text = text;
        this.toField = toField;
        this.date = date;
    }
    public Message(Member member, String text, String toField, Date date, String status, String remarkMess1, String remarkMess2) {
       this.member = member;
       this.text = text;
       this.toField = toField;
       this.date = date;
       this.status = status;
       this.remarkMess1 = remarkMess1;
       this.remarkMess2 = remarkMess2;
    }
   
    public Integer getMessageId() {
        return this.messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    public String getText() {
        return this.text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    public String getToField() {
        return this.toField;
    }
    
    public void setToField(String toField) {
        this.toField = toField;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public String getRemarkMess1() {
        return this.remarkMess1;
    }
    
    public void setRemarkMess1(String remarkMess1) {
        this.remarkMess1 = remarkMess1;
    }
    public String getRemarkMess2() {
        return this.remarkMess2;
    }
    
    public void setRemarkMess2(String remarkMess2) {
        this.remarkMess2 = remarkMess2;
    }




}


