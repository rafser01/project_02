/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.managedBean;

import com.smartMess.dao.DAO;
import com.smartMess.pojos.Meal;
import com.smartMess.pojos.Member;
import com.smartMess.pojos.Money;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author HP
 */
@ManagedBean
@RequestScoped
public class Transactions implements Serializable{
    boolean depositClicked=true;
    MealBean mealBean=new MealBean();
    DAO dao=new DAO();
    

    Member member=new Member();
    String user;
    Map<String, List<Money>> moneyListAllMembers=new HashMap<String, List<Money>>();
    List<Money> userMoneyList=new ArrayList<Money>();
    /**
     * Creates a new instance of Transactions
     */
    public Transactions() {
    }

    public MealBean getMealBean() {
        mealBean.setMember(getMember());
        return mealBean;
    }

    public void setMealBean(MealBean mealBean) {
        this.mealBean = mealBean;
    }

    public boolean isDepositClicked() {
        return depositClicked;
    }

    public void setDepositClicked(boolean depositClicked) {
        this.depositClicked = depositClicked;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
         
        this.user = user;
    }

    public Map<String, List<Money>> getMoneyListAllMembers() {
        return moneyListAllMembers;
    }

    public void setMoneyListAllMembers(Map<String, List<Money>> moneyListAllMembers) {
        this.moneyListAllMembers = moneyListAllMembers;
    }

    public List<Money> getUserMoneyList() {
        return userMoneyList;
    }

    public void setUserMoneyList(List<Money> userMoneyList) {
        this.userMoneyList = userMoneyList;
    }
    
    
    
    @PostConstruct
    public void init(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            Member mem = (Member) session.getAttribute("user");
            setMember(mem);
            setUser(mem.getUser());
            setMoneyListAllMembers(dao.getMoneyListofAllMembers(mem.getMessName()));
            setUserMoneyList(getMoneyListAllMembers().get(getUser()));
             
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
    
     
    
    public void setSubmenu(String user, String submenu){
        setUser(user);
        if(submenu.equals("deposit"))
            setDepositClicked(true);
        else if(submenu.equals("deduction"))
            setDepositClicked(false);
        setUserMoneyList(getMoneyListAllMembers().get(getUser()));
         
    }
    
    public void onRowEdit(RowEditEvent event) {
        Money money = (Money) event.getObject();
         
        
        dao.update(money);
}
    
    
    
}
