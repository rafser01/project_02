package com.smartMess.pojos;
// Generated Apr 24, 2016 3:13:47 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Status generated by hbm2java
 */
public class Status  implements java.io.Serializable {


     private Integer idAuto;
     private Member member;
     private Date date;
     private Integer like;
     private Integer dislike;
     private String comments;
     private String remark1;
     private String remark2;
     private String statusText;

    public Status() {
    }

	
    public Status(Member member, Date date, String statusText) {
        this.member = member;
        this.date = date;
        this.statusText = statusText;
    }
    public Status(Member member, Date date, Integer like, Integer dislike, String comments, String remark1, String remark2, String statusText) {
       this.member = member;
       this.date = date;
       this.like = like;
       this.dislike = dislike;
       this.comments = comments;
       this.remark1 = remark1;
       this.remark2 = remark2;
       this.statusText = statusText;
    }
   
    public Integer getIdAuto() {
        return this.idAuto;
    }
    
    public void setIdAuto(Integer idAuto) {
        this.idAuto = idAuto;
    }
    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Integer getLike() {
        return this.like;
    }
    
    public void setLike(Integer like) {
        this.like = like;
    }
    public Integer getDislike() {
        return this.dislike;
    }
    
    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }
    public String getComments() {
        return this.comments;
    }
    
    public void setComments(String comments) {
        this.comments = comments;
    }
    public String getRemark1() {
        return this.remark1;
    }
    
    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }
    public String getRemark2() {
        return this.remark2;
    }
    
    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }
    public String getStatusText() {
        return this.statusText;
    }
    
    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }




}


