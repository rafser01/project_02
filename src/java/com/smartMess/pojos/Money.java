package com.smartMess.pojos;
// Generated Apr 24, 2016 3:13:47 AM by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Money generated by hbm2java
 */
public class Money  implements java.io.Serializable {


     private Integer idAuto;
     private Member member;
     private Double deposit;
     private Double houseRent;
     private Double utilities;
     private Double serventBill;
     private Double foodCost;
     private Double maintenance;
     private Date date;
     private Double bazarCost;
     private String remark1;
     private String remark2;

    public Money() {
    }

	
    public Money(Member member, Date date) {
        this.member = member;
        this.date = date;
    }
    public Money(Member member, Double deposit, Double houseRent, Double utilities, Double serventBill, Double foodCost, Double maintenance, Date date, Double bazarCost, String remark1, String remark2) {
       this.member = member;
       this.deposit = deposit;
       this.houseRent = houseRent;
       this.utilities = utilities;
       this.serventBill = serventBill;
       this.foodCost = foodCost;
       this.maintenance = maintenance;
       this.date = date;
       this.bazarCost = bazarCost;
       this.remark1 = remark1;
       this.remark2 = remark2;
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
    public Double getDeposit() {
        return this.deposit;
    }
    
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }
    public Double getHouseRent() {
        return this.houseRent;
    }
    
    public void setHouseRent(Double houseRent) {
        this.houseRent = houseRent;
    }
    public Double getUtilities() {
        return this.utilities;
    }
    
    public void setUtilities(Double utilities) {
        this.utilities = utilities;
    }
    public Double getServentBill() {
        return this.serventBill;
    }
    
    public void setServentBill(Double serventBill) {
        this.serventBill = serventBill;
    }
    public Double getFoodCost() {
        return this.foodCost;
    }
    
    public void setFoodCost(Double foodCost) {
        this.foodCost = foodCost;
    }
    public Double getMaintenance() {
        return this.maintenance;
    }
    
    public void setMaintenance(Double maintenance) {
        this.maintenance = maintenance;
    }
    public Date getDate() {
        return this.date;
    }
    
    public void setDate(Date date) {
        this.date = date;
    }
    public Double getBazarCost() {
        return this.bazarCost;
    }
    
    public void setBazarCost(Double bazarCost) {
        this.bazarCost = bazarCost;
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

