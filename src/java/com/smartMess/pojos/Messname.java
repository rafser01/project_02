package com.smartMess.pojos;
// Generated Apr 24, 2016 3:13:47 AM by Hibernate Tools 4.3.1



/**
 * Messname generated by hbm2java
 */
public class Messname  implements java.io.Serializable {


     private int idAuto;
     private Member member;
     private String messName;
     private String area;
     private String location;
     private String remark1;
     private String remark2;

    public Messname() {
    }

	
    public Messname(int idAuto, Member member, String messName, String location) {
        this.idAuto = idAuto;
        this.member = member;
        this.messName = messName;
        this.location = location;
    }
    public Messname(int idAuto, Member member, String messName, String area, String location, String remark1, String remark2) {
       this.idAuto = idAuto;
       this.member = member;
       this.messName = messName;
       this.area = area;
       this.location = location;
       this.remark1 = remark1;
       this.remark2 = remark2;
    }
   
    public int getIdAuto() {
        return this.idAuto;
    }
    
    public void setIdAuto(int idAuto) {
        this.idAuto = idAuto;
    }
    public Member getMember() {
        return this.member;
    }
    
    public void setMember(Member member) {
        this.member = member;
    }
    public String getMessName() {
        return this.messName;
    }
    
    public void setMessName(String messName) {
        this.messName = messName;
    }
    public String getArea() {
        return this.area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
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




}


